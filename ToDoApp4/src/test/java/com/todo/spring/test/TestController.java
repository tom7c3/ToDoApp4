package com.todo.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.todo.spring.controller.MyController;
import com.todo.spring.dao.TaskDAO;
import com.todo.spring.model.ToDo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
 
import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
public class TestController 
{
	private MockMvc mockMvc;
	
	@Autowired
    private TaskDAO todoServiceMock;
	
	@Test
	public void shouldAddNewObject() throws Exception
	{
		ToDo first = new ToDo();
		ToDo second = new ToDo();
		
		when(todoServiceMock.getList()).thenReturn(Arrays.asList(first, second));
		
		mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("ToDoList"))
        .andExpect(forwardedUrl("/WEB-INF/views/ToDoList.jsp"))
        .andExpect(model().attribute("todos", hasSize(2)))
        .andExpect(model().attribute("todos", hasItem(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("name", is("Lorem ipsum"))
                )
        )))
        .andExpect(model().attribute("todos", hasItem(
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("name", is("Lorem ipsum"))
                )
        )));

		verify(todoServiceMock, times(1)).getList();
		verifyNoMoreInteractions(todoServiceMock);
	}
}


















