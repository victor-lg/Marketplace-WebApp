<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.wuolahpop.data.*"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>WuolahPop</title>

 		<!-- Google font -->
 		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

 		<!-- Bootstrap -->
 		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
	

		<!-- HEADER -->
		<header>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					<ul class="header-links pull-left">
						<li><a href="#"><i class="fa fa-phone"></i> 916886138</a></li>
						<li><a href="#"><i class="fa fa-envelope-o"></i> info@wuolahpop.com</a></li>
						<li><a href="#"><i class="fa fa-map-marker"></i> Madrid</a></li>
					</ul>
					<ul class="header-links pull-right">
						<li><a href="account.jsp"><i class="fa fa-user-o"></i>Mi Cuenta</a></li>
						<li><a href="add-product.jsp"><i class="fa fa-user-o"></i>Añadir Producto</a></li>
					</ul>
					<form action="ControllerServlet" id="logOutForm" method="post" style="float: right;">
						<input type="submit" value="Out" class="zmdi zmdi-power" style="width: 42px; margin-right: 10px; padding: 2px ">
						<input type="hidden" name="typeOfQuery" value="logOut" >
					</form>
				</div>
			</div>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="store.jsp" class="logo" style="color: white; font-size: 40px">
									WUOLAHPOP
								</a>
							</div>
						</div>
						<!-- /LOGO -->

					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->

		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Motor</a></li>
						<li><a href="#">Moda</a></li>
						<li><a href="#">Telefonía</a></li>
						<li><a href="#">Informática y Electrónica</a></li>
						<li><a href="#">Deporte y Ocio</a></li>
						<li><a href="#">Hogar y Jardín</a></li>
						<li><a href="#">Cine, Libros y Música</a></li>
						<li><a href="#">TV, Audio y Foto</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
		
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="#">Home</a></li>
							<li><a href="#">All Categories</a></li>

						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
				
					<!-- ASIDE -->
					<div id="aside" class="col-md-3">
						
						<form action="controlador" method="post">
							<h3 class="aside-title">Categoría</h3>
							<select name="product-category-selection">
								<option value="0">Seleccionar categoría</option>
								<option value="Motor">Motor</option>
								<option value="Moda">Moda</option>
								<option value="Telefonia">Telefonía</option>
								<option value="Informatica y Electronica">Informática y Electrónica</option>
								<option value="Deporte y Ocio">Deporte y Ocio</option>
								<option value="Hogar y Jardin">Hogar y Jardín</option>
								<option value="Cine, Libros y Musica">Cine, Libros y Música</option>
								<option value="TV, Audio y Foto">TV, Audio y Foto</option>
							</select><br><br>
							  <input type="hidden" name="typeOfQuery" value="searchByCategory" >
							  <button type="submit">Search</button>
							  
						</form>
					</div>
					
						<!-- ASIDE -->
						<div id="aside" class="col-md-3">
							
							<form action="controlador" method="post">
								<h3 class="aside-title">Titulo</h3>
								<input type="text" name="titleSelected" placeholder="Portátil">
								<input type="hidden" name="typeOfQuery" value="searchByTitle" >
								<button type="submit">Search</button>
								  
							</form>
						</div>

						<!-- ASIDE -->
						<div id="aside" class="col-md-3">
							
							<form action="controlador" method="post">
								<h3 class="aside-title">Vendedor</h3>
								<input type="text" name="vendorSelected" placeholder="antonio@gmail.com">
								<input type="hidden" name="typeOfQuery" value="searchByVendor" >
								<button type="submit">Search</button>
								  
							</form>
						</div>

						
					</div>
					<!-- /ASIDE -->

					<!-- STORE -->
					<div id="store" class="col-md-12" style="padding: 0px">
						<!-- store top filter -->
						<div class="store-filter clearfix">
							<div class="store-sort">
								<label>
									Sort By:
									<select class="input-select">
										<option value="0">Popular</option>
										<option value="1">Position</option>
									</select>
								</label>

								<label>
									Show:
									<select class="input-select">
										<option value="0">20</option>
										<option value="1">50</option>
									</select>
								</label>
							</div>
							<ul class="store-grid">
								<li class="active"><i class="fa fa-th"></i></li>
								<li><a href="#"><i class="fa fa-th-list"></i></a></li>
							</ul>
						</div>
						<!-- /store top filter -->

						<!-- store products -->
						
						<h1>PRODUCTOS</h1>
						<form action="controlador" method="post">
							
							<input type="hidden" name="typeOfQuery" value="getAllProducts" >
							<input type="submit" value="Actualizar">
						</form>
						
						
						<%
							Item [] itemsList = (Item[]) session.getAttribute("itemsList");
							User user = (User) session.getAttribute("user");
							for (Item myItem : itemsList) {
								if(myItem.getState().equals("Disponible")) {
									if( !myItem.getVendor().equals(user.getMail()) ) {
										
									

						%>
							<div class="col-md-4 col-xs-6">
								<div class="product">
									<div class="product-img">
										<img src="data:image/*;base64,<%=myItem.getPhoto() %>" />
										
									</div>

									<div class="product-body">
										<p class="product-category"><%= myItem.getCategory() %></p>
										<h3 class="product-name"><a href="#"> <%= myItem.getTitle() %> </a></h3>
										<h4 class="product-price"><%= myItem.getPrice() %>€</h4>
										<div class="product-rating">
											<p class="product-category"><%= myItem.getVendor() %></p>
										</div>
										<div class="product-btns">
											<p class="product-status"><%= myItem.getState() %></p>
										</div>
									</div>
									<div class="add-to-cart">
									
									<form action="controlador" method="post">
										<input type="hidden" name="typeOfQuery" value="buyProduct" >
										<input type="hidden" name="productID" value="<%= myItem.getItemId() %>" >
										<button type="submit" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>Comprar</button>
									</form>

										
									</div>
								<!-- /product -->
								</div>
							</div>
						<% 		}
							}
						}
						%>
						
						
					<!-- /STORE -->

						
				</div>

				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- FOOTER -->
		<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">About Us</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<li><a href="#">Hot deals</a></li>
									<li><a href="#">Laptops</a></li>
									<li><a href="#">Smartphones</a></li>
									<li><a href="#">Cameras</a></li>
									<li><a href="#">Accessories</a></li>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="#">Track My Order</a></li>
									<li><a href="#">Help</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->

			<!-- bottom footer -->
			<div id="bottom-footer" class="section">
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-12 text-center">
							<ul class="footer-payments">
								<li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
								<li><a href="#"><i class="fa fa-credit-card"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
							</ul>
							<span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>
						</div>
					</div>
						<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /bottom footer -->
		</footer>
		
					</div>

		<!-- /FOOTER -->

		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>

	</body>
</html>
