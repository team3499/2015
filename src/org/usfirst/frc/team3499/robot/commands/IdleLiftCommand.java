package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3499.robot.Robot;

/**
 * Returns the lift to the idle location.
 *
 * The idle location is above a tote on the ground, but below a tote captured
 * by the dogs.
 */
public class IdleLiftCommand extends Command {

    Timer timer = new Timer();
    double timeout = 4.0;
    double speed   = -0.2;  // TEMPORARY! TODO - Change back to -0.5 to -1.0
    public static int idlePosition = 1000;

    public IdleLiftCommand() {
        requires(Robot.liftSubsystem);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
        // We're above the idle position, switch direction of travel
        if (Robot.liftSubsystem.getEncPosition() > idlePosition) { speed = -speed; }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.liftSubsystem.set(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        int currentPosition = Robot.liftSubsystem.getEncPosition();
        return (speed < 0 && currentPosition >= idlePosition) ||
               (speed > 0 && currentPosition <= idlePosition) ||
               (timer.hasPeriodPassed(timeout));
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.liftSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
