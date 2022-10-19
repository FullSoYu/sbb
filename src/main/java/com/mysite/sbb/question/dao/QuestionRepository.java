package com.mysite.sbb.question.dao;

import com.mysite.sbb.question.domain.Question;
import com.mysite.sbb.question.service.QuestionVoterInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String s);

    List<Question> findAllBySubject(String subject);

    List<Question> findAllBySubjectIn(List<String> searchWordList);

    List<Question> findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String searchString);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    @Query(value = "SELECT * FROM question_voter WHERE voter_id = ?1 AND question_id = ?2", nativeQuery = true)
    QuestionVoterInterface findQuestionByVoter(Long userId, Long questionId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM question_voter WHERE voter_id = ?1 AND question_id = ?2", nativeQuery = true)
    void deleteQuestionByVoter(Long userId, long questionId);


    @Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

}