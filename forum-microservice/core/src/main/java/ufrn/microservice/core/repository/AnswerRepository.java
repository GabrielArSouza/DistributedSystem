package ufrn.microservice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.Answer;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {
}
