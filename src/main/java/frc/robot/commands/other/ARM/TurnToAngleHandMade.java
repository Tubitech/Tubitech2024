package frc.robot.commands.other.ARM;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.other.LimelightHelpers;
import frc.robot.subsystems.other.feeder;
import frc.robot.subsystems.other.shooter;
import frc.robot.subsystems.other.ARM.ArmSubHandMade;

public class TurnToAngleHandMade extends Command {
    private ArmSubHandMade armSubHandMade;
    private shooter Shooter;
    private feeder Feeder;
    private double setpoint;
    private double lastTime;
    private double errorSum;
    private double lastError;
    private double error;
    private double startTime;
    private boolean isInPosition = false;
    private double shooterStartTime;
    private Joystick joystick;

    public TurnToAngleHandMade(ArmSubHandMade arm, shooter shooter, feeder feedersub, Joystick joy) {
        armSubHandMade = arm;
        Shooter = shooter;
        Feeder = feedersub;
        joystick = joy;
        addRequirements(armSubHandMade, Shooter, feedersub);
    }

    @Override
    public void initialize() {
        double theta = LimelightHelpers.getTY("limelight");
        SmartDashboard.putNumber("limelight dikey aci", theta);
        theta += ArmConstants.kLLAngleToGround;
        theta += ArmConstants.kMorseOffset;
        
        setpoint = theta;

        if(setpoint<20){
            setpoint+=2.5;
        }
        if(setpoint>40){
            setpoint-=2.5;
        }
        if(theta>45)setpoint-=2.5;
        SmartDashboard.putNumber("SetPoint", setpoint);
        errorSum = 0;
        lastTime = Timer.getFPGATimestamp();
        startTime = Timer.getFPGATimestamp();
        if(joystick.getRawButton(8)){
            setpoint = 36;
        }

    }


    @Override
    public void execute() {
        error = setpoint - armSubHandMade.getEncoderPos();
        error = -error / 90;
        double dt = Timer.getFPGATimestamp() - lastTime;
        if (Math.abs(error) < ArmConstants.kArmIZone) {
            errorSum += dt * error;
        }
        double errorRate = (error - lastError) / dt;
        if (setpoint<45&&setpoint>=30)
        {
            double output = (2* error) + 1.2 * errorSum + 0.15 * errorRate;
            armSubHandMade.setMotors(-output);
        }
        else if(setpoint<30)
        {
             double output = 2.25 * error + 1.65 * errorSum + ArmConstants.kArmKd * errorRate;
            armSubHandMade.setMotors(-output);
        }
        else
        {
            double output = ArmConstants.kArmKp * error + ArmConstants.kArmKi * errorSum + ArmConstants.kArmKd * errorRate;
            armSubHandMade.setMotors(-output);
        }
        lastTime = Timer.getFPGATimestamp();
        lastError = error;
        SmartDashboard.putNumber("error", error);
        SmartDashboard.putNumber("errorsumArm", errorSum);
        SmartDashboard.putNumber("setpoint-error", Math.abs(setpoint - error * 90));
        SmartDashboard.putString("arm", "evet");
    }

    @Override
    public boolean isFinished() {
        if(Math.abs(Timer.getFPGATimestamp() - startTime)<5&&!joystick.getRawButton(7))
        {
            if (Math.abs(error * 90) < 1) {
                if (isInPosition) {
                    double diff = Timer.getFPGATimestamp() - shooterStartTime;
                    if (diff > 1.7) {
                        Shooter.setShooter(0);
                        Feeder.setFeeder(0);
                        SmartDashboard.putString("arm", "hayir");
                        return true;
                    } else {
                        if (Timer.getFPGATimestamp() - shooterStartTime < 0.3) {
                            Shooter.setShooter(1);
                        } else {
                            Shooter.setShooter(1);
                            Feeder.setFeeder(1);
                        }
                        return false;
                    }
                }
                if (!isInPosition) {
                    shooterStartTime = Timer.getFPGATimestamp();
                    isInPosition = true;
                    return false;
                }
                SmartDashboard.putString("arm", "hayir");
                return true;
            }
            else{
                shooterStartTime = Timer.getFPGATimestamp();
                isInPosition = false;
                Shooter.setShooter(0);
                Feeder.setFeeder(0);
                return false;
            }
        }
        else
        {
            SmartDashboard.putString("arm", "hayir");
            return true;
        }
    }
}