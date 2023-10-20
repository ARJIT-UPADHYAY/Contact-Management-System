package com.contact.management.system.response;

import java.util.List;

import com.contact.management.system.entity.Contacts;

public class Response {
	
	String status;
	String description;
	List<Contacts> data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Contacts> getData() {
		return data;
	}
	public void setData(List<Contacts> data) {
		this.data = data;
	}
	
	

}
