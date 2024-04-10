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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<!-- Font awesome CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/style.css" rel="stylesheet">

<!-- Favicon -->
<link rel="shortcut icon" href="#">
<!-- Link Swiper's CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
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
							<a href="#" data-bs-toggle="modal"
								data-bs-target="#exampleModalScrollable"> <img
								class="img-responsive" src="img/team/1.jpg" alt="Team Member" />
							</a>
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
								alt="Team Member" /> </a>
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
						<div class="swiper-wrapper" id="swiperWrapper">
							<c:forEach var="bc" items="${list}">
								<div class="swiper-slide" style="cursor: pointer;"
									data-slide-id="${bc.card_id}">
									<p>${bc.title}</p>
								</div>
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


	<!-- Scrollable modal -->
	<c:forEach var="bc" items="${list}">
		<div class="modal" id="exampleModalScrollable" tabindex="-1"
			aria-labelledby="exampleModalScrollableLabel" aria-hidden="true">
			<div
				class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalScrollableLabel">명함
							등록하기</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<!-- 모달 내용 -->
						<form method="post" enctype="multipart/form-data" action="/reg"
							id="myCard">
							<div class="row">
								<div class="mb-3">
									<label for="title" class="form-label">타이틀</label> <input
										type="text" class="form-control" id="title" name="title"
										aria-describedby="title">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<label for="userName" class="form-label">이름(닉네임)</label> <input
										type="text" class="form-control" id="userName" name="userName"
										aria-describedby="userName">
								</div>
								<div class="col">
									<label for="age" class="form-label">나이</label> <input
										type="number" class="form-control" id="age" name="age"
										aria-describedby="age">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="phone" class="form-label">핸드폰</label> <input
										type="text" maxlength="11" class="form-control" id="phone"
										name="phone" aria-describedby="phone">
								</div>
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="position" class="form-label">직업포지션</label> <input
										type="text" class="form-control" id="position" name="position"
										aria-describedby="position">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="file" class="form-label">첨부파일</label> <input
										type="file" class="form-control" id="files" name="files"
										aria-describedby="file" multiple>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="url" class="form-label">포트폴리오 url</label> <input
										type="text" class="form-control" id="url" name="url"
										aria-describedby="url">
								</div>
							</div>
							<div class="row" style="padding: 10px;">
								<div class="form-check form-switch col">
									<input class="form-check-input" type="checkbox" role="switch"
										id="pub" name="pub"> <label class="form-check-label"
										for="pub">메인에 내 명함을 공개</label>
								</div>
								<div class="form-check form-switch col">
									<input class="form-check-input" type="checkbox" role="switch"
										id="jobState" name="jobState"> <label
										class="form-check-label" for="jobState">현재 재직중여부</label>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">닫기</button>
								<button type="submit" class="btn btn-primary">등록</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<!-- update modal -->
	<c:forEach var="bc" items="${list}">
		<div class="modal" id="${bc.card_id}" tabindex="-1"
			aria-labelledby="exampleModalScrollableLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalScrollableLabel">등록된
							명함 정보</h5>
						<div class="col" style="text-align:right;">
							<label for="regDate" class="form-label">최종 수정일</label> 
							<p id="regDate" style="display:inline;">${bc.reg_date}</p>
						</div>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<!-- 모달 내용 -->
						<form method="post" enctype="multipart/form-data" action="/update"
							id="myCard_${bc.card_id}" >
							<div class="row">
								<div class="mb-3">
									<input type="text" class="form-control" style="display:none;" id="upCardId" name="upCardId" value="${bc.card_id}">
									<label for="title" class="form-label">타이틀</label> <input
										type="text" class="form-control" id="upTitle" name="upTitle"
										aria-describedby="upTitle" value="${bc.title}">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<label for="userName" class="form-label">이름(닉네임)</label> <input
										type="text" class="form-control" id="upUserName"
										name="upUserName" aria-describedby="upUserName"
										value="${bc.user_name}">
								</div>
								<div class="col">
									<label for="age" class="form-label">나이</label> <input
										type="number" class="form-control" id="upAge" name="upAge"
										aria-describedby="upAge" value="${bc.age}">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="phone" class="form-label">핸드폰</label> <input
										type="text" maxlength="11" class="form-control" id="upPhone"
										name="upPhone" aria-describedby="upPhone" value="${bc.phone}">
								</div>
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="position" class="form-label">직업포지션</label>
									<input
										type="text" class="form-control" id="upPosition"
										name="upPosition" aria-describedby="upPosition"
										value="${bc.position}">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="file" class="form-label">첨부파일</label>
									<button class="btn btn-outline-secondary" type="button" id="addUpFile">추가</button>
									<c:forEach var="f" items="${filesList}">
										<c:if test="${f.file_card_id eq bc.card_id}">
											<%-- <input
											type="text" class="form-control" id="beforefile" name="beforefile"
											aria-describedby="beforefile" multiple value="${f.content_type}"> --%>
											<div class="input-group mb-3">
						                        <input type="text" class="form-control" id="beforefile" name="beforefile"
						                            aria-describedby="beforefile" readonly value="${f.content_type}">
						                        <button class="btn btn-outline-secondary" type="button" id="fileRemove">삭제</button>
						                    </div>
										</c:if>
									</c:forEach>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div id="aa" class="form-text"></div>
									<label for="url" class="form-label">포트폴리오 url</label> <input
										type="text" class="form-control" id="upUrl" name="upUrl"
										aria-describedby="upUrl" value="${bc.url}">
								</div>
							</div>
							<div class="row" style="padding: 10px;">
								<div class="form-check form-switch col">
									<input class="form-check-input" type="checkbox" role="switch"
										id="upPub" name="upPub" ${bc.pub_yn ? "checked" : ""}> <label
										class="form-check-label" for="pub">메인에 내 명함을 공개</label>
								</div>
								<div class="form-check form-switch col">
									<input class="form-check-input" type="checkbox" role="switch"
										id="upJobState" name="upJobState" ${bc.job_state ? "checked" : ""}>
									<label class="form-check-label" for="jobState">현재 재직중여부</label>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" id="btnDel" class="btn btn-secondary"
									data-bs-dismiss="modal" data-card-id="${bc.card_id}" >삭제</button>
								<button type="submit" class="btn btn-primary">수정</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<!-- Javascript files -->
	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<!-- Bootstrap JS -->
	<script src="js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Respond JS for IE8 -->
	<script src="js/respond.min.js"></script>
	<!-- HTML5 Support for IE -->
	<script src="js/html5shiv.js"></script>
	<!-- Custom JS -->
	<script src="js/custom.js"></script>
	<!-- JQuery  -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- Swiper JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

	<!-- swipers 의 하나의 swiper 클릭시 모달 -->
	<script>
	document.addEventListener('DOMContentLoaded', () => {
		<!-- Initialize Swiper -->
		var swiper = new Swiper(".mySwiper", {
		    slidesPerView: 3,
		    spaceBetween: 30,
		    pagination: {
		      el: ".swiper-pagination",
		      clickable: true,
		    },
		});
		// 슬라이드 클릭시 모달 오픈
	    const slides = document.querySelectorAll('.swiper-slide');
    	slides.forEach((slide) => {
	    	slide.addEventListener('click', ()=>{
	            const slideId = slide.getAttribute('data-slide-id');
	            $('#' + slideId).modal('show'); // 모달 표시
	    	})
	    });
    	// 모달 파일 삭제
    	const fileRemovebtns = document.querySelectorAll('#fileRemove');
    	const beforefile = document.querySelectorAll('#beforefile');
    	fileRemovebtns.forEach(button => {
    	    button.addEventListener('click', () => {
    	    	const parentElement = button.parentNode; 
    	        parentElement.parentNode.removeChild(parentElement); 
    	    });
    	});
    	//모달 카드삭제
    	const submitDel = document.querySelector('#btnDel');
    	submitDel.addEventListener('click', (e) => {
    		const cardId = event.target.getAttribute('data-card-id');
    		console.log(cardId);
    		document.getElementById('myCard_' + cardId).action = '/delete';
	    });
    		
	});
	</script>

</body>
</html>