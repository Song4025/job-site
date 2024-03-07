<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My Business card</title>
<!-- Description, Keywords and Author -->
<meta name="description" content="Your description">
<meta name="keywords" content="Your,Keywords">
<meta name="author" content="ResponsiveWebInc">

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
<!-- Link Swiper's CSS -->
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<style>
.swiper {
      width: 100%;
      height: 100%;
    }

    .swiper-slide {
    	width: 300px;
    	height: 280px;
	    text-align: center;
	    font-size: 18px;
	    background: #fff;
	    display: flex;
	    justify-content: center;
	    align-items: center;
    }

    .swiper-slide a {
        display: block;
        width: 100%;
        height: 100%;
        object
    }
</style>
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
				<h2>I'm Banner Heading for This Page</h2>
				<!-- paragraph -->
				<p>It is our belief that in order to be most efficient it
					requires adaptive technology and software our customers can focus
					on their core business.</p>
			</div>
		</div>
		<!-- banner end -->


		<!-- team -->
		<div class="team" id="team">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<!-- team member -->
						<div class="member">
							<div class="default-heading">
								<h2>이력서 작성</h2>
							</div>
							<!-- images -->
							<a href="#"> <img class="img-responsive" src="img/team/1.jpg"
								alt="Team Member" /></a>
							<!-- <span class="dig">CEO</span>
								<a href="#">executive.member@bloger.com</a> -->
						</div>
					</div>
					<div class="col-md-6 col-sm-6">
						<!-- team member -->
						<div class="member">
							<div class="default-heading">
								<h2>로그인</h2>
							</div>
							<!-- images -->
							<a href="#"><img class="img-responsive" src="img/team/2.jpg"
								alt="Team Member" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- team end -->
		<div class="team" id="team">
			<div class="container">
				<div class="row">
					<!-- Swiper -->
					<div class="swiper mySwiper">
					  <div class="swiper-wrapper">
						<div class="swiper-slide">Slide 2</div>
					    <div class="swiper-slide">Slide 3</div>
					    <div class="swiper-slide">Slide 4</div>
					    <div class="swiper-slide">Slide 5</div>
					    <div class="swiper-slide">Slide 6</div>
					    <div class="swiper-slide">Slide 7</div>
					    <div class="swiper-slide">Slide 8</div>
					    <div class="swiper-slide">Slide 9</div>
   					    <c:forEach var="bc" items="${list}" >
						    <div class="swiper-slide">${bc.title}</div>
					    </c:forEach>
					  </div>
					  <div class="swiper-pagination"></div>
				  </div>
			  </div>
			</div>
		</div>

		<!-- footer -->
		<footer>
			<div class="container">
				<p>
					<a href="#">Home</a> | <a href="#work">works</a> | <a href="#team">Team</a>
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
	<!-- Swiper JS -->
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<!-- Initialize Swiper -->
	<script>
	  var swiper = new Swiper(".mySwiper", {
	    slidesPerView: 3,
	    spaceBetween: 30,
	    pagination: {
	      el: ".swiper-pagination",
	      clickable: true,
	    },
	  });
	</script>
	
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