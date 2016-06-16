package cl.subdere.componente.negocio.util;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class FileLoaderDto {

	private MultipartFormDataInput filedata;
	private String filename;
    private String filepath;
    private String contenttype;
    private String description;
    private String siteid;
    private String containerid;
    private String uploaddir;
    private boolean overwrite;
    private boolean thumbnails;
    
    
    public MultipartFormDataInput getFiledata() {
        return filedata;
    }

    public void setFiledata(MultipartFormDataInput filedata) {
        this.filedata = filedata;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getContainerid() {
        return containerid;
    }

    public void setContainerid(String containerid) {
        this.containerid = containerid;
    }

    public String getUploaddir() {
        return uploaddir;
    }

    public void setUploaddir(String uploaddir) {
        this.uploaddir = uploaddir;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public boolean isThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(boolean thumbnails) {
        this.thumbnails = thumbnails;
    }

    
}
