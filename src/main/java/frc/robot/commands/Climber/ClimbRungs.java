// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.Climber;

// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;
// import frc.robot.subsystems.Climber;
// import frc.robot.subsystems.DriveTrain;

// public class ClimbRungs extends CommandBase {

//   private Climber m_climber;
//   private DriveTrain m_driveTrain;
//   private int m_phase;
//   private Timer m_timer;
//   private double m_targetTicks;
//   private boolean m_shouldEnd;

//   /** Creates a new ClimbRungs. */
//   public ClimbRungs(Climber climber, DriveTrain driveTrain) {
//     m_climber = climber;
//     m_driveTrain = driveTrain;
//     m_phase = 0;
//     m_timer = new Timer();
//     m_shouldEnd = false;

//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(m_climber, m_driveTrain);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     m_timer.stop();
//     m_timer.reset();
//     m_timer.start();
//   }

//   // Left is claw 1, Right is claw 2

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     switch (m_phase) {
//       case 0: // Clamp on to first bar and extend finger on other side
//         m_climber.setFingerOne(true);
//         m_climber.setClampOne(true);
//         m_climber.setFingerTwo(true);
//         m_climber.setClampTwo(false);

//         if (m_timer.get() >= 10) {
//           m_phase++;
//           m_targetTicks = m_driveTrain.getAverageDistance() + Constants.Climber.FIRST_RUNG_DEGREES * Constants.Climber.GEAR_RATIO;
//         }

//         break;
//       case 1: //Spin the bar a set amount to get to the next thing
//         m_climber.setLeftFingers(true);
//         m_climber.setLeftClamps(true);
//         m_climber.setRightFingers(true);
//         m_climber.setRightClamps(false);
        
//         m_driveTrain.set(Constants.Climber.CLIMB_SPEED);

//         // Move onto next phase after target angle is reached
//         if (m_driveTrain.getAverageDistance() >= m_targetTicks) {
//           m_timer.stop();
//           m_timer.reset();
//           m_timer.start();
//           m_driveTrain.stop();
//           m_phase++;
//         }

//         break;
//       case 2: //Clamp onto the next one, and unclamp the other side
//         m_climber.setRightClamps(true);
//         m_climber.setRightFingers(true);

//         if (m_timer.get() >= 10) { // Unclamp after three seconds
//           m_climber.setLeftClamps(false);
//           m_climber.setLeftFingers(false);
//         } else if (m_timer.get() >= 17) { // Start next phase after another three seconds
//           m_targetTicks = m_driveTrain.getAverageDistance() + Constants.Climber.SECOND_RUNG_DEGREES * Constants.Climber.GEAR_RATIO;
//           m_phase++;
//         }

//         break;
//       case 3: // just case one again but with different encoder values
//         m_climber.setLeftFingers(true);
//         m_driveTrain.set(Constants.Climber.CLIMB_SPEED);

//         // Move onto next phase after target angle is reached
//         if (m_driveTrain.getAverageDistance() >= m_targetTicks) {
//           m_timer.stop();
//           m_timer.reset(); 
//           m_timer.start();
//           m_driveTrain.stop();
//           m_phase++;
//         }

//         break;
//       case 4: //just case 2 again but the opposite sides
//         m_climber.setLeftClamps(true);
//         m_climber.setLeftFingers(true);

//         if (m_timer.get() >= 10) { // Unclamp after 3 second
//           m_climber.setRightClamps(false);
//           m_climber.setRightFingers(false);
//         } else if (m_timer.get() >= 17) { // End the command after 3 more seconds
//           m_shouldEnd = true;
//         }
        
//         break;
//       default:
//         m_driveTrain.stop();
//         m_climber.setLeftClamps(true);
//         m_climber.setLeftFingers(true);
//         m_climber.setRightClamps(true);
//         m_climber.setRightFingers(true);
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     m_driveTrain.stop();
//     m_climber.setLeftClamps(true);
//     m_climber.setLeftFingers(true);
//     m_climber.setRightClamps(true);
//     m_climber.setRightFingers(true);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return m_shouldEnd;
//   }
// }
