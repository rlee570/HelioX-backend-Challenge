package com.heliox.answers;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import java.util.List;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AnswerResourceTest {

  private static final ObjectMapper objectmapper = new ObjectMapper();

  @Test
  void submit() throws JsonProcessingException {
    var answerList =
        new AnswerSubmitRequest(List.of(new SubmittedAnswer(0, AnswerType.BINARY, "YES")));
    var expectedResponse = objectmapper.writeValueAsString(new AnswerSubmitResponse(false));

    given()
        .when()
        .contentType(APPLICATION_JSON)
        .body(answerList)
        .post("/v1/answers/submit")
        .then()
        .statusCode(OK.getStatusCode())
        .contentType(APPLICATION_JSON)
        .body(is(expectedResponse));
  }
}
