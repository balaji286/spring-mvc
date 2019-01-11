<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="lead">Edit File Page </span>
            <button type="button" ng-click="attachCtrl.closeDialogue('edit')" class="close" >&times;</button>
        </div>
        <div class="formcontainer" >
            <form ng-submit="attachCtrl.update(this)" enctype="multipart/form-data" name="form.editAttachForm" class="form-horizontal">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="file">File Upload</label>
                        <div class="col-md-7">
                            <input type="file" class="form-control input-sm" file-input="custFile" ng-required="true"/>
                        </div>
                    </div>
                </div>   

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="file">File Description</label>
                        <div class="col-md-7">
                            <input type="text" valid-file name="fileDesc" ng-model="attachCtrl.editAttachmentFile.description" ng-required="true" class="form-control input-sm" placeholder="Type something about file..."/>
                            <div class="has-error" ng-show="form.editcreateAttachForm.$dirty">
                                <span ng-show="form.editAttachForm.fileDesc.$error.required">This is a required field</span>
                                <span ng-show="form.editAttachForm.fileDesc.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="floatRight">
                        <input type="submit" ng-disabled="form.editAttachForm.$invalid" class="btn btn-primary btn-sm">
                        <button type="reset" id="editFileReset" ng-disabled="form.editAttachForm.$pristine" ng-click="attachCtrl.reset('edit')" class="btn btn-warning btn-sm">Reset Form</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>