package com.anirban.QuizApp;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String quiz_title;
	@ManyToMany
	private List<Question> questions;

	public Quiz() {
		//Empty constructor is needed  
	}
	
	public Quiz(int id, String quiz_title, List<Question> questions) {
		super();
		this.id = id;
		this.quiz_title = quiz_title;
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public String getQuiz_title() {
		return quiz_title;
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setQuiz_title(String quiz_title) {
		this.quiz_title = quiz_title;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", quiz_title=" + quiz_title + ", questions=" + questions + "]";
	}
	
	
	
	

}
