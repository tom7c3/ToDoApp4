  package com.todo.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.todo.spring.dao.TaskDAO;
import com.todo.spring.model.ToDo;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class MyController 
{
	@Autowired
	private TaskDAO userDao;

	@RequestMapping("/")
	public ModelAndView getList() throws Exception 
	{
		List<ToDo> todoList = userDao.getList();
		ModelAndView model = new ModelAndView("ToDoList");
		model.addObject("todoList", todoList);
		return model;
	}

	@RequestMapping("/json")
	@ResponseBody
	public List<ToDo> getListAsJson() throws Exception 
	{
		return userDao.getList();
	}
	

	@RequestMapping(value = "/json2", method = RequestMethod.GET)
	@ResponseBody
	public String getListAsJson2() throws Exception 
	{
	    JSONArray userArray = new JSONArray();
	    
	    List<ToDo> todo = userDao.getList();
	    for ( int i = 0; i < todo.size(); ++i )
	    {
	    	JSONObject userJSON = new JSONObject();
	    	userJSON.put("id", todo.get(i));
	    	userArray.put(userJSON);
	    }
	    
	    return userArray.toString();
	    
//	    for (User user : userRepository.findAll()) {
//	        JSONObject userJSON = new JSONObject();
//	        userJSON.put("id", user.getId());
//	        userJSON.put("firstName", user.getFirstName());
//	        userJSON.put("lastName", user.getLastName());
//	        userJSON.put("email", user.getEmail());
//	        userArray.put(userJSON);
//	    }
//	    return userArray.toString();
	}
	
	@RequestMapping("/json/set")
	@ResponseBody
	public void setJson( String json )
	{
		
	}

	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUser() 
	{
		ModelAndView model = new ModelAndView("ToDoForm");
		model.addObject("todo", new ToDo());
		return model;		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) 
	{
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		return new ModelAndView("redirect:/");		
	}
	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	public ModelAndView deleteAll(HttpServletRequest request) 
	{
		userDao.deleteAll();
		return new ModelAndView("redirect:/");		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute ToDo user) 
	{
		userDao.saveOrUpdate(user);
		return new ModelAndView("redirect:/");
	}
	
}
