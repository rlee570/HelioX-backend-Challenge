package com.heliox.sections;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "ALl sections for gastric based questions")
public enum GastricSection implements Section {
  SYMPTOMS,
  HEALTH,
  MEDICATION,
  AGREEMENT
}
