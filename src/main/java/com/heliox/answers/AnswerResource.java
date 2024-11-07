package com.heliox.answers;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/v1/answers")
public class AnswerResource {

  private final AnswerService answerService;

  public AnswerResource(AnswerService answerService) {
    this.answerService = answerService;
  }

  @POST
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  @Path("/submit")
  public AnswerSubmitResponse submit(AnswerSubmitRequest request) {
    return answerService.calculateResponse(request);
  }
}
