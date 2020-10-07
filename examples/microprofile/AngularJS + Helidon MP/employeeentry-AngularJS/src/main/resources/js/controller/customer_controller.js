'use strict';

angular.module('myApp').controller('CreditController', ['$scope', 'CreditService', function($scope, CreditService) {
  var self = this;
  self.user={id:"",name:"",role:"", response:""};
  self.response='';

  self.submit = submit;
  self.reset = reset;


  function creditScore(user){ 
    CreditService.creditScore(user)
      .then(
        function(d) {
          self.user.response = "Employee record inserted successfully";
          //console.error(self.user.response);
        },
        function(errResponse){
          self.user.response = "Employee record could not be inserted";
          //console.error('Employee record could not be inserted');
        }
      );
  }

  function submit() {
    console.log('Getting ', self.user);
     creditScore(self.user);
     reset();
  }


  function reset(){
    self.user={id:'',name:'',role:'', response:''};
    $scope.myForm.$setPristine(); //reset Form
  }

}]);
