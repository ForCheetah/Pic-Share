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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/persionalPage/css/demo.css">
    <link href="${pageContext.request.contextPath}/persionalPage/css/alpha.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/image/indexImage/row.ico" rel="shortcut icon">



    <style type="text/css">
        #exampleContainer{
            width: 600px;
            height: 400px;
            background: url(${pageContext.request.contextPath}/<s:property value="picture.purl"/>);
            background-size: cover;
            z-index: 999;
            margin: 20px auto;
            margin-right:120px;
            margin-top:100px;
        }
        .center{text-align: center;}
    </style>

</head>
<body style="background:#1A1C1E">








      <%@include file="/jsp/header.jsp"%>


<div id="page" class="container">





    <div id="header">
		<div id="logo">
			<img src="${pageContext.request.contextPath}/<s:property value="owner.uimg"/>" alt="" style="width: 80px;height: 80px;" />
			<br/>图片主人：<h1 style="color: #bce8f1">${owner.uname}</h1>
			<span>已经来到此处&nbsp;&nbsp;<s:property value="#request.day"/>&nbsp;&nbsp; 天了</span>
		</div>
		<div id="menu" style='padding-left:68px;'>
			<div class="button-effect">
				<h2>items</h2>
				<a class="effect effect-5" href="${pageContext.request.contextPath}/<s:property value="picture.purl"/>" title="Learn More" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'>图 片 下 载</a>
				<a class="effect effect-5" href="${pageContext.request.contextPath}/picture_updatePicPage?pictureId=<s:property value="picture.pid"/>" title="添加图片的故事" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'><span id="pictureUpload">添 加 故 事</span></a>
                <a class="effect effect-5" href="#" title="Learn More" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'>编 辑 资 料</a>
				<a class="effect effect-5" href="#" title="Learn More" style='padding-left:30px;margin-top: 10px;margin-bottom: 10px;'>m o r e</a>
			</div>
		</div>
	</div>


    <!--漂浮的图片特效-->

        <div id="exampleContainer" style="left: -91px; top: 17px; border-width: 5px; border-style: solid; margin-bottom: 0px; margin-top: 50px; margin-right: 60px;"></div>



    <script src="${pageContext.request.contextPath}/persionalPage/js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/persionalPage/js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/persionalPage/js/jquery.ui.swingdrag.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $("#exampleContainer").swingdrag({
            rotationAngleDeg: 8,
            showShadow: true,
            pickUpScaleFactor: 1.1
        });
    </script>


    <!--文字的样式-->
    <div class="htmleaf-container">

        <section id="testimonials">


            <div class="testimonials-wrapper" >

                <ul class="testimonials-line">

                    <li class="customer" style="display: inline-block;	margin-left: 10px;">

                        <div class="testimonial-bubble" style="width: 430px;">
                            <p style="color: white;">
                                <s:if test="#request.picture.pexpress!=null">
                                    <s:property value="picture.pexpress"/>
                                </s:if>
                                <s:else>
                                    该图片的主人还未添加背后的故事，不要着急哟。
                                </s:else>

                            </p>
                        </div>



                    </li>
                </ul>
            </div>
        </section>
    </div>

    <script src="${pageContext.request.contextPath}/persionalPage/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/persionalPage/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/persionalPage/js/alpha.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#testimonials').alpha();
        })
    </script>
</div>



</body>
</html>
