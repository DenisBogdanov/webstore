<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <title>404 Not Found</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<section>
  <div class="jumbotron">
    <div class="container">
      <h1 class="alert alert-danger">${message}</h1>
    </div>
  </div>
</section>
<section>
  <div class="container">
    <p>${url}</p>
    <p>${exception}</p>
  </div>
  <div class="container">
    <p>
      <a href="<spring:url value="/market/products"/>" class="btn btn-primary">
        <span class="glyphicon-hand-left glyphicon"></span> products
      </a>
    </p>
  </div>
</section>

</body>
</html>
