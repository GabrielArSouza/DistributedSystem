package ufrn.microservice.question.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ufrn.microservice.core.model.Question;
import ufrn.microservice.core.repository.QuestionRepository;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionService {

    private final QuestionRepository questionRepository;
    public Iterable<Question> list (Pageable pageable){
        return questionRepository.findAll(pageable);
    }
    public void add (Question question) {questionRepository.save(question);}

}
