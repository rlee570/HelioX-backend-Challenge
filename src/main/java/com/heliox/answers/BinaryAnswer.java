package com.heliox.answers;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Binary option answer yes or no")
public enum BinaryAnswer implements Answer {
  YES,
  NO
}
