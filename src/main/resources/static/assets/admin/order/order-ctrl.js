app.controller("order-ctrl",function($scope , $http){
    $scope.order = [];
    $scope.form = {};
    $scope.odt = [];
  
    $scope.initialize = function(){
        //Load order
        $http.get("/rest/orders").then(resp => {
            $scope.order = resp.data;
            $scope.order.forEach(ord =>{
                ord.CreateDate = new Date(ord.CreateDate)
            })
            
        });
    }
    
     $scope.update = function(ord) {
        var ord = angular.copy($scope.form);
        $http.put(`/rest/orders/${ord.order_id}`, ord).then(resp => {
            var index = $scope.order.findIndex(o => o.order_id == ord.order_id);
            $scope.order[index] = ord;
            alert("cap nhap thanh cong");
        }).catch(error => {
            alert("Lỗi cập nhập đơn hàng");
            console.log("Error", error);
        })
    }
    
    
   
    $scope.listdetail =  function(form){
        $http.get(`/rest/orderdetails/${form.order_id}`).then(resp => {
            $scope.odt = resp.data;
        });
    }
    // Khởi đầu
    $scope.initialize();

    //Hiển thị lên form
    $scope.search= function(ord){
        $scope.form = angular.copy(ord);
        $scope.listdetail($scope.form);
        $(".nav-tabs a:eq(0)").tab('show')
        
    }

    $scope.pager = {
        page : 0,
        size  :10,
        get order(){
            var start = this.page * this.size;
            return $scope.order.slice(start , start + this.size); 
        },
        get count(){
            return Math.ceil(1.0*$scope.order.length / this.size);
        },
        first(){
            this.page = 0;
        },

        prev(){
            this.page --;
            if(this.page < 0){
                this.last();
            }
        },
        
        next(){
            this.page ++;
            if(this.page >= this.count){
                this.first();
            }
        },
        last(){
            this.page = this.count - 1;
        }
          
    }
    
    

    
});