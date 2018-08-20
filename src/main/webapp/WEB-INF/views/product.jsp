<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>${product.name}</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<section>
  <div class="jumbotron">
    <div class="container">
      <h1>Products</h1>
    </div>
  </div>
</section>

<section class="container">
  <div class="row">
    <div class="col-md-5">
      <h3>${product.name}</h3>
      <p>${product.description}</p>
      <p>
        <strong>Item Code:</strong>
        <span class="label label-warning">${product.productId}</span>
      </p>
      <p>
        <strong>Manufacturer: </strong>${product.manufacturer}
      </p>
      <p>
        <strong>Category: </strong>${product.category}
      </p>
      <p>
        <strong>Available units in stock: </strong>${product.unitsInStock}
      </p>
      <h4>${product.unitPrice} USD</h4>
      <p>
        <a href="<spring:url value="/market/products"/>" class="btn btn-default">
          <span class="glyphicon-hand-left glyphicon"></span> Back
        </a>
        <a href="#" class="btn btn-warning btn-large">
          <span class="glyphicon glyphicon-shopping-cart"></span> Order Now
        </a>
      </p>
    </div>
  </div>
</section>

</body>
</html>
