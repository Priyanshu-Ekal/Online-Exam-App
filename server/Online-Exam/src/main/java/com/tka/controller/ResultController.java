package com.tka.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entity.Result;

@RestController
@CrossOrigin("http://localhost:4200")
public class ResultController {

	@Autowired
	SessionFactory factory;
	
//	{"username":"jerry","subject":"java", "score":1}
	
	@RequestMapping("saveResult")
	public void saveResult(@RequestBody Result result)
	{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
			session.persist(result);
		tx.commit();
	}
	
	@RequestMapping("getResults/{subject}/{pageno}")
	public List<Result> getResult(@PathVariable String subject, @PathVariable int pageno)
	{
		Session session = factory.openSession();
		
		Query<Long> query = session.createQuery("select count('subject') from Result where subject=:subject", Long.class);
		query.setParameter("subject", subject);
		Long records = query.uniqueResult();
		
		int pagenumber = 1;
		while(3*pagenumber<records) {
			pagenumber += 1;
		}
		
		int[] indexarray = new int[pagenumber]; //[0,3,6,9] //i=4
		for (int i = 0; i < indexarray.length; i++) { //count=count+3
			indexarray[i] = i*3;
		}
		System.out.println("Number of records are : "+ records);
		
		Query<Result> query2 = session.createQuery("from Result where subject=:subject", Result.class);
		query2.setParameter("subject", subject);
		int startindex = indexarray[pageno-1];
		
		query2.setMaxResults(3);
		query2.setFirstResult(startindex);
		
		List<Result> list = query2.list();
		return list;
	}
}
