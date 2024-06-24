package frc.robot.commands.other;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commands.other.ARM.TurnToAngleHandMade;
import frc.robot.subsystems.other.LimelightHelpers;
import frc.robot.subsystems.other.feeder;
import frc.robot.subsystems.other.shooter;
import frc.robot.subsystems.other.ARM.ArmSubHandMade;

// public class ArmLimelight extends Command{
//     private ArmSubHandMade arm;
//     private shooter Shooter;
//     private feeder FeederS;
//     private Joystick joystick;
//     public ArmLimelight(ArmSubHandMade armS, shooter shooter, feeder feeder,Joystick joy2){
//         arm = armS;
//         Shooter = shooter;
//         FeederS = feeder;
//         joystick = joy2;
//         addRequirements(arm,shooter,feeder);
//     }
//     @Override
//     public void execute()
//     {
//         double theta = LimelightHelpers.getTY("limelight");
//         SmartDashboard.putNumber("limelight dikey aci", theta);
//         theta += ArmConstants.kLLAngleToGround;
//         SmartDashboard.putNumber("SetPoint", theta);
//             new TurnToAngleHandMade(arm, theta, Shooter,FeederS, joystick).schedule();
//     }
//     @Override
//     public boolean isFinished() {
//         return true;
//     }
// }

public class ArmLimelight extends SequentialCommandGroup{
    public ArmLimelight(ArmSubHandMade armS, shooter shooter, feeder feeder,Joystick joy2){
        
    }
}


