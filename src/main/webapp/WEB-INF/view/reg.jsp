<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My Business card</title>
<!-- Description, Keywords and Author -->
<meta name="description" content="Your description">
<meta name="keywords" content="Your,Keywords">
<meta name="author" content="ResponsiveWebInc">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Styles -->
<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Font awesome CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">

<!-- Favicon -->
<link rel="shortcut icon" href="#">
</head>

<body>

	<div class="wrapper">

		<!-- header -->
		<header>
			<!-- navigation -->
			<nav class="navbar navbar-default" role="navigation">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"> My Business Card </a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="registration.html">Signup</a></li>
							<li><a href="login.html">Login</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Menu <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#event">Events</a></li>
									<li><a href="#blog">New Blogs</a></li>
									<li><a href="#subscribe">Subscribe</a></li>
									<li><a href="#team">Executive Team</a></li>
									<li><a href="#">One more separated link</a></li>
								</ul></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</header>

		<!-- banner -->
		<div class="banner">
			<div class="container">
				<!-- heading -->
				<h2>명함 작성</h2>
				<!-- paragraph -->
				<p>아래 내용을 채워주세요</p>
			</div>
		</div>
		<!-- banner end -->


		<!-- team -->
		<div class="team" id="team">
			<div class="container">
				<form method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="mb-3">
							<label for="title" class="form-label">명함에 한마디</label> <input
								type="text" class="form-control" id="title"
								aria-describedby="title">
						</div>
					</div>
					<div class="row">
						<div class="col">
							<label for="userName" class="form-label">이름(닉네임)</label> <input
								type="text" class="form-control" id="userName"
								aria-describedby="userName">
						</div>
						<div class="col">
							<label for="age" class="form-label">나이</label> <input
								type="number" class="form-control" id="age"
								aria-describedby="age">
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div id="aa" class="form-text"></div>
							<label for="phone" class="form-label">핸드폰</label> <input
								type="phone" class="form-control" id="phone"
								aria-describedby="phone">
						</div>
						<div class="col">
							<div id="aa" class="form-text"></div>
							<label for="position" class="form-label">직업포지션</label> <input
								type="text" class="form-control" id="position"
								aria-describedby="position">
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div id="aa" class="form-text"></div>
							<label for="file" class="form-label">첨부파일</label> <input
								type="file" class="form-control" id="file" name="file"
								aria-describedby="file">
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div id="aa" class="form-text"></div>
							<label for="url" class="form-label">포트폴리오 url</label> <input
								type="text" class="form-control" id="url"
								aria-describedby="url">
						</div>
					</div>
					<div class="row" style="padding:10px;">
						<div class="form-check form-switch col">
							<input class="form-check-input" type="checkbox" role="switch"
								id="pub" checked> <label class="form-check-label"
								for="pub">등록된 명함을 공개</label>
						</div>
						<div class="form-check form-switch col">
							<input class="form-check-input" type="checkbox" role="switch"
								id="jobState" checked> <label class="form-check-label"
								for="jobState">현재 재직중여부</label>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">등록</button>
				</form>
			</div>
		</div>
		<!-- team end -->

		<!-- footer -->
		<footer>
			<div class="container">
				<p>
					<a href="#">Home</a> | <a href="#">works</a> | <a href="#team">Team</a>
					| <a href="#contact">Contact</a>
				</p>
				<div class="social">
					<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
						class="fa fa-twitter"></i></a> <a href="#"><i
						class="fa fa-dribbble"></i></a> <a href="#"><i
						class="fa fa-linkedin"></i></a> <a href="#"><i
						class="fa fa-google-plus"></i></a>
				</div>
				<!-- copy right -->
				<!-- This theme comes under Creative Commons Attribution 4.0 Unported. So don't remove below link back -->
				<p class="copy-right">
					Copyright &copy; 2014 <a href="#">Your Site</a> | Designed By : <a
						href="http://www.indioweb.in/portfolio">IndioWeb</a>, All rights
					reserved.
				</p>
			</div>
		</footer>

	</div>


	<!-- Javascript files -->
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<!-- Bootstrap JS -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Respond JS for IE8 -->
	<script src="js/respond.min.js"></script>
	<!-- HTML5 Support for IE -->
	<script src="js/html5shiv.js"></script>
	<!-- Custom JS -->
	<script src="js/custom.js"></script>
</body>
</html>