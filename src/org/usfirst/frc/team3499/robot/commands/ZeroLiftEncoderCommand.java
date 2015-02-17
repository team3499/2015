package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3499.robot.Robot;

/**
 * Zeros the lift encoder by dropping it to the bottom limit switch
 * within 3 seconds.
 */
public class ZeroLiftEncoderCommand extends Command {

    Timer timer = new Timer();

    public ZeroLiftEncoderCommand() {
        requires(Robot.liftSubsystem);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
    }

    protected void execute() {
        Robot.liftSubsystem.set(-0.5);
    }

    protected boolean isFinished() {
        if (Robot.liftSubsystem.isAtBottom()) {
            Robot.liftSubsystem.zeroEncoder();
            return true;
        } else if (timer.hasPeriodPassed(3.0)) {
            return true;
        }
        return false;
    }

    protected void end() {
        Robot.liftSubsystem.stop();
    }

    protected void interrupted() {
        end();
    }
}