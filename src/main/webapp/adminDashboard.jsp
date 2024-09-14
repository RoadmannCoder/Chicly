<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/adminDashboard.css">
</head>
<body>
<div class="d-flex" id="wrapper">
    <div class="bg-black border-right" id="sidebar-wrapper">
        <div class="sidebar-heading text-white"><img src="img/admin-logo.png" width="130" height="130" alt=""></div>
        <div class="list-group list-group-flush">
            <a href="addCategory" class="list-group-item list-group-item-action bg-black text-white">Add Category</a>
            <a href="addSubCategory" class="list-group-item list-group-item-action bg-black text-white">Add SubCategory</a>
            <a href="addProduct" class="list-group-item list-group-item-action bg-black text-white">Manage Products</a>
            <a href="productView" class="list-group-item list-group-item-action bg-black text-white">Manage SubProducts</a>
            <a href="customerView" class="list-group-item list-group-item-action bg-black text-white">Customer Profiles</a>
        </div>
    </div>

    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-white border-bottom">
            <div class="container-fluid d-flex justify-content-between align-items-center">
                <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>
                <div class="ml-auto">
                    <img src="img/logo.png" width="130" height="70" alt="Project Logo"> <!-- Aligned to the right -->
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <h1 class="mt-4">Welcome, Admin</h1>
            <p>Use the sidebar to manage the website.</p>

            <div class="row">
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Product Statistics</h5>
                            <p>Total Categories: 5</p>
                            <p>Total Products: 120</p>
                            <p>Low Stock Alerts: 3</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Customer Statistics</h5>
                            <p>Total Customers: 350</p>
                            <p>New Customers (Last 7 Days): 15</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Orders</h5>
                            <p>Orders in Progress: 12</p>
                            <p>Completed Orders: 200</p>
                        </div>
                    </div>
                </div>
            </div>

            <h4>Notifications</h4>
            <div class="alert alert-warning" role="alert">
                Low Stock Alert: Black Hoodie (5 units remaining).
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
</body>
</html>
