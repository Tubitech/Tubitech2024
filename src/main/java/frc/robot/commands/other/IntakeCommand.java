package frc.robot.commands.other;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.intake;

public class IntakeCommand extends Command{
    private intake Intake;
    private Joystick joystick2;
    private Joystick joystick1;
    public IntakeCommand(intake IntakeSub,Joystick joy2, Joystick joy1){
        Intake = IntakeSub;
        joystick1 = joy1;
        joystick2 = joy2;
        addRequirements(Intake);
    }
    @Override
    public void execute(){  
        if(joystick2.getRawButton(5)||joystick1.getRawAxis(7)>0.5){
            Intake.setIntake(-1);
        }
        else if (joystick2.getRawButton(6)||joystick1.getRawAxis(7)<-0.5){            
              Intake.setIntake(1);
        }
        else{
              Intake.setIntake(0);
        }   
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}
