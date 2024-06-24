package frc.robot.subsystems.other.ARM;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.other.encoder.throughBoreSubsystem;

public class ArmSubsystem extends ProfiledPIDSubsystem {
    private final CANSparkMax motor1 = new CANSparkMax(ArmConstants.kArmMotor1Id, MotorType.kBrushless);
    private final CANSparkMax motor2 = new CANSparkMax(ArmConstants.kArmMotor2Id, MotorType.kBrushless);
    private final throughBoreSubsystem encoder = new throughBoreSubsystem(ArmConstants.kEncoderPort1,
            ArmConstants.kEncoderPort2, ArmConstants.kEncoderPort3);
    private final ArmFeedforward feedforward = new ArmFeedforward(ArmConstants.kFeedforwardKg,
            ArmConstants.kFeedforwardKs, ArmConstants.kFeedforwardKv);

    public ArmSubsystem() {
        super(
                new ProfiledPIDController(ArmConstants.kArmKp, 0, 0, new TrapezoidProfile.Constraints(
                        ArmConstants.kMaxVelocityRadPerSecond, ArmConstants.kMaxAccelerationRadPerSecSquared)),
                0);
        setGoal(ArmConstants.kArmOffsetRads);
    }

    @Override
    public double getMeasurement() {
        return encoder.getRelativeDist() + ArmConstants.kArmOffsetRads; //hatalı encoder muhtemelen
    }

    @Override
    public void useOutput(double output, TrapezoidProfile.State setpoint) {
        double Feedforward = feedforward.calculate(setpoint.position, setpoint.velocity);
        motor1.setVoltage(output + Feedforward);
        motor2.setVoltage(output + Feedforward);
    }
}
