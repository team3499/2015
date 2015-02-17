package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

import org.usfirst.frc.team3499.robot.RobotMap;
import org.usfirst.frc.team3499.robot.commands.LiftTeleopCommand;

/**
 * The lift motor subsystem -- single Talon SRX controlling the tote elevator
 * and (if finished in time) the pneumatic dogs.
 */
public class LiftSubsystem extends Subsystem {

    public static double P = 0.4;
    public static double I = 0.00001;
    public static double D = 0.0;

    public static double scale = 0.5;   // run at 50% max speed

    CANTalon motor = new CANTalon(RobotMap.liftMotorCanId);

    public void initDefaultCommand() {
        setDefaultCommand(new LiftTeleopCommand());
    }

    public void init() {
        enable();
        set(0.0);
    }

    public void enable() {
        motor.enableControl();
        motor.changeControlMode(ControlMode.PercentVbus);
        motor.enableBrakeMode(true);
        motor.reverseSensor(true);
        motor.setPID(P, I, D);
        motor.setSafetyEnabled(true);
        motor.setExpiration(0.100);
        motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
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

    public void zeroEncoder() {
        motor.setPosition(0);
    }

    public double get() {
        return motor.get();
    }

    public void set(double value) {
        motor.set(value * scale);
    }

    public void stop() {
        motor.stopMotor();
    }
}

