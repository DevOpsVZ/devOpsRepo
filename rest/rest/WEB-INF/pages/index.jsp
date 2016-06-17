<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Login Form</title>
        <link rel="stylesheet" href="css/style.css">
  </head>

  <body>

    <div class="login-page" >
  <div class="form">
    <form class="register-form">
      <input type="text" placeholder="name"/>
      <input type="password" placeholder="password"/>
      <input type="text" placeholder="email address"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
    <form class="login-form" method="post" action="login.html">
		<span>${error_msg}</span>
      <input type="text" placeholder="username" name="username" id="username" />
      <input type="password" placeholder="password" name="password" id="password" />
      <input type="submit" id="loginBtn" value="Login" />
      <p class="message">Not registered? <a href="#">Create an account</a></p>
    </form>
  </div>
</div>
    <script src="js/jquery.min.js"></script>
	<script src="js/angular.min.js"></script>
    <script src="js/index.js"></script>
	
	<script>
	/*var login = angular.module("login", []);
	login.controller("loginCtrl", function($scope){
		$scope.login = function(){
			var username = $("#username").val();
			var password = $("#password").val();
			
			if(username == '' || username.length < 6){
				alert("Username must be atleast 6 char long");
				return false;
			}
			if(password == '' || password.length < 6){
				alert("Password must be atleast 6 char long");
				return false;
			}
			
			$.ajax({
				url : '/api/user/login',
				method : 'POST',
				contentType : "application/json",
				data : {'username' : username.
					'password' : password
				},
				success : function(response){
					console.log(response);
				},
				error : function(error){
					alert(error);
				}
			});
		}
	});*/
	
	</script>
	
  </body>
</html>
