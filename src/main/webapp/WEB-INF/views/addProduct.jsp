<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
  <title>Add product</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<section>
  <div class="jumbotron">
    <div class="container">
      <h1>Products</h1>
      <p>Add products</p>
    </div>
  </div>
</section>

<section class="container">
  <form:form method="post" modelAttribute="productToAdd" class="form-horizontal">
    <fieldset>
      <legend>Add new product</legend>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="productId">
          Product Id
        </label>
        <div class="col-lg-10">
          <form:input id="productId" path="productId" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="name">
          Name
        </label>
        <div class="col-lg-10">
          <form:input id="name" path="name" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="unitPrice">
          Unit Price
        </label>
        <div class="col-lg-10">
          <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="manufacturer">
          Manufacturer
        </label>
        <div class="col-lg-10">
          <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="category">
          Category
        </label>
        <div class="col-lg-10">
          <form:input id="category" path="category" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
          Units in stock
        </label>
        <div class="col-lg-10">
          <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2" for="description">
          Description
        </label>
        <div class="col-lg-10">
          <form:textarea id="description" path="description" rows="2"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2">
          Condition
        </label>
        <div class="col-lg-10">
          <form:radiobutton path="condition" value="New"/>New
          <form:radiobutton path="condition" value="Old"/>Old
          <form:radiobutton path="condition" value="Refurbished"/>Refurbished
        </div>
      </div>

      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
        </div>
      </div>
    </fieldset>
  </form:form>
</section>

</body>
</html>
