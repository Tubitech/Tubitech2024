package frc.robot.commands.other;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.feeder;

public class FeederAuto extends Command{
    private final feeder Feeder;
    private double start;
    public FeederAuto(feeder feeder){
        Feeder = feeder;
        addRequirements(Feeder);
    }
    @Override
    public void initialize() {
        start = Timer.getFPGATimestamp();
    }
    @Override
    public void execute() {
        Feeder.setFeeder(0.1);
    }
    @Override
    public boolean isFinished() {
        if(Timer.getFPGATimestamp()-start<0.5){
            return false;
        }
        else{
            Feeder.setFeeder(0.0);
             return true;
        }
       
    }
}
