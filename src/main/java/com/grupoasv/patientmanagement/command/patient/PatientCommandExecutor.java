package com.grupoasv.patientmanagement.command.patient;

import com.grupoasv.patientmanagement.command.Command;
import com.grupoasv.patientmanagement.command.CommandExecutor;
import com.grupoasv.patientmanagement.exception.command.UnknownCommandException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientCommandExecutor implements CommandExecutor<Command> {
  private CreatePatientCommandExecutor createPatientCommandExecutor;
  private UpdatePatientCommandExecutor updatePatientCommandExecutor;
  private DeletePatientCommandExecutor deletePatientCommandExecutor;

  @Override
  public void execute(Command command, String user) {
    if (executeCreateCommand(command, user)) return;

    if (executeUpdateCommand(command, user)) return;

    if (executeDeleteCommand(command, user)) return;

    throw new UnknownCommandException(command.getClass().getSimpleName());
  }

  private boolean executeDeleteCommand(Command command, String user) {
    if (command instanceof DeletePatient) {
      deletePatientCommandExecutor.execute((DeletePatient) command, user);
      return true;
    }
    return false;
  }

  private boolean executeUpdateCommand(Command command, String user) {
    if (command instanceof UpdatePatient) {
      updatePatientCommandExecutor.execute((UpdatePatient) command, user);
      return true;
    }
    return false;
  }

  private boolean executeCreateCommand(Command command, String user) {
    if (command instanceof CreatePatient) {
      createPatientCommandExecutor.execute((CreatePatient) command, user);
      return true;
    }
    return false;
  }
}
