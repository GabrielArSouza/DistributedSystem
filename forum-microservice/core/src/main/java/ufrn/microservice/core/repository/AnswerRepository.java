package ufrn.microservice.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.Answer;
import ufrn.microservice.core.model.Question;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {

}
