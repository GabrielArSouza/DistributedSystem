package ufrn.microservice.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Answer implements  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'answer' is mandatory")
    @Column(nullable = false)
    @Lob
    private String answer;

    @NotNull(message = "The field 'userId' is mandatory")
    @Column(nullable = false)
    private Long userId;

    @NotNull(message = "The field 'questionId' is mandatory")
    @Column(nullable = false)
    private Long questionId;
}
