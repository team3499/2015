package org.usfirst.frc.team3499.robot.commands;

import org.usfirst.frc.team3499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {

    private double driveSpeed = 1.0;

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveSubsystem);
    }

    public DriveCommand(double maxSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveSubsystem);
        this.setDriveSpeed(maxSpeed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveSubsystem.startDrive(getDriveSpeed());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveSubsystem.startDrive(getDriveSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveSubsystem.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.driveSubsystem.stopMotor();
    }

    public double getDriveSpeed() {
        return driveSpeed;
    }

    public void setDriveSpeed(double driveSpeed) {
        this.driveSpeed = driveSpeed;
    }
}
