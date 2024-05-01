package com.tka.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entity.User;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController 
{
		@Autowired
		SessionFactory factory;
		// {"username":"mangesh","password":"java","mobno":1234,"emailid":"a@hh"}
		// @RequestBody annonation will apply JSON data to java object
		// Transient object is that object which is not from DB
		// Persistent object is that object which is from DB
		
		@PostMapping("saveUser")
		public void saveUser(@RequestBody User user)
		{
			Session session=factory.openSession();
			// session==> [save() , update()] Session object
			Transaction tx=session.beginTransaction();
					session.persist(user);
			tx.commit();
		}
		@PutMapping("updateUser")
		public void updateUser(@RequestBody User user)
		{
			Session session=factory.openSession();	
			// session==> [save() , update()] Session object
			Transaction tx=session.beginTransaction();
					session.merge(user);
			tx.commit();
		}
		
		// If method is mapped with @DeleteMapping , then client must use delete request method .
		
		@DeleteMapping("deleteUser/{username}")
		public void deleteUser(@PathVariable String username)
		{
			Session session=factory.openSession();
			// session==> [save() , update()] Session object
			User userfromdb=session.get(User.class,username);
			Transaction tx=session.beginTransaction();
					session.remove(userfromdb);
			tx.commit();
		}

		// localhost:8080/getUser/momin
		
		@GetMapping("getUser/{username}")
		public User getUser(@PathVariable String username)
		{
			Session session=factory.openSession();
			// session==> [save() , update()] Session object
			User userfromdb=session.get(User.class,username);
			return userfromdb;
		}
		
		// client can call below method using any http Request method (get/post/put/delete)
		
		@RequestMapping("getAllUsers")
		public List<User> getAllUsers()
		{
			Session session=factory.openSession();
			Query query=session.createQuery("from User");
			return query.list();
		}
}
