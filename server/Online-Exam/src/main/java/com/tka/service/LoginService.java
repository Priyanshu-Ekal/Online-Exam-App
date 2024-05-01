package com.tka.service;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tka.DAO.LoginDAO;
import com.tka.entity.User;

@Service
public class LoginService {

	@Autowired
	LoginDAO dao;
	
	public boolean validate(User user)
	{
		String dbpassword = dao.getPasswordFromDataBase(user.getUsername());
		
		if(dbpassword==null)
			return false;
		else if(dbpassword.equals(user.getPassword()))
			return true;
		else
			return false;
	}
}