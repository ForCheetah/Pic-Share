<%--
  Created by IntelliJ IDEA.
  User: 王化森
  Date: 2019/3/6
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id='cssmenu' >
    <ul>
        <li class="active"><a href='${pageContext.request.contextPath}/user_goToIndex'><span>Pic&Story</span></a></li>
        <!--<li class=' has-sub'><a href='#'><span>Category</span></a>-->
            <!--<ul>-->
                <!--<li class='has-sub'><a href='#'><span>Item 1</span></a>-->
                    <!--<ul>-->
                        <!--<li><a href='#'><span>Sub Item</span></a></li>-->
                        <!--<li class='last'><a href='#'><span>Sub Item</span></a></li>-->
                    <!--</ul>-->
                <!--</li>-->
                <!--<li class='has-sub'><a href='#'><span>Item 2</span></a>-->
                    <!--<ul>-->
                        <!--<li><a href='#'><span>Sub Item</span></a></li>-->
                        <!--<li class='last'><a href='#'><span>Sub Item</span></a></li>-->
                    <!--</ul>-->
                <!--</li>-->
            <!--</ul>-->
        <!--</li>-->
        <li><a href='${pageContext.request.contextPath}/picture_showPool?page=1'><span>随意浏览</span></a></li>
        <li><a href='${pageContext.request.contextPath}/picture_showFlow?page=1'><span>找寻一个故事</span></a></li>
        <li><a href='${pageContext.request.contextPath}/user_contactUs'><span>联系我们</span></a></li>

        <%--<li class='last' style="float:right">--%>
                    <%--<s:if test="#session.existUser==null">--%>
                        <%--<a href='${pageContext.request.contextPath}/fileForLogin/login.jsp'>--%>
                            <%--<span>--%>
                                <%--登录/注册--%>
                            <%--</span>--%>
                        <%--</a>--%>
                    <%--</s:if>--%>
                    <%--<s:else>--%>
                        <%--<a href='${pageContext.request.contextPath}/user_findPersionalPage?userId=<s:property value="#session.existUser.uid"/>'>--%>
                            <%--<span><s:property value="#session.currentUser.uname"/>--%>
                            <%--</span>--%>
                        <%--</a>--%>
                    <%--</s:else>--%>

        <%--</li>--%>



        <s:if test="#session.existUser==null">
            <li class='last' style="float:right"><a href='${pageContext.request.contextPath}/fileForLogin/login.jsp'><span>登录/注册</span></a></li>
        </s:if>
        <s:else>
            <li class='last' style="float:right"><a href='${pageContext.request.contextPath}/user_findPersionalPage?userId=<s:property value="#session.existUser.uid"/>'><span>欢迎： &nbsp;<s:property value="#session.existUser.uname"/></span></a></li>
        </s:else>
    </ul>
</div>
