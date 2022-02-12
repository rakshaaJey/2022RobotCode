// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.Utilities;

public class Shooter extends SubsystemBase {

  private final TalonFX m_motorLeft, m_motorRight;
  private final PIDController pidShooter;
  public double shooterRPM;
  public double wantedRPMExternal;
  public double averageSpeed;

  /** Creates a new Motor. */
  public Shooter() {    

    m_motorLeft = new TalonFX(RobotMap.ShooterPorts.SHOOTER_LEFT);
    m_motorRight = new TalonFX(RobotMap.ShooterPorts.SHOOTER_RIGHT);

    shooterRPM = 0;
    
    m_motorLeft.setInverted(true);
    m_motorRight.setInverted(false);

    m_motorRight.configClosedloopRamp(0.0);
    m_motorLeft.configClosedloopRamp(0.0);
    m_motorRight.configOpenloopRamp(0.0);
    m_motorLeft.configOpenloopRamp(0.0);

    pidShooter = new PIDController(Constants.rates.turnP, Constants.rates.turnI, Constants.rates.turnD);
    setSetpoint(Constants.rates.wantedRPM);

    pidShooter.setTolerance(1.0);
  }

  public boolean atSetpoint() {
    return pidShooter.atSetpoint();
  }
  
  public double getAverageSpeed() {
    return ((getRightSpeed() + getLeftSpeed()) / 2);
  }

  /*
  getSelectedSensorVelocity returns motor units per 100 ms, do something with 2048 then convert it to seconds, 
  times that by 60 for RPM
  */

  public double getRightSpeed() {
    return (((m_motorRight.getSelectedSensorVelocity() / 2048d) * 10) * 60d);
  }

  public double getLeftSpeed() {
    return (((m_motorLeft.getSelectedSensorVelocity() / 2048d) * 10) * 60d);    
  }

  public void setSetpoint(double wantedRPM) {
    wantedRPM = Utilities.clip(wantedRPM, Constants.rates.minRPM, Constants.rates.maxRPM);
    pidShooter.setSetpoint(wantedRPM);
    wantedRPMExternal = wantedRPM;
  }

  public void setWithPID() {
    // averageSpeed = Util.clip(getAverageSpeed(), Constants.rates.minRPM, Constants.rates.maxRPM);
    m_motorLeft.set(ControlMode.PercentOutput, pidShooter.calculate(averageSpeed));
    m_motorRight.set(ControlMode.PercentOutput, pidShooter.calculate(averageSpeed));
  }

  public void stop() {
    m_motorLeft.set(ControlMode.PercentOutput, 0.0);
    m_motorRight.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    shooterRPM = getAverageSpeed(); // * Constants.rates.ratio / Constants.rates.degrees * 60; //Might have a problem
    SmartDashboard.putNumber("Current Shooter RPM", shooterRPM * Constants.rates.ratio);
    SmartDashboard.putNumber("Desired Shooter RPM", wantedRPMExternal * Constants.rates.ratio);
  }

}
