app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/assets/admin/product/index.html",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/assets/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/category", {
            templateUrl: "/assets/admin/category/index.html",
            controller: "category-ctrl"
        })
         .when("/account", {
            templateUrl: "/assets/admin/account/index.html",
            controller: "account-ctrl"
        })
        .when("/order", {
            templateUrl: "/assets/admin/order/index.html",
            controller: "order-ctrl"
        })
        .when("/trademark", {
            templateUrl: "/assets/admin/trademark/index.html",
            controller: "trademark-ctrl"
        })
        .when("/char", {
            templateUrl: "/assets/admin/char/index.html",

        })
        .when("/char2", {
            templateUrl: "/assets/admin/char2/index.html",

        })
         .when("/char3", {
            templateUrl: "/assets/admin/char3/index.html",

        })
         .when("/char4", {
            templateUrl: "/assets/admin/char4/index.html",

        })
        .otherwise({
            template: "<h1 class='text-center'>GoalShop</h1>"
        });
})