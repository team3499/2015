package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.OI;

/**
 * The default command for the DriveSubsystem.
 *
 * This command is run when no other commands are requiring the
 * DriveSubsystem.  It takes operator input from a single joystick
 * and translates it to left/right chassis motor PWM signals.
 */
public class DriveTeleopCommand extends Command {

    public DriveTeleopCommand() {
        requires(Robot.driveSubsystem);
    }

    protected void initialize() {

    }

    protected void execute() {
        double x = OI.driveStick.getX();
        double y = OI.driveStick.getY();
        Robot.driveSubsystem.set(y, y);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.driveSubsystem.stop();
    }

    protected void interrupted() {
        // do nothing -- we're coming back since we're the default command
    }
}
