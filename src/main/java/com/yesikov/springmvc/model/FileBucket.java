package com.yesikov.springmvc.model;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {

	private String name;

	private String content;

	private MultipartFile file;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}