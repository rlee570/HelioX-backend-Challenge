package com.heliox.questions;

import com.heliox.answers.Answer;
import com.heliox.sections.Section;
import java.util.List;

public record Question(
    // in production this would be better suited as a UUID
    int id,
    int position,
    boolean isVisible,
    boolean isRequired,
    String questionText,
    List<String> symptoms,
    Answer[] answer,
    Section section) {}
