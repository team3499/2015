package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

import org.usfirst.frc.team3499.robot.RobotMap;
import org.usfirst.frc.team3499.robot.Drive;
import org.usfirst.frc.team3499.robot.Drive.Mode;
import org.usfirst.frc.team3499.robot.commands.DriveTeleopCommand;

/**
 *  The drive motor subsystem -- 4 Jaguar motor controllers under PWM
 *  control ganged in twos on each side of the chassis.
 */
public class DriveSubsystem extends Subsystem {

    // Jaguar motor1 = new Jaguar(RobotMap.driveMotorLFPort);
    // Jaguar motor2 = new Jaguar(RobotMap.driveMotorLRPort);
    // Jaguar motor3 = new Jaguar(RobotMap.driveMotorRFPort);
    // Jaguar motor4 = new Jaguar(RobotMap.driveMotorRRPort);

    // drive_simple START
    private CANTalon motor1 = new CANTalon(RobotMap.liftMotorCanId);
    private CANTalon motor2 = new CANTalon(RobotMap.liftMotorCanId + 1);
    private CANTalon motor3 = new CANTalon(RobotMap.liftMotorCanId + 2);
    private CANTalon motor4 = new CANTalon(RobotMap.liftMotorCanId + 3);
    // drive_simple END

    private Drive drive = new Drive(motor1, motor2, motor3, motor4);

    public void initDefaultCommand() {
        setDefaultCommand(new DriveTeleopCommand());
    }

    public void init() {
        // drive_simple START
        motor1.enableControl();
        motor1.changeControlMode(ControlMode.PercentVbus);
        motor1.setSafetyEnabled(true);
        motor1.setExpiration(0.100);
        motor1.set(0.0);
        // drive_simple END
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

