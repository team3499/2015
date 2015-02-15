package org.usfirst.frc.team3499.robot;

import java.lang.Math;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

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
    public static double crawlPercent = 0.2;     // percentage of full speed
    public static double rampThrottle = 0.02;    // max change per 10ms
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
                // TODO
                break;
            case RAW:
                updateRampSample(leftOutput, rightOutput);
                super.setLeftRightMotorOutputs(leftOutput, rightOutput);
                return;
        }

        setRampedLeftRightMotorOutputs(leftOutput, rightOutput);
    }

    // When not in RAW mode, only allow changes of rampThrottle per 10ms in the
    // value of motor output
    public void setRampedLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        double now = timer.get();
        double maxChange = Math.min((now - rampedLastSampleTime) * 100 * rampThrottle, 1.0);

        if (Math.abs(leftOutput - rampedLeftOutput) > maxChange) {
            if (leftOutput < rampedLeftOutput) { leftOutput = rampedLeftOutput - maxChange; }
            else { leftOutput = rampedLeftOutput + maxChange; }
        }
        if (Math.abs(rightOutput - rampedRightOutput) > maxChange) {
            if (rightOutput < rampedRightOutput) { rightOutput = rampedRightOutput - maxChange; }
            else { rightOutput = rampedRightOutput + maxChange; }
        }

        updateRampSample(now, leftOutput, rightOutput);
        super.setLeftRightMotorOutputs(leftOutput, rightOutput);
    }

    private void updateRampSample(double leftOutput, double rightOutput) {
        updateRampSample(timer.get(), leftOutput, rightOutput);
    }

    private void updateRampSample(double time, double leftOutput, double rightOutput) {
        rampedLastSampleTime = time;
        rampedLeftOutput     = leftOutput;
        rampedRightOutput    = rightOutput;
    }
}
