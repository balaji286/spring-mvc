'use strict';

angular.module('myApp').factory('CustomerService', ['$http', '$q', '$location', function ($http, $q, $location) {

        var REST_SERVICE_URI = $location.absUrl() + 'customers/';

        var factory = {
            fetchAllCustomers: fetchAllCustomers,
            fetchcustomerAttachment: fetchcustomerAttachment,
            createCustomer: createCustomer,
            updateCustomer: updateCustomer,
            deleteCustomer: deleteCustomer
        };

        return factory;

        function fetchAllCustomers() {
            var deferred = $q.defer();
            $http.get(REST_SERVICE_URI).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function fetchcustomerAttachment(custId) {
            var deferred = $q.defer();
            $http.get(REST_SERVICE_URI + 'files/' + custId).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function createCustomer(customer) {
            var deferred = $q.defer();
            $http.post(REST_SERVICE_URI + 'create', customer).then(
                    function (response) {
                        alert("create");
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function updateCustomer(id, customer) {
            var deferred = $q.defer();
            $http.put(REST_SERVICE_URI + id, customer).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function deleteCustomer(id) {
            var deferred = $q.defer();
            $http.delete(REST_SERVICE_URI + id).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

    }]);