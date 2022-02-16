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

  private final DoubleSolenoid m_fingerOne, m_ClampOne, m_fingerTwo, m_ClampTwo;
  //private final DigitalInput m_leftLimitSwitch, ;m_rightLimitSwitch

  /** Creates a new ExampleSubsystem. */
  public Climber() {
    m_fingerOne = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.FINGER_ONE_A, RobotMap.PCM.FINGER_ONE_B);
    m_ClampOne = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.CLAMP_ONE_A, RobotMap.PCM.CLAMP_ONE_B);
    m_fingerTwo = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.FINGER_TWO_A, RobotMap.PCM.FINGER_TWO_B);
    m_ClampTwo = new DoubleSolenoid(PneumaticsModuleType.REVPH, RobotMap.PCM.CLAMP_TWO_A, RobotMap.PCM.CLAMP_TWO_B);


    //m_leftLimitSwitch = new DigitalInput(RobotMap.DIGITALINPUT.LEFTLIMITSWITCH);
    //m_rightLimitSwitch = new DigitalInput(RobotMap.DIGITALINPUT.RIGHTLIMITSWITCH);

  }

  /**
   * Sets the climber pistons to the specified position
   *
   * @param value the potition to set them to
   */
  public void setFingerOne(Value value) {
    m_fingerOne.set(value);
  }

  public void setClampOne(Value value) {
    m_ClampOne.set(value);
  }

  public void setFingerTwo(Value value) {
    m_fingerTwo.set(value);
  }

   public void setClampTwo(Value value) {
    m_ClampTwo.set(value);
  } 

  /**
   * Sets the climber pistons to the specified position
   *
   * @param out true if forwards, false if reverse
   */
  public void setFingerOne(boolean out) {
    setFingerOne(out ? Value.kForward : Value.kReverse);
  }

  public void setClampOne(boolean out) {
    setClampOne(out ? Value.kForward : Value.kReverse);
  }

  public void setFingerTwo(boolean out) {
    setFingerTwo(out ? Value.kForward : Value.kReverse);
  }

  public void setClampTwo(boolean out) {
    setClampTwo(out ? Value.kForward : Value.kReverse);
  }

  /**
   * Returns the value of the limit switch
   */
  // public boolean getLeftLimitSwitch(){
  //   return m_leftLimitSwitch.get();
  // }
  
  // public boolean getRightLimitSwitch(){
  //   return m_rightLimitSwitch.get();
  // }

  public void toggleFingerOne() {
    setFingerOne(m_fingerOne.get() == Value.kReverse);
  }

  public void toggleFingerTwo() {
    setFingerTwo(m_fingerTwo.get() == Value.kReverse);
  }

  public void toggleClampOne() {
    setClampOne(m_ClampOne.get() == Value.kReverse);
  }

  public void toggleClampTwo() {
    setClampTwo(m_ClampTwo.get() == Value.kReverse);
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
