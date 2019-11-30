package ufrn.microservice.view.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.IClassFileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ufrn.microservice.core.model.Answer;
import ufrn.microservice.core.model.Question;
import ufrn.microservice.core.util.HttpRequestManager;

import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.util.Arrays;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionController {

    private final String gateway = "http://localhost:8080/gateway/";
    private final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/question/add")
    public String formQuestion(){return "registerQuestion";}

    @PostMapping("/question/add")
    @SneakyThrows
    public String addQuestion (Question question, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");
        question.setUserId(userId);

        String data = mapper.writeValueAsString(question);
        log.info("Adding a new question");

        HttpResponse<String> response = HttpRequestManager.requestPostOperationWithAuth(
                data, gateway+"backend/question/add", token);

        if (response.statusCode() == 200)
            log.info("Successfully question register");
        else  log.info("Error - code:" + response.statusCode());

        return "redirect:/question/list";
    }

    @RequestMapping("/question/list")
    @SneakyThrows
    public ModelAndView listQuestion (HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        HttpResponse<String> response = HttpRequestManager.requestGetOperationWithAuth(
                gateway+"backend/question/list", token);

        ModelAndView mv = new ModelAndView("listQuestion");
        Iterable<Question> questions = Arrays.asList(mapper.readValue(response.body(), Question[].class));
        mv.addObject("questions", questions);
        return mv;
    }

    @GetMapping("/answer/{id}")
    @SneakyThrows
    public ModelAndView answer (@PathVariable("id") Long id, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        HttpResponse<String> response = HttpRequestManager.requestGetOperationWithAuth(
                gateway+"backend/question/"+id, token);
        Question question = mapper.readValue(response.body(), Question.class);

        log.info("parsing question with id '{}'", question.getId());

        ModelAndView mv = new ModelAndView("answerQuestion");
        mv.addObject("question", question);
        return mv;
    }

    @PostMapping("/answer/{id}")
    @SneakyThrows
    public String answerQuestion (@PathVariable("id") Long id, Answer answer, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        answer.setUserId(userId);
        answer.setQuestionId(id);

        String data = mapper.writeValueAsString(answer);
        log.info("Adding a new answer");

        HttpResponse<String> response = HttpRequestManager.requestPostOperationWithAuth(
                data, gateway+"backend/answer/add", token);

        if (response.statusCode() == 200)
            log.info("Successfully question register");
        else  log.info("Error - code:" + response.statusCode());

        return "redirect:/question/list";

    }


}
