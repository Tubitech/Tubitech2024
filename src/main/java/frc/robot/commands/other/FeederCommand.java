package frc.robot.commands.other;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.feeder;

public class FeederCommand extends Command{
    private feeder Feeder;
    private Joystick joystick1;
    private Joystick joystick2;
    double k_feeder=0.3;
    public FeederCommand(feeder FeederSub, Joystick CommandJoy, Joystick driverJoy){
        Feeder = FeederSub;
        joystick1 = driverJoy;
        joystick2 = CommandJoy;
        addRequirements(Feeder);
    }
    @Override
    public void execute(){
        if(joystick2.getRawAxis(2)>0.05)
            Feeder.setFeeder(joystick2.getRawAxis(2)*-0.2);//ahmet geri feeder trigger
        else if (joystick2.getRawButton(1))
            Feeder.setFeeder(0.1);
        else if (joystick1.getRawButton(7))
            Feeder.setFeeder(0.2 );
        else if (joystick2.getRawAxis(3)>0.05)
            Feeder.setFeeder(joystick2.getRawAxis(3)*0.2);// ahmet ileri feeder trigger
        else if(joystick2.getRawButton(2))
            Feeder.setFeeder(0.6);
        else if(Math.abs(joystick1.getRawAxis(4))>0.5)
            Feeder.setFeeder(-joystick1.getRawAxis(4)/20);
        else
            Feeder.setFeeder(0);
    //     double forward = joystick2.getRawAxis(2);
    //     double backward = -joystick2.getRawAxis(3);
    //     double ff = joystick1.getRawAxis(4);
    //     if(joystick2.getRawButton(1)){
    //         Feeder.setFeeder(-0.18);
    //     }
    //     else if(joystick2.getRawButton(2)){
    //         Feeder.setFeeder(-0.4);
    //     }
    //     else{
    //     if(ff!=0){
    //         Feeder.setFeeder(ff*0.2);
    //     }
    //     else if(backward!=0 || forward !=0){
    //         if(forward>0.1){
    //         Feeder.setFeeder(forward*0.5);
    //         }
    //         else if(backward<-0.1){
    //             Feeder.setFeeder(backward*0.5);
    //         }
        
        
    //     }
    //     else{
    //         Feeder.setFeeder(0);    
    //     }
    // } Last Code

    }
    @Override
    public boolean isFinished(){
        return false;
    }
}