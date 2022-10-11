package com.mysite.sbb.answer.service;

import com.mysite.sbb.answer.dao.AnswerRepository;
import com.mysite.sbb.answer.domain.Answer;
import com.mysite.sbb.question.domain.Question;
import com.mysite.sbb.siteuser.domain.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser siteUser) {

        Answer answer = new Answer();

        answer.setQuestion(question);
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(siteUser);
        answerRepository.save(answer);

    }

}
