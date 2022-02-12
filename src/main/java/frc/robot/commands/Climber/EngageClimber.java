// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;

public class EngageClimber extends CommandBase {

  Climber m_climber;
  DriveTrain m_driveTrain;
  Timer m_timer;
  PIDController m_controller;
  double m_startDistance;
  double m_setpoint;

  /** Creates a new EngageClimber. */
  public EngageClimber(Climber climber, DriveTrain driveTrain) {
    m_climber = climber;
    m_driveTrain = driveTrain;
    m_timer = new Timer();
    m_controller = new PIDController(Constants.Climber.ENGAGE_P, Constants.Climber.ENGAGE_I, Constants.Climber.ENGAGE_D);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_climber, m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.stop();
    m_timer.reset();
    m_timer.start();
    m_startDistance = m_driveTrain.getAverageDistance();
    m_setpoint = m_startDistance + Constants.Climber.INITIAL_CLIMB_DEGREES * Constants.Climber.GEAR_RATIO;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_timer.get() <= 3) {
      m_driveTrain.setHighGear(false);
      m_driveTrain.setClimbGear(true);
    } else if (m_timer.get() <= 8) {
      // Set all clamps and fingers to correct position
      m_climber.setLeftClamps(true);
      m_climber.setLeftFingers(false);
      m_climber.setRightFingers(false);
      m_climber.setRightClamps(false);

      // Rotate climb bar until vertical
      double distanceTravelled = m_driveTrain.getAverageDistance();
      m_driveTrain.set(m_controller.calculate(m_setpoint - distanceTravelled, m_setpoint));
    } else {
      m_driveTrain.setHighGear(true);
      m_driveTrain.setClimbGear(false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() >= 12;
  }
}
