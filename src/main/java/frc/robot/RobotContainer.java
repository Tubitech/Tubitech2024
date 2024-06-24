// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.other.ArmCommand;
import frc.robot.commands.other.ArmLimelight;
import frc.robot.commands.other.FeederAuto;
import frc.robot.commands.other.FeederCommand;
import frc.robot.commands.other.IntakeCommand;
import frc.robot.commands.other.LimeLightCommand;
import frc.robot.commands.other.ShooterCommand;
import frc.robot.commands.other.intakeAuto;
import frc.robot.commands.other.ARM.TurnToAngleHandMade;
import frc.robot.subsystems.other.feeder;
import frc.robot.subsystems.other.intake;
import frc.robot.subsystems.other.shooter;
import frc.robot.subsystems.other.ARM.ArmSubHandMade;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{
  

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));

  // Replace with CommandPS4Controller or CommandJoystick if needed
  final Joystick driverXbox = new Joystick(0);
  ///////////////////////////////// OSMAN //////////////////////////////////////////////
  private final intake Intake = new intake();
  private final feeder Feeder = new feeder();
  private final shooter Shooter = new shooter();
  private final ArmSubHandMade Arm = new ArmSubHandMade();

  private final Joystick CommandController = new Joystick(1);


  private final JoystickButton zeroGyro = new JoystickButton(driverXbox, 6);
  private final JoystickButton drivebaselock = new JoystickButton(driverXbox, 4);

  private final JoystickButton turnSwerveToSpeaker = new JoystickButton(CommandController, 10);
  private final JoystickButton turnArmToSpeaker = new JoystickButton(CommandController, 4);
  private final JoystickButton turnArmToSpeakera = new JoystickButton(CommandController, 8);
  // private final JoystickButton turnArmToAmp = new JoystickButton(CommandController, 2);

  ///////////////////////////////// OSMAN //////////////////////////////////////////////

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();

    // AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
    //                                                                () -> -MathUtil.applyDeadband(driverXbox.getRawAxis(1),
    //                                                                                             OperatorConstants.LEFT_Y_DEADBAND),
    //                                                                () -> -MathUtil.applyDeadband(driverXbox.getRawAxis(0),
    //                                                                                             OperatorConstants.LEFT_X_DEADBAND),
    //                                                                () -> -MathUtil.applyDeadband(driverXbox.getRawAxis(2),
    //                                                                                             OperatorConstants.RIGHT_X_DEADBAND),
    //                                                                driverXbox.getHID()::getYButtonPressed,
    //                                                                driverXbox.getHID()::getAButtonPressed,
    //                                                                driverXbox.getHID()::getXButtonPressed,
    //                                                                driverXbox.getHID()::getBButtonPressed);

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
    // Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
    //     () -> MathUtil.applyDeadband(driverXbox.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
    //     () -> MathUtil.applyDeadband(driverXbox.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
    //     () -> driverXbox.getRawAxis(2),
    //     () -> driverXbox.getRawAxis(3));

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND), //1
        () -> MathUtil.applyDeadband(-driverXbox.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND), //0
        () -> -driverXbox.getRawAxis(2));                                                     //2

    // Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
    //     () -> MathUtil.applyDeadband(-driverXbox.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND), //1
    //     () -> MathUtil.applyDeadband(driverXbox.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND), //0
    //     () -> driverXbox.getRawAxis(2));         
        
    drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
    // drivebase.setDefaultCommand(
    //   !RobotBase.isSimulation() ? driveFieldOrientedAnglularVelocity : driveFieldOrientedDirectAngleSim);

    ////////////////////////////////////// OSMAN ///////////////////////////////////////////////
    Intake.setDefaultCommand(new IntakeCommand(Intake, CommandController, driverXbox));
    Feeder.setDefaultCommand(new FeederCommand(Feeder, CommandController, driverXbox));
    Shooter.setDefaultCommand(new ShooterCommand(Shooter, CommandController, driverXbox));
    Arm.setDefaultCommand(new ArmCommand(CommandController, driverXbox, Arm));

    NamedCommands.registerCommand("Swerve", new LimeLightCommand(drivebase));
    NamedCommands.registerCommand("Arm",new TurnToAngleHandMade(Arm, Shooter, Feeder, CommandController));
    NamedCommands.registerCommand("Intake", new intakeAuto(Intake));
    NamedCommands.registerCommand("Feeder", new FeederAuto(Feeder));

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    drivebaselock.onTrue(Commands.runOnce(drivebase::lock));
    zeroGyro.onTrue((Commands.runOnce(drivebase::zeroGyro)));
    // driverXbox.x().onTrue(Commands.runOnce(drivebase::addFakeVisionReading));
    // driverXbox.b().whileTrue(
    //     Commands.deferredProxy(() -> drivebase.driveToPose(
    //                                new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
    //                           ));
    // driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());

    ///////////////////////////Osman///////////////////////////////////////////
    turnSwerveToSpeaker.onTrue(new LimeLightCommand(drivebase));
    turnArmToSpeaker.onTrue(new TurnToAngleHandMade(Arm, Shooter, Feeder, CommandController));
    turnArmToSpeakera.onTrue(new TurnToAngleHandMade(Arm, Shooter, Feeder, CommandController));
    // turnArmToAmp.onTrue(new AmpShooterCommand(Feeder, Shooter, Arm));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return drivebase.getAutonomousCommand("ht");
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
