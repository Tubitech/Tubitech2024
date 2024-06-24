package frc.robot.commands.other.ARM;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.other.ARM.ArmSubsystem;

public class TurnToAngleCommand extends ProfiledPIDCommand {
    public TurnToAngleCommand(double targetAngleDegrees, ArmSubsystem armSubsystem) {
        super(new ProfiledPIDController(ArmConstants.kArmKp, ArmConstants.kArmKi, ArmConstants.kArmKd,
                new TrapezoidProfile.Constraints(ArmConstants.kMaxVelocityRadPerSecond,
                        ArmConstants.kMaxAccelerationRadPerSecSquared)),
                armSubsystem::getMeasurement, targetAngleDegrees,
                (output, setpoint) -> armSubsystem.useOutput(output, setpoint), armSubsystem);
        getController().enableContinuousInput(-180, 180);
        getController().setTolerance(ArmConstants.kTurnToleranceDeg);
    }

    @Override
    public boolean isFinished() {
        return getController().atGoal();
    }
}