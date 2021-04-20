<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- 로그아웃 일때(회원가입, 로그인) -->

<c:if test="${ loginClient == null}">
	<header class="header trans_300">
	
		<!-- Top Navigation(제일 위 검은 부분) -->
		<div class="top_nav">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="top_nav_left">저희 EbookMall 더 잘 이용하기 위해서는 로그인해 주십시오.</div>
					</div>
					<div class="col-md-6 text-right">
						<div class="top_nav_right">
							<ul class="top_nav_menu">
	
								<!-- Currency / Language / My Account -->
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<!-- Main Navigation -->
	
		<div class="main_nav_container">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-right">
						<div class="logo_container">
							<a href="${pageContext.request.contextPath}/IndexController">ebook<span>mall</span></a>
						</div>
						<nav class="navbar">
							<ul class="navbar_menu">
								<li><a href="${pageContext.request.contextPath}/IndexController">home</a></li>
								<li><a href="${pageContext.request.contextPath}/InsertClientController">회원가입</a></li>
								<li><a href="${pageContext.request.contextPath}/EbookCalendarController">신간달력</a></li>
							</ul>
							<ul class="navbar_user">								
								<li>
									<form action="${pageContext.request.contextPath}/LoginController" method="post">
										ID: <input type="text" name="clientMail">
										PW: <input type="password" name="clientPw">
										<button type="submit">로그인</button>
									</form>
								</li>
							</ul>
							<div class="hamburger_container">
								<i class="fa fa-bars" aria-hidden="true"></i>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>
</c:if>
<c:if test="${ loginClient != null}">
	<header class="header trans_300">	
		<!-- Top Navigation -->
		<div class="top_nav">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="top_nav_left">${loginClient.clientMail}님 반갑습니다.</div>
					</div>
					<div class="col-md-6 text-right">
						<div class="top_nav_right">
							<ul class="top_nav_menu">
	
								<!-- Currency / Language / My Account -->
	
								<li class="currency">
									<a href="#">
										usd
										<i class="fa fa-angle-down"></i>
									</a>
									<ul class="currency_selection">
										<li><a href="#">cad</a></li>
										<li><a href="#">aud</a></li>
										<li><a href="#">eur</a></li>
										<li><a href="#">gbp</a></li>
									</ul>
								</li>
								<li class="language">
									<a href="#">
										English
										<i class="fa fa-angle-down"></i>
									</a>
									<ul class="language_selection">
										<li><a href="#">French</a></li>
										<li><a href="#">Italian</a></li>
										<li><a href="#">German</a></li>
										<li><a href="#">Spanish</a></li>
									</ul>
								</li>
								<li class="account">
									<a href="#">
										My Account
										<i class="fa fa-angle-down"></i>
									</a>
									<ul class="account_selection">
										<li><a href="#"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign In</a></li>
										<li><a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i>Register</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<!-- Main Navigation -->
	
		<div class="main_nav_container">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-right">
						<div class="logo_container">
							<a href="${pageContext.request.contextPath}/IndexController">ebook<span>mall</span></a>
						</div>
						<nav class="navbar">
							<ul class="navbar_menu">
								<li><a href="${pageContext.request.contextPath}/IndexController">home</a></li>
								<li><a href="${pageContext.request.contextPath}/OrdersListController">주문리스트</a></li>
								<li><a href="${pageContext.request.contextPath}/EbookCalendarController">신간달력</a></li>
								<li><a href="${pageContext.request.contextPath}/ClientOneController">회원정보</a></li>
								<li><a href="#">베스트셀러</a></li>
								<li><a href="#">ebook목록보기</a></li>
								<li><a href="${pageContext.request.contextPath}/LogoutController">로그아웃</a></li>
							</ul>
							<ul class="navbar_user">
								<li><a href="#"><i class="fa fa-search" aria-hidden="true"></i></a></li>
								<li><a href="${pageContext.request.contextPath}/ClientOneController"><i class="fa fa-user" aria-hidden="true"></i></a></li>
								<li class="checkout">
									<a href="${pageContext.request.contextPath}/CartListController">
										<i class="fa fa-shopping-cart" aria-hidden="true"></i>
										<span id="checkout_items" class="checkout_items">2</span>
									</a>
								</li>
							</ul>
							<div class="hamburger_container">
								<i class="fa fa-bars" aria-hidden="true"></i>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- 뭐에다 쓰는건지 알 수 없음... -->
	<div class="fs_menu_overlay"></div>
	<div class="hamburger_menu">
		<div class="hamburger_close"><i class="fa fa-times" aria-hidden="true"></i></div>
		<div class="hamburger_menu_content text-right">
			<ul class="menu_top_nav">
				<li class="menu_item has-children">
					<a href="#">
						usd
						<i class="fa fa-angle-down"></i>
					</a>
					<ul class="menu_selection">
						<li><a href="#">cad</a></li>
						<li><a href="#">aud</a></li>
						<li><a href="#">eur</a></li>
						<li><a href="#">gbp</a></li>
					</ul>
				</li>
				<li class="menu_item has-children">
					<a href="#">
						English
						<i class="fa fa-angle-down"></i>
					</a>
					<ul class="menu_selection">
						<li><a href="#">French</a></li>
						<li><a href="#">Italian</a></li>
						<li><a href="#">German</a></li>
						<li><a href="#">Spanish</a></li>
					</ul>
				</li>
				<li class="menu_item has-children">
					<a href="#">
						My Account
						<i class="fa fa-angle-down"></i>
					</a>
					<ul class="menu_selection">
						<li><a href="#"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign In</a></li>
						<li><a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i>Register</a></li>
					</ul>
				</li>
				<li class="menu_item"><a href="#">home</a></li>
				<li class="menu_item"><a href="#">shop</a></li>
				<li class="menu_item"><a href="#">promotion</a></li>
				<li class="menu_item"><a href="#">pages</a></li>
				<li class="menu_item"><a href="#">blog</a></li>
				<li class="menu_item"><a href="#">contact</a></li>
			</ul>
		</div>
	</div>
	
</c:if>
</body>
</html>