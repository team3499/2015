package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Sensors.Location;

/**
 * Periodic command to read the left, center, and right tote sensor states.
 */
public class DetectToteCommand extends Command {

    public DetectToteCommand() {
        requires(Robot.toteProximitySubsystem);
    }

    protected void initialize() {

    }

    protected void execute() {
        Robot.sensors.setState(Location.TOTE_LEFT, Robot.toteProximitySubsystem.getLeft());
        Robot.sensors.setState(Location.TOTE_CENTER, Robot.toteProximitySubsystem.getCenter());
        Robot.sensors.setState(Location.TOTE_RIGHT, Robot.toteProximitySubsystem.getRight());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
