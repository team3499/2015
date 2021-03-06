package org.usfirst.frc.team3499.robot.commands;

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

    public LiftTeleopCommand() {
        requires(Robot.liftSubsystem);
    }

    protected void initialize() {

    }

    protected void execute() {
        double move  = OI.getLiftMove();
        double scale = OI.getLiftScale();
        boolean drop = OI.isDropBox();
        boolean push = OI.isPushBox();

        if (move < 0.1 && move > -0.1) { Robot.liftSubsystem.holdPosition(); }
        else { Robot.liftSubsystem.set(move * scale); }

        Robot.liftSubsystem.open(drop);
        Robot.liftSubsystem.push(push);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.liftSubsystem.stop();
        Robot.liftSubsystem.open(false);
    }

    protected void interrupted() {
        // do nothing -- we're coming back since we're the default command
    }
}
