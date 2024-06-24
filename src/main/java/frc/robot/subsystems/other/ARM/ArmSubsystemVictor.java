package frc.robot.subsystems.other.ARM;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.other.encoder.throughBoreSubsystem;

public class ArmSubsystemVictor extends ProfiledPIDSubsystem {
    private final VictorSPX motor1 = new VictorSPX(0);
    private final VictorSPX motor2 = new VictorSPX(1);
    private final throughBoreSubsystem encoder = new throughBoreSubsystem(ArmConstants.kEncoderPort1,
            ArmConstants.kEncoderPort2, ArmConstants.kEncoderPort3);
    private final ArmFeedforward feedforward = new ArmFeedforward(ArmConstants.kFeedforwardKg,
            ArmConstants.kFeedforwardKs, ArmConstants.kFeedforwardKv); //velocity 0 diyor defult

    public ArmSubsystemVictor(double intialVal) {
        super(
                new ProfiledPIDController(ArmConstants.kArmKp, ArmConstants.kArmKi, ArmConstants.kArmKd, new TrapezoidProfile.Constraints(
                        ArmConstants.kMaxVelocityRadPerSecond, ArmConstants.kMaxAccelerationRadPerSecSquared)),
                0);
        setGoal(ArmConstants.kArmOffsetRads);//Debugda kaldırılacak
    }
    @Override
    public double getMeasurement() {
        return encoder.getAbsolutePos() + Units.radiansToDegrees(ArmConstants.kArmOffsetRads); //hatalı encoder muhtemelen //erkamdan not: kesin çalışıyo
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
        double Feedforward = feedforward.calculate(setpoint.position, setpoint.velocity);
        motor1.set(ControlMode.Velocity,output + Feedforward);
        motor2.set(ControlMode.Velocity,output + Feedforward);
    }
}
