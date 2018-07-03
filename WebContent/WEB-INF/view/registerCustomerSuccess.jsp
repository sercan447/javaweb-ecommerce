
<%@include file="/WEB-INF/view/templates/header.jsp" %>
	
	<div class="container-wrapper">
		<div class="container">
			<section>
				<div class="jumbotron">
					<div class="container">
						<h1>Customer registered successfully</h1>
					</div>
				</div>
			</section>
			
			<section class="container">
					<p>
						<a href="<spring:url value='/product/productList' />" class="btn btn-default">Products</a>
					</p>
			</section>
		</div>
	</div>

<%@include file="/WEB-INF/view/templates/footer.jsp" %>