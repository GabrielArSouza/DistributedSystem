package ufrn.microservice.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.Question;

import java.util.Optional;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

    Optional<Question> findById(Long id);

}
