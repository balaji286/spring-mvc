/**
 * A service interface of Attachment
 */
package com.sample.dao.attachmentService;

import java.util.List;

import com.sample.entity.FileAttachment;

public interface AttachmentServiceDao {
    
    public List<FileAttachment> getCustomerAttachment(int custId); // Fetch all attachments based on customer
    
    public FileAttachment getFileById(int attachId); // Return a file based on attachId to download in UI
    
    public FileAttachment createFile(FileAttachment attachment); // Create a new attachment file for customer
    
    public FileAttachment updateFile(FileAttachment attachment); // Update an existing file in customer
    
    public void deleteFile(int attachId); // Delete an attachment File
    
    public String createSampleFile(int custId);
    
}
