package ufrn.microservice.core.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ufrn.microservice.core.model.Question;

import javax.transaction.Transactional;
import java.util.Optional;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

    Optional<Question> findById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Question q SET q.answerNumber = ?1 WHERE q.id = ?2")
    void updateNumberAnswer(int answer, Long id);

}
