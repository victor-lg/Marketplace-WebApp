<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
						<li><a href="#"><i class="fa fa-user-o"></i>Darse de baja</a></li>
					</ul>
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

			<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
					<!-- STORE -->
					<div id="store" class="col-md-9">
						<!-- mis productos-->
						<h3>Mis productos</h3>
						<div class="row">
							<!-- product -->
							<div class="col-md-4 col-xs-6">
								<!-- product -->
								<div class="product">
									<div class="product-img">
										<img src="./img/product01.png" alt="">
										
									</div>
									<div class="product-body">
										<p class="product-category">Informática y Electrónica</p>
										<h3 class="product-name"><a href="#">MacBook Pro</a></h3>
										<h4 class="product-price">960€</h4>
										<div class="product-rating">
											<p class="product-category">Borja Rivera González</p>
										</div>
									</div>
									<div class="modify-product">
										<button onclick="showProductInfo()" class="add-to-cart-btn">Modificar</button><br><br>
										
									</div>
								</div>
								<!-- /product -->
								
						<div class="row">
							<div class="product-info" id="product-info" style="display:none">
								<form name="modify-product-form" class="modify-product-form" >

									  <legend><strong>Modificar Producto</strong></legend>
									
									  <label for="nombre">Titulo del producto</label> <br>  
									  <input name="product-name" id="product-name" size="50" maxlength="250" type="text"><br><br>
									  
									  <label for="product-state">Estado</label> 
									  <select name="product-state-selection">
										<option value="0">Disponible</option>
										<option value="1">Vendido</option>
									  </select><br><br>
									  
									  <label for="product-category">Categoría</label> 
									  <select name="product-category-selection">
										<option value="0">Seleccionar categoría</option>
										<option value="1">Motor</option>
										<option value="2">Moda</option>
										<option value="3">Telefonía</option>
										<option value="4">Informática y Electrónica</option>
										<option value="5">Deporte y Ocio</option>
										<option value="6">Hogar y Jardín</option>
										<option value="7">Cine, Libros y Música</option>
										<option value="8">TV, Audio y Foto</option>
									  </select><br><br>
									
									  <label for="descripcion">Descripción</label> <br>  
									  <textarea name="product-description" id="description" maxlength="500" cols="40" rows="5"></textarea><br><br>
									
									  <label for="image">Foto</label> <br> 
									  <input name="image" type="file"><br><br>  
									  
									  <label for="precio">Precio</label> 
									  <input size="5" id="precio" name="precio" type="text"> € <br><br>
									  
									  <button type="Submit">Modificar</button><br><br>
									  

				  
								</form>
							</div>
						</div>
								
						<div class="row">
							<div class="user-info">
								<form>
									<h3>Mis datos</h3>
									
									Nombre <br><input type="text" name="name" placeholder="Borja" size="50" maxlength="250"><br><br>
									Primer Apellido<br><input type="text" name="surname1" placeholder="Rivera" size="50" maxlength="250"><br><br>
									Segundo Apellido<br><input type="text" name="surname2" placeholder="Gonzalez" size="50" maxlength="250" ><br><br>
									Ciudad<br><input type="text" name="city" placeholder="Madrid" size="50" maxlength="250"><br><br>
									Email<br><input type="email" name="email" placeholder="borja@gmail.com" size="50" maxlength="250" readonly><br><br>
									Contraseña<br><input type="password" name="pswd" size="50" maxlength="250"><br><br>
			
									<button>Modificar</button><br><br>
								</form>
							</div>
						</div>
						<!-- /mis productos -->
					</div>
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
