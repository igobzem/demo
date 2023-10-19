package com.example.demo;

import com.example.demo.data.Quiz;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);

    private final List<Quiz> quizzes = new ArrayList<>();
    public record Dto (@NotBlank String title, @NotBlank String text, @NotNull @Size(min=2) List<String> options, List<Integer> answer){};

    public record Answer (List<Integer> answer){};

    public static final String addQuiz = "/api/quizzes";

    @PostMapping(addQuiz)
    @ResponseStatus(HttpStatus.OK)
    public Quiz addQuiz(@RequestBody @Valid Dto dto) {
        Quiz quiz = new Quiz();
        quiz.setId(quizzes.size());
        quiz.setText(dto.text);
        quiz.setTitle(dto.title);
        quiz.setOptions(dto.options);
        quiz.setAnswer(dto.answer);
        quizzes.add(quiz);
        return quiz;
    }

}
