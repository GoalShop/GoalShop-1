app.controller("authority-ctrl",function($scope , $http , $location){
   $scope.roles=[];
   $scope.admins = [];
   $scope.authorities = [];

   $scope.initialize = function(){
       // load all roles
       $http.get("/rest/roles").then(resp =>{
           $scope.roles = resp.data;
       })

       //load staffs and directors(administrators)
       $http.get("/rest/accounts").then(resp =>{
           $scope.admins = resp.data;
       })

       //load authorities of staffs and directors
       $http.get("/rest/authorities").then(resp =>{
            $scope.authorities = resp.data;
         }).catch(error =>{
             $location.path("/unauthorized");
         })
   }

   $scope.authority_of = function(acc , role){
       if($scope.authorities){
           return $scope.authorities.find(ur => ur.account.username == acc.username 
                                            && ur.role.role_id == role.role_id);
       }
   }
   $scope.authority_changed = function(acc,role){
       var authority = $scope.authority_of(acc , role);
       if(authority){
           $scope.revoke_authority(authority);
       }
       else{
           authority = {account:acc , role : role};
           $scope.grant_authority(authority);
       }
   }

   //thêm mới authority
   $scope.grant_authority = function(authority){
       $http.post(`/rest/authorities`, authority).then(resp =>{
           $scope.authorities.push(resp.data);
           alert("Cấp quyền sử dụng thành công");
       }).catch(error =>{
           alert("Cấp quyền thất bại");
            console.log("Error" , error);
       })
   }

   //Xoá authority
   $scope.revoke_authority = function(authority){
       $http.delete(`/rest/authorities/${authority.authorize_id}`).then(resp =>{
           var index = $scope.authorities.findIndex(a => a.Authorize_id == authority.authorize_id);
           $scope.authorities.splice(index ,1);
           alert("Thu hồi quyền sử dụng thành công");
       }).catch(error =>{
        alert("Thu hồi quyền sử dụng thất bại");
        console.log("Eror",error );
       })
   }

   $scope.initialize();
    $scope.pager = {
        page: 0,
        size: 10,
        get admins() {
            var start = this.page * this.size;
            return $scope.admins.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1 * $scope.admins.length / this.size);
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