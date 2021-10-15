app.controller("category-ctrl", function($scope, $http) {
    $scope.items = [];

    $scope.form = {};

    $scope.initialize = function() {
        $http.get("/rest/categories").then(resp => {
            $scope.items = resp.data;
           
        });
    }
    $scope.initialize();
    $scope.reset = function() {
        $scope.form = {
            
        }
    }
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
       
    }
    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post(`/rest/categories`, item).then(resp => {
            resp.data, createDate = new Date(resp.data.createDate);
            $scope.items.push(resp.data);
            $scope.reset();
            alert("them moi thanh cong");
        }).catch(error => {
            alert("Loi them moi san pham");
            console.log("Error", error);
        })
    }
    $scope.update = function(item) {
        var item = angular.copy($scope.form);
        $http.put(`/rest/categories/${item.category_id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhập thành công");
        }).catch(error => {
            alert("Cập nhập thất bại");
            console.log("Error", error);
        })
    }
    $scope.delete = function(item) {
        $http.delete(`/rest/categories/${item.category_id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("xoa thanh cong");
        }).catch(error => {
            alert("Loi xoa san pham");
            console.log("Error", error);
        })
    }
    $scope.pager = {
        page: 0,
        size: 6,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
});