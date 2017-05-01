<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container"> 
        <h2>Welcome, ${pageContext.request.userPrincipal.name} !</h2>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${contextPath}">Bookstore</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="${contextPath}/welcome">Add user</a></li>
      <li><a href="${contextPath}/deleteUser">Delete user</a></li>
      <li><a href="${contextPath}/editUser">Edit user info</a></li>
      <li><a href="${contextPath}/seeUsers">See users</a></li>
      <li><a href="${contextPath}/seeReports">Books out of stock</a></li>
      <li><a href="${contextPath}/seeBooks">See books</a></li>
      <li><a href="${contextPath}/deleteBook">Delete book</a></li>
      <li><a href="${contextPath}/addBook">Add book</a></li>
      <li class="active"><a href="${contextPath}/updateBook">Edit Book</a></li>
    </ul>
    <a href="${contextPath}/login"> <button class="btn btn-danger navbar-btn">
          Log out</button></a>
  </div>
</nav>


<div class="container">

    <form:form method="POST" modelAttribute="bookForm" class="form-signin">
        <h2 class="form-signin-heading">Update book</h2>
               <p>Insert ISBN:</p>
       <form:input type="text" path="ISBN" class="form-control" placeholder="ISBN"
                            autofocus="true"></form:input>
       <h2><span style="color:red" >${errorMessage}</span></h2>
       <form:input type="hidden" path="title" class="form-control" placeholder="title"
                            autofocus="true"></form:input>
       <form:input type="hidden" path="author" class="form-control" placeholder="author"
                            autofocus="true"></form:input>
       <button class="btn btn-lg btn-primary btn-block" type="submit">Search</button>
        <form:input type="hidden" path="price" class="form-control" placeholder="price"
                            autofocus="true"></form:input>
        <form:input type="hidden" path="genre" class="form-control" placeholder="genre"
                            autofocus="true"></form:input>
       <form:input type="hidden" path="quantity" class="form-control" placeholder="quantity"
                            autofocus="true"></form:input>
    </form:form>
        <form:form method="POST" modelAttribute="bookUpdate" class="form-signin">
        <!--  <p>Insert ISBN:</p>-->
       <form:input type="hidden" path="ISBN" class="form-control" placeholder="ISBN" value="${bookUpdate.getISBN()}"
                            autofocus="true"></form:input> 
       <p>Insert title:</p>
       <form:input type="text" path="title" class="form-control" value="${bookUpdate.getTitle()}" placeholder="title"
                            autofocus="true"></form:input>
       <p>Insert author:</p>
       <form:input type="text" path="author" class="form-control" value="${bookUpdate.getAuthor()}" placeholder="author"
                            autofocus="true"></form:input>
       <p>Insert genre:</p>
       <form:input type="text" path="genre" class="form-control" placeholder="genre" value="${bookUpdate.getGenre()}"
                            autofocus="true"></form:input>
        <p>Insert price:</p>
       <form:input type="number" path="price" class="form-control" placeholder="price" value="${bookUpdate.getPrice()}"
                            autofocus="true"></form:input>
        <p>Insert quantity:</p>
       <form:input type="number" path="quantity" class="form-control" placeholder="quantity" value="${bookUpdate.getQuantity()}"
                            autofocus="true"></form:input>
		
		
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    	<h2><span style="color:red" >${error}</span></h2>
    	<h2><span style="color:green" >${success}</span></h2>
    </form:form>
</div>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
