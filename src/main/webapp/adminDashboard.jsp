<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/adminDashboard.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="#">Products</a></li>
            <li><a href="#">Customers</a></li>
            <li><a href="#">Orders</a></li>
            <li><a href="#">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h1>Admin Dashboard</h1>
    <section id="product-management">
        <h2>Product Management</h2>
        <table>
            <thead>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <a href="#" class="edit-btn">Edit</a>
                        <a href="#" class="delete-btn">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button class="add-btn">Add Product</button>
    </section>
    <section id="customer-management">
        <h2>Customer Management</h2>
        <table>
            <thead>
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.email}</td>
                    <td>${customer.address}</td>
                    <td>
                        <a href="#" class="edit-btn">Edit</a>
                        <a href="#" class="delete-btn">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button class="add-btn">Add Customer</button>
    </section>
    <section id="order-management">
        <h2>Order Management</h2>
        <table>
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Customer ID</th>
                <th>Order Date</th>
                <th>Total</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.customerId}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.total}</td>
                    <td>${order.status}</td>
                    <td>
                        <a href="#" class="view-details-btn">View Details</a>
                        <a href="#" class="update-status-btn">Update Status</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button class="add-btn">Add Order</button>
    </section>
</main>
<footer>
    <p>&copy; 2023 Your Company</p>
</footer>
</body>
</html>