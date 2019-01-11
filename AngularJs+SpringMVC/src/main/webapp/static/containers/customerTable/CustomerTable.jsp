<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="lead">Customer Details </span>
        </div>
        <div class="tablecontainer" >
            <img data-toggle="modal" ng-click="hideBodyOverflow()" class="pointerCursor" data-target="#createCustomer" ng-src="{{ add_cust_image}}">
            <div ng-if="isClickEnabled" style="display: contents">
                <img data-toggle="modal" ng-click="hideBodyOverflow()" class="pointerCursor" data-target="#editCustomer" ng-src="{{ edit_image}}">
                <img ng-confirm-click="Are you sure want to delete?" class="pointerCursor" confirmed-click="ctrl.remove()" ng-src="{{ delete_image}}">
                <img data-toggle="modal" ng-click="hideBodyOverflow()" class="pointerCursor floatRight" data-target="#attachmentTable" ng-src="{{ attachment_image}}">
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Customer Id</th>
                        <th>Customer Name</th>
                        <th>Creation Date</th>
                        <th>Last Updated Date</th>
                        <th>Account Number</th>
                        <th>Created By</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="custRow" 
                        ng-repeat="cust in customers" ng-click="ctrl.attachmentTablePopup(cust.custId, cust.customerName, cust.custAcctNumber, $index)"
                        ng-class="{'selected': $index === selectedRow}"
                        >
                        <td><span>{{$index + 1}}</span></td>
                        <td><span ng-bind="cust.custId"></span></td>
                        <td><span ng-bind="cust.customerName"></span></td>
                        <td><span ng-bind="cust.creationDate"></span></td>
                        <td><span ng-bind="cust.lastUpdateDate"></span></td>
                        <td><span ng-bind="cust.custAcctNumber"></span></td>
                        <td><span ng-bind="cust.createdBy"></span></td>
                    </tr>
                </tbody>
            </table>
            <div class="modal fade" id="createCustomer" role="dialog">
                <div ng-include src="'static/containers/customerTable/CreateCustomer.jsp'"></div>
            </div>
            <div class="modal fade" id="editCustomer" role="dialog">
                <div ng-include src="'static/containers/customerTable/EditCustomer.jsp'"></div>
            </div>
            <div class="modal fade" id="attachmentTable" role="dialog">
                <div ng-include src="'static/containers/attachmentTable/AttachmentTable.jsp'"></div>
            </div>
        </div>
    </div>
</div>
