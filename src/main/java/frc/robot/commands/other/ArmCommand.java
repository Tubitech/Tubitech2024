package frc.robot.commands.other;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.other.ARM.ArmSubHandMade;

public class ArmCommand extends Command{
        public final ArmSubHandMade Arm;
        public final Joystick joystick1;
        public final Joystick joystick2;
        public final POVButton povButton180;
        public final POVButton povButton0;
     public ArmCommand(Joystick CommandController, Joystick DriverController, ArmSubHandMade ArmSub){
        Arm = ArmSub;
        joystick2 = CommandController;
        joystick1 = DriverController;
        addRequirements(Arm);
        povButton0 = new POVButton(joystick2, 0);
        povButton180 = new POVButton(joystick2, 180);
     }
     @Override
     public void initialize(){
        
     }
     @Override
     public void execute(){
      if(povButton0.getAsBoolean()||joystick1.getRawAxis(3)<-0.5){
         Arm.setMotors(0.3);
      }
      else if(povButton180.getAsBoolean()||joystick1.getRawAxis(3)>0.5){
         Arm.setMotors(-0.3);
      }
      else{
         Arm.setMotors(0);
      }
     }
     @Override
     public boolean isFinished(){
        return false;
     }
}
