package frc.robot.commands.other;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.shooter;

public class ShooterCommand extends Command{
    private shooter shooter;
    private Joystick joystick2;
    private Joystick joystick1;
    public ShooterCommand(shooter shooterSub, Joystick joy2, Joystick joy1){
        shooter = shooterSub;
        joystick2 = joy2;
        joystick1 = joy1;
        addRequirements(shooter);
    }
    @Override
    public void execute(){
        if(joystick2.getRawButton(3)||joystick1.getRawAxis(5)<-0.5)
            shooter.setShooter(0.2);

        else if (joystick1.getRawAxis(5)>0.5)
            shooter.setShooter(-0.3);
        else if (Math.abs(joystick2.getRawAxis(1))>0.3)
        {
            if(-joystick2.getRawAxis(1)>0.3)
                shooter.setShooter(-joystick2.getRawAxis(1));
            else if (-joystick2.getRawAxis(1)<-0.5)
                shooter.setShooter(-0.3);
        }
        else if (joystick1.getRawButton(8))
            shooter.setShooter(0.7);
        else
        {
            shooter.setShooter(0);
        }
        // if(joystick2.getRawButton(3)){
        //     shooter.setShooter(0.18);
        // }
        // else if(joystick2.getRawButton(4)){
        //     shooter.setShooter(1);
        // }
        // else{
        //     shooter.setShooter(-joystick.getRawAxis(3)*0.2);
        // } Last Code
        
        // shooter.setShooter(0.4);
    }
    @Override
    public boolean isFinished(){
    //     if(shooter.getSensorVal()){
    //         return false;
    //     }
        return false;
}
}