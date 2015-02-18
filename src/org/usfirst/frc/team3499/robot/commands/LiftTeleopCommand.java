package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.OI;

/**
 * The default command for the LiftSubsystem.
 *
 * This command is run when no other commands are requiring the
 * LiftSubsystem.  It takes operator input from a single joystick
 * and translates it to lift motor movement.
 */
public class LiftTeleopCommand extends Command {
	
	private Solenoid solenoid = new Solenoid(60, 0);

    public LiftTeleopCommand() {
        requires(Robot.liftSubsystem);
    }

    protected void initialize() {
    	solenoid.set(false);
    }

    protected void execute() {
        double move  = OI.getLiftMove();
        double scale = OI.getLiftScale();
        boolean drop = OI.isDropBox();
        Robot.liftSubsystem.set(move * scale);
        solenoid.set(drop);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.liftSubsystem.stop();
        solenoid.set(false);
    }

    protected void interrupted() {
        // do nothing -- we're coming back since we're the default command
    }
}
