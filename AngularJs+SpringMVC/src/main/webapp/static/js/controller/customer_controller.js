'use strict';

angular.module('myApp').controller('CustomerController', ['$scope', 'CustomerService', function ($scope, CustomerService) {
        $scope.add_cust_image = 'static/images/add_customer.svg';
        $scope.edit_image = 'static/images/edit.svg';
        $scope.delete_image = 'static/images/delete.svg';
        $scope.attachment_image = 'static/images/attachment.svg';
        $scope.download_image = 'static/images/download.svg';
        $scope.add_file_image = 'static/images/add_file.svg';
        $scope.selectedRow = null;
        $scope.selectedCustId = null;
        $scope.form = {};

        var self = this;
        self.customer =
                {
                    customerName: '',
                    custAcctNumber: ''
                };
        self.editCustomer = {customerName: '', custAcctNumber: '', custId: ''};
        self.closeDialogue = closeDialogue;
        self.attachmentTablePopup = attachmentTablePopup;
        self.submit = submit;
        self.update = update;
        self.remove = remove;
        self.reset = reset;

        fetchAllCustomers();

        function closeDialogue(mode) {
            mode === 'create' ? $('#createCustomer').modal('hide') : $('#editCustomer').modal('hide');
            $('body').css('overflow', 'visible');
        }

        function fetchAllCustomers() {
            CustomerService.fetchAllCustomers()
                    .then(
                            function (d) {
                                $scope.customers = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Customers');
                            }
                    );
        }

        function attachmentTablePopup(custId, customerName, custAcctNumber, index) {
            $scope.isClickEnabled = true;
            $scope.form.editForm.$pristine = false; // To make reset button visible
            self.editCustomer = {customerName: customerName, custAcctNumber: custAcctNumber, custId: custId};
            $scope.selectedCustId = custId;
            $scope.selectedRow = index;
            CustomerService.fetchcustomerAttachment(custId)
                    .then(
                            function (d) {
                                $scope.attachments = d;
                            }
                    );
        }

        function submit() {
            CustomerService.createCustomer(self.customer)
                    .then(
                            function (d) {
                                $scope.customers = d;
                                reset('create');
                                closeDialogue('create');
                            });
        }

        function update() {
            CustomerService.updateCustomer(self.editCustomer.custId, self.editCustomer)
                    .then(
                            function (d) {
                                $scope.customers = d;
                                reset('edit');
                                closeDialogue('edit');
                            });
        }

        function remove() {
            CustomerService.deleteCustomer(self.editCustomer.custId)
                    .then(
                            function (d) {
                                $scope.customers = d;
                            });
        }

        $scope.hideBodyOverflow = function () {
            $('body').css('overflow', 'hidden');
        };

        function reset(mode) {
            if (mode === 'create') {
                $scope.form.createForm.$invalid = true; // For Submit Button
                $scope.form.createForm.$setPristine(); // For Reset Button
                document.getElementById("createReset").click();
            } else {
                $scope.form.editForm.$invalid = true; // For Update Button
                $scope.form.editForm.$setPristine(); // For Reset Button
                document.getElementById("editReset").click();
            }

        }

    }]);
