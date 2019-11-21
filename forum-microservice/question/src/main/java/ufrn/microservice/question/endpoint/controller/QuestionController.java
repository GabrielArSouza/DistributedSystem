package ufrn.microservice.question.endpoint.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufrn.microservice.core.model.Question;
import ufrn.microservice.question.endpoint.service.QuestionService;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequestMapping("v1/adm/question")
@RestController
public class QuestionController {
    @Autowired
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Question>> list(Pageable pageable){
        return new ResponseEntity<>(questionService.list(pageable), OK);
    }
}
