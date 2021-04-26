<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebookCalendar</title>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/contact_styles.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/contact_responsive.css">

</head>

<body>

<div class="super_container">

	<!-- Header -->

	<div><!-- 네비게이션 -->
		<jsp:include page="/WEB-INF/view/inc/mainMenuTest.jsp"></jsp:include>
	</div>

	<div class="container contact_container">
		<div class="row">
			<div class="col">

				<!-- Breadcrumbs -->

				<div class="breadcrumbs d-flex flex-row align-items-center">
					<ul>
						<li><a href="index.html">Home</a></li>
						<li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>Cart List</a></li>
					</ul>
				</div>
			</div>
		</div>



		<!-- Contact Us -->

		<div class="row">

			<div class="col-lg-12 contact_col">
				<div class="contact_contents">
					<h1>신간목록</h1>
					<div>
						<a href="${pageContext.request.contextPath}/EbookCalendarController?currentYear=${preYear}&currentMonth=${preMonth}">이전달</a>
						<span>${currentYear}년</span>
						<span>${currentMonth}월</span>
						<a href="${pageContext.request.contextPath}/EbookCalendarController?currentYear=${nextYear}&currentMonth=${nextMonth}">다음달</a>
					</div>
					<table border="1">
						<tr>
							<th>일</th>
							<th>월</th>
							<th>화</th>
							<th>수</th>
							<th>목</th>
							<th>금</th>
							<th>토</th>
						</tr>
						<tr>
							<c:forEach var="i" begin="1" end="${endDay + (firstDayOfWeek-1)}" step="1">
								
								<c:if test="${i-(firstDayOfWeek-1) <= 0}">
									<td>&nbsp;</td> 
								</c:if>
								
								<c:if test="${i-(firstDayOfWeek-1) > 0}">
									<td>
										<div>${i-(firstDayOfWeek-1)}</div>
										<div>
											<c:forEach var="m" items="${ebookListByMonth}">
												<c:if test="${(i-(firstDayOfWeek-1)) == m.d}">
													<div>
														<a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${m.ebookNo}">
															<c:if test="${m.ebookTitle.length() > 10 }">
																 ${m.ebookTitle.substring(0, 10)}...
															</c:if>
															<c:if test="${m.ebookTitle.length() <= 10 }">
																${m.ebookTitle}
															</c:if>
														</a>
													</div>
												</c:if>
											</c:forEach>
										</div>
									</td> 
								</c:if>
								
								<c:if test="${i%7 == 0 }">
									</tr><tr>
								</c:if>
							</c:forEach>
							<!-- td 반복 후 채워지지 않는 자리가 있다면... -->
							<c:if test="${endDay + (firstDayOfWeek-1) % 7 != 0}">
								<c:forEach begin="1" end="${7 - ((endDay + (firstDayOfWeek-1)) % 7)}" step="1">
									<td>&nbsp;</td>
								</c:forEach>
							</c:if>
						</tr>
					</table>
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
						<div class="cr">©2018 All Rights Reserverd. Template by <a href="#">Colorlib</a> &amp; distributed by <a href="https://themewagon.com">ThemeWagon</a></div>
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
<script src="${pageContext.request.contextPath}/https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCIwF204lFZg1y4kPSIhKaHEXMLYxxuMhA"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/contact_custom.js"></script>
</body>

</html>
