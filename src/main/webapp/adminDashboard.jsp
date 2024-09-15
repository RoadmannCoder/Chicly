<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="admin-dashboard/css/adminDashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Chart.js CDN -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="d-flex" id="wrapper">
    <!-- Sidebar -->
    <div class="bg-black border-right" id="sidebar-wrapper">
        <div class="sidebar-heading text-white"><img src="img/admin-logo.png" width="130" height="130" alt=""></div>
        <div class="list-group list-group-flush">
            <a href="addCategory" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-list"></i> Add Category
            </a>
            <a href="addSubCategory" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-tags"></i> Add SubCategory
            </a>
            <a href="addProduct" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-boxes"></i> Manage Products
            </a>
            <a href="productView" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-cubes"></i> Manage SubProducts
            </a>
            <a href="customerView" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-users"></i> Customer Profiles
            </a>
            <a href="adminlogout" class="list-group-item list-group-item-action bg-black text-white">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </div>
    </div>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-white border-bottom">
            <div class="container-fluid d-flex justify-content-between align-items-center">
                <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>
                <div class="ml-auto">
                    <img src="img/logo.png" width="100" height="50" alt="Project Logo">
                    <p>Admin ${sessionScope.adminName}</p>
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

            <!-- Sales Data Chart -->
            <div class="row">
                <div class="col-md-12">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">Sales Data</h5>
                            <canvas id="productsChart" width="400" height="300"></canvas>
                            <canvas id="subProductsChart" width="400" height="300"></canvas>
                            <canvas id="customersChart" width="400" height="300"></canvas>
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

<!-- Chart.js Script for Sales Data -->
<script>
    // Get the data from the server-side (JSP request attributes)
    const totalProducts = ${totalProducts};
    const totalSubProducts = ${totalSubProducts};
    const totalCustomers = ${totalCustomers};

    // Products Chart
    var ctx1 = document.getElementById('productsChart').getContext('2d');
    var productsChart = new Chart(ctx1, {
        type: 'bar',
        data: {
            labels: ['Products'],
            datasets: [{
                label: 'Total Products',
                data: [totalProducts],
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
            }]
        }
    });

    // SubProducts Chart
    var ctx2 = document.getElementById('subProductsChart').getContext('2d');
    var subProductsChart = new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: ['SubProducts'],
            datasets: [{
                label: 'Total SubProducts',
                data: [totalSubProducts],
                backgroundColor: 'rgba(75, 192, 192, 0.6)',
            }]
        }
    });

    // Customers Chart
    var ctx3 = document.getElementById('customersChart').getContext('2d');
    var customersChart = new Chart(ctx3, {
        type: 'bar',
        data: {
            labels: ['Customers'],
            datasets: [{
                label: 'Total Customers',
                data: [totalCustomers],
                backgroundColor: 'rgba(153, 102, 255, 0.6)',
            }]
        }
    });
    // Toggle Menu Script
    document.getElementById("menu-toggle").addEventListener("click", function(e) {
        e.preventDefault();
        document.getElementById("wrapper").classList.toggle("toggled");
    });
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
