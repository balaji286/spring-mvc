/**
 * A Pojo class for FileAttachment table in PropertyLease database(MySql: phpMyAdmin)
 */
package com.sample.entity;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileAttachment {

    private int custId;
    private int attachId;
    private String description;
    private String attachmentName;
    private String fileName;
    private String fileType;
    private InputStream attachment;
    private String createdBy;
    private String creationDate;
    private String lastUpdateDate;

    // Setting out a custom date format in order to display on UI screen
    private Date date;
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/YYYY"); // Eg: 23/04/2004

    public int getCustId() {
        return custId;
    }

    public void setCust_id(int custId) {
        this.custId = custId;
    }

    public int getAttachId() {
        return attachId;
    }

    public void setAttachId(int attachId) {
        this.attachId = attachId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment_name() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public InputStream getAttachment() {
        return attachment;
    }

    public void setAttachment(InputStream attachment) {
        this.attachment = attachment;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        date = new Date(creationDate.getTime());
        this.creationDate = FORMATTER.format(date);
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        date = new Date(lastUpdateDate.getTime());
        this.lastUpdateDate = FORMATTER.format(date);
    }

    /**
     * *********RETURNING FILE_ATTACHMENT DETAIL AS ARRAY**********
     * @return 
     */
    @Override
    public String toString() {
        return "FileAttachment "
                + "[cust_id=" + custId + ", attach_id=" + attachId 
                + ", attachmentname=" + attachmentName + ", description="+ description
                + ", file_name=" + fileName + ", file_type="+ fileType
                + ", created_by=" + createdBy + ", creationDate="+ creationDate
                + ", lastUpdateDate=" + lastUpdateDate
                + ']';
    }

}
