<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDo</title>
    </head>
    <body>
        <div align="center">
	        <h1>ToDo List</h1>
	        <h2><a href="new">New ToDo</a></h2>
	        <h2><a href="deleteAll">Delete All</a></h2>
	        
        	<table border="1">
	        	<th>No</th>
	        	<th>ToDo</th>
	        	<th>Action</th>
	        	
				<c:forEach var="todo" items="${todoList}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${todo.name}</td>
					<td>
						<a href="delete?id=${todo.id}">Delete</a>
					</td>
	        	</tr>
				</c:forEach>	        	
        	</table>
        </div>
    </body>
</html>
