package com.mysite.sbb;

import com.mysite.sbb.question.dao.QuestionRepository;
import com.mysite.sbb.question.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private QuestionRepository questionRepository;



    @GetMapping("/creteQuestion")
    @ResponseBody

    public List<Question> createQuestion() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("sbb가 무엇인가요?");
        q2.setContent("sbb에 대해 알고 싶습니다.");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);

        return this.questionRepository.findAll();
    }

    @RequestMapping("/sbb")
    @ResponseBody
    public String index() {

        return "안녕하세요 sbb에 오신것을 환영합니다.";
    }

    @GetMapping("/page1")
    @ResponseBody

    public String showPage1() {
        return """
                <form method="POST" action = "/page2">
                <input type = 'number' name = "age" placeholder="나이"/>
                <input type="submit" value = "page2로  POST방식으로 이동" />
                """;

    }

    @PostMapping("/page2")
    @ResponseBody

    public String showPage2(@RequestParam (value = "age", defaultValue = "0") int age) {
        return """
                
                <h1>입력된 나이 : %d </h1>
                <h1>Post 방식으로 옴</h1>
                
                """.formatted(age);

    }

    @GetMapping("/page2")
    @ResponseBody

    public String showPageGet(@RequestParam (value = "age", defaultValue = "0") int age) {
        return """
                
                <h1>입력된 나이 : %d </h1>
                <h1>Get 방식으로 옴</h1>
                
                """.formatted(age);

    }

}
