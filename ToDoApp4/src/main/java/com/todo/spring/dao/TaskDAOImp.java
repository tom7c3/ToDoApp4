package com.todo.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todo.spring.model.ToDo;

  
@Repository
public class TaskDAOImp implements TaskDAO 
{
//	@PersistenceContext
//	private EntityManager em;
	
//	public static EntityManager entityManager = Persistence.createEntityManagerFactory("devsample")
//            .createEntityManager();
 
	
	@Autowired
	private SessionFactory sessionFactory;

	public TaskDAOImp()
	{
		
	}
	
	public TaskDAOImp(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<ToDo> getList()
	{
		@SuppressWarnings("unchecked")
		List<ToDo> listToDo = (List<ToDo>) sessionFactory.getCurrentSession()
				.createCriteria(ToDo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		for ( int i= 0; i < listToDo.size(); ++i )
		{
			System.out.println(listToDo.get(i).getId() + " " + listToDo.get(i).getName());
		}

		return listToDo;
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(ToDo user) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void delete(int id) 
	{
		System.out.println("delete( int id) : " + id);
		
		ToDo todo = (ToDo)sessionFactory.getCurrentSession().get( ToDo.class, id);
		System.out.println(todo.getName());
		
//		ToDo userToDelete = new ToDo();
//		userToDelete.setId(id);
		
//		sessionFactory.getCurrentSession().delete(userToDelete);
		sessionFactory.getCurrentSession().delete(todo);
	}
	
	@Override
	@Transactional
	public void deleteAll()
	{	
		System.out.println("deleteAll");
		
//		Query query = sessionFactory.getCurrentSession().createQuery("select count(id) FROM ToDo id");
//		Query query = sessionFactory.getCurrentSession().createQuery("select id FROM ToDo");
		Query query = sessionFactory.getCurrentSession().createQuery("delete FROM ToDo");
		query.executeUpdate();
//		@SuppressWarnings("unchecked")
//		List<ToDo> list = (List<ToDo>)query.list();
//        System.out.println("Size: " + list.size() );
//        
//        //delete(34);
//        
//        for ( int i = 0; i < list.size(); ++i )
//        {
//        	ToDo todo = new ToDo();
//        	todo.
//        	ToDo todo = list.get(i);
////        	todo.getName();
////        	System.out.println(list.get(i).getId());
//        	//delete(i);
//        }
        
//		
//		Session session = sessionFactory.getCurrentSession().getT;
//		session.beginTransaction();
//		System.out.println("begin");
//		session.createQuery("select * from todoapp");
//		System.out.println("query");
//		session.getTransaction().commit();
//		System.out.println("commit");
//		
//		String task = "SELECT * from todoapp";
//		System.out.println("task");
//		sessionFactory.getCurrentSession().beginTransaction();
//		
//		Query query = sessionFactory.getCurrentSession().createQuery(task);
//		System.out.println("query");
//		@SuppressWarnings("unchecked")
//		List<ToDo> listUser = (List<ToDo>) query.list();
//		
//		System.out.println("Size: " + listUser.size() );
		
//		if (listUser != null && !listUser.isEmpty()) 
//		{
//			return listUser.get(0);
//		}
	}
 
//	@Override
//	@Transactional
//	public ToDo get(int id) 
//	{
//		System.out.println("get(int id)");
//		
//		String hql = "from User where id=" + id;
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
//		
//		@SuppressWarnings("unchecked")
//		List<ToDo> listUser = (List<ToDo>) query.list();
//		
//		if (listUser != null && !listUser.isEmpty()) 
//		{
//			System.out.println(listUser.get(0).getName());
//			return listUser.get(0);
//		}
//		
//		return null;
//	}

}










