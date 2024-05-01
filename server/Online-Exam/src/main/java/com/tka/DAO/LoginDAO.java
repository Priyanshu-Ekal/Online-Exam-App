package com.tka.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {
	
	@Autowired
	SessionFactory factory;
	
	public String getPasswordFromDataBase(String usernameFromBrowser)
	{
		Session session=factory.openSession();
		
		Query query=session.createQuery("select password from User where username=:username");
				
		//    password  
		//       y        
		//    RESULTSET
		
		query.setParameter("username",usernameFromBrowser);
		
		String dbpassword = (String) query.uniqueResult();
		
		return dbpassword;
	}
}