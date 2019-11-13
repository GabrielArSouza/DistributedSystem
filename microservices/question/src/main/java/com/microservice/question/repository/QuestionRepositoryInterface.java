package com.microservice.question.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.question.models.Question;

@Repository
public interface QuestionRepositoryInterface extends JpaRepository<Question, Integer> {

	@Query("SELECT q FROM questions q WHERE q.title LIKE CONCAT('%',?1,'%')")
	List<Question> findByQuestions(String term);
	
	@Query("SELECT q FROM questions q WHERE q.title LIKE CONCAT('%','teste','%')")
	List<Question> findByQuestions();
	
}
