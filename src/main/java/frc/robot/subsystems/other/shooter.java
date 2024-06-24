package frc.robot.subsystems.other;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooter extends SubsystemBase{
    private CANSparkMax spark1;
    private  CANSparkMax spark2;
    // private MZ80Subsystem mz80;
    public shooter(){
        spark1 = new CANSparkMax(11, MotorType.kBrushless);
        spark2 = new CANSparkMax(12, MotorType.kBrushless);
        // mz80 = new MZ80Subsystem(MZ80Port);
    }
    public void setShooter(double speed){
        
        spark1.set(-speed);
        spark2.set(-speed);

    }
    // public boolean getSensorVal(){
    //     return mz80.getSensorValue();
    // }
}
