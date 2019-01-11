/**
 * A Controller which is taken care of AttatchmentService
 */
package com.sample.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.dao.attachmentService.AttachmentServiceDao;
import com.sample.entity.FileAttachment;


@RestController
public class AttachmentController {
    
    // Logger is used to display information (ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF)
    private static final Logger LOGGER  = Logger.getLogger(AttachmentController.class);
    
    @Autowired
    AttachmentServiceDao attachmentService;
    
    //Fetching all files associated with customers
    @GetMapping(value = "/customers/files/{custId}", headers = "Accept=application/json")
    public List<FileAttachment> getCustomerFiles(@PathVariable int custId) {
        LOGGER.info("Getting files for associated customer");
        return attachmentService.getCustomerAttachment(custId);
    }
    
    //Download a file in UI by attachmentId
    @GetMapping(value = "/files/{attachId}")
    public void getFile(@PathVariable int attachId, HttpServletResponse response) throws IOException {        
        FileAttachment attachment = attachmentService.getFileById(attachId);
        LOGGER.info("Getting a file, FileName = "+attachment.getFileName());
        LOGGER.info("Attachment Name = "+attachment.getAttachment_name());
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename="+attachment.getAttachment_name());
        IOUtils.copy(attachment.getAttachment(), response.getOutputStream());
        response.getOutputStream().flush();
    }
    
    // Create new attachment file based on customer
    @PostMapping(value = "/files/create", headers = "Content-Type= multipart/form-data")
    public @ResponseBody List<FileAttachment> createFile(
            @RequestParam(value = "custId") int custId,
            @RequestParam(value = "file", required=true) MultipartFile file,
            @RequestParam(value = "desc") String desc
    ) throws IOException {
        String fileFullName = file.getOriginalFilename();
        String fileName = fileFullName.substring(0, fileFullName.indexOf('.'));
        String fileExtension = fileFullName.substring(fileFullName.indexOf('.')+1);
        FileAttachment attachment = new FileAttachment();
        attachment.setCust_id(custId);
        attachment.setDescription(desc);
        attachment.setFileName(fileName);
        attachment.setFileType(fileExtension);
        attachment.setAttachment(file.getInputStream());
        attachmentService.createFile(attachment);
        LOGGER.info("Successfully created a file, FileName = "+attachment.getFileName());
        return attachmentService.getCustomerAttachment(custId);
    }
    
    // Update the existing file details
    @PutMapping(value = "/files/update", headers = "Content-Type= multipart/form-data")
    public @ResponseBody List<FileAttachment> updateFile(
            @RequestParam(value = "custId") int custId,
            @RequestParam(value = "attachId") int attachId,
            @RequestParam(value = "file", required=true) MultipartFile file,
            @RequestParam(value = "desc") String desc
    ) throws IOException {
        String fileFullName = file.getOriginalFilename();
        String fileName = fileFullName.substring(0, fileFullName.indexOf('.'));
        String fileExtension = fileFullName.substring(fileFullName.indexOf('.')+1);
        FileAttachment attachment = new FileAttachment();
        attachment.setAttachId(attachId);
        attachment.setDescription(desc);
        attachment.setFileName(fileName);
        attachment.setFileType(fileExtension);
        attachment.setAttachment(file.getInputStream());
        attachmentService.updateFile(attachment);
        LOGGER.info("Successfully updated a file, FileName = "+attachment.getFileName());
        return attachmentService.getCustomerAttachment(custId);
    }
    
    // Delete an existing file which is present in attachment table
    @DeleteMapping(value = "/files/delete/{attachId}/{custId}", headers = "Accept=application/json")
    public List<FileAttachment> deleteFile(@PathVariable int attachId, @PathVariable int custId) {
        attachmentService.deleteFile(attachId);
        LOGGER.info("Deleted a File successfully..!");
        return attachmentService.getCustomerAttachment(custId);
    }
    
    @PostMapping(value = "/files/create/sample/{custId}", headers = "Accept=application/json")
    public String createSampleFile(@PathVariable int custId) {
        return attachmentService.createSampleFile(custId);
    }
    
}
