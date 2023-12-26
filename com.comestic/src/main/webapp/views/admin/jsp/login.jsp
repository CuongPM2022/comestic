<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<body>
	<div id="contain">
		<div class="grid wide">
			<div class="login">
				<div class="row center">
					<div class="col p-4 t-6 m-12">
						<div class="body">
							<form method="post" action="" id="form">
								<div class="header">Đăng nhập</div>
								<div class="formGroupt">
									<div class="box__input">
										<input type="text" name="username" placeholder="Tên đăng nhập" rules="require">
									</div>
									<span class="form-message">Trường này là bắt buộc!</span>
								</div>

								<div class="formGroupt">
									<div class="box__input">
										<input type="password" name="password" placeholder="Mật khẩu"
											   autocomplete="on" rules="require">
									</div>
									<span class="form-message">Trường này là bắt buộc!</span>
								</div>

								<div type="submit" class="btn_LogIn form-submit">Đăng nhập</div>

								<div class="footer">
									<a href="#">Quên mật khẩu?</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>