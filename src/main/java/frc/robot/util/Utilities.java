package frc.robot.util;

public class Utilities {

    public static double conformAngle(double angle) {
        while (angle < 0) {
          angle += 360;
        }
        return angle % 360;
    }

    /**
     * Clips the input to a maximum or minimum if the input is out of bounds
     *
     * @param input The input to clip
     * @param min   The lower bound
     * @param max   The upper bound
     * @return The clipped input
     */
    public static double clip(double input, double min, double max) {
        return Math.max(min, Math.min(input, max));
    }
}
