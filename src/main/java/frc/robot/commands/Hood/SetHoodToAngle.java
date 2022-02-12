// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Hood;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class SetHoodToAngle extends CommandBase {

  private Hood m_hood;
  private DoubleSupplier m_hoodAngle;

  /** Creates a new SetHoodToAngle. */
  public SetHoodToAngle(DoubleSupplier hoodAngle, Hood hood) {
    // Use addRequirements() here to declare subsystem dependencies
    hoodAngle = m_hoodAngle;
    m_hood = hood;

    addRequirements(m_hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_hood.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_hood.setSetpoint(m_hoodAngle.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hood.stop();
    m_hood.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_hood.atSetpoint();
  }
}
