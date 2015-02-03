package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.subsystems.LightshowSubsystem;
import org.usfirst.frc.team3499.robot.Robot;

/**
 *  Displays a preconfigured lightshow on the ring LEDs.
 *
 *  This command is designed to be used as a one-show command.  It will
 *  set the PWM value and finish.  It can be queued from anywhere, e.g.
 *
 *  new DisplayLightshowCommand(LightshowSubsystem.Pattern.BLACKOUT).start();
 */
public class DisplayLightshowCommand extends Command {

    private static LightshowSubsystem.Pattern currentPattern;
    private LightshowSubsystem.Pattern pattern;

    public DisplayLightshowCommand() {
        requires(Robot.lightshowSubsystem);
    }

    public DisplayLightshowCommand(LightshowSubsystem.Pattern pattern) {
        this.pattern = pattern;
    }

    protected void initialize() {
        if (currentPattern != pattern) {
            currentPattern = pattern;
            Robot.lightshowSubsystem.setShow(pattern);
        }
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
