package com.grupoasv.patientmanagement.command;

import com.grupoasv.patientmanagement.command.Command;

public interface CommandExecutor<C extends Command> {
  void execute(C command, String user);
}
