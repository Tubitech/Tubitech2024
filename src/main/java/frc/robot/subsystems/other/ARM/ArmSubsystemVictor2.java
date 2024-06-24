package frc.robot.subsystems.other.ARM;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystemVictor2 extends SubsystemBase{
    public VictorSPX victorSPX = new VictorSPX(0);
    public ArmSubsystemVictor2(){
        // victorSPX.configSelectedFeedbackSensor();
        victorSPX.configMotionAcceleration(0);
        victorSPX.configMotionCruiseVelocity(0);
        victorSPX.configMotionSCurveStrength(0);
        victorSPX.config_kP(0, 0);
        victorSPX.config_kI(0, 0);
        victorSPX.config_kD(0, 0);
        victorSPX.config_kF(0, 0);
        //Hatalı
        // final PositionVoltage req = new PositionVoltage(0);
        // victorSPX.set(VictorSPXControlMode.Position,req.withPosition(0).Position);
        //Hatalı
    }
    public void setMotor(){
        
        victorSPX.set(VictorSPXControlMode.MotionMagic, 4.0);
    }
    
}
