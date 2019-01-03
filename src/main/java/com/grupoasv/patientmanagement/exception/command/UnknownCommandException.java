package com.grupoasv.patientmanagement.exception.command;

public class UnknownCommandException extends RuntimeException {
  public UnknownCommandException(String commandName) {
    super("Unknown command: " + commandName);
  }
}
