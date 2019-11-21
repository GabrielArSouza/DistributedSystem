package ufrn.microservice.question.endpoint.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ufrn.microservice.core.model.Question;
import ufrn.microservice.core.repository.QuestionRepository;

@Service
@Slf4j
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    public QuestionService (QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public Iterable<Question> list (Pageable pageable){
        return questionRepository.findAll(pageable);
    }

}
