package org.usfirst.frc.team3499.robot;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Drive.Mode;

/**
 * Gather metrics gathered from various subsystems on the robot.
 */
public class Metrics {

    public static boolean  sensorRampLeft            = false;
    public static boolean  sensorRampRight           = false;
    public static boolean  sensorToteLeft            = false;
    public static boolean  sensorToteCenter          = false;
    public static boolean  sensorToteRight           = false;
    public static double[] driveMotorPercent         = { 0.0, 0.0, 0.0, 0.0 };
    public static double   driveMotorScale           = 0.0;
    public static String   driveMode                 = "NORMAL";
    public static boolean  driveThrottleLeft         = false;
    public static boolean  driveThrottleRight        = false;
    public static double   liftMotorPercent          = 0.0;
    public static double   liftMotorScale            = 0.0;
    public static int      liftMotorEncoderPosition  = 0;
    public static boolean  liftMotorLimitSwitchLower = false;
    public static boolean  liftMotorLimitSwitchUpper = false;

    public static void read() {
        readRamp();
        readTote();
        readDrive();
        readLift();
    }

    public static void readRamp() {
        sensorRampLeft  = Robot.rampProximitySubsystem.getLeft();
        sensorRampRight = Robot.rampProximitySubsystem.getRight();
    }

    public static void readTote() {
        sensorToteLeft   = Robot.toteProximitySubsystem.getLeft();
        sensorToteCenter = Robot.toteProximitySubsystem.getCenter();
        sensorToteRight  = Robot.toteProximitySubsystem.getRight();
    }

    public static void readDrive() {
        driveMotorPercent[0] = Robot.driveSubsystem.getMotorPWM(0) * 100.0;
        driveMotorPercent[1] = Robot.driveSubsystem.getMotorPWM(1) * 100.0;
        driveMotorPercent[2] = Robot.driveSubsystem.getMotorPWM(2) * -100.0;
        driveMotorPercent[3] = Robot.driveSubsystem.getMotorPWM(3) * -100.0;
        driveMotorScale      = OI.getDriveScale() * 100.0;
        driveThrottleLeft    = Robot.driveSubsystem.isThrottlingLeft();
        driveThrottleRight   = Robot.driveSubsystem.isThrottlingRight();
        Mode mode = Robot.driveSubsystem.getMode();
        switch (mode) {
            case NORMAL:
                driveMode = "NORMAL";
                break;
            case CRAWL:
                driveMode = "CRAWL";
                break;
            case RAMP:
                driveMode = "RAMP";
                break;
            case RAW:
                driveMode = "RAW";
                break;
        }
    }

    public static void readLift() {
        liftMotorPercent          = Robot.liftSubsystem.get() * 100.0;
        liftMotorScale            = OI.getLiftScale() * 100.0;
        liftMotorEncoderPosition  = Robot.liftSubsystem.getEncPosition();
        liftMotorLimitSwitchUpper = Robot.liftSubsystem.isAtTop();
        liftMotorLimitSwitchLower = Robot.liftSubsystem.isAtBottom();
    }
}
