app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/assets/admin1/product/index.html",
            controller: "product-ctrl"
        })
       
        .when("/category", {
            templateUrl: "/assets/admin1/category/index.html",
            controller: "category-ctrl"
        })
       
        .when("/order", {
            templateUrl: "/assets/admin1/order/index.html",
            controller: "order-ctrl"
        })
        .when("/trademark", {
            templateUrl: "/assets/admin1/trademark/index.html",
            controller: "trademark-ctrl"
        })
     
        .otherwise({
           
             templateUrl: "/assets/admin1/layout/index.html",
        });
})