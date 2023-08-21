package com.entity;
//import java.util.Date;

public class Notice {
	private int id;
    private String content;
    private String createdAt;
	
    public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(String content, String createdAt) {
		super();
		this.content = content;
		this.createdAt = createdAt;
	}
	

	public Notice(int id, String content, String createdAt) {
		super();
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", content=" + content + ", createdAt=" + createdAt + "]";
	}
	
    
    
}
