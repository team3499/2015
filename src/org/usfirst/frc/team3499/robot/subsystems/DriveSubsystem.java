package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Jaguar;

import org.usfirst.frc.team3499.robot.RobotMap;
import org.usfirst.frc.team3499.robot.Drive;
import org.usfirst.frc.team3499.robot.Drive.Mode;
import org.usfirst.frc.team3499.robot.commands.DriveTeleopCommand;

/**
 *  The drive motor subsystem -- 4 Jaguar motor controllers under PWM
 *  control ganged in twos on each side of the chassis.
 */
public class DriveSubsystem extends Subsystem {

    Jaguar[] motors = { new Jaguar(RobotMap.driveMotorLFPort),
                        new Jaguar(RobotMap.driveMotorLRPort),
                        new Jaguar(RobotMap.driveMotorRFPort),
                        new Jaguar(RobotMap.driveMotorRRPort) };

    private Drive drive = new Drive(motors[0], motors[1], motors[2], motors[3]);

    public void initDefaultCommand() {
        setDefaultCommand(new DriveTeleopCommand());
    }

    public void init() {
        for (Jaguar motor : motors) { init(motor); }
    }

    public void enable() {
        for (Jaguar motor : motors) { enable(motor); }
    }

    private void init(Jaguar motor) {
        enable(motor);
        motor.set(0.0);
    }

    private void enable(Jaguar motor) {
        motor.Feed();   // motor.enableControl();
        motor.setSafetyEnabled(true);
        motor.setExpiration(0.100);
    }

    public Drive.Mode getMode() {
        return drive.mode;
    }

    public void setMode(Mode mode) {
        drive.mode = mode;
    }

    public void set(double move, double rotate) {
        drive.arcadeDrive(move, rotate);
    }

    public void set(double move, double rotate, Mode mode) {
        drive.mode = mode;
        set(move, rotate);
    }

    public void stop() {
        drive.stopMotor();
    }
}

