package com.todo.spring.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "TODOAPP")
public class ToDo {
	private int id;
	private String name;
//	private Map<String, Object> data;

	@Id
//	@GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getId() 
	{
		return id;  
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}
	
//	public Map<String, Object> getData() {
//		return data;
//	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
//	public void setData(Map<String, Object> data) {
//		this.data = data;
//	}

}
