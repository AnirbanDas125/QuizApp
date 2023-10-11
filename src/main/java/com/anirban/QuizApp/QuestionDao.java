package com.anirban.QuizApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question,Long> {
	
	public List<Question> findByCategory(String subject);
//If i don't find a suitable method I can design my custom method in here.
//Here there was no method that would filter out questions by category, So I made one.	

    @Query(value = "SELECT * FROM question q Where q.category= :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery=true)
	public List<Question> findRandomQuestionByCategory(String category, int numQ);
/*JpaRepository will automatically create method for us to  an extend, if we want something complex we can create a method and 
 write a query according to which it will fetch the data. For that we are using @Query annotation and Writing the query */
    
}
