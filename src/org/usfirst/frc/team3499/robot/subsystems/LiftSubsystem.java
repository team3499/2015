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

    public static double P = 4.0;
    public static double I = 0.00001;
    public static double D = 0.0;

    public static double scale = 1000.0;   // 1000 ticks per 10ms max

    CANTalon motor         = new CANTalon(RobotMap.liftMotorMasterCanId);
    CANTalon motorFollower = new CANTalon(RobotMap.liftMotorFollowerCanId);
    
    Solenoid solenoidLeft  = new Solenoid(RobotMap.pControlCAN, RobotMap.pControlPortLeft);
    Solenoid solenoidRight = new Solenoid(RobotMap.pControlCAN, RobotMap.pControlPortRight);

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
        motor.setPID(P, I, D);
        motor.setSafetyEnabled(true);
        motor.setExpiration(0.200);
        motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        motorFollower.enableControl();
        motorFollower.changeControlMode(ControlMode.Follower);
        motorFollower.enableBrakeMode(true);
        motorFollower.setPID(P, I, D);
        motorFollower.setSafetyEnabled(true);
        motorFollower.setExpiration(0.200);
        motorFollower.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    }

    public boolean isAtBottom() {
        return motor.isRevLimitSwitchClosed();
    }

    public boolean isAtTop() {
        return motor.isFwdLimitSwitchClosed();
    }

    public int getEncPosition() {
        return motor.getEncPosition();
    }

    public double getMaster() {
        return motor.get();
    }
    
    public double getFollower() {
    	return motorFollower.get();
    }

    public void set(double value) {
        if (value > 0.0) { motor.set(value * scale * 0.1); }
        else { motor.set(value * scale); }
        motorFollower.set(motor.getDeviceID());
    }

    public void stop() {
        motor.stopMotor();
    }
    
    public void open(boolean open) {
    	solenoidLeft.set(open);
    	solenoidRight.set(open);
    }
}

