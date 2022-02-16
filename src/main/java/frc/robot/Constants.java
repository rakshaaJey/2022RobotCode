// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DriveTrain {
        public static final double TRACK_WIDTH = 0.0; // Inches
        public static final int ENCODER_RESOLUTION = 128; //why is this so low
        public static final int TALON_ENCODER_RESOLUTION = 2048;
        public static final double ENCODER_METERS_PER_TICK = (0.0254 * 6d) * Math.PI / (double) ENCODER_RESOLUTION;
    
        public static final double VELOCITY_P = 0.0;
        public static final double VELOCITY_I = 0.0;
        public static final double VELOCITY_D = 0.0;
        public static final double VELOCITY_FF = 0.0;
    
        public static final double TURN_P = 0.0;
        public static final double TURN_I = 0.0;
        public static final double TURN_D = 0.0;
    
        public static final double TURN_TOLERANCE = 05;
    
        public static final double DRIVE_P = 0.0;
        public static final double DRIVE_I = 0.0;
        public static final double DRIVE_D = 0.0;
    
        public static final double DRIVE_TOLERANCE = 0.025;

      }

  public static final class Climber {
    public static final double ENGAGE_P = 0.0;
    public static final double ENGAGE_I = 0.0;
    public static final double ENGAGE_D = 0.0;
    
    public static final double GEAR_RATIO = 127.22;

    public static final double CLIMB_SPEED = 0.2;

    public static final double INITIAL_CLIMB_DEGREES = 512d;
    public static final double FIRST_RUNG_DEGREES = 700d;
    public static final double SECOND_RUNG_DEGREES = 1024d;
  }

  public static final class Hood {
    public static final double POSITION_P = 0.0;
    public static final double POSITION_I = 0.0;
    public static final double POSITION_D = 0.0;
    
    public static final double PWM_MIN = 0.0;
    public static final double PWM_MAX = 0.0;
    public static final double PWM_CENTER = 0.0;
    public static final double PWM_DEADBAND_MIN = 0.0;
    public static final double PWM_DEADBAND_MAX = 0.0;

    public static final double POT_DEGREES_PER_HOOD_MOVE = 0.0;
    public static final double POT_SCALE = 0.0;

    public static final double MAX_ANGLE = 0.0;
    public static final double MIN_ANGLE = 0.0;

    public static final double MIN_RAW_ANGLE = 0.0;
    public static final double MAX_RAW_ANGLE = 0.0;

    public static final double AIO_MAX_VOLTAGE = 0.0;

    public static final double POSITION_TOLERANCE = 0.0;
  }

  public static final class rates {
    public static double wantedRPM = 100;
    public static double minRPM = (0);
    public static double maxRPM = (3000); // so wrong, equals like 7 million

    public static double turnP = 0.00055;
    public static double turnI = 0.000001;
    public static double turnD = 0.00001;

    public static double ratio = 2d / 1d; //For every 1 motor spin, the flywhell spins twice
  }
}