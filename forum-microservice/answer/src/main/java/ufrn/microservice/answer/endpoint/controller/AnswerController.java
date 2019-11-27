package ufrn.microservice.answer.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufrn.microservice.answer.endpoint.service.AnswerService;
import ufrn.microservice.core.model.Answer;

@Slf4j
@RequestMapping("")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerController {
    private final AnswerService answerService;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody Answer answer){answerService.add(answer);}

}
