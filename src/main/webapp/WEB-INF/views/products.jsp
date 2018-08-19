<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>Products</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<section>
  <div class="jumbotron">
    <div class="container">
      <h1>Products</h1>
      <p>All the available products in our store</p>
    </div>
  </div>
</section>

<section class="container">
  <div class="row">
    <div class="col-sm-6 col-md-3" style="padding-bottom: 15px;">
      <div class="thumbnail">
        <div class="caption">
          <h3>${iphone.name}</h3>
          <p>${iphone.description}</p>
          <p>${iphone.unitPrice} USD</p>
          <p>Available ${iphone.unitsInStock} units in stock</p>
        </div>
      </div>
    </div>
  </div>
</section>

</body>
</html>
