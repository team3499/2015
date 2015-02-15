package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

import org.usfirst.frc.team3499.robot.RobotMap;
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

    private CANTalon motor1 = new CANTalon(RobotMap.liftMotorCanId);

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

    public void set(double left, double right) {
        motor1.set(left);
        // motor2.set(left);
        // motor3.set(right);
        // motor4.set(right);
    }

    public void stop() {
        motor1.stopMotor();
        // motor2.stopMotor();
        // motor3.stopMotor();
        // motor4.stopMotor();
    }
}

