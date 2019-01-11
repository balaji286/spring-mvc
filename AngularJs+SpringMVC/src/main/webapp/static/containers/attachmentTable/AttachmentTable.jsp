<div class="generic-container" ng-controller="AttachmentController as attachCtrl">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="lead">Attachment Detail Form </span>
            <button type="button" class="close" ng-click="attachCtrl.closeDialogue('')">&times;</button>
        </div>
        <div class="tablecontainer" >
            <img data-toggle="modal" data-target="#createFile" ng-src="{{ add_file_image}}">
            <img data-toggle="modal" data-target="#editFile" ng-src="{{ edit_image}}">
            <img ng-confirm-click="Are you sure want to delete?" confirmed-click="attachCtrl.remove()" ng-src="{{ delete_image}}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Customer Id</th>
                        <th>Attachment Id</th>
                        <th>Attachment Name</th>
                        <th>Description</th>
                        <th>File Name</th>
                        <th>File Type</th>
                        <th>Creation Date</th>
                        <th>Last Updated Date</th>
                        <th>Created By</th>
                        <th>Download</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="custRow" 
                        ng-click="attachCtrl.createAttachmentPopup(file.attachId, file.description, $index)"
                        ng-class="{'selected': $index === attachCtrl.selectedRow}"
                        ng-repeat="file in attachments"
                        >
                        <td><span>{{$index + 1}}</span></td>
                        <td><span ng-bind="file.custId"></span></td>
                        <td><span ng-bind="file.attachId"></span></td>
                        <td><span ng-bind="file.attachment_name"></span></td>
                        <td><span ng-bind="file.description"></span></td>
                        <td><span ng-bind="file.fileName"></span></td>
                        <td><span ng-bind="file.fileType"></span></td>
                        <td><span ng-bind="file.creationDate"></span></td>
                        <td><span ng-bind="file.lastUpdateDate"></span></td>
                        <td><span ng-bind="file.createdBy"></span></td>
                        <td><a ng-href="{{ file_url+file.attachId }}"><img ng-src="{{ download_image}}"></a></td>
                    </tr>
                </tbody>
            </table>
            <div class="modal fade" id="createFile" role="dialog">
                <div ng-include src="'static/containers/attachmentTable/CreateFile.jsp'"></div>
            </div>
            <div class="modal fade" id="editFile" role="dialog">
                <div ng-include src="'static/containers/attachmentTable/EditFile.jsp'"></div>
            </div>
        </div>
    </div>
</div>