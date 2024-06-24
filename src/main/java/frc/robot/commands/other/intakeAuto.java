package frc.robot.commands.other;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.intake;

public class intakeAuto extends Command{
    private final intake Intake;
    private double start;
    public intakeAuto(intake intake){
        Intake = intake;
        addRequirements(Intake);
    }
    @Override
    public void initialize() {
        start = Timer.getFPGATimestamp();
    }
    @Override
    public void execute() {
        Intake.setIntake(1);
    }
    @Override
    public boolean isFinished() {
        if(Timer.getFPGATimestamp()-start<5){
            return false;
        }
        else{
            Intake.setIntake(0);
             return true;
        }
       
    }
}
