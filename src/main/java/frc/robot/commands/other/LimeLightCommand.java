package frc.robot.commands.other;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.other.LimelightHelpers;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;


public class LimeLightCommand extends Command{
    private SwerveSubsystem swerve;
    double rotate=0;
    int fin = 0;
    double arr;
    double kP = .05;
    private double startTime = 0;
    public LimeLightCommand(SwerveSubsystem s_Swerve){
        swerve = s_Swerve;
        addRequirements(s_Swerve);
    }
  double limelight_aim_proportional()
  {    
    // tx ranges from (-hfov/2) to (hfov/2) in degrees. If your target is on the rightmost edge of 
    // your limelight 3 feed, tx should return roughly 31 degrees.
    arr = LimelightHelpers.getTX("limelight");
    SmartDashboard.putNumber("test LL", arr);
    double targetingAngularVelocity = arr*kP;
    // convert to radians per second for our drive method
    return targetingAngularVelocity;

  }
   @Override
    public void initialize() {
        startTime = Timer.getFPGATimestamp();
    }
    @Override
    public void execute(){    
        final var rot_limelight = limelight_aim_proportional();
         rotate = rot_limelight;
        if(Math.abs(arr)<1){
          rotate = 0;
        }
        SmartDashboard.putNumber("Limelight yatay aci", rotate);
        swerve.drive(new Translation2d(0,0),rotate*-1.1,true);  
}
@Override
public boolean isFinished() {
  if(Math.abs(Timer.getFPGATimestamp() - startTime)<2)
  {
  SmartDashboard.putNumber("finarm", fin);
    if(rotate == 0&&fin<5)
    {
      fin++;
      return false;
    }
    else if(rotate == 0&&fin>4){
      fin=0;
      return true;
    }
    return false;
    }
    else 
    return true;
}
}