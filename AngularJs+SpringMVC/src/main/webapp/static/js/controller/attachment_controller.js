/* global angular */

'use strict';
angular.module('myApp').controller('AttachmentController', ['$scope', '$location', 'AttachmentService', 'CustomerService', function ($scope, $location, AttachmentService, CustomerService) {
        var self = this;
        self.selectedRow = '';
        self.closeDialogue = closeDialogue;
        self.createAttachmentPopup = createAttachmentPopup;
        self.attachmentFile = {description: ''};
        self.editAttachmentFile = {description: '', attachId: ''};
        self.submit = submit;
        self.update = update;
        self.remove = remove;
        self.reset = reset;

        $scope.file_url = $location.absUrl() + 'files/';

        function createAttachmentPopup(attachId, desc, index) {
            $scope.form.editAttachForm.$pristine = false; // To make reset button visible
            self.editAttachmentFile = {description: desc, attachId: attachId};
            $scope.selectedAttachId = attachId;
            self.selectedRow = index;
        }

        function closeDialogue(mode) {
            if (mode === '') {
                $('#attachmentTable').modal('hide');
                $('body').css('overflow', 'visible');
            }
            mode === 'create' ? $('#createFile').modal('hide') : $('#editFile').modal('hide');
        }

        function submit(element) {
            var fd = new FormData();
            fd.append('custId', $scope.selectedCustId);
            fd.append('file', element.custFile);
            fd.append('desc', self.attachmentFile.description);
            AttachmentService.createFile(fd, $scope.selectedCustId).then(
                    function (response) {
                        CustomerService.fetchcustomerAttachment($scope.selectedCustId)
                                .then(
                                        function (d) {
                                            console.log('finally');
                                            $scope.attachments = d;
                                            reset('create');
                                            closeDialogue('create');
                                        }
                                );
                    }
            );
        }

        function update(element) {
            var editForm = new FormData();
            editForm.append('file', element.custFile);
            editForm.append('desc', self.editAttachmentFile.description);
            editForm.append('attachId', self.editAttachmentFile.attachId);
            editForm.append('custId', $scope.selectedCustId);
            AttachmentService.updateFile(editForm)
                    .then(
                            function (d) {
                                $scope.attachments = d;
                                reset('edit');
                                closeDialogue('edit');
                            });
        }

        function remove() {
            AttachmentService.deleteFile($scope.selectedAttachId, $scope.selectedCustId)
                    .then(
                            function (d) {
                                $scope.attachments = d;
                            });
        }

        function reset(mode) {
            if (mode === 'create') {
                $scope.form.createAttachForm.$invalid = true; // For Submit Button
                $scope.form.createAttachForm.$setPristine(); // For Reset Button
                document.getElementById("createFileReset").click();
            } else {
                $scope.form.editAttachForm.$invalid = true; // For Update Button
                $scope.form.editAttachForm.$setPristine(); // For Reset Button
                document.getElementById("editFileReset").click();
            }
        }

    }]);
