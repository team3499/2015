package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Sensors.Location;

/**
 * Periodic command to read the left and right ramp sensor states.
 */
public class DetectRampCommand extends Command {

    public DetectRampCommand() {
        requires(Robot.rampProximitySubsystem);
    }

    protected void initialize() {

    }

    protected void execute() {
        Robot.sensors.setState(Location.RAMP_LEFT, Robot.rampProximitySubsystem.getLeft());
        Robot.sensors.setState(Location.RAMP_RIGHT, Robot.rampProximitySubsystem.getRight());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
