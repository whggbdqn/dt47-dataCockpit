<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<script>
    var verifyCode = new GVerify("v_container");
    document.getElementById("code_input").onblur = function(){
      var res = verifyCode.validate(document.getElementById("code_input").value);
      if(!res){
        alert("验证码错误");
      }
    }
  
</script>


<!-- 验证密码是否正确 -->
<script type="text/javascript">
 $("input[name=oldPassword]").blur(function(){
	var  password=$("input[name=oldPassword]").val();
    var  flag1=(password=="");
	if(flag1){
		 $("#title").html("原密码不能为空");
	}else{
   $.ajax({
		 url:"./password.shtml",  //请求ajax
		 type:"post",
		 dataType:"json",
		 data:{
			"password":password, 
		 },
	     success:function(result){
	    	 var flag = result;
	    	 if(flag){
	    		 $("#submit").removeAttr("disabled");// 按钮可用
	    		 $("#title").html("");
	    	 }else{
	    		 $("#submit").attr({"disabled":"disabled"})//按钮置为灰
	    		 $("#title").html("原密码错误，请重新输入");
	    	 }
	     }
	   });//$.ajax
	   }
	});
</script>
		
		
		

<script type="text/javascript">
	$("input[name=password1]").blur(function(){
		var pass=$("input[name=password]").val();
		var pass1=$("input[name=password1]").val();
		if(pass!=pass1){
			$("#submit").attr({"disabled":"disabled"})//按钮置为灰
			$("#error").html("*两次密码输入不一致！");
		}else{
			 $("#submit").removeAttr("disabled");// 按钮可用
			$("#error").html("");
		}
	});
</script>