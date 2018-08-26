<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Add product</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<section>
  <div class="pull-right" style="padding-right:50px">
    <a href="?language=en">English</a> | <a href="?language=nl">Dutch</a>
    <a href="<c:url value="/logout" /> ">Logout</a>
  </div>
</section>

<section>
  <div class="jumbotron">
    <div class="container">
      <h1>Products</h1>
      <p>Add products</p>
    </div>
  </div>
</section>

<section class="container">
  <form:form method="post" modelAttribute="productToAdd" class="form-horizontal" enctype="multipart/form-data">
    <fieldset>
      <legend>Add new product</legend>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="productId">
          <spring:message code="addProduct.form.productId.label"/>
        </label>
        <div class="col-lg-10">
          <form:input id="productId" path="productId" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="name">
          <spring:message code="addProduct.form.name.label"/>
        </label>
        <div class="col-lg-10">
          <form:input id="name" path="name" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="unitPrice">
          <spring:message code="addProduct.form.unitPrice.label"/>
        </label>
        <div class="col-lg-10">
          <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="manufacturer">
          <spring:message code="addProduct.form.manufacturer.label"/>
        </label>
        <div class="col-lg-10">
          <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="category">
          <spring:message code="addProduct.form.category.label"/>
        </label>
        <div class="col-lg-10">
          <form:input id="category" path="category" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
          <spring:message code="addProduct.form.unitsInStock.label"/>
        </label>
        <div class="col-lg-10">
          <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2" for="description">
          <spring:message code="addProduct.form.description.label"/>
        </label>
        <div class="col-lg-10">
          <form:textarea id="description" path="description" rows="2"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2">
          <spring:message code="addProduct.form.condition.label"/>
        </label>
        <div class="col-lg-10">
          <form:radiobutton path="condition" value="New"/> New<br>
          <form:radiobutton path="condition" value="Old"/> Old<br>
          <form:radiobutton path="condition" value="Refurbished"/> Refurbished<br>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2" for="productImage">
          <spring:message code="addProduct.form.productImage.label"/>
        </label>
        <div class="col-lg-10">
          <form:input path="productImage" id="productImage" type="file" cssClass="form:input-large"/>
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
