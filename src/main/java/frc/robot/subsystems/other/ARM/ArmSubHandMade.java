package frc.robot.subsystems.other.ARM;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.other.encoder.throughBoreSubsystem;

public class ArmSubHandMade extends SubsystemBase{
    private final VictorSPX motor1 = new VictorSPX(2);
    private final VictorSPX motor2 = new VictorSPX(3);
    private final throughBoreSubsystem encoder = new throughBoreSubsystem(0, 1, 2);

    public ArmSubHandMade(){

    }
    public void setMotors(double speed){
        if(getEncoderPos()>90&&speed>0){//92 son
                speed = 0;
        }
        if(getEncoderPos()<5&&speed<0){//14.2
                speed = 0;
        }
        if(speed>0.40){
            speed = 0.40;
        }
        if(speed<-0.40){
            speed = -0.40;
        }
        motor1.set(VictorSPXControlMode.PercentOutput, speed);
        motor2.set(VictorSPXControlMode.PercentOutput, speed);
    }
    public double getEncoderPos(){
        double rawEncoder = encoder.getAbsolutePos();
        rawEncoder -= ArmConstants.kArmOffsetZero;
        rawEncoder *= -1;
        return rawEncoder;
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Throughbore Encoder", getEncoderPos());
    }
}
