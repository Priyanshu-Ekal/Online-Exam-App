package com.tka.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.entity.Question;

@Repository
public class QuestionDAO {
	@Autowired
	SessionFactory factory;
	
	public List<Question> getAllQuestions(String subject){
		Session session = factory.openSession();
		Query query=session.createQuery("from Question where subject=:subject");
		query.setParameter("subject", subject);
		List<Question> list = query.list();
		return list;
	}
}
