package org.usfirst.frc.team3499.robot.commands;

import org.usfirst.frc.team3499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LEDCommand extends Command {

    public LEDCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ledSubsystem);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ledSubsystem.on();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ledSubsystem.on();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ledSubsystem.off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ledSubsystem.off();
    }
}
