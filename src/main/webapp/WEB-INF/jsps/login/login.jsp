<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>登录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/fileForLogin/css/normalize.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fileForLogin/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fileForLogin/css/sign-up-login.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fileForLogin/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fileForLogin/css/inputEffect.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/fileForLogin/css/tooltips.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/fileForLogin/css/spop.min.css" />

    <link href="${pageContext.request.contextPath}/image/indexImage/favicon.ico" rel="shortcut icon">

<script src="${pageContext.request.contextPath}/fileForLogin/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/fileForLogin/js/snow.js"></script>
<script src="${pageContext.request.contextPath}/fileForLogin/js/jquery.pure.tooltips.js"></script>
<script src="${pageContext.request.contextPath}/fileForLogin/js/spop.min.js"></script>
<script>	
	(function() {
		// trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
		if (!String.prototype.trim) {
			(function() {
				// Make sure we trim BOM and NBSP
				var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
				String.prototype.trim = function() {
					return this.replace(rtrim, '');
				};
			})();
		}

		[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
			// in case the input is already filled..
			if( inputEl.value.trim() !== '' ) {
				classie.add( inputEl.parentNode, 'input--filled' );
			}

			// events:
			inputEl.addEventListener( 'focus', onInputFocus );
			inputEl.addEventListener( 'blur', onInputBlur );
		} );

		function onInputFocus( ev ) {
			classie.add( ev.target.parentNode, 'input--filled' );
		}

		function onInputBlur( ev ) {
			if( ev.target.value.trim() === '' ) {
				classie.remove( ev.target.parentNode, 'input--filled' );
			}
		}
	})();
	
	$(function() {	
		$('#login #login-password').focus(function() {
			$('.login-owl').addClass('password');
		}).blur(function() {
			$('.login-owl').removeClass('password');
		});
		$('#login #register-password').focus(function() {
			$('.register-owl').addClass('password');
		}).blur(function() {
			$('.register-owl').removeClass('password');
		});
		$('#login #register-repassword').focus(function() {
			$('.register-owl').addClass('password');
		}).blur(function() {
			$('.register-owl').removeClass('password');
		});
		$('#login #forget-password').focus(function() {
			$('.forget-owl').addClass('password');
		}).blur(function() {
			$('.forget-owl').removeClass('password');
		});
	});
	
	function goto_register(){
		$("#register-username").val("");
		$("#register-password").val("");
		$("#register-repassword").val("");
		$("#register-code").val("");
		$("#tab-2").prop("checked",true);
	}
	
	function goto_login(){
		$("#login-username").val("");
		$("#login-password").val("");
		$("#tab-1").prop("checked",true);
	}


	function login(){//登录
		var username = $("#login-username").val(),
			password = $("#login-password").val(),
			validatecode = null,
			flag = false;
		//判断用户名密码是否为空
		if(username == ""||username==null){
			$.pt({
        		target: $("#login-username"),
        		position: 'r',
        		align: 't',
        		width: 'auto',
        		height: 'auto',
        		content:"用户名不能为空"
        	});
			flag = true;
		}
		if(password == ""||password==null){
			$.pt({
        		target: $("#login-password"),
        		position: 'r',
        		align: 't',
        		width: 'auto',
        		height: 'auto',
        		content:"密码不能为空"
        	});
			flag = true;
		}
		//用户名长度
        if(username.length>30){
            $.pt({
                target: $("#login-username"),
                position: 'r',
                align: 't',
                width: 'auto',
                height: 'auto',
                content:"昵称长度不能大于30个字符"
            });
            flag = true;
        }

		
		if(flag){
			return false;
		}else{//登录
			return true;
		}
	}
	
	//注册
	function register(){
		var username = $("#register-username").val(),
			password = $("#register-password").val(),
			repassword = $("#register-repassword").val(),
			code = $("#register-code").val(),
			flag = false,
			validatecode = null;
		//判断用户名密码是否为空
		if(username == ""){
			$.pt({
        		target: $("#register-username"),
        		position: 'r',
        		align: 't',
        		width: 'auto',
        		height: 'auto',
        		content:"昵称不能为空"
        	});
			flag = true;
		}
		if(password == ""){
			$.pt({
        		target: $("#register-password"),
        		position: 'r',
        		align: 't',
        		width: 'auto',
        		height: 'auto',
        		content:"密码不能为空"
        	});
			flag = true;
		}else{
			if(password != repassword){
				$.pt({
	        		target: $("#register-repassword"),
	        		position: 'r',
	        		align: 't',
	        		width: 'auto',
	        		height: 'auto',
	        		content:"两次输入的密码不一致"
	        	});
				flag = true;
			}
		}
		//密码在30字节以内
		var regExp = new RegExp("[a-zA-Z0-9_]{1,30}$");
		if(!regExp.test(password)){
			$.pt({
        		target: $("#register-password"),
        		position: 'r',
        		align: 't',
        		width: 'auto',
        		height: 'auto',
        		content:"密码要在30个字节以内"
        	});
			flag = true;
		}
        //用户名长度
        if(username.length>30){
            $.pt({
                target: $("#login-username"),
                position: 'r',
                align: 't',
                width: 'auto',
                height: 'auto',
                content:"昵称长度不能大于30个字符"
            });
            flag = true;
        }
		//检查用户名是否已经存在
		//调后台代码检查用户名是否已经被注册
		
		//检查注册码是否正确
		//调后台方法检查注册码，这里写死为11111111

		
		
		if(flag){
			return false;
		}else{
            spop({
                template: '<h4 class="spop-title">注册成功</h4>即将于3秒后返回登录',
                position: 'top-center',
                style: 'success',
                autoclose: 3000,
                onOpen : function(){
                    var second = 2;
                    var showPop = setInterval(function(){
                        if(second == 0){
                            clearInterval(showPop);
                        }
                        $('.spop-body').html('<h4 class="spop-title">注册成功</h4>即将于'+second+'秒后返回登录');
                        second--;
                    },1000);
                },
                onClose : function(){
                    goto_login();
                }
            });

		    return true;

		}
	}

	
	
	
	
	
	
