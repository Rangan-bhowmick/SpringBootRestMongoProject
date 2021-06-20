package com.SpringBoot.Rest.mongo.rest.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "db_sequence")

public class DbSequence {
    
	
	@Id
    private String  id;
    private int seq;
    
    public String getId() {
		return id;
	}
	public int getSeq() {
		return seq;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public DbSequence(String id, int seq) {
		super();
		this.id = id;
		this.seq = seq;
	}
	public DbSequence() {
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "DbSequence [id=" + id + ", seq=" + seq + "]";
	}
	
	
}
