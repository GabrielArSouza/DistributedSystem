package ufrn.microservice.backend.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.microservice.core.model.Answer;
import ufrn.microservice.core.model.Question;
import ufrn.microservice.core.util.HttpRequestManager;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("answer")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerController {

    private final String gateway = "http://localhost:8080/gateway/";
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/add")
    @SneakyThrows
    public ResponseEntity<String> addQuestion(@RequestBody Answer answer, @RequestHeader("Authorization") String token){
        String data = mapper.writeValueAsString(answer);
        HttpResponse<String> response = HttpRequestManager.requestPostOperationWithAuth(data, gateway+"answer/add", token);
        if (response.statusCode() == 200) return new ResponseEntity<>("Success", HttpStatus.OK);
        else return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
    }
}
