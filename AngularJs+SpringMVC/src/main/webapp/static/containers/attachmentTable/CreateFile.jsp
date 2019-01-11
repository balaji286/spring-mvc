<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="lead">Create File Page </span>
            <button type="button" class="close" ng-click="attachCtrl.closeDialogue('create')">&times;</button>
        </div>
        <div class="formcontainer" >
            <form ng-submit="attachCtrl.submit(this)" enctype="multipart/form-data" name="form.createAttachForm" class="form-horizontal">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="file">File Upload</label>
                        <div class="col-md-7">
                            <input type="file" ng-required="true" class="form-control input-sm" file-input="custFile"/>                        
                        </div>
                    </div>
                </div> 
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="file">File Description</label>
                        <div class="col-md-7">
                            <input type="text" valid-file name="fileDesc" ng-model="attachCtrl.attachmentFile.description" ng-required="true" class="form-control input-sm" placeholder="Type something about file..."/>
                            <div class="has-error" ng-show="form.createAttachForm.$dirty">
                                <span ng-show="form.createAttachForm.fileDesc.$error.required">This is a required field</span>
                                <span ng-show="form.createAttachForm.fileDesc.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="floatRight">
                        <input type="submit" ng-disabled="form.createAttachForm.$invalid" class="btn btn-primary btn-sm">
                        <button type="reset" id="createFileReset" ng-disabled="form.createAttachForm.$pristine" ng-click="attachCtrl.reset('create')" class="btn btn-warning btn-sm">Reset Form</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>