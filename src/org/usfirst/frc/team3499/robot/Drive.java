package org.usfirst.frc.team3499.robot;

import java.lang.Math;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    public static double crawlPercent = 0.4;     // percentage of full speed
    public static double rampThrottle = 0.02;    // max change per 10ms
    public static boolean throttleLeftActive  = false;  // is currently throttling input
    public static boolean throttleRightActive = false;  // is currently throttling input
    public static double derateLeft  = 1.0;             // amount to derate the left motors
    public static double derateRight = 1.0;             // amount to derate the right motors
    private static Timer timer = new Timer();

    private double rampedLastSampleTime;
    private double rampedLeftOutput;
    private double rampedRightOutput;

    public Drive(SpeedController frontLeftMotor,
                 SpeedController rearLeftMotor,
                 SpeedController frontRightMotor,
                 SpeedController rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        timer.start();
        updateRampSample(0.0, 0.0);
    }

    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {

        switch (mode) {
            case NORMAL:
                leftOutput  = leftOutput * OI.getDriveScale();
                rightOutput = rightOutput * OI.getDriveScale();
                break;
            case CRAWL:
                leftOutput  = leftOutput * crawlPercent;
                rightOutput = rightOutput * crawlPercent;
                break;
            case RAMP:
                if (OI.isRampRightEnable()) { rightOutput = 0.0; }
                else if (OI.isRampLeftEnable()) { leftOutput  = 0.0; }
                leftOutput  = leftOutput * crawlPercent;
                rightOutput = rightOutput * crawlPercent;
                break;
            case RAW:
                updateRampSample(leftOutput, rightOutput);
                super.setLeftRightMotorOutputs(leftOutput, rightOutput*0.75);
                //super.setLeftRightMotorOutputs(leftOutput, rightOutput);
                return;
        }

        setRampedLeftRightMotorOutputs(leftOutput, rightOutput);
    }

    // When not in RAW mode, only allow changes of rampThrottle per 10ms in the
    // value of motor output
    public void setRampedLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        double now = timer.get();
        double maxChange = Math.min((now - rampedLastSampleTime) * 100 * rampThrottle, 1.0);
        throttleLeftActive  = false;
        throttleRightActive = false;

        if (Math.abs(leftOutput - rampedLeftOutput) > maxChange) {
            throttleLeftActive  = true;
            if (leftOutput < rampedLeftOutput) { leftOutput = rampedLeftOutput - maxChange; }
            else { leftOutput = rampedLeftOutput + maxChange; }
        }
        if (Math.abs(rightOutput - rampedRightOutput) > maxChange) {
            throttleRightActive = true;
            if (rightOutput < rampedRightOutput) { rightOutput = rampedRightOutput - maxChange; }
            else { rightOutput = rampedRightOutput + maxChange; }
        }

        // apply derating to correct for geartrain
        leftOutput  *= derateLeft;
        rightOutput *= derateRight;

        updateRampSample(now, leftOutput, rightOutput);
        super.setLeftRightMotorOutputs(leftOutput, rightOutput*0.75);
        //super.setLeftRightMotorOutputs(leftOutput, rightOutput);
    }

    private void updateRampSample(double leftOutput, double rightOutput) {
        updateRampSample(timer.get(), leftOutput, rightOutput);
    }

    private void updateRampSample(double time, double leftOutput, double rightOutput) {
        // DEBUG Drive Output
        SmartDashboard.putDouble("DEBUG LEFT", leftOutput);
        SmartDashboard.putDouble("DEBUG RIGHT", rightOutput);

        rampedLastSampleTime = time;
        rampedLeftOutput     = leftOutput;
        rampedRightOutput    = rightOutput;
    }
}
