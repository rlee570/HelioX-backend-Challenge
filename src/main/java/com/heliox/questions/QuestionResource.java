package com.heliox.questions;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/v1/questions")
public class QuestionResource {

  private final QuestionService questionService;

  public QuestionResource(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GET
  @Produces(APPLICATION_JSON)
  public Questions questions() {
    return questionService.getAllQuestions();
  }
}
