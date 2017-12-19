package web.Common;

import org.springframework.web.context.support.WebApplicationContextUtils;
import web.Common.proto.Srv;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GPSListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        String part = event.getServletContext().getInitParameter("socketPort");
        System.out.println(part);
        Srv srv = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()).getBean(Srv.class);
        srv.start();
        event.getServletContext().log("++++ Socket服务已经启动完毕 ++++");
    }

    public void contextDestroyed(ServletContextEvent event) {

    }

}
