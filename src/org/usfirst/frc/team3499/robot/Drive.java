package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Extends RobotDrive to allow crawl and ramp control
 */
public class Drive extends RobotDrive {

    public enum Mode {
        NORMAL,      // Normal driving with squared input
        CRAWL,       // Scaled down input
        RAMP,        // Ramp tuned control
        RAW;         // Unfiltered
    };

    public static Mode mode = Mode.NORMAL;
    public double crawlPercent = 0.3;

    public Drive(SpeedController frontLeftMotor,
                 SpeedController rearLeftMotor,
                 SpeedController frontRightMotor,
                 SpeedController rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        switch (mode) {
            case NORMAL:
                break;
            case CRAWL:
                leftOutput = leftOutput * crawlPercent;
                rightOutput = rightOutput * crawlPercent;
                break;
            case RAMP:
                // TODO
                break;
            case RAW:
                // TODO
                break;
        }
        super.setLeftRightMotorOutputs(leftOutput, rightOutput);
    }
}
