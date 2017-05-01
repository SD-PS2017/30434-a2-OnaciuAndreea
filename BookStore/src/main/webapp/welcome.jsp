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
      <li class="active"><a href="${contextPath}/welcome">Add user</a></li>
      <li><a href="${contextPath}/deleteUser">Delete user</a></li>
      <li><a href="${contextPath}/editUser">Edit user info</a></li>
      <li><a href="${contextPath}/seeUsers">See users</a></li>
      <li><a href="${contextPath}/seeReports">Books out of stock</a></li>
      <li><a href="${contextPath}/seeBooks">See books</a></li>
      <li><a href="${contextPath}/deleteBook">Delete book</a></li>
      <li><a href="${contextPath}/addBook">Add book</a></li>
      <li><a href="${contextPath}/updateBook">Edit Book</a></li>
    </ul>
    <a href="${contextPath}/login"> <button class="btn btn-danger navbar-btn">
          Log out</button></a>
  </div>
</nav>


<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Add employee</h2>
       <p>Insert username:</p>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>
		<p>Insert password:</p>
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>
        <p>Insert password again:</p>
        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>
        
         <p>Insert name:</p>
    	<input name="name"  type="text" type="text" class="form-control" placeholder="Name"
                   autofocus="true"/> 
    	  <p>Insert salary:</p>
    	<input name="salary"  type="number" class="form-control" placeholder="Salary"
                   autofocus="true"/>
		
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    	<h2><span style="color:red" >${error }</span></h2>
    	<h2><span style="color:green" >${success}</span></h2>
    </form:form>
</div>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
