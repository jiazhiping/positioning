package web.Common;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义拦截器类（判断用户是否登录）
 * Created by Myk on 2017/10/22.
 */
public class CheckLoginInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation in) throws Exception {
        // 判断用户是否登录
        ActionContext act = ActionContext.getContext();
        String currUserId = (String) act.getSession().get("currUserId");
        HttpServletRequest request = ServletActionContext.getRequest();
        String path = request.getServletPath();
        //★★★多文件上传不判断登录★★★
        if (path.indexOf("/course_uploadFile_") >= 0 || path.indexOf("/kind_uploadFile.htmls") >= 0) {
            return in.invoke();// 继续下一个拦截器请求
        }
        if (currUserId == null) {
            currUserId = request.getParameter("currUserId");
            if (currUserId != null) {
                act.getSession().put("currUserId", currUserId);
                return in.invoke();// 继续下一个拦截器请求
            }
            // 判断是Ajax请求还是Http请求
            if (request.getHeader("X-Requested-With") != null
                    && request.getHeader("X-Requested-With").equalsIgnoreCase(
                    "XMLHttpRequest")) {
                // 此处为Ajax请求
                HttpServletResponse response = ServletActionContext
                        .getResponse();
                PrintWriter pw = response.getWriter();
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html");
                pw.print("noLoginAjax");
                pw.flush();
                pw.close();
                return null;
            } else {
                // 此处为Http请求
                return "noLogin";// 未登录
            }
        }
        return in.invoke();// 继续下一个拦截器请求
    }

}
