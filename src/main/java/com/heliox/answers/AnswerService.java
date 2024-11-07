package com.heliox.answers;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class AnswerService {

  /**
   * There wasn't a specific mention of how to calculate if the request should be approved or not so for now the assumption
   * is more positive than negative responses then it should be approved.
   * (Assumption is in the future more answer types will be added so the switch looks incorrect now but in the future
   * it will be more useful)
   * @param request List of the clients answers
   * @return true or false dependent on the
   */
  public AnswerSubmitResponse calculateResponse(AnswerSubmitRequest request) {
    // Required due to lambda use
    AtomicInteger positiveResponseCount = new AtomicInteger();
    AtomicInteger negativeResponseCount = new AtomicInteger();

    request.answers().parallelStream().forEach(submittedAnswer -> {
      var result =
          switch (submittedAnswer.type()) {
            case BINARY -> handleBinaryAnswer(submittedAnswer.answer());
            default -> {
              Log.error("Failed to find an answer type");
              throw new IllegalStateException("Failed to find an answer type");
            }
          };

      if (result) {
        positiveResponseCount.incrementAndGet();
      } else {
        negativeResponseCount.incrementAndGet();
      }
    });

    if (Log.isDebugEnabled()) {
      Log.debug("Positive Count: " + positiveResponseCount.get() + ", Negative Count: "
          + negativeResponseCount.get());
    }

    if (positiveResponseCount.get() > negativeResponseCount.get()) {
      return new AnswerSubmitResponse(true);
    } else {
      return new AnswerSubmitResponse(false);
    }
  }

  /**
   * Handles yes or no style questions(Yes would imply yes they do have the condition/issue no being no they do not)
   * @param submittedAnswer the answer text
   * @return true or false dependent on the response
   */
  private boolean handleBinaryAnswer(String submittedAnswer) {
    return BinaryAnswer.valueOf(submittedAnswer) == BinaryAnswer.NO;
  }
}
