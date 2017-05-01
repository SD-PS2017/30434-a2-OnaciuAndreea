<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Create an account</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script>
function submitter(btn) {
    var param = btn.parentElement.parentElement.id;
    var myForm = document.forms["myForm"];
    myForm.elements["param"].value = param;
    myForm.submit();
}
</script>
</head>

<body>

	<div class="container">
		<h2>Welcome, ${pageContext.request.userPrincipal.name} !</h2>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextPath}">Bookstore</a>
				</div>
				<a href="${contextPath}/login">
					<button class="btn btn-danger navbar-btn">Log out</button>
				</a>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/welcome">Buy
							book</a></li>
				</ul>

			</div>
		</nav>


		<div class="container">
			<form:form method="POST" modelAttribute="bookForm">
				<form:input type="hidden" path="ISBN" class="form-input"
					placeholder="ISBN" autofocus="true"></form:input>
				<p class="form-input-text">Insert title:</p>
				<form:input type="text" path="title" class="form-input"
					placeholder="title" autofocus="true"></form:input>
				<p></p>
				<p class="form-input-text">Insert author:</p>
				<form:input type="text" path="author" class="form-input"
					placeholder="author" autofocus="true"></form:input>
				<p></p>
				<p class="form-input-text">Insert genre:</p>
				<form:input type="text" path="genre" class="form-input"
					placeholder="genre" autofocus="true"></form:input>
				<p></p>
				<form:input type="hidden" path="price" class="form-input"
					placeholder="price" autofocus="true"></form:input>
				<form:input type="hidden" path="quantity" class="form-input"
					placeholder="quantity" autofocus="true"></form:input>
        &nbsp;
         &nbsp;
          &nbsp;
		
        <button class="form-input-button" type="submit">Search
					book</button>
			</form:form>
		</div>
		<p></p>
		
		
		<table id="books" 
			class="table table-striped table-bordered table-hover">
			
			<thead>
				<tr>
					<th>ISBN</th>
					<th>Title</th>
					<th>Author</th>
					<th>Genre</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Availability</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
					<form:form method="POST" modelAttribute="bookToBuy">
		<form:input type="hidden" path="ISBN" class="form-control" value="${book.getISBN()}" placeholder="ISBN"
                            autofocus="true"></form:input>		
		<form:input type="hidden" path="title" class="form-control"  value="${book.getTitle()}" placeholder="title"
                            autofocus="true"></form:input>
       <form:input type="hidden" path="author" class="form-control" value="${book.getAuthor()}" placeholder="author"
                            autofocus="true"></form:input>
       
        <form:input type="hidden" path="price" class="form-control" value="${book.getPrice()}" placeholder="price"
                            autofocus="true"></form:input>
        <form:input type="hidden" path="genre" class="form-control" value="${book.getGenre()}" placeholder="genre"
                            autofocus="true"></form:input>
       <form:input type="hidden" path="quantity" class="form-control" value="${book.getQuantity()}"  placeholder="quantity"
                            autofocus="true"></form:input>
                            
						<td>${book.getISBN()}</td>
						<td>${book.getTitle()}</td>
						<td>${book.getAuthor()}</td>
						<td>${book.getGenre()}</td>
						<td>${book.getPrice()}</td>
						<td>${book.getQuantity()}</td>
						<c:choose>
							<c:when test="${book.getQuantity()=='0'}">
        					<td>Book out of stock</td> 
                            <br />
							</c:when>
							<c:otherwise>
                            <td>
							<button class="btn btn-lg btn-primary btn-block" type="submit">Buy book</button>
								<span style="color:green" >${message}</span>
						     </td>
						   
                            <br />
							</c:otherwise>
						</c:choose>
						</form:form>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
