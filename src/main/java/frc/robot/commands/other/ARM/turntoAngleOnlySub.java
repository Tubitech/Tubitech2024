package frc.robot.commands.other.ARM;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.ARM.ArmSubsystem;

public class turntoAngleOnlySub extends Command{
    private double point;
    private ArmSubsystem armSubsystem;
    public turntoAngleOnlySub(double setPoint, ArmSubsystem arm){
        point = setPoint;
        armSubsystem = arm;
        addRequirements(armSubsystem);
    }
    @Override
    public void execute(){
        armSubsystem.setGoal(point);
        armSubsystem.enable();
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}