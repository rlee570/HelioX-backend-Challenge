package com.heliox.questions;

import com.heliox.answers.BinaryAnswer;
import com.heliox.sections.GastricSection;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class QuestionService {

  public Questions getAllQuestions() {
    var firstQuestion = new Question(
        1,
        1,
        true,
        true,
        "Are you aged over 50 with new or recently changed symptoms?",
        Collections.emptyList(),
        BinaryAnswer.values(),
        GastricSection.SYMPTOMS);

    var secondQuestion = new Question(
        2,
        2,
        true,
        true,
        "Are you experiencing acid reflux at least twice a week? Symptoms include:",
        List.of(
            "Heartburn - a burning feeling in the chest just behind the breastbone that occurs after eating and lasts a few minutes to several hours",
            "Chest pain, especially after bending over, lying down or eating",
            "Burning in the throat, or hot, sour, acidic or salty-tasting fluid at the back of the throat",
            "Feeling of food \"sticking\" in the middle of the chest or throat"),
        BinaryAnswer.values(),
        GastricSection.SYMPTOMS);

    var questionList = new ArrayList<Question>();
    questionList.add(firstQuestion);
    questionList.add(secondQuestion);

    return new Questions(questionList);
  }
}
