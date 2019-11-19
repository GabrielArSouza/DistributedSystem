package com.microservice.question.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.question.models.Question;
import com.microservice.question.repository.QuestionRepositoryInterface;

@RestController
public class QuestionRESTController {

	@Autowired
	private QuestionRepositoryInterface repo;
	
    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> GetById(@PathVariable(value = "id") long id)
    {
        List<Question> questions = repo.findQuestionByUserId(id);
        if(questions != null)
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