</script>
<style type="text/css">
html{width: 100%; height: 100%;}

body{

	background-repeat: no-repeat;

	background-position: center;

	background-color: #00BDDC;

	background-image: url(${pageContext.request.contextPath}/fileForLogin/images/snow.jpg);

	background-size: 100% 100%;

}


</style>
</head>
<body>

	<!-- 登录控件 -->
	<div id="login">
		<input id="tab-1" type="radio" name="tab" class="sign-in hidden" checked />
		<input id="tab-2" type="radio" name="tab" class="sign-up hidden" />
        <input id="tab-3" type="radio" name="tab" class="sign-out hidden" />
		<div class="wrapper">
			<!-- 登录页面 -->
			<div class="login sign-in-htm">
				<form class="container offset1 loginform" method="post" action="${pageContext.request.contextPath}/user_tologin">
					<!-- 猫头鹰控件 -->
					<div id="owl-login" class="login-owl">
						<div class="hand"></div>
						<div class="hand hand-r"></div>
						<div class="arms">
							<div class="arm"></div>
							<div class="arm arm-r"></div>
						</div>
					</div>
					<div class="pad input-container">
						<section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="login-username" name="uname"
									autocomplete="off" placeholder="你的昵称" tabindex="1" maxlength="30" />
								<label class="input__label input__label--hideo" for="login-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="login-password" name="upassword"
                                       placeholder="还有密码呀" tabindex="2" maxlength="15"/>
								<label class="input__label input__label--hideo" for="login-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
						</section>
					</div>
					<div class="form-actions">
						<a tabindex="5" class="btn btn-link text-muted" onClick="goto_register()">注册</a>
						<input class="btn btn-primary" type="submit" tabindex="3" onClick="return login()" value="登录"
							style="color:white;"/>
					</div>
                    <s:fielderror fieldName="nameDontExist"/>
                    <s:fielderror fieldName="passwordError"/>
                    <s:actionmessage/>
				</form>
			</div>

			<!-- 注册页面 -->
			<div class="login sign-up-htm">
				<form action="${pageContext.request.contextPath}/user_saveUser" method="post" class="container offset1 loginform">
					<!-- 猫头鹰控件 -->
					<div id="owl-login" class="register-owl">
						<div class="hand"></div>
						<div class="hand hand-r"></div>
						<div class="arms">
							<div class="arm"></div>
							<div class="arm arm-r"></div>
						</div>
					</div>
					<div class="pad input-container">
						<section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="register-username" name="uname"
									autocomplete="off" placeholder="取一个昵称吧" maxlength="30"/>
								<label class="input__label input__label--hideo" for="register-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="register-password" name="upassword" placeholder="请输入密码" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="register-repassword" placeholder="请确认密码" maxlength="15"/>
								<label class="input__label input__label--hideo" for="register-repassword">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="haha" name="verifyCode"
									autocomplete="off" placeholder="验证码" maxlength="10"/>
								<label class="input__label input__label--hideo" for="register-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>

							</span>
                            <img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/verifyCode" title="点击更换验证码" onclick="_change()">
							
						</section>
					</div>
					<div class="form-actions">
						<a class="btn pull-left btn-link text-muted" onClick="goto_login()">返回登录</a>
						<input class="btn btn-primary" type="submit" onClick="return register()"  value="注册"
							style="color:white;"/>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div style="text-align:center;">
</div>
</body>
</html>