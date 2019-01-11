/* global angular */

'use strict';
angular.module('myApp').factory('AttachmentService', ['$http', '$q', '$location', function ($http, $q, $location) {

        var REST_SERVICE_URI = $location.absUrl() + 'files/';

        var factory = {
            createFile: createFile,
            updateFile: updateFile,
            deleteFile: deleteFile
        };

        return factory;

        function blockStatus(formData, attachId, custId) {
            $http.post("http://172.16.13.86:7000/create_attachment", formData, {headers: {'Content-Type': undefined}}).then(
//                    $http.get("http://172.16.13.86:7000/block_status").then(
//                    function (response) {
//                        console.log(response);
//                        if (response.data.req_status === 'Committed') {
//                            return updateFile(formData);
//                        }
//                        return deleteFile(attachId, custId);
//                    }
//            )
                    );
        }

        function createFile(formData, custId) {
            var deferred = $q.defer();
            $http.post(REST_SERVICE_URI + 'create/sample/' + custId).then(
                    function (response) {
                        formData.append('attachId', response.data);
                        for (var pair of formData.entries()) {
                            console.log(pair[0] + ', ' + pair[1]);
                        }
                        blockStatus(formData, response.data, custId);
                        deferred.resolve(response.data);
                    }
            );
            return deferred.promise;
        }

//        function createFile(formData, custId) {
//            var deferred = $q.defer();
//            $http.post(REST_SERVICE_URI + 'create', formData).then(
//                    function (response) {
//                        formData.append('attachId', response.data);
//                        for (var pair of formData.entries()) {
//                            console.log(pair[0] + ', ' + pair[1]);
//                        }
//                        blockStatus(formData, response.data, custId);
//                        deferred.resolve(response.data);
//                    }
//            );
//            return deferred.promise;
//        }

        function updateFile(formData) {
            var deferred = $q.defer();
            $http.put(REST_SERVICE_URI + 'update', formData, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            }).then(
                    function (response) {
                        deferred.resolve(response.data);
                    },
                    function (errResponse) {
                        deferred.reject(errResponse);
                    }
            );
            return deferred.promise;
        }

        function deleteFile(attachId, custId) {
            var deferred = $q.defer();
            $http.delete(REST_SERVICE_URI + 'delete/' + attachId + '/' + custId).then(
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