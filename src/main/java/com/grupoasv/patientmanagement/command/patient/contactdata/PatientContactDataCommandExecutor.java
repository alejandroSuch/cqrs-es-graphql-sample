package com.grupoasv.patientmanagement.command.patient.contactdata;

import com.grupoasv.patientmanagement.command.Command;
import com.grupoasv.patientmanagement.command.CommandExecutor;
import com.grupoasv.patientmanagement.exception.command.UnknownCommandException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientContactDataCommandExecutor implements CommandExecutor<Command> {
  private CreatePatientContactDataCommandExecutor createContactDataCommandExecutor;
  private UpdatePatientContactDataCommandExecutor updateContactDataCommandExecutor;
  private DeletePatientContactDataCommandExecutor deleteContactDataCommandExecutor;

  @Override
  public void execute(Command command, String user) {
    if (executeCreateCommand(command, user)) return;

    if (executeUpdateCommand(command, user)) return;

    if (executeDeleteCommand(command, user)) return;

    throw new UnknownCommandException(command.getClass().getSimpleName());
  }

  private boolean executeDeleteCommand(Command command, String user) {
    if (command instanceof DeletePatientContactData) {
      deleteContactDataCommandExecutor.execute((DeletePatientContactData) command, user);
      return true;
    }
    return false;
  }

  private boolean executeUpdateCommand(Command command, String user) {
    if (command instanceof UpdatePatientContactData) {
      updateContactDataCommandExecutor.execute((UpdatePatientContactData) command, user);
      return true;
    }
    return false;
  }

  private boolean executeCreateCommand(Command command, String user) {
    if (command instanceof CreatePatientContactData) {
      createContactDataCommandExecutor.execute((CreatePatientContactData) command, user);
      return true;
    }
    return false;
  }
}
