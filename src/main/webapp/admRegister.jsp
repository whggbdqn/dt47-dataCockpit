<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
    String context = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ context + "/";
%>
<!DOCTYPE html>
<html style="height: 500px;">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="<%=basePath%>/resource/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=basePath%>/resource/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="<%=basePath%>/resource/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=basePath%>/resource/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="<%=basePath%>/resource/css/blue.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<style type="text/css">
input {
	width: 200px;
	height: 34px;
}

#fjd {
	width: 200px;
	height: 25px;
	border-color: red
}
</style>
</head>
<!-- pattern="[1]([3]|[4][5]|[7]|[8])\d{9}" -->
<body
	style="background: url(resource/images/login_bg.jpg); height: 500px;">

	<div class="login-box">
		<div class="login-logo">
			<a href="#" style="color: white; font-family: Microsoft YaHei;"><b>注&nbsp;册</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body"
			style="background-color: rgba(54, 167, 129, .4); height: 570px;">
			<form   id="myform" action="<%=basePath%>/insertAdminReg.shtml" method="post">
				<div class="form-group has-feedback">
					<input id="tel" type="text" name="phone" placeholder="&emsp;请输入手机号"
						 required="required"
						style="width: 320px;"> <span
						style="display: block; margin-top: -26px; margin-left: 291px"><img
						src="<%=basePath%>/resource/images/iphone.png"
						style="width: 27px; height: 25px"></span>
				</div>
				<div id="fjd">
					<span id="fjd-1">&nbsp;&nbsp;&nbsp;</span>
				</div>
				<div class="form-group has-feedback">
					<input id="comp" type="text" name="corpname" placeholder="&emsp;请输入企业名称"
						required="required" style="width: 320px;"> <span
						style="display: block; margin-top: -26px; margin-left: 291px"><img
						src="<%=basePath%>/resource/images/comp.png"
						style="width: 27px; height: 25px"></span>
				</div>
				<div id="fjd">
					<span id="fjd-comp"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="username" type="text" name="name" placeholder="&emsp;请输入姓名"
						required="required" style="width: 320px;"> <span
						style="display: block; margin-top: -26px; margin-left: 291px"><img
						src="<%=basePath%>/resource/images/ios7-contact-outline.png"
						style="width: 27px; height: 25px"></span>
				</div>
				<div id="fjd">
					<span id="fjd-3"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="profession" type="text" name="job" placeholder="&emsp;请输入职业"
						required="required" style="width: 320px;"> <span
						style="display: block; margin-top: -26px; margin-left: 291px"><img
						src="<%=basePath%>/resource/images/social-wordpress.png"
						style="width: 27px; height: 25px"></span>
				</div>
				<div id="fjd">
					<span id="fjd-profession"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="pcode" type="email" name="email" placeholder="&emsp;请输入email"
						required="required" style="width: 320px;"> <span
						style="display: block; margin-top: -26px; margin-left: 291px"><img
						src="<%=basePath%>/resource/images/ios7-email.png"
						style="width: 27px; height: 25px"></span>
				</div>
				<div id="fjd">
					<span id="fjd-5"></span>
				</div>
				<div class="form-group has-feedback">
					<input id="password" type="password" class="form-control" placeholder="请输入密码"
						required="required" name="password"> <span
						style="display: block; margin-top: -30px; margin-left: 293px"><img
						src="<%=basePath%>/resource/images/locked.png"
						style="width: 20px; height: 25px"></span>
				</div>
				<div id="fjd">
					<span id="fjd-pwd"></span>
				</div>
				<!-- <span id="mess"
					style="color: red; display: block; text-align: right;"></span> -->
				<div class="form-group has-feedback">
					<input id="password1" type="password" class="form-control" placeholder="请输入确认密码"
						required="required" name="password1"> <span
						style="display: block; margin-top: -30px; margin-left: 293px"><img
						src="<%=basePath%>/resource/images/checkmark.png"
						style="width: 20px; height: 25px"></span>
				</div>
				<div>
					<span style="color: red" id="erroMessage"></span>
				</div>
				<div class="col-xs-4" style="width: 320px; margin-top: 1px">
					<button type="submit" class="btn btn-primary btn-block btn-flat"
						style="font-size: 16px;">立即添加</button>
				</div>

				<!-- /.col -->
				<%--         <div class="col-xs-4" style="font-size: 14px;margin-top: 7px;margin-left:250px;">
            <a href="<%=basePath %>/pages/register.jsp" class="text-center" style="color: #FFFFFF">申请合作</a>
        </div> --%>

				<%--   <div class="col-xs-4" style="font-size: 14px;margin-top: 7px;margin-left:105px">
           <a href="<%=basePath %>/pages/wangjimima.jsp" class="text-center" style="color: #FFFFFF">忘记密码</a>
        </div> --%>

				<!-- /.col -->
		</div>
		</form>


		<!-- /.social-auth-links -->




	</div>
	<!-- /.login-box-body -->
	</div>


	<!-- /.login-box -->

	<!-- jQuery 3 -->
	<script src="<%=basePath%>/resource/js/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="<%=basePath%>/resource/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="<%=basePath%>/resource/js/icheck.min.js"></script>
	<script src="<%=basePath%>/resource/js/gVerify.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		//绑定失去焦点事件
	   $("#profession").blur(checkProfession);
		$("#comp").blur(checkComp);
		$("#username").blur(checkUsername);
		$("#tel").blur(checkTel);
		$("#pcode").blur(checkPcode);
		$("#password").blur(checkPassword);

		//提交表单,调用验证函数
		$("#myform").submit(function() {
			var flag = true;
			 if(!checkProfession()) flag = false;
			if(!checkComp()) flag = false;
			if(!checkUsername()) flag = false;
			//if(!checkTel()) flag = false; alert("手机号"+flag);
			if(!checkPcode()) flag = false;
			if(!checkPassword()) flag = false; 
			return flag;
		});
	})
	
	
	
	
	
	function checkProfession(){ 
		var username = /^[\u4e00-\u9fa5]+$/;
        console.log($("#profession").val());
        if(!username.test($("#profession").val())){
         $("#fjd-profession").text("*请输入纯中文!").css("color","yellow");
         return false;
        }else{
         $("#fjd-profession").text("");
         return true;
      }
		}
	
	function checkComp(){
		var username = /^[\u4e00-\u9fa5]+$/;
        console.log($("#comp").val());
        if(!username.test($("#comp").val())){
         $("#fjd-comp").text("*请输入纯中文!").css("color","yellow");
         return false;
        }else{
         $("#fjd-comp").text("");
         return true;
      }
	}
    function checkUsername(){
    	 var username = /^[\u4e00-\u9fa5]+$/;
         console.log($("#username").val());
         if(!username.test($("#username").val())){
          $("#fjd-3").text("*请输入纯中文!").css("color","yellow");
          return false;
         }else{
          $("#fjd-3").text("");
          return true;
       }
	}
	   function checkPcode(){
    	 var username =/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/ ;
         console.log($("#pcode").val());
         if(!username.test($("#pcode").val())){
          $("#fjd-5").text("*请输入正确的邮箱!").css("color","yellow");
          return false;
         }else{
          $("#fjd-5").text("");
          return true;
       }
	}
	
	 function checkTel(){
		var flag = false;
		var pattren = /^1[3|4|5|8][0-9]\d{8}$/;
		if (!pattren.test($("input[name=phone]").val())) {
			$("#fjd-1").text(" *请输入正确的手机号码!").css("color","yellow");
			 return flag;
		} else{
			var $phone=$("#tel").val();
			$.ajax({
				 url:"<%=basePath%>checkPhone.shtml",
				 type:"get",
				 data:{
					 phone:$phone
				 },
				 dataType:"json",
				 success:function(result){
					 if(result>=1){
						 $("#fjd-1").text("*此手机号已被使用").css("color","yellow");
						 return flag;
					 }else{
						 $("#fjd-1").text("");
						 flag=true;
						 return flag;
						 alert("手机号可使用"+flag);
 				 }
					 return flag;
					  }
			 }); 
		}
		
		}
		
		 	function checkPassword() {
		 		var $pwd = $("#password");
		 		if($pwd.val().length < 6) {
		 			$("#fjd-pwd").text("*密码长度应大于等于6个字符!").css("color","yellow");
		 			 return false;
				}else{
				var password = $("#password").val();
				var password1 = $("#password1").val();
				if (password != password1) {
					 $("#fjd-pwd").text("*两次输入的密码不一致！").css("color","yellow");
					 return false;
				} else {
					 $("#fjd-pwd").text("");
					 return true;
				}
				}
		
	} 
	
	</script>

	<script>
		$("#v_container").find("img").click(function() {
			var $img = $(this);
			$(this).attr("src", "");
			$.post("getYzm.shtml", function(data) {
				$img.attr("src", "getYzm.shtml")
			});
		})
	</script>
</body>
</html>
