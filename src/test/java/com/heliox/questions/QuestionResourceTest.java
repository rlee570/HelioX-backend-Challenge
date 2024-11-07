package com.heliox.questions;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heliox.answers.BinaryAnswer;
import com.heliox.sections.GastricSection;
import io.quarkus.test.junit.QuarkusTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

@QuarkusTest
class QuestionResourceTest {

  private static final ObjectMapper objectmapper = new ObjectMapper();

  @Test
  void questions() throws JsonProcessingException {
    var questionList = getQuestions();
    var questions = new Questions(questionList);
    var expectedResponse = objectmapper.writeValueAsString(questions);

    given()
        .when()
        .get("/v1/questions")
        .then()
        .statusCode(OK.getStatusCode())
        .contentType(APPLICATION_JSON)
        .body(is(expectedResponse));
  }

  private static ArrayList<Question> getQuestions() {
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
    return questionList;
  }
}
