package fileupload.resources;

import javax.persistence.Column;

import fileupload.model.FileEO;

public class FileRO {
	private Long id;
	private String name;
	private String extension;
	private Long size;
	
	public FileRO(){}
	

	public FileRO(FileEO f){
		id = f.getId();
		name = f.getName();
		extension = f.getExtension();
		size = f.getSize();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
	
}
