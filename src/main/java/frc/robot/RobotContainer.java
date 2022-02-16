// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.CustomButtons.DoubleShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Utilities;
//import frc.robot.commands.Climber.ClimbRungs;
//import frc.robot.commands.Climber.EngageClimber;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final DriveTrain m_driveTrain;
  private final Shooter m_shooter;
  private final Climber m_climber;

  private final Joystick m_driveJoystick;
  private final Joystick m_operatorJoystick;
  
  private double wantedRPM;


/**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    m_driveTrain = new DriveTrain();
    m_shooter = new Shooter();
    m_climber = new Climber();

    wantedRPM = Constants.rates.wantedRPM; // default rpm
    

    m_driveJoystick = new Joystick(RobotMap.JOYSTICK.DRIVER);
    m_operatorJoystick = new Joystick(RobotMap.JOYSTICK.OPERATOR);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //JoystickButton runShooter = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.shooterOne);
    //JoystickButton runShooterTwo = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.shooterTwo);

    JoystickButton toggleFingerOne = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.FINGER_ONE);
    JoystickButton toggleFingerTwo = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.FINGER_TWO);

    JoystickButton toggleClampOne = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.CLAMP_ONE);
    JoystickButton toggleClampTwo = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.CLAMP_TWO);

    JoystickButton doHighGear = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.highGear);
    JoystickButton doClimbGear = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.climbGear);

    //JoystickButton increase = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.increaseButton); //Increase flywheel rpm by 100
    //JoystickButton decrease = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.decreaseButton); //Decrease flywheel rpm by 100

    JoystickButton driveForward = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.driveForward);
    JoystickButton driveBackwards = new JoystickButton(m_driveJoystick, RobotMap.ControllerPorts.driveBackward);

    // JoystickButton enagageClimbButton = new JoystickButton(m_driveJoystick, RobotMap.JOYSTICK_BUTTONS.ENGAGE_CLIMB);
    // JoystickButton climbButton = new JoystickButton(m_driveJoystick, RobotMap.JOYSTICK_BUTTONS.CLIMB);

    /* m_driveTrain.setDefaultCommand(new RunCommand(() -> {
      m_driveTrain.arcadeDrive(-m_driveJoystick.getRawAxis(RobotMap.JOYSTICK_AXIS.DRIVE),
          -m_driveJoystick.getRawAxis(RobotMap.JOYSTICK_AXIS.TURN));
    }, m_driveTrain)); */

      driveForward.whenHeld(new RunCommand(() -> {
        m_driveTrain.set(0.2);
      }), true);
      
      driveForward.whenReleased(new RunCommand(() -> {
        m_driveTrain.stop();
      }), true);

      driveBackwards.whenHeld(new RunCommand(() -> {
        m_driveTrain.set(-0.2);
      }), true);

      driveBackwards.whenReleased(new RunCommand(() -> {
        m_driveTrain.stop();
      }), true);

    toggleFingerOne.whenPressed(new InstantCommand(() -> {
      m_climber.toggleFingerOne();
    }), true);

    toggleFingerTwo.whenPressed(new InstantCommand(() -> {
      m_climber.toggleFingerTwo();
    }), true);

    toggleClampOne.whenPressed(new InstantCommand(() -> {
      m_climber.toggleClampOne();
    }), true);

    toggleClampTwo.whenPressed(new InstantCommand(() -> {
      m_climber.toggleClampTwo();
    }), true);

    doHighGear.whenPressed(new InstantCommand(() -> {
      m_driveTrain.toggleHighGear();
    }), true);

    doClimbGear.whenPressed(new InstantCommand(() -> {
      m_driveTrain.toggleClimbGear();
    }), true);

    /*shootButtons.whenHeld(new RunCommand(() -> {
      m_shooter.setSetpoint(wantedRPM);
      m_shooter.setWithPID(); 
      }), true);
    shootButtons.whenReleased(new RunCommand(() -> { m_shooter.stop(); }), true); */

    


    
    /*increase.whenPressed(new InstantCommand(() -> { 
      
      wantedRPM = Utilities.clip(wantedRPM, 0, 3000);
      wantedRPM += 50.0; //Motor RPM
      m_shooter.setSetpoint(wantedRPM);
    }));

    decrease.whenPressed(new InstantCommand(() -> {
        
        wantedRPM = Utilities.clip(wantedRPM, 0, 3000);
        wantedRPM -= 50.0; //Motor RPM
        m_shooter.setSetpoint(wantedRPM);
      })); */
  }

  /**
   * Resets sensors, should be called in Robot.robotInit
   */
  public void robotInit() {
    // m_driveTrain.
    m_driveTrain.setHighGear(true);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new RunCommand(() -> {});
  }
}
