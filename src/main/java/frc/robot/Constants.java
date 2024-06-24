// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = 48; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag

  public static final class AutonConstants
  {

    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.5, 0, 0);
    public static final PIDConstants ANGLE_PID   = new PIDConstants(0.2, 0, 0.01);
  }

  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND  = 0.1;
    public static final double LEFT_Y_DEADBAND  = 0.1;
    public static final double RIGHT_X_DEADBAND = 0.1;
    public static final double TURN_CONSTANT    = 6;
  }
  public static final class ArmConstants {
    public static final int kArmMotor1Id = 0;
    public static final int kArmMotor2Id = 1;

    public static final int kEncoderPort1 = 0;
    public static final int kEncoderPort2 = 1;
    public static final int kEncoderPort3 = 2;

    public static final double kFeedforwardKs = 0;
    public static final double kFeedforwardKg = 0;
    public static final double kFeedforwardKv = 0;

    public static final double kArmKp = 1.4;
    public static final double kArmKi = 0.8;
    public static final double kArmKd = 0.14;
    public static final double kArmIZone = 100;

    public static final double kMaxVelocityRadPerSecond = 0;
    public static final double kMaxAccelerationRadPerSecSquared = 0;

    public static final double kArmOffsetRads = 0;

    public static final double kTurnToleranceDeg = 0;
    
    public static final double kLLAngleToGround = 35;
    public static final double kArmOffsetToGround = 13.5;
    public static final double kArmOffsetZero = 260;
    public static final double kEncoderOffset = kArmOffsetToGround+kLLAngleToGround; 
    public static final double kMorseOffset =-1;

  }
}
