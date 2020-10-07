'use strict';

angular.module('myApp').factory('CreditService', ['$http', '$q', function($http, $q){

  var REST_SERVICE_URI = '/employees';

  var factory = {
    creditScore: creditScore,
  };

  return factory;

  function creditScore(user) {
    var deferred = $q.defer();
    
    $http.post('http://localhost:8081/employees/', JSON.stringify(user))
        .then(
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Employee record NOT inserted successfully');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
  }

}]);
