package frc.robot.commands.other;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.feeder;
import frc.robot.subsystems.other.shooter;
import frc.robot.subsystems.other.ARM.ArmSubHandMade;

public class AmpShooterCommand extends Command{
    private feeder Feeder;
    private shooter Shooter;
    private ArmSubHandMade arm;
    public AmpShooterCommand(feeder feeder, shooter shooter, ArmSubHandMade arm1){
        Feeder = feeder;
        Shooter = shooter;
        arm = arm1;
        addRequirements(Feeder,Shooter,arm1);
    }
    @Override
    public void initialize() {
    }
    @Override
    public void execute() {
        while(arm.getEncoderPos()<100){
            arm.setMotors(-0.2);
        }
        
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
