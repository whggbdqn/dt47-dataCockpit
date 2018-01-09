<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="cn.bdqn.datacockpit.entity.Companyinfo" isELIgnored="false"%>
<section class="content container-fluid"
	style="background: url(resource/images/pic5.jpg); width: 100%; height: 100%">
	<div class="register-box">
		<div class="register-logo">
			<a href="#" style="margin-left: 50px"><b>用户资料修改</b></a>
		</div>
		<div class="register-box-body" style="width: 390px">

			<form action="./aduser_update2.shtml" method="post">
				<div class="form-group has-feedback">
					<!-- 设置不显示 -->
					<input type="hidden" name="id" class="form-control"
						style="width: 270px; margin-left: 85px;" value="${comp.id }">
					<input type="hidden" name="state" class="form-control"
						style="width: 270px; margin-left: 85px;" value="${comp.state }">
					<input type="hidden" name="approval" class="form-control"
						style="width: 270px; margin-left: 85px;" value="${comp.approval }">
					<input type="hidden" name="corpname" class="form-control"
						style="width: 270px; margin-left: 85px;" value="${comp.corpname }">
					<input type="hidden" name="password" class="form-control"
						style="width: 270px; margin-left: 85px;" value="${comp.password }">
					<sapn style="display: block;margin-bottom: -30px">企业名称： </sapn>
					<input type="text" class="form-control" disabled="disabled"
						style="width: 270px; margin-left: 85px;" value="${comp.corpname }">
					<span style="display: block; margin-top: -30px; margin-left: 320px">
						<img src="resource/images/ios7-world.png"
						style="width: 27px; height: 25px">
					</span>
				</div>
				<div class="form-group has-feedback" style="margin-top: 30px">

					<sapn style="display: block;margin-bottom: -30px">联系人姓名： </sapn>
					<input type="text" class="form-control"
						pattern="^[\u4e00-\u9fa5]+$" required="required" title="请输入正确的姓名"
						style="width: 270px; margin-left: 85px" name="name"
						value="${comp.name }"> <span
						style="display: block; margin-top: -30px; margin-left: 320px">
						<img src="resource/images/ios7-contact-outline.png"
						style="width: 27px; height: 25px">
					</span>
				</div>

				<div class="form-group has-feedback" style="margin-top: 30px">

					<sapn style="display: block;margin-bottom: -30px">联系人职务： </sapn>
					<input type="text" class="form-control"
						pattern="^[\u4e00-\u9fa5]+$" required="required"
						title="请输入正确的联系人职务" style="width: 270px; margin-left: 85px"
						name="job" value="${comp.job }"> <span
						style="display: block; margin-top: -30px; margin-left: 320px">
						<img src="resource/images/social-wordpress.png"
						style="width: 27px; height: 25px">
					</span>
				</div>

				<div class="form-group has-feedback" style="margin-top: 30px">

					<sapn style="display: block;margin-bottom: -30px">手机号码： </sapn>
					<input type="text" class="form-control" pattern="1[34578]\d{9}"
						required="required" title="请输入正确的手机号码"
						style="width: 270px; margin-left: 85px" name="phone"
						value="${comp.phone }"> <span
						style="display: block; margin-top: -30px; margin-left: 320px">
						<img src="resource/images/iphone.png"
						style="width: 27px; height: 25px">
					</span>
				</div>
				<div class="form-group has-feedback" style="margin-top: 30px">
					<sapn style="display: block;margin-bottom: -30px">邮箱地址： </sapn>
					<input type="email" class="form-control"
						style="width: 270px; margin-left: 85px" name="email"
						value="${comp.email }"> <span
						style="display: block; margin-top: -30px; margin-left: 320px">
						<img src="resource/images/ios7-email.png"
						style="width: 27px; height: 25px">
					</span>
				</div>
				<div class="row" style="margin-top: 20px">
					<!-- /.col -->
					<div class="col-xs-4" style="margin-top: 10px; width: 380px">
						<button type="submit" class="btn btn-primary btn-block btn-flat">立即修改</button>
					</div>
					<!-- /.col -->
				</div>
			</form>


		</div>
		<!-- /.form-box -->
	</div>

</section>