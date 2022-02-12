// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import frc.robot.subsystems.Shooter;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterPrep extends CommandBase {
  private final Shooter m_shooter;
  private final DoubleSupplier m_speed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  // shhhhh nobody is gunna see this - Theo
  public ShooterPrep(DoubleSupplier speed, Shooter shooter) {
    m_shooter = shooter;
    m_speed = speed;

    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      m_shooter.setSetpoint(0.5);
      
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setSetpoint(m_speed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  public void end() {
      m_shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
