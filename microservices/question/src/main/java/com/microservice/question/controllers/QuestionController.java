package com.microservice.question.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.microservice.question.models.Question;
import com.microservice.question.repository.QuestionRepositoryInterface;

@Controller
public class QuestionController {

	@Autowired
	private QuestionRepositoryInterface repo;
	
	@RequestMapping(value="/registerQuestion", method = RequestMethod.GET)
	public String form() {
		return "registerQuestion";
	}
	
	@RequestMapping(value="/registerQuestion", method = RequestMethod.POST)
	public String form(Question question) {
		question.setIdUser(0);
		question.setDate(new Date(System.currentTimeMillis()).toString());
		question.setScore(0);
		question.setNumberAnswer(0);
		repo.save(question);
		return "redirect:/registerQuestion";
	}
	
	@RequestMapping(value="/searchQuestion")
	public String search () {
		return "searchQuestion";
	}
	
	@RequestMapping(value="/searchQuestion", method = RequestMethod.GET)
	public ModelAndView search (String tag) {
		ModelAndView mv = new ModelAndView("searchQuestion");
		List<Question> questions = repo.findByQuestions(tag);
		mv.addObject("questions", questions);
		return mv;
	}
	
	@RequestMapping("/questions")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("listQuestion");
		Iterable<Question> questions = repo.findAll();
		mv.addObject("questions", questions);
		return mv;
	}
	
}
