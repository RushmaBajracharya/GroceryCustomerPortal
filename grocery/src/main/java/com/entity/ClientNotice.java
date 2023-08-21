package com.entity;

public class ClientNotice {
		private int id;
	    private String content;
	    private String createdAt;
		
	    public  ClientNotice() {
			super();
			// TODO Auto-generated constructor stub
		}

		public  ClientNotice(String content, String createdAt) {
			super();
			this.content = content;
			this.createdAt = createdAt;
		}
		

		public  ClientNotice(int id, String content, String createdAt) {
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
