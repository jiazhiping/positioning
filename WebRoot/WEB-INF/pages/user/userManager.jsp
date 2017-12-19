<%--
    User: Myk   Date: 2017/10/31    Time: 15:26
--%>
<%--@elvariable id="userForm" type="web.user.form.UserForm"--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html manifest="cache.manifest">
<head>
    <base href="<%=basePath%>">
    <title>${currentTitle}</title>
    <s:set value="currOpt" name="opt"/>
    <script type="text/javascript" src="js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="js/user.js"></script>
</head>

<body>
<div class="formBody">
    <form action="" method="post" name="uForm" id="uForm">
        <div class="formTitle">
            <span>
                ${currentTitle}
            </span>
        </div>
        <div>
            <ul class="formInfo">
                <li>
                    <label> 用户名 <b>*</b></label>
                    <s:textfield id="uLoginNameId" cssClass="dfInput" placeholder="请输入用户名"
                                 maxlength="12" name="userForm.userLoginName"/>
                </li>
                <li>
                    <label> 用户类型 <b>*</b></label>
                    <s:textfield id="uTypeId" cssClass="dfInput" value="管理员" disabled="true"/>
                </li>
                <s:if test="%{#opt == 'add'}">
                    <li>
                        <label> 用户密码 <b>*</b></label>
                        <s:password id="uLoginPasswordId" cssClass="dfInput" placeholder="请输入用户密码"
                                    maxlength="12" name="userForm.userLoginPassword"/>
                    </li>
                    <li>
                        <label> 确认密码 <b>*</b></label>
                        <s:password id="uLoginPasswordIds" cssClass="dfInput" placeholder="请确认密码"
                                    maxlength="12"/>
                    </li>
                </s:if>
                <s:else>
                    <li>
                        <label> 新密码 </label>
                        <s:password id="upLoginPasswordId" cssClass="dfInput" placeholder="请输入新密码"

                                    maxlength="12" name="userForm.userLoginPassword"/>
                    </li>
                    <li>
                        <label> 确认新密码 </label>
                        <s:password id="upLoginPasswordIds" cssClass="dfInput" placeholder="请确认新密码"

                                    maxlength="12"/>
                    </li>
                </s:else>
                <li style="height: 30px;"></li>
                <s:if test="%{#opt == 'update'}">
                    <li>
                        <label> 头像 </label>
                        <img src="${userForm.userIcon}" style="width: 188px;"/>
                    </li>
                </s:if>
                <li>
                    <label> 上传头像 </label>
                    <s:file id="fileId" name="userForm.myFile" cssClass="dfInput"/>
                <li>
                <li>
                    <label> 真实姓名 </label>
                    <s:textfield id="uNameId" cssClass="dfInput" placeholder="请输入用户真实姓名" maxlength="24"
                                 name="userForm.userName"/>
                </li>
                <li>
                    <label> 电话 </label>
                    <s:textfield id="uPhoneId" cssClass="dfInput" placeholder="请输入用户电话" maxlength="24"
                                 name="userForm.userPhone"/>
                </li>
                <li>
                    <label> 邮箱 </label>
                    <s:textfield id="uEmailId" cssClass="dfInput" placeholder="请输入用户邮箱" maxlength="24"
                                 name="userForm.userEmail"/>
                </li>
                <li>
                    <label> 部门 </label>
                    <s:textfield id="uDepartmentId" cssClass="dfInput" placeholder="请输入用户部门" maxlength="12"
                                 name="userForm.userDepartment"/>
                </li>
                <li>
                    <label> &nbsp; </label>
                    <s:if test="%{#opt == 'add'}">
                        <input type="button" class="scBtn" value="确认添加" onclick="addClick()"
                               style="margin: 10px 0 0 150px;"/>
                    </s:if>
                    <s:else>
                        <input type="button" class="scBtn" value="确认修改" onclick="updateClick()"
                               style="margin: 10px 0 0 150px;"/>
                    </s:else>
                    <input type="button" class="scBtn" value="返回" onclick="queryClick()"
                           style="margin: 10px 0 0 100px;"/>
                </li>
            </ul>
        </div>
        <s:hidden name="userForm.userId"/>
        <s:hidden name="curPage"/>
    </form>
</div>
</body>
</html>



