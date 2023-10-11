package com.anirban.QuizApp;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anirban.QuizApp.Question;
import com.anirban.QuizApp.QuestionDao;
//This is a extra Service layer for a spring boot web application, This extra layer performs extra business logic to data  
@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
//We are using this ResponseEntity class to handle error response, we are wrapping our List response and then sending it to the controller  	
	public ResponseEntity<List<Question>> getAllQuestions(){
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}		
	}
//Also we are using try catch() to show different status code in case bad response or bad request or bad gateway etc. that we can customize		

	public ResponseEntity<List<Question>> getQuestionsByCategory(String subject) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(subject),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_GATEWAY);
		}
		
		
	}

	public ResponseEntity<String> addNewQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("New Iteam Created",HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Body could not be added");
		}	
	}

	public String updateQuestion(Question question) {
		try {
		 questionDao.save(question);
		 return "Successfully Updated";	
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public ResponseEntity<String> deleteQuestion(long id) {
		
		try {
			if(questionDao.existsById(id)) {
				questionDao.deleteById(id);
				return new ResponseEntity<>("Question deleted of ID "+id,HttpStatus.OK);	
			}else {
				return ResponseEntity.badRequest().body("Question does not exist by ID "+id);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Could not delete, Didn't found the ID "+id);
		}
		
	}
	
	

}
