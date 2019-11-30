package ufrn.microservice.answer.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufrn.microservice.core.model.Answer;
import ufrn.microservice.core.repository.AnswerRepository;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void add (Answer answer){answerRepository.save(answer);}
}
