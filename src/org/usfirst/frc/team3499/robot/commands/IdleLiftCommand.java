package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3499.robot.Robot;

/**
 *
 */
public class IdleLiftCommand extends Command {

    Timer timer = new Timer();

    public IdleLiftCommand() {
        requires(Robot.liftSubsystem);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.liftSubsystem.set(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.liftSubsystem.getEncPosition() == 1000 ||
               timer.hasPeriodPassed(4.0);
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
