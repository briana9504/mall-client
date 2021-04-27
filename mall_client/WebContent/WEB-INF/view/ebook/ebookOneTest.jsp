<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Single Product</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Colo Shop Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap4/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/themify-icons/themify-icons.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/single_styles.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/single_responsive.css">
</head>

<body>

<div class="super_container">
	<div><!-- 네비게이션 -->
		<jsp:include page="/WEB-INF/view/inc/mainMenuTest.jsp"></jsp:include>
	</div>

	<div class="fs_menu_overlay"></div>

	
	<div class="container single_product_container">
		<div class="row">
			<div class="col">

				<!-- Breadcrumbs -->

				<div class="breadcrumbs d-flex flex-row align-items-center">
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="categories.html"><i class="fa fa-angle-right" aria-hidden="true"></i>ebookList</a></li>
						<li><a href="categories.html"><i class="fa fa-angle-right" aria-hidden="true"></i>${ebook.categoryName}</a></li>
						<li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>${ebook.ebookTitle}</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-7">
				<div class="single_product_pics">
					<div class="row">
						
						<div class="col-lg-9 image_col order-lg-2 order-1">
							<div class="single_product_image">
								<div class="single_product_image_background" style="background-image:url(img/default.jpg)"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-5">
				<div class="product_details">
					<div class="product_details_title">
						<!-- 책이름 -->
						<h2>${ebook.ebookTitle}</h2>
						<p>
							<div>작가: ${ebook.ebookAuthor}</div>
							<div>카테고리: ${ebook.categoryName}</div>
							<div>출판사: ${ebook.ebookCompany}</div>
							<div>상태: ${ebook.ebookState}</div>
						</p>
					</div>
					<div class="free_delivery d-flex flex-row align-items-center justify-content-center">
						<c:if test="${loginClient == null || !ebook.ebookState.equals('판매중')}">
							<span class="ti-truck"></span><span>바로 주문하기</span><!-- 주문완됨 -->
						</c:if>
						<c:if test="${loginClient != null && ebook.ebookState.equals('판매중')}"><!-- 주문가능 -->
							<a href="${pageContext.request.contextPath}/InsertOrdersContorller?ebookNo=${ebook.ebookNo}">
								<span class="ti-truck"></span><span>바로 주문하기</span>
							</a>
						</c:if>
						
					</div>
					<div class="original_price"></div>
					<div class="product_price">&#8361;${ebook.ebookPrice}</div>
					
					
					<div class="quantity d-flex flex-column flex-sm-row align-items-sm-center">
						<span>Quantity:</span>
						<div class="quantity_selector">
							<span class="minus"><i class="fa fa-minus" aria-hidden="true"></i></span>
							<span id="quantity_value">1</span>
							<span class="plus"><i class="fa fa-plus" aria-hidden="true"></i></span>
						</div>
						
							<c:if test="${loginClient == null || !ebook.ebookState.equals('판매중')}">	
								<div class="red_button add_to_cart_button">
									<a href="">		
										add the cart	
									</a>					
								</div>
							</c:if>
							<c:if test="${loginClient != null && ebook.ebookState.equals('판매중')}">
								<div class="red_button add_to_cart_button">
									<a href="${pageContext.request.contextPath}/InsertCartController?ebookNo=${ebook.ebookNo}">
										add the cart
									</a>
								</div>
							</c:if>	
						<div class="product_favorite d-flex flex-column align-items-center justify-content-center"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Tabs -->

	<div class="tabs_section_container">

		<div class="container">
			<div class="row">
				<div class="col">
					<div class="tabs_container">
						<ul class="tabs d-flex flex-sm-row flex-column align-items-left align-items-md-center justify-content-center">
							<li class="tab active" data-active-tab="tab_2"><span>Additional Information</span></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">

					<!-- Tab Additional Info -->

					<div id="tab_2" class="tab_container active">
						<div class="row">
							<div class="col additional_info_col">
								<div class="tab_title additional_info_title">
									<h4>Additional Information</h4>
								</div>
								<p>ISBN:<span>${ebook.ebookISBN}</span></p>
								<p>판매날짜:<span>${ebook.ebookDate.substring(0,11)}</span></p>
								<p>페이지 수:<span>${ebook.ebookPageCount}</span></p>
								<p>소개:<span>${ebook.ebookSummary}</span></p>
								<p>판매날짜:<span>${ebook.ebookDate.substring(0,11)}</span></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- Benefit -->

	<div class="benefit">
		<div class="container">
			<div class="row benefit_row">
				<div class="col-lg-3 benefit_col">
					<div class="benefit_item d-flex flex-row align-items-center">
						<div class="benefit_icon"><i class="fa fa-truck" aria-hidden="true"></i></div>
						<div class="benefit_content">
							<h6>free shipping</h6>
							<p>Suffered Alteration in Some Form</p>
						</div>
					</div>
				</div>
				<div class="col-lg-3 benefit_col">
					<div class="benefit_item d-flex flex-row align-items-center">
						<div class="benefit_icon"><i class="fa fa-money" aria-hidden="true"></i></div>
						<div class="benefit_content">
							<h6>cach on delivery</h6>
							<p>The Internet Tend To Repeat</p>
						</div>
					</div>
				</div>
				<div class="col-lg-3 benefit_col">
					<div class="benefit_item d-flex flex-row align-items-center">
						<div class="benefit_icon"><i class="fa fa-undo" aria-hidden="true"></i></div>
						<div class="benefit_content">
							<h6>45 days return</h6>
							<p>Making it Look Like Readable</p>
						</div>
					</div>
				</div>
				<div class="col-lg-3 benefit_col">
					<div class="benefit_item d-flex flex-row align-items-center">
						<div class="benefit_icon"><i class="fa fa-clock-o" aria-hidden="true"></i></div>
						<div class="benefit_content">
							<h6>opening all week</h6>
							<p>8AM - 09PM</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Newsletter -->

	<div class="newsletter">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="newsletter_text d-flex flex-column justify-content-center align-items-lg-start align-items-md-center text-center">
						<h4>Newsletter</h4>
						<p>Subscribe to our newsletter and get 20% off your first purchase</p>
					</div>
				</div>
				<div class="col-lg-6">
					<form action="post">
						<div class="newsletter_form d-flex flex-md-row flex-column flex-xs-column align-items-center justify-content-lg-end justify-content-center">
							<input id="newsletter_email" type="email" placeholder="Your email" required="required" data-error="Valid email is required.">
							<button id="newsletter_submit" type="submit" class="newsletter_submit_btn trans_300" value="Submit">subscribe</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->

	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="footer_nav_container d-flex flex-sm-row flex-column align-items-center justify-content-lg-start justify-content-center text-center">
						<ul class="footer_nav">
							<li><a href="#">Blog</a></li>
							<li><a href="#">FAQs</a></li>
							<li><a href="contact.html">Contact us</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center">
						<ul>
							<li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-skype" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="footer_nav_container">
						<div class="cr">©2018 All Rights Reserverd. This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#">Colorlib</a> &amp; distributed by <a href="https://themewagon.com">ThemeWagon</a></div>
					</div>
				</div>
			</div>
		</div>
	</footer>

</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/styles/bootstrap4/popper.js"></script>
<script src="${pageContext.request.contextPath}/styles/bootstrap4/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="${pageContext.request.contextPath}/plugins/easing/easing.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/single_custom.js"></script>
</body>

</html>
