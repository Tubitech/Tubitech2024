package frc.robot.subsystems.other;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class arm extends SubsystemBase{
    // private PIDController pidController =  new PIDController(0.1, 0, 0);
    private final VictorSPX armMotor1;
    private final VictorSPX armMotor2;

    // private throughBoreSubsystem encoder = new throughBoreSubsystem(0, 1, 2);
    public arm(){
        armMotor1 = new VictorSPX(2);
        armMotor2 = new VictorSPX(3);

    }
    @Override
    public void periodic(){
        // SmartDashboard.putNumber("encoder", encoder.getAbsolutePos());
    }
    public void setPos(double setpoint){
        // armMotor.set(pidController.calculate(boreEncoder.getAbsolutePosition(),setpoint));
        // armMotor1.set(VictorSPXControlMode.PercentOutput,pidController.calculate(encoder.getAbsolutePos(),setpoint));
        // armMotor2.set(VictorSPXControlMode.PercentOutput,pidController.calculate(encoder.getAbsolutePos(),setpoint));

        SmartDashboard.putNumber("armDegree", setpoint);
    }
    public double getSetPoint(){
        return 0;
        // return pidController.getSetpoint();
    }
    public void setMotors(double speed){
        armMotor1.set(VictorSPXControlMode.PercentOutput, speed);
        armMotor2.set(VictorSPXControlMode.PercentOutput, speed);
    }

}