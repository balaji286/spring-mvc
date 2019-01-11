<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="lead">Manage Customer</span>
            <button type="button" ng-click="ctrl.closeDialogue('create')" class="close" >&times;</button>
        </div>
        <div class="formcontainer" >
            <form ng-submit="ctrl.submit()" name="form.createForm" class="form-horizontal">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="file">Customer name</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.customer.customerName" name="customerName" class="form-control input-sm" required placeholder="Enter your name"/>
                            <div class="has-error" ng-show="form.createForm.$dirty">
                                <span ng-show="form.createForm.customerName.$error.required">This is a required field</span>
                            </div>
                        </div>
                    </div>
                </div>   

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="file">Account number</label>
                        <div class="col-md-7">
                            <input type="number" name="custAcctNumber" ng-model="ctrl.customer.custAcctNumber" required ng-minlength="10" class="form-control input-sm" placeholder="Enter your account number"/>
                            <div class="has-error" ng-show="form.createForm.$dirty">
                                <span ng-show="form.createForm.custAcctNumber.$error.required">This is a required field</span>
                                <span ng-show="form.createForm.custAcctNumber.$error.minlength">Minimum length required is 10</span>
                                <span ng-show="form.createForm.custAcctNumber.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="floatRight">
                        <input type="submit" ng-disabled="form.createForm.$invalid" class="btn btn-primary btn-sm" >
                        <button type="reset" id="createReset" ng-disabled="form.createForm.$pristine" class="btn btn-warning btn-sm" ng-click="ctrl.reset('create')">Reset Form</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>