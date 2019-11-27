package ufrn.microservice.question.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all available question", response = Question[].class)
    @SneakyThrows
    public ResponseEntity<String> list(Pageable pageable){
        Iterable<Question> questions = questionService.list(pageable);
        Question[] newQuestions = Iterables.toArray(questions, Question.class);
        String data = mapper.writeValueAsString(newQuestions);
        return new ResponseEntity<>(data, OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addQuestion (@RequestBody Question question){
        questionService.add(question);
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<String> getQuestion(@PathVariable("id") Long id){
        Question question = questionService.getQuestion(id);
        String data = mapper.writeValueAsString(question);
        return new ResponseEntity<>(data, OK);
    }

    @PostMapping("/update/answers/{id}")
    @SneakyThrows
    public ResponseEntity<String> addNewAnswer(@PathVariable("id") Long id){
        Question question = questionService.getQuestion(id);
        question.setAnswerNumber(question.getAnswerNumber()+1);
        questionService.update(question);
        return new ResponseEntity<>("Updated", OK);
    }

}
