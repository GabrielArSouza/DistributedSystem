package ufrn.microservice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
