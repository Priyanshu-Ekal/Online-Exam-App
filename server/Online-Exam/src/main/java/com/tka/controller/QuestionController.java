package com.tka.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.DAO.QuestionDAO;
import com.tka.dto.Answer;
import com.tka.entity.Question;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuestionController {
	@Autowired
	QuestionDAO dao;
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("getFirstQuestion/{subject}")
	public Question getFirstQuestion(@PathVariable String subject) 
	{
		List<Question> list = dao.getAllQuestions(subject);
		HttpSession httpSession = LoginController.httpsession;
		httpSession.setAttribute("allquestions", list);
		Question question = list.get(0);
		return question;
	}
	
	@GetMapping("getAllQuestions/{subject}")
	public List<Integer> getAllQuestions(@PathVariable String subject)
	{
		Session session=factory.openSession();
		Query<Integer> query = session.createQuery("select distinct qno from Question where subject=: subject", Integer.class);
		query.setParameter("subject", subject);
		List<Integer> list = query.list();
		return list;
	}
	
	@GetMapping("nextQuestion")
	public Question nextQuestion() 
	{
HttpSession httpsession=LoginController.httpsession;
		
		List<Question> list=(List<Question>) httpsession.getAttribute("allquestions");
				
		int index=(int) httpsession.getAttribute("questionIndex");//2
		
		Question question=null;
		
		if(index<list.size()-1)
		{
			int newindex=index+1;//2
		
			httpsession.setAttribute("questionIndex",newindex);//2
		
			question=list.get(newindex);
		}
		else
		{
			question=list.get(list.size()-1);
		}
		
		return question;
	}
	
	@GetMapping("previousQuestion")
	public Question previousQuestion() 
	{
		HttpSession httpSession = LoginController.httpsession;
		List<Question> list = (List<Question>) httpSession.getAttribute("allquestions");
		int questionindex = (int)httpSession.getAttribute("questionIndex")-1;
//		updating questionindex attribute's value and reading question should be done only if below condition is true
		
		if(questionindex >= 0) {
			httpSession.setAttribute("questionIndex", questionindex);
			return list.get(questionindex);  // get(int index) accepts index and returns object present at that index
		}
		else {
			return null;
		}
	}
	
//	{"qno":1, "question":"what is 2+2?", "submittedAnswer":3, "correctAnswer":4}
	
	@PostMapping("saveAnswer")
	public void saveAnswer(@RequestBody Answer answer)
	{
		HttpSession httpsession=LoginController.httpsession;
		HashMap hashMap = (HashMap) httpsession.getAttribute("submittedDetails");
		hashMap.put(answer.getQno(), answer);
		System.out.println(hashMap);
	}
	
	@GetMapping("calculateScore")
	public int calculateScore()
	{
		HttpSession httpsession=LoginController.httpsession;
		HashMap hashMap = (HashMap) httpsession.getAttribute("submittedDetails");
		Collection<Answer> collection = hashMap.values();
		
		for (Answer answer : collection) 
		{
			if(answer.getCorrectAnswer().equals(answer.getSubmittedAnswer()))
			{
				httpsession.setAttribute("score", (int)httpsession.getAttribute("score")+1);
			}
		}
		return (int)httpsession.getAttribute("score");
	}
	
	@GetMapping("allAnswers")
	public Collection<Answer> allAnswers()
	{
		HttpSession httpsession=LoginController.httpsession;
		HashMap hashMap = (HashMap) httpsession.getAttribute("submittedDetails");
		Collection<Answer> collection = hashMap.values();
		return collection;
	}
	
	@GetMapping("getAllSubjects")
	public List<String> getAllSubjects()
	{
		Session session=factory.openSession();
		Query<String> query = session.createQuery("select distinct subject from Question", String.class);
		List<String> list = query.list();
		return list;
	}
}