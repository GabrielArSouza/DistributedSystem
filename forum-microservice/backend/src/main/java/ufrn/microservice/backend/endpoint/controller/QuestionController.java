package ufrn.microservice.backend.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufrn.microservice.core.model.ApplicationUser;
import ufrn.microservice.core.model.Question;
import ufrn.microservice.core.util.HttpRequestManager;

import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("question")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionController {

    private final String gateway = "http://localhost:8080/gateway/";
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/add")
    @SneakyThrows
    public ResponseEntity<String> addQuestion(@RequestBody Question question, @RequestHeader("Authorization") String token){
        String data = mapper.writeValueAsString(question);
        HttpResponse<String> response = HttpRequestManager.requestPostOperationWithAuth(data, gateway+"question/add", token);
        if (response.statusCode() == 200) return new ResponseEntity<>("Success", HttpStatus.OK);
        else return new ResponseEntity<>("Error", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/list")
    @SneakyThrows
    public ResponseEntity<String> listQuestions(@RequestHeader("Authorization") String token){
        HttpResponse<String> response = HttpRequestManager.requestGetOperationWithAuth(gateway+"question/", token);
        return new ResponseEntity<>(response.body(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<String> getQuestion(@PathVariable("id") Long id,
                                              @RequestHeader("Authorization") String token)
    {
        HttpResponse<String> response = HttpRequestManager.requestGetOperationWithAuth(gateway+"question/"+id, token);
        return new ResponseEntity<>(response.body(), HttpStatus.OK);
    }
}
