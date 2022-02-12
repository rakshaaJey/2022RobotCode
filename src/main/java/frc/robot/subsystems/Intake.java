//sus

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {

  private final DoubleSolenoid m_solenoid;
  private final TalonFX m_intake;

  public Intake() {
    //We're not sure of this being a REVPH, if this is wrong then idk
    m_solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.INTAKE_A, RobotMap.PCM.INTAKE_B);
    m_intake = new TalonFX(RobotMap.CAN.TALONFX_INTAKE);
  }

/**
 * Sets the speed
 */

  public void set(double speed) {
    m_intake.set(ControlMode.PercentOutput, speed);
  }

/**
 * Stops the motors
 */

  public void stop() {
    set(0.0);
  }

  /**
   * @return true if solenoid isExtended
   */

  public boolean isExtended() {
    return m_solenoid.get() == Value.kForward;
  }

/**
 * Sets the solenoid to extended
 */

  public void setExtended(boolean extended) {
    m_solenoid.set(extended ? Value.kForward : Value.kReverse);
  }

/**
 * Extend the solenoid to a specific value
 */

  public void setExtended(Value value) {
    m_solenoid.set(value);
  }

  @Override
  public void periodic() {
    // SmartDashboard.putBoolean("intake/extended", m_solenoid.get() == Value.kForward);
  }
}