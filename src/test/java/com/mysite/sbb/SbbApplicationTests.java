package com.mysite.sbb;

import com.mysite.sbb.answer.dao.AnswerRepository;
import com.mysite.sbb.answer.domain.Answer;
import com.mysite.sbb.question.dao.QuestionRepository;
import com.mysite.sbb.question.domain.Question;
import com.mysite.sbb.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void contextLoads() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
    }

    @Test
    void getAll() {
        List<Question> all = questionRepository.findAll();
        assertEquals(8, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요???", q.getSubject());
    }

    @Test
    void getQuestionById() {
        Optional<Question> oq = questionRepository.findById(1);

        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇입네까?", q.getSubject());
        }
    }

    @Test
    void getBySubject() {
        Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    @Test
    void getByAllSubjects() {
        List<Question> questions = questionRepository.findAllBySubject("sbb가 무엇인가요?");

        System.out.println(questions.size());

    }

    @Test
    void getByOrSubject() {
        List<Question> questions = questionRepository.findBySubjectOrSubject("sbb가 무엇인가요?", "스프링부트 모델 질문입니다.");

        System.out.println("실행 합니까? : " + questions.size());
    }

    @Test
    void getBySubjectAndContent() {
        List<Question> oq = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해 알고 싶습니다.");

        System.out.println("실행 됩니까?" + oq.size());
    }

    @Test
    void getBySubjectLike() {
        List<Question> questions = questionRepository.findBySubjectLike("sbb%");

        System.out.println("실행 합니까? : " + questions.size());
    }

    @Test
    void EditEntity() {
        Optional<Question> oq = questionRepository.findById(2);
        if (oq.isPresent()) {
            Question q = oq.get();

            q.setSubject("수정된 질문");
            q.setContent("수정된 내용");
            q.setCreateDate(LocalDateTime.now());
            questionRepository.save(q);
        }
    }

    @Test
    void DeleteEntity() {
        Optional<Question> oq = questionRepository.findById(1);
        if (oq.isPresent()) {
            Question question = oq.get();

            questionRepository.delete(question);
        }

    }

    @Test
    void createAnswer() {
        Optional<Question> oq = questionRepository.findById(2);

        if (oq.isPresent()) {
            Question question = oq.get();

            Answer answer = new Answer();

            answer.setContent("답변3이 등록되었습니다.");
            answer.setCreateDate(LocalDateTime.now());
            answer.setQuestion(question);

            answerRepository.save(answer);

        }

    }

    @Test
    @Transactional
    void getAnswerByQuestion() {
        Optional<Question> oq = questionRepository.findById(2);

        if (oq.isPresent()) {
            Question question = oq.get();

            List<Answer> answers = question.getAnswerList();

            assertEquals(4, answers.size());
        }
    }

    @Test
    @Transactional
        //답변 삭제 메소드 에러는 없으나 삭제가 안됨
    void deleteAnswerByquestion() {
        Optional<Answer> oa = answerRepository.findById(9);

        Answer answer = oa.get();

        answerRepository.delete(answer);

    }

    @Autowired
    private QuestionService questionService;

    @Test
    void testJpa() {
        for (int i = 0; i <= 300; i++) {
            String subject = String.format("테스트 데이터 입니다.:[%03d]", i);

            String content = "내용무";
            questionService.create(subject, content, null);
        }
    }

}


