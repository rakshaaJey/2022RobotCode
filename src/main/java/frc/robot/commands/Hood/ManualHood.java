package frc.robot.commands.Hood;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hood;
import frc.robot.util.Utilities;

public class ManualHood extends CommandBase {

  private final Hood m_hood;
  private final DoubleSupplier m_speedSupplier;

  /**
   * Creates a new ManualHood command.
   */
  public ManualHood(DoubleSupplier speedSupplier, Hood hood) {
    m_hood = hood;
    m_speedSupplier = speedSupplier;
    addRequirements(m_hood);
  }

  @Override
  public void initialize() {
    m_hood.disable();
  }

  @Override
  public void execute() {
    double speed = Utilities.clip(m_speedSupplier.getAsDouble(), -0.9, 0.9);
    // -0.9 to 0.9 to avoid PWM scaling errors
    m_hood.set(speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_hood.stop();
    m_hood.enable();
  }
}