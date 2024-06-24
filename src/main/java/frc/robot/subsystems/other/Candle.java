package frc.robot.subsystems.other;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.TwinkleAnimation;
import com.ctre.phoenix.led.TwinkleAnimation.TwinklePercent;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Candle extends SubsystemBase{
    private final CANdle m_candle;
    private final int LedCount = 60;
    private Animation animate;
    public Candle(){
        m_candle = new CANdle(1, "Canivore");
        // animate.setLedOffset(8);
    }
    public void ArmCandle(){
        animate = new TwinkleAnimation(30, 70, 60, 0, 0.4, LedCount, TwinklePercent.Percent6);
        m_candle.animate(animate);
    }
    public void FeederCandle(){
        animate = new TwinkleAnimation(30, 70, 60, 0, 0.4, LedCount, TwinklePercent.Percent6);
        m_candle.animate(animate);
    }
    public void ShooterCandle(){
        animate = new TwinkleAnimation(30, 70, 60, 0, 0.4, LedCount, TwinklePercent.Percent6);
        m_candle.animate(animate);
    }
    public void IntakeCandle(){
        animate = new TwinkleAnimation(30, 70, 60, 0, 0.4, LedCount, TwinklePercent.Percent6);
        m_candle.animate(animate);
    }

}
