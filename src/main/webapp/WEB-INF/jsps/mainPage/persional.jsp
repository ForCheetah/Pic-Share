<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Pic&Story</title>
<link href="${pageContext.request.contextPath}/persionalPage/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/persionalPage/fonts.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/persionalPage/css/normalize.css" />
<link href="${pageContext.request.contextPath}/persionalPage/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/persionalPage/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexCss/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexCss/menu.css">
    <link href="${pageContext.request.contextPath}/image/indexImage/row.ico" rel="shortcut icon">


    <script type="text/javascript" src="${pageContext.request.contextPath}/persionalPage/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/persionalPage/diyUpload/css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/persionalPage/diyUpload/css/diyUpload.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/persionalPage/diyUpload/js/webuploader.html5only.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/persionalPage/diyUpload/js/diyUpload.js"></script>

    <script type="text/javascript">
        function realize() {
            persional=false;
            var display=$('#box').css('display');
            if(display=='none'){
                $('#box').show();
                $('#pictureUpload').text('结 束 上 传');
            }else{
                $('#box').hide();
                $('#pictureUpload').text('分 享 上 传');
            }

        }
        function persionUP() {
            persional=true;
            var display=$('#box').css('display');
            if(display=='none'){
                $('#box').show();
                $('#persionalUpload').text('结 束 上 传');
            }else{
                $('#box').hide();
                $('#persionalUpload').text('私 密 上 传');
            }

        }
    </script>

    <style type="text/css">
        #box{ margin:50px auto; width:540px; min-height:400px; background:#FF9;}
    </style>



</head>
<body style="background:#1A1C1E">








      <%@include file="/jsp/header.jsp"%>


<div id="page" class="container">

    <!--文件上传-->
    <div id="box" style="position:absolute;z-index:9999;margin-left: 30%;margin-top: 300px;display: none">
        <div id="test" ></div>
    </div>
    <script type="text/javascript">
        var userId='8A715EEB75094DB29B06ED642063B626';
        var persional=true;
    </script>

    <script type="text/javascript">

        /*
        * 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
        * 其他参数同WebUploader
        */

        $('#test').diyUpload({
            url:'${pageContext.request.contextPath}/picture_savePicture',
            success:function( data ) {
                console.info( data );

            },
            error:function( err ) {
                console.info( err );
            }
        });

    </script>


    <div id="header">
		<div id="logo">
			<img src="${pageContext.request.contextPath}/<s:property value="user.uimg"/>" alt="" style="width: 80px;height: 80px;" />
			<h1 style="color: #bce8f1">${user.uname}</h1>
			<span>已经来到此处&nbsp;&nbsp;<s:property value="#request.day"/>&nbsp;&nbsp; 天了</span>
		</div>
		<div id="menu" style='padding-left:68px;'>
			<div class="button-effect">
				<h2>items</h2>
				<a class="effect effect-5" href="${pageContext.request.contextPath}/picture_showPersionalPage?uid=8A715EEB75094DB29B06ED642063B626&page=1" title="查看自己的图片" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'>我 的 图 片</a>
				<a class="effect effect-5" href="javascript:realize();" title="上传分享图片" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'><span id="pictureUpload">分 享 上 传</span></a>
				<a class="effect effect-5" href="javascript:persionUP();" title="上传不公开图片" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'><span id="persionalUpload">私 密 上 传</span></a>
                <a class="effect effect-5" href="${pageContext.request.contextPath}/user_updateUser?uid=8A715EEB75094DB29B06ED642063B626" title="修改您的资料" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'>编 辑 资 料</a>
				<a class="effect effect-5" href="#" title="Learn More" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'>m o r e</a>
			</div>
		</div>
	</div>
	<div id="main">
		<div id="banner">
			<img src="${pageContext.request.contextPath}/<s:property value="user.uphoto"/>" class="image-full" />
		</div>
		<div id="welcome">
			<div class="title">
				<span class="byline">
                  <s:if test="#request.user.motto==null">
                      还没有添加自己的个性签名呢！！！
                  </s:if>
                <s:else>
                    <s:property value="#request.user.motto"/>
                </s:else>
                </span>
			</div>
            <p>
                <s:if test="#request.user.uexpress==null">
                    还没有添加自己的描述呢！！！！添加请点击“编辑资料”
                </s:if>
                <s:else>
                    &nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#request.user.uexpress"/>
                </s:else>
            </p>
		</div>

		<div id="copyright">
			<span>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a></span>
			<span>Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</span>
		</div>
	</div>
</div>





</body>
</html>
