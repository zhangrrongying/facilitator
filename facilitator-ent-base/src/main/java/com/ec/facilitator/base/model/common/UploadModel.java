package com.ec.facilitator.base.model.common;

public class UploadModel {
	private boolean success;
	private String url;
	private String message;

	private boolean deleted;
	private String filename;

	private String fakeFilename;
	private long id;
	
	private String imageName;

	public String getFakeFilename() {
		return fakeFilename;
	}

	public void setFakeFilename(String fakeFilename) {
		this.fakeFilename = fakeFilename;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isSuccess() {
		return success;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
