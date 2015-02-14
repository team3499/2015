package org.usfirst.frc.team3499.robot.subsystems;

import org.usfirst.frc.team3499.robot.OI;
import org.usfirst.frc.team3499.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Jaguar motor1 = new Jaguar(RobotMap.driveMotorLFPort);
    Jaguar motor2 = new Jaguar(RobotMap.driveMotorLRPort);
    Jaguar motor3 = new Jaguar(RobotMap.driveMotorRFPort);
    Jaguar motor4 = new Jaguar(RobotMap.driveMotorRRPort);

    RobotDrive robotDrive = new RobotDrive(motor1, motor2, motor3, motor4);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void startDrive(double maxSpeed) {
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(MotorType.kRearRight, true);
        robotDrive.arcadeDrive(OI.dJoystick);
        robotDrive.setMaxOutput(maxSpeed);
    }

    public void stopMotor() {
        robotDrive.stopMotor();
    }
}

