package frc.robot.subsystems.other.encoder;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class throughBoreSubsystem extends SubsystemBase{
    public final DutyCycleEncoder dutyCycleEncoder;
    public final Encoder relativeEncoder;
    public throughBoreSubsystem(int DutyCycleChannel, int ChannelA, int ChannelB){
        dutyCycleEncoder = new DutyCycleEncoder(DutyCycleChannel);
        relativeEncoder = new Encoder(ChannelA, ChannelB);
        
        relativeEncoder.setDistancePerPulse(360.0/2048.0);
        // dutyCycleEncoder.setPositionOffset(ArmConstants.kArmOffsetZero/360);
    }
    @Override
    public void periodic(){
        SmartDashboard.putNumber("RelativeValue", getRelative());
        SmartDashboard.putNumber("RelativeDistance", getRelativeDist());
        SmartDashboard.putNumber("AbsolutePosition", getAbsolutePos());
    }
    public int getRelative(){
        return relativeEncoder.get();
    }
    public double getRelativeDist(){
        return relativeEncoder.getDistance();
    }
    public double getAbsolutePos(){
        return dutyCycleEncoder.getAbsolutePosition()*360;
    }
}