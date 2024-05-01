package com.tka.controller;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entity.Admin;
import com.tka.entity.Answer;
import com.tka.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class LoginController 
{
		@Autowired
		SessionFactory factory;
		
		static HttpSession httpsession;
		
		//{"username":"x","password":"z"}
		@PostMapping("validate")
		public boolean validate(@RequestBody User userfromBrowser, HttpServletRequest request) {
		    httpsession = request.getSession();
		    Session session = factory.openSession();
		    
		    // Retrieve user from the database based on the username
		    User userfromdb = session.get(User.class, userfromBrowser.getUsername());

		    // Check if userfromdb is null (i.e., no user found with the given username)
		    if (userfromdb == null) {
		        return false;
		    }
		    
		    // Compare the usernames and passwords
		    boolean usernameMatch = userfromBrowser.getUsername().equals(userfromdb.getUsername());
		    boolean passwordMatch = userfromBrowser.getPassword().equals(userfromdb.getPassword());
		    
		    // Return true only if both username and password match
		    if (usernameMatch && passwordMatch) {
		        httpsession.setAttribute("score", 0);
		        httpsession.setAttribute("questionIndex", 0);
		        HashMap<Integer, Answer> hashMap = new HashMap<>();
		        httpsession.setAttribute("submittedDetails", hashMap);
		        return true;
		    } 
		    else
		    	return false;
		}
		
		@PostMapping("validate2")
		public Boolean validate2(@RequestBody Admin userfrombrowser,HttpServletRequest request)
		{
			
			System.out.println("user from browser " + userfrombrowser);
		
			Session session=factory.openSession();
			
			Admin userfromdatabase=session.get(Admin.class,userfrombrowser.getUsername());
			
			System.out.println("user from database " +userfromdatabase);
						
			// this refers to that object which is used to call method
			
			if(userfromdatabase==null)
			{
				return false;
			}
			
			boolean answer=userfrombrowser.equals(userfromdatabase);
					
			System.out.println("answer from equals() of Object class is " + answer);
				
			return answer;
					
		}
		
}