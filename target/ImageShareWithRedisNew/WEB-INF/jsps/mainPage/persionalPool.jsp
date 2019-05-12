<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Pic&Story</title>
<script src="${pageContext.request.contextPath}/pool/js/jquery-1.6.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/pool/js/lyz.delayLoading.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexCss/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexCss/menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pool/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexCss/zerogrid.css">
    <link href="${pageContext.request.contextPath}/image/indexImage/row.ico" rel="shortcut icon">

<script type='text/javascript'>

$(function () {
	$("img").delayLoading({
		defaultImg: "images/loading.jpg",           // 预加载前显示的图片
		errorImg: "",                        // 读取图片错误时替换图片(默认：与defaultImg一样)
		imgSrcAttr: "originalSrc",           // 记录图片路径的属性(默认：originalSrc，页面img的src属性也要替换为originalSrc)
		beforehand: 0,                       // 预先提前多少像素加载图片(默认：0)
		event: "scroll",                     // 触发加载图片事件(默认：scroll)
		duration: "normal",                  // 三种预定淡出(入)速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认:"normal"
		container: window,                   // 对象加载的位置容器(默认：window)
		success: function (imgObj) { },      // 加载图片成功后的回调函数(默认：不执行任何操作)
		error: function (imgObj) { }         // 加载图片失败后的回调函数(默认：不执行任何操作)
	});
});
</script>
<style type="text/css">
.box{ width:1280px; margin:0 auto}
.box ul li { width:400px; height:280px; display: inline-block; margin:10px; border:1px solid #e6e6e6; list-style:none; }
.box ul li img{ width:100%; height:100%;}
</style>
</head>
<body style="background:#1A1C1E" >
    <%--<h1><s:property value="picturePageBean.totalPage"/> </h1>--%>

    <div class="header">
        <%@include file="/jsp/header.jsp"%>
        <%--<div class="custom-banner">--%>
            <%--<img src="${pageContext.request.contextPath}/image/indexImage/main.jpg">--%>
        <%--</div>--%>
    </div>

    <!--背景，看看好用不-->
    <div class="custom-ban">
        <img src="${pageContext.request.contextPath}/image/indexImage/night.jpg">
    </div>

    <div class="box">
        <ul>
            <s:iterator value="picturePageBean.list" var="p">
                <li>
                    <a  href="${pageContext.request.contextPath}/picture_pictureHome?pictureId=<s:property value="#p.pid"/>">
                        <img src="${pageContext.request.contextPath}/<s:property value="#p.purl"/>" style="width: 100%;height: 100%"/>
                    </a>
                </li>
            </s:iterator>
        </ul>
    </div>


    <!--先用S标签将总页数读出来保存到PAge域中，供EL标签读取，因为使用C标签死活读不出来-->
    <%--<s:set name="total" value="picturePageBean.totalPage" scope="page"/>--%>

    <div class="custom-ban">
        <img src="${pageContext.request.contextPath}/image/indexImage/face.jpg">
    </div>
    <div class="custom-ban">
        <img src="${pageContext.request.contextPath}/image/indexImage/bar.jpg">
    </div>

    <div class="wrapper">
        <div class="archive-pages">
            <ul>
                <!--设置上一页-->
                <c:choose>
                    <c:when test="${requestScope.page==1}">
                        <c:set var="last" value="1"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="last" value="${requestScope.page-1}"/>
                    </c:otherwise>
                </c:choose>
                <li class="first"><a href="${pageContext.request.contextPath}/picture_showPool?page=1" title="first page">首页</a></li>
                <li class="previous"><a href="${pageContext.request.contextPath}/picture_showPool?page=${pageScope.last}" title="previous page">上一页</a></li>

                <!--设置点击的页码-->
                <c:choose>
                    <%--当总页数小于5页的时候--%>
                    <c:when test="${requestScope.total<5}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${requestScope.total}"/>
                    </c:when>
                    <c:otherwise>
                        <%--当页数大于5页的时候，采用公式计算--%>
                        <c:set var="begin" value="${requestScope.page-2}"/>
                        <c:set var="end" value="${requestScope.page+2}"/>
                        <%--当尾溢出的时候--%>
                        <c:if test="${end>requestScope.total}">
                            <c:set var="begin" value="${pageScope.total-4}"/>
                            <c:set var="end" value="${pageScope.total}"/>
                        </c:if>
                        <%--当最小页溢出的时候--%>
                        <c:if test="${begin<1}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:if>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="i" begin="${pageScope.begin}" end="${pageScope.end}">
                    <c:choose>
                        <c:when test="${i eq requestScope.page}">
                            <li class="selected">${i}</li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/picture_showPool?page=${i}" title="Pagina 2">${i}</a></li>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>

                <!--设计下一页-->
                <c:choose>
                    <c:when test="${requestScope.page==requestScope.total}">
                        <c:set var="next" value="${requestScope.total}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="next" value="${requestScope.page+1}"/>
                    </c:otherwise>
                </c:choose>

                <li class="next"><a href="${pageContext.request.contextPath}/picture_showPool?page=${pageScope.next}" title="next page">下一页</a></li>
                <li class="last"><a href="${pageContext.request.contextPath}/picture_showPool?page=${requestScope.total}" title="last page">尾页</a></li>
            </ul>
        </div>
        <!--End-->
    </div>



    <div class="custom-ban">
        <img src="${pageContext.request.contextPath}/image/indexImage/bar.jpg">
    </div>


        <%@include file="/jsp/footer.jsp"%>

</body>
</html>
