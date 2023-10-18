<%@ page import="java.util.List" %>
<%@ page import="models.Product" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 17/10/2023
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/confirm-dialog.js"></script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            <% String username = (String) request.getAttribute("username"); %>
            <% if(username != null)
            { %>
            Xin chào <span class="text-danger">
                <%= username %>
                </span> | <a href="#">Logout</a>
            <% }
            %>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" action = "product">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="price">
                </div>
                <div class="form-group">
                    <button type = "submit" class="btn btn-success mr-2">Thêm sản phẩm</button>
                </div>
            </form>

            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>

            <% if(errorMessage != null)
            { %>
            <div class="alert alert-danger alert-dismissible fade show">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <%= errorMessage %>
            </div>
            <% }
            %>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <% if(errorMessage == null)
                { %>
                    <tbody>
                    <% List<Product> products = (List<Product>) request.getAttribute("products");
                        Integer stt = 0;%>
                    <% for (Product product:products) {
                    %>
                    <tr>
                        <td> <%= stt+1 %> </td>
                        <td> <%= product.getName() %> </td>
                        <td> <%= product.getPrice() %> </td>
                        <td>
                            <a href="#">Edit</a> |
                            <form action="product" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="<%= product.getId() %>">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" class="btn btn-danger delete-product">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <%= stt= stt + 1 %>
                    <%} %>
                    </tbody>
                <% }
                %>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
