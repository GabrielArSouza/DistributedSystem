package ufrn.microservice.view.endpoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.IClassFileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

        return "redirect:question/list";
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

    @RequestMapping("/answer/{id}")
    @SneakyThrows
    public ModelAndView answer (@PathVariable("id") Long id, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        HttpResponse<String> response = HttpRequestManager.requestGetOperationWithAuth(
                gateway+"backend/question/"+id, token);
        Question question = mapper.readValue(response.body(), Question.class);
        ModelAndView mv = new ModelAndView("answerQuestion");
        mv.addObject("question", question);
        return mv;
    }

}
