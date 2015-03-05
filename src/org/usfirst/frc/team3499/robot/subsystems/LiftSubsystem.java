package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

import org.usfirst.frc.team3499.robot.RobotMap;
import org.usfirst.frc.team3499.robot.commands.LiftTeleopCommand;

/**
 * The lift motor subsystem -- single Talon SRX controlling the tote elevator
 * and (if finished in time) the pneumatic dogs.
 */
public class LiftSubsystem extends Subsystem {

    public static double P = 1.0;
    public static double I = 0.0;
    public static double D = 0.0;

    public static double scale = 1000.0;   // 1000 ticks per 10ms max

    public static int[]  softLimit = { 10, 10000 };

    private double holdPosition = 0.0;

    CANTalon motor         = new CANTalon(RobotMap.liftMotorMasterCanId);
    CANTalon motorFollower = new CANTalon(RobotMap.liftMotorFollowerCanId);

    Solenoid solenoidLift  = new Solenoid(RobotMap.pControlCAN, RobotMap.pControlPortLift);
    Solenoid solenoidPush  = new Solenoid(RobotMap.pControlCAN, RobotMap.pControlPortPush);

    public void initDefaultCommand() {
        setDefaultCommand(new LiftTeleopCommand());
    }

    public void init() {
        enable();
        set(0.0);
    }

    public void enable() {
        motor.enableControl();
        motor.changeControlMode(ControlMode.Speed);
        motor.enableBrakeMode(true);
        motor.setSafetyEnabled(false);
        motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        motorFollower.enableControl();
        motorFollower.changeControlMode(ControlMode.Follower);
        motorFollower.enableBrakeMode(true);
        motorFollower.setSafetyEnabled(false);
        motorFollower.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        updatePID();
    }

    public void updatePID() {
        motor.setPID(P, I, D);
        motorFollower.setPID(P, I, D);
    }

    public void enableSoftLimits(boolean enable) {
        motor.setForwardSoftLimit(softLimit[0]);
        motor.setReverseSoftLimit(softLimit[1]);
        motor.enableForwardSoftLimit(enable);
        motor.enableReverseSoftLimit(enable);
    }

    public boolean isAtBottom() {
        return motor.isFwdLimitSwitchClosed();
    }

    public boolean isNearBottom() {
        return motor.getPosition() < (getBottomSoftLimit() + 1000.0);
    }

    public double getBottomSoftLimit() {
        return (double)softLimit[0];
    }

    public boolean isAtTop() {
        return motor.isRevLimitSwitchClosed();
    }

    public boolean isNearTop() {
        return motor.getPosition() > (getTopSoftLimit() - 1000.0);
    }

    public double getTopSoftLimit() {
        return (double)softLimit[1];
    }

    public int getEncPosition() {
        return motor.getEncPosition();
    }

    public void zeroEncoder() {
        motor.setPosition(0);
    }

    public double getMaster() {
        return motor.get();
    }

    public double getFollower() {
        return motorFollower.get();
    }

    public void set(double value) {
        cancelHoldPosition();
        double scaledValue = value * scale;
        if (value > 0.0) {  // down
            if (isNearBottom() && scaledValue > 0.4) { scaledValue = 0.4; }
            motor.set(scaledValue * 0.5);
        } else {            // up
            if (isNearTop() && scaledValue < -0.4) { scaledValue = -0.4; }
            motor.set(scaledValue);
        }
        motorFollower.set(motor.getDeviceID());
    }

    // This method will attempt to hold the current position.  It combats the
    // tendancy of the lift to drop under the weight of totes.
    public void holdPosition() {
        if (holdPosition < 0.1) { holdPosition = motor.getPosition(); }

        if (motor.getPosition() < holdPosition) { motor.set(-0.2); }
        else { motor.set(0.0); }
    }

    public void cancelHoldPosition() {
        holdPosition = 0.0;
    }

    public void stop() {
        set(0.0);
    }

    public void disable() {
        motor.disableControl();
        motorFollower.disableControl();
    }

    public void open(boolean open) {
        solenoidLift.set(open);
    }

    public void push(boolean push) {
        solenoidPush.set(push);
    }
}
