package com.todo.spring.dao;

import java.util.List;

import com.todo.spring.model.ToDo;
 
public interface TaskDAO 
{
	public List<ToDo> getList();
//	public ToDo get(int id);
	public void saveOrUpdate(ToDo user);
	public void delete(int id);
	public void deleteAll();
}   