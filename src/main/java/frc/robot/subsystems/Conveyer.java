/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
public class ConveyorBelt extends SubsystemBase {
  /**
   * Creates a new ConveyorBelt.
   */
  private TalonSRX m_conveyer;
  public ConveyorBelt() {
    
    m_conveyer = new TalonFX(RobotMap.TALONFX_CONVEYER);
  }

  /**
   * Sets the left conveyor to the desired speed
   * @param speed the speed that the left conveyor will run at
   */
  public void setConveyerBelt(double speed){
    m_conveyer.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Stops the conveyor motor
   */
  public void stopConveyer(){
    setConveyorBelt(0.0);
  }

}