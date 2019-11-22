package ufrn.microservice.question.endpoint.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.microservice.core.model.Question;
import ufrn.microservice.question.endpoint.service.QuestionService;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequestMapping("")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all available question", response = Question[].class)
    public ResponseEntity<Iterable<Question>> list(Pageable pageable){
        return new ResponseEntity<>(questionService.list(pageable), OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addQuestion (@RequestBody Question question){
        questionService.add(question);
    }

}
