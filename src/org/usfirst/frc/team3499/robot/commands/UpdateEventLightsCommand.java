package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Sensors.Location;
import org.usfirst.frc.team3499.robot.subsystems.EventLightsSubsystem.Color;

/**
 *  Displays sensor state on the ring LEDs.
 */
public class UpdateEventLightsCommand extends Command {

    public UpdateEventLightsCommand() {
        requires(Robot.eventLightsSubsystem);
    }

    protected void initialize() {

    }

    protected void execute() {
        // set tote indicator
        if (Robot.sensors.getState(Location.TOTE_LEFT) ||
            Robot.sensors.getState(Location.TOTE_RIGHT) ||
            Robot.sensors.getState(Location.TOTE_CENTER)) {
            if (Robot.sensors.getState(Location.TOTE_CENTER)) {
                Robot.eventLightsSubsystem.setTopColor(Color.GREEN);
            } else {
                Robot.eventLightsSubsystem.setTopColor(Color.BLUE);
            }
        } else {
            Robot.eventLightsSubsystem.setTopColor(Color.RED);
        }

        // set ramp left indicator
        if (Robot.sensors.getState(Location.RAMP_LEFT)) {
            Robot.eventLightsSubsystem.setLeftColor(Color.GREEN);
        } else {
            Robot.eventLightsSubsystem.setLeftColor(Color.RED);
        }

        // set ramp right indicator
        if (Robot.sensors.getState(Location.RAMP_RIGHT)) {
            Robot.eventLightsSubsystem.setRightColor(Color.GREEN);
        } else {
            Robot.eventLightsSubsystem.setRightColor(Color.RED);
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        Robot.sensors.clearDirty();
    }

    protected void interrupted() {
        end();
    }
}
