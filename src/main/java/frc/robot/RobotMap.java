package frc.robot;

/**
 * Contains all port numbers for motors, sensors, joysticks & OI stuff, etc...
 *
 * Only reason these values aren't in constants is because I like having the
 * port numbers and OI stuff separate from the PID Tunings, etc...
 */
public class RobotMap {
    public static final class JOYSTICK {
        public static final int DRIVER = 0;
        public static final int OPERATOR = 1;
    }

    public static final class JOYSTICK_AXIS {
        public static final int DRIVE = 1;
        public static final int TURN = 0;
    }

    public static final class JOYSTICK_BUTTONS {
        public static final int ENGAGE_CLIMB = 1; // A
        public static final int CLIMB = 2; // B
    }

    public static final class CAN {
        public static final int TALONFX_DRIVE_LEFT_FRONT = 3;
        public static final int TALONFX_DRIVE_LEFT_MID = 0;
        public static final int TALONFX_DRIVE_LEFT_BACK = 4;
        public static final int TALONFX_DRIVE_RIGHT_FRONT = 1;
        public static final int TALONFX_DRIVE_RIGHT_MID = 0;
        public static final int TALONFX_DRIVE_RIGHT_BACK = 2;

        public static final int TALONFX_SHOOTER_RIGHT = 0; // dummy port values
        public static final int TALONFX_SHOOTER_LEFT = 0; // dummy port values

        public static final int TALONFX_INTAKE = 0; // dummy port number
    }
    public static final class ShooterPorts { //temp name
        public static final int SHOOTER_RIGHT = 0;
        public static final int SHOOTER_LEFT = 1;
    }

    public static final class ControllerPorts {
        //public static final int decreaseButton = 5; //A
        //public static final int increaseButton = 6; //B

        //public static final int shooterOne = 3; //X
        //public static final int shooterTwo = 4; //Y

        public static final int leftClamps = 5; //LB
        public static final int rightClamps = 6; //RB

        public static final int leftFingers = 3; //X
        public static final int rightFingers = 4; //Y

        public static final int highGear = 1;
        public static final int climbGear = 2;
    }
    /**
     * Analog Port Numbers
     */
    public static final class AIO {
        public static final int HOOD_POT = 0;
    }

    public static final class DIO {
          public static final int DRIVE_LEFT_ENCODER_A = 0;
          public static final int DRIVE_LEFT_ENCODER_B = 0;

          public static final int DRIVE_RIGHT_ENCODER_A = 0;
          public static final int DRIVE_RIGHT_ENCODER_B = 0;
      }

    /**
     * PWM Port Numbers
     */
    public static final class PWM {
        public static final int HOOD_SERVO = 0;
    }

    public static final class PCM {
        public static final int DRIVE_SHIFTER_A = 0;
        public static final int CLIMB_SHIFTER_A = 0;
        public static final int DRIVE_SHIFTER_B = 0;
        public static final int CLIMB_SHIFTER_B = 0;
        
        // These are for climb bar A
        public static final int LEFTFINGER_AA = 0;
        public static final int LEFTFINGER_BA = 0;
        public static final int RIGHTFINGER_AA = 0;
        public static final int RIGHTFINGER_BA = 0;
        public static final int LEFTCLAMP_AA = 0;
        public static final int LEFTCLAMP_BA = 0;
        public static final int RIGHTCLAMP_AA = 0;
        public static final int RIGHTCLAMP_BA = 0;

        // These are for climb bar B
        public static final int LEFTFINGER_AB = 0;
        public static final int LEFTFINGER_BB = 0;
        public static final int RIGHTFINGER_AB = 0;
        public static final int RIGHTFINGER_BB = 0;
        public static final int LEFTCLAMP_AB = 0;
        public static final int LEFTCLAMP_BB = 0;
        public static final int RIGHTCLAMP_AB = 0;
        public static final int RIGHTCLAMP_BB = 0;

        public static final int INTAKE_A = 0; 
        public static final int INTAKE_B = 0;
    }
    
    public static final class DIGITALINPUT {
        public static final int LEFTLIMITSWITCH = 0;
        public static final int RIGHTLIMITSWITCH = 0;
    }

    // #endregion
}
