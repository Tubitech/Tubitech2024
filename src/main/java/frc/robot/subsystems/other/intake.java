package frc.robot.subsystems.other;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intake extends SubsystemBase{
    private VictorSPX victorSPX1;
    // private MZ80Subsystem mz80;
    public intake(){
        victorSPX1 = new VictorSPX(1);
        // mz80 = new MZ80Subsystem(MZ80Port);
    }
    public void setIntake(double speed){
        
        victorSPX1.set(ControlMode.PercentOutput, speed);

    }
    // public boolean getSensorVal(){
    //     return mz80.getSensorValue();
    // }
}
