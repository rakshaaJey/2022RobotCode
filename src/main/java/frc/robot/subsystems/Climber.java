// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle.VBatOutputMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {

  private final DoubleSolenoid m_leftClampA, m_leftFingerA, m_rightClampA, m_rightFingerA;
  private final DoubleSolenoid m_leftClampB, m_leftFingerB, m_rightClampB, m_rightFingerB;
  private final DigitalInput m_leftLimitSwitch, m_rightLimitSwitch;

  /** Creates a new ExampleSubsystem. */
  public Climber() {
    m_leftClampA = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.LEFTCLAMP_AA, RobotMap.PCM.LEFTCLAMP_BA);
    m_leftFingerA = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.LEFTFINGER_AA, RobotMap.PCM.LEFTFINGER_BA);
    m_rightClampA = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.RIGHTCLAMP_AA, RobotMap.PCM.RIGHTCLAMP_BA);
    m_rightFingerA = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.RIGHTFINGER_AA, RobotMap.PCM.RIGHTFINGER_BA);

    m_leftClampB = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.LEFTCLAMP_AB, RobotMap.PCM.LEFTCLAMP_BB);
    m_leftFingerB = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.LEFTFINGER_AB, RobotMap.PCM.LEFTFINGER_BB);
    m_rightClampB = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.RIGHTCLAMP_AB, RobotMap.PCM.RIGHTCLAMP_BB);
    m_rightFingerB = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.RIGHTFINGER_AB, RobotMap.PCM.RIGHTFINGER_BB);

    m_leftLimitSwitch = new DigitalInput(RobotMap.DIGITALINPUT.LEFTLIMITSWITCH);
    m_rightLimitSwitch = new DigitalInput(RobotMap.DIGITALINPUT.RIGHTLIMITSWITCH);

  }

  /**
   * Sets the climber pistons to the specified position
   *
   * @param value the potition to set them to
   */
  public void setLeftClamps(Value value) {
    m_leftClampA.set(value);
    m_leftClampB.set(value);
  }

  public void setLeftFingers(Value value) {
    m_leftFingerA.set(value);
    m_leftFingerB.set(value);
  }

  public void setRightClamps(Value value) {
    m_rightClampA.set(value);
    m_rightClampB.set(value);
  }

   public void setRightFingers(Value value) {
    m_rightFingerA.set(value);
    m_rightFingerB.set(value);
  } 

  /**
   * Sets the climber pistons to the specified position
   *
   * @param out true if forwards, false if reverse
   */
  public void setLeftClamps(boolean out) {
    setLeftClamps(out ? Value.kForward : Value.kReverse);
  }

  public void setLeftFingers(boolean out) {
    setLeftFingers(out ? Value.kForward : Value.kReverse);
  }

  public void setRightClamps(boolean out) {
    setRightClamps(out ? Value.kForward : Value.kReverse);
  }

  public void setRightFingers(boolean out) {
    setRightFingers(out ? Value.kForward : Value.kReverse);
  }

  /**
   * Returns the value of the limit switch
   */
  public boolean getLeftLimitSwitch(){
    return m_leftLimitSwitch.get();
  }
  
  public boolean getRightLimitSwitch(){
    return m_rightLimitSwitch.get();
  }

  public void toggleLeftClamps() {
    setLeftClamps(m_leftClampA.get() == Value.kReverse);
  }

  public void toggleRightClamps() {
    setRightClamps(m_rightClampA.get() == Value.kReverse);
  }

  public void toggleLeftFingers() {
    setLeftFingers(m_leftFingerA.get() == Value.kReverse);
  }

  public void toggleRightFingers() {
    setRightFingers(m_rightFingerA.get() == Value.kReverse);
  }

 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
