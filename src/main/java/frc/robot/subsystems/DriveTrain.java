// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.Utilities;

public class DriveTrain extends SubsystemBase {
  
  private final TalonFX m_leftFront, m_leftMid, m_leftBack, m_rightFront, m_rightMid, m_rightBack;

  private final Encoder m_leftEncoder, m_rightEncoder;

  private final Solenoid m_highGearA, m_climbGearA, m_climbGearB, m_highGearB;

  //private final AHRS m_gyro;

  public DriveTrain() {

    m_leftFront = new TalonFX(RobotMap.CAN.TALONFX_DRIVE_LEFT_FRONT);
    m_leftMid = new TalonFX(RobotMap.CAN.TALONFX_DRIVE_LEFT_MID);
    m_leftBack = new TalonFX(RobotMap.CAN.TALONFX_DRIVE_LEFT_BACK);
    m_rightFront = new TalonFX(RobotMap.CAN.TALONFX_DRIVE_RIGHT_FRONT);
    m_rightMid = new TalonFX(RobotMap.CAN.TALONFX_DRIVE_RIGHT_MID);
    m_rightBack = new TalonFX(RobotMap.CAN.TALONFX_DRIVE_RIGHT_BACK);

    m_highGearA = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.DRIVE_SHIFTER_A);
    m_climbGearA = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.CLIMB_SHIFTER_A);
    m_highGearB = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.DRIVE_SHIFTER_B);
    m_climbGearB = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.CLIMB_SHIFTER_B);

    m_leftEncoder = new Encoder(RobotMap.DIO.DRIVE_LEFT_ENCODER_A, RobotMap.DIO.DRIVE_LEFT_ENCODER_B);
    m_rightEncoder = new Encoder(RobotMap.DIO.DRIVE_RIGHT_ENCODER_A, RobotMap.DIO.DRIVE_RIGHT_ENCODER_B);


    //check with real bot
    m_leftEncoder.setReverseDirection(true);
    m_rightEncoder.setReverseDirection(false);

    m_leftEncoder.setDistancePerPulse(Constants.DriveTrain.ENCODER_METERS_PER_TICK);
    m_rightEncoder.setDistancePerPulse(Constants.DriveTrain.ENCODER_METERS_PER_TICK);

    m_leftFront.follow(m_leftBack);
    m_leftMid.follow(m_leftBack);
    m_rightFront.follow(m_rightBack);
    m_rightMid.follow(m_rightBack);


    //Check with real bot
    m_leftFront.setInverted(true);
    m_leftMid.setInverted(true);
    m_leftBack.setInverted(true);
    m_rightFront.setInverted(false);
    m_rightMid.setInverted(false);
    m_rightBack.setInverted(false);

    //AHRS is commented out right now because it sucks and is bad and is currently in jail
    /* AHRS gyro;
    try {
      gyro = new AHRS(SPI.Port.kMXP));
    } catch (Exception e) {
      gyro = null;
      e.printStackTrace();
    }
    m_gyro = gyro; */

  }
  
  // We only set back ones here and not front ones
  // If that causes a problem, then blame build
  public void set(double speed) {
    m_leftBack.set(ControlMode.PercentOutput, speed);
    m_rightBack.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Stops the robot
   */
  public void stop() {
    m_leftBack.set(ControlMode.PercentOutput, 0.0);
    m_rightBack.set(ControlMode.PercentOutput, 0.0);
  }

  /**
   * @param drive the forward movement of the robot
   * @param turn  the angle that the robot would turn to
   */
  public void arcadeDrive(double drive, double turn) {

    drive = Utilities.clip(drive, -1, 1);
    turn = Utilities.clip(turn, -1, 1);

    drive = Math.copySign(Math.pow(drive, 2), drive);
    turn = Math.copySign(Math.pow(turn, 2), turn);

    m_leftBack.set(ControlMode.PercentOutput, drive - turn);
    m_rightBack.set(ControlMode.PercentOutput, drive + turn);
  }

  /**
  * @return the left encoder's output
  */
  public double getLeftDistance() {
    return m_leftEncoder.getDistance();
  }

  /**
   * @return the right encoder's output
   */
  public double getRightDistance() {
    return m_rightEncoder.getDistance();
  }

  /**
   * @return the average of the left and right encoder output
   */
  public double getAverageDistance() {
    return (getLeftDistance() + getRightDistance()) / 2;
  }

  /**
   * Gets the speed of the right side of the drivetrain
   *
   * @return the speed of the right side of the drivetrain in meters/second
   */
  public double getRightVelocity() {
    return m_rightEncoder.getRate();
  }

  /**
   * Gets the speed of the left side of the drivetrain
   *
   * @return the speed of the left side of the drivetrain in meters/second
   */
  public double getLeftVelocity() {
    return m_leftEncoder.getRate();
  }

  public void setHighGear(boolean on) {
    m_highGearA.set(on);
    m_highGearB.set(on);
  }

  public boolean isHighGear() {
    return m_highGearA.get();
  }

  public void setClimbGear(boolean on) {
    m_climbGearA.set(on);
    m_climbGearB.set(on);
  }

  public boolean isClimbGear() {
    return m_climbGearA.get();
  }

  public void toggleHighGear() {
    m_highGearB.toggle();
    m_highGearA.toggle();
  }

  public void toggleClimbGear() {
    m_climbGearA.toggle();
    m_climbGearB.toggle();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("drive/encoders/leftEncoder", getLeftDistance());
    SmartDashboard.putNumber("drive/encoders/rightEncoder", getRightDistance());
    //SmartDashboard.putNumber("drive/gyroAngle", getGyroAngle());
    SmartDashboard.putBoolean("drive/lowGear", !isHighGear());
    SmartDashboard.putNumber("drive/speed", Math.abs((getLeftVelocity() + getRightVelocity()) / 2d));
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
