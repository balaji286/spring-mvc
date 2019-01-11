/*
 * A service class implementation of attachment interface
 */
package com.sample.dao.attachmentService;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.RowMapper;

import com.sample.entity.FileAttachment;
import com.sample.utility.DbUtility;
import java.sql.Timestamp;
import org.springframework.dao.DataAccessException;

@Service("attachmentService") // @Service annotation is to make a class Autowired
public class AttachmentServiceDaoImpl implements AttachmentServiceDao {

    // Getting Database connection from Utility
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(DbUtility.getDataSource());

    // Logger is used to display information (ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF)
    private static final Logger LOGGER = Logger.getLogger(AttachmentServiceDaoImpl.class);

    private static final String FETCH_ALL_ATTACHMENTS_BY_CUST_ID_QUERY
            = "SELECT CUST_ID,ATTACH_ID,DESCRIPTION,FILE_NAME,FILE_TYPE,ATTACHMENT,CREATED_BY,CREATION_DATE,LAST_UPDATE_DATE FROM xxpm_trans_attachment WHERE CUST_ID = ?";
    private static final String FETCH_FILE_BY_ATTACH_ID_QUERY
            = "SELECT FILE_NAME,FILE_TYPE,ATTACHMENT FROM xxpm_trans_attachment WHERE ATTACH_ID = ?";
    // now() is used to set current date with time in mySql
    private static final String INSERT_FILE_QUERY
            = "INSERT INTO xxpm_trans_attachment(CUST_ID,DESCRIPTION,FILE_NAME,FILE_TYPE,ATTACHMENT,CREATION_DATE,LAST_UPDATE_DATE) VALUES (?, ?, ?, ?, ?,now(), now())";
    private static final String UPDATE_FILE_QUERY
            = "UPDATE xxpm_trans_attachment SET DESCRIPTION = ?, FILE_NAME = ?, FILE_TYPE = ?, ATTACHMENT = ?, LAST_UPDATE_DATE = now() WHERE ATTACH_ID = ?";
    private static final String DELETE_FILE_QUERY
            = "DELETE FROM xxpm_trans_attachment WHERE ATTACH_ID = ?";
    private static final String INSERT_SAMPLE_FILE_QUERY
            = "INSERT INTO xxpm_trans_attachment(CUST_ID,DESCRIPTION,FILE_NAME,FILE_TYPE,ATTACHMENT,CREATION_DATE,LAST_UPDATE_DATE) VALUES (?, null, null, null, null, now(), now())";
    
    @Override
    public List<FileAttachment> getCustomerAttachment(int custId) {
        List<FileAttachment> attachments = null;
        try {
            attachments = new ArrayList<FileAttachment>();
            List<Map<String, Object>> rows;
            rows = jdbcTemplate.queryForList(
                    FETCH_ALL_ATTACHMENTS_BY_CUST_ID_QUERY,
                    new Object[]{
                        custId
                    }
            );

            // Setting custom rows mapping to attachment table
            for (Map row : rows) {
                FileAttachment attachment = new FileAttachment();
                attachment.setCust_id((int) row.get("cust_id"));
                attachment.setAttachId((int) row.get("attach_id"));
                attachment.setDescription((String) row.get("description"));
                attachment.setAttachmentName(row.get("file_name").toString() + '.' + row.get("file_type").toString());
                attachment.setFileName((String) row.get("file_name"));
                attachment.setFileType((String) row.get("file_type"));
                attachment.setCreatedBy((String) row.get("created_by"));
                attachment.setCreationDate((Timestamp) row.get("CREATION_DATE"));
                attachment.setLastUpdateDate((Timestamp) row.get("LAST_UPDATE_DATE"));
                attachments.add(attachment);
            }
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while fetching files", exception);
        }
        return attachments;
    }

    // To download a file in UI
    @Override
    public FileAttachment getFileById(int attachId) {
        FileAttachment attachment = null;
        try {
            attachment = (FileAttachment) jdbcTemplate.queryForObject(
                    FETCH_FILE_BY_ATTACH_ID_QUERY,
                    new Object[]{
                        attachId
                    },
                    // Anonymous method for setting custom row using RowMapper interface
                    new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    FileAttachment file = new FileAttachment();
                    file.setFileName(rs.getString("file_name"));
                    file.setFileType(rs.getString("file_type"));
                    file.setAttachmentName(rs.getString("file_name") + '.' + rs.getString("file_type"));
                    file.setAttachment(rs.getBinaryStream("attachment"));
                    return file;
                }
            }
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while get a file", exception);
        }
        return attachment;
    }

    @Override
    public FileAttachment createFile(FileAttachment attachment) {
        try {
            jdbcTemplate.update(
                    INSERT_FILE_QUERY,
                    new Object[]{
                        attachment.getCustId(),
                        attachment.getDescription(),
                        attachment.getFileName(),
                        attachment.getFileType(),
                        attachment.getAttachment()
                    }
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while creating a file", exception);
        }
        return attachment;
    }

    @Override
    public FileAttachment updateFile(FileAttachment attachment) {
        try {
            jdbcTemplate.update(
                    UPDATE_FILE_QUERY,
                    new Object[]{
                        attachment.getDescription(),
                        attachment.getFileName(),
                        attachment.getFileType(),
                        attachment.getAttachment(),
                        attachment.getAttachId()
                    }
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while updating a file", exception);
        }
        return attachment;
    }

    @Override
    public void deleteFile(int attachId) {
        try {
            jdbcTemplate.update(
                    DELETE_FILE_QUERY,
                    attachId
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while deleting a file", exception);
        }
    }

    @Override
    public String createSampleFile(int custId) {
        List<Map<String, Object>> rows = null;
        try {
            jdbcTemplate.update(
                    INSERT_SAMPLE_FILE_QUERY,
                    custId
            );            
            LOGGER.info("Sample File Created successfully");
            rows = jdbcTemplate.queryForList(
                    FETCH_ALL_ATTACHMENTS_BY_CUST_ID_QUERY,
                    new Object[]{
                        custId
                    }
            );            
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while creating a sample file", exception);
        }
        return rows.get(rows.size()-1).get("attach_id").toString();
    }

}
