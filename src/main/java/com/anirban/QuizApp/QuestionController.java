package com.anirban.QuizApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//This annotation will define that all the HTTP request coming to this base url will trigger 
 //the methods inside this controller class, which means 
@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/allQuestions")//http://localhost:8080/question/allQuestion
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{subject}")// http://localhost:8080/question/category/Math OR http://localhost:8080/History
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String subject) {
		return questionService.getQuestionsByCategory(subject);
		
	}
	
	@PostMapping("/add")//http://localhost:8080/question/add
	public ResponseEntity<String> addNewQuestion(@RequestBody Question question) {
		return questionService.addNewQuestion(question);
	}
	
	@PutMapping("/update/{id}")//http://localhost:8080/update/10 or /12
	public  ResponseEntity<String> updateQuestion(@PathVariable long id, @RequestBody Question question) {
		
		if (id != question.getId()) {
	        return ResponseEntity.badRequest().body("Product ID in path does not match the request body.");
	    }
		
		return new ResponseEntity<>(questionService.updateQuestion(question),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")//http://localhost:8080/delete/10 or /12
	public ResponseEntity<String> deleteQuestion(@PathVariable long id) {
		return questionService.deleteQuestion(id);
	}
	

}
