<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--[if IE 6]><html lang="zh" class="no-js old ie6"><![endif]-->
<!--[if IE 7]><html lang="zh" class="no-js old ie7"><![endif]-->
<!--[if IE 8]><html lang="zh" class="no-js old ie8"><![endif]-->
<!--[if IE 9]><html lang="zh" class="no-js modern ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="zh" class="no-js modern"><!--<![endif]-->
<head>
<meta charset="utf-8" />
<title>Pic&Story</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/updateUser/include/css/style.css" />
    <link href="${pageContext.request.contextPath}/updateUser/include/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/updateUser/include/css/help-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/updateUser/include/css/foxholder-styles.css" />


</head>
<body>
<div id="page-wrap">

</div>

<div class="htmleaf-container">
    <div class="container">

        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1">
                <div class="form-container-10">
                    <h3 class="text-center">修改信息</h3>
                    <p class="text-muted text-center">提交您的新信息</p>
                    <form action="${pageContext.request.contextPath}/user_updateData" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="updateuid" value="<s:property value='#session.existUser.uid'/>"/>
                        <input id="first-input-10" type="text" name="updatename" placeholder="修改昵称" value="<s:property value='#session.existUser.uname'/>"/>
                        <input id="img" name="upload" type="file" placeholder="上传头像"  required/>
                        <input id="photo" name="uploadphoto" type="file" placeholder="上传照片" required />
                        <input id="second-input-10" name="updatemotto" type="text" placeholder="修改个性签名" value="<s:property value='#session.existUser.motto'/>" />
                        <textarea id="msg-10" name="updateexpress" placeholder="自我描述" value="<s:property value='#session.existUser.uexpress'/>"> </textarea>
                        <button type="submit">提交</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/updateUser/include/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/updateUser/include/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/updateUser/include/js/prism.js"></script>
<script src="${pageContext.request.contextPath}/updateUser/include/js/foxholder.js"></script>
<script src="${pageContext.request.contextPath}/updateUser/include/js/script.js"></script>

	
</body>
</html>