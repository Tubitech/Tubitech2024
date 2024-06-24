package frc.robot.subsystems.other;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class feeder extends SubsystemBase {
    private CANSparkMax spark1;
    private  CANSparkMax spark2;;
    public feeder(){
        spark1 = new CANSparkMax(9, MotorType.kBrushless);
        spark2 = new CANSparkMax(10, MotorType.kBrushless);
    }
    public void setFeeder(double speed){
        spark1.set( -speed);
        spark2.set( -speed);
    }
}
