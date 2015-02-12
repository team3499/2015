package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.subsystems.EventLightsSubsystem;
import org.usfirst.frc.team3499.robot.Robot;

/**
 *  Displays sensor state on the ring LEDs.
 *
 *  This is one-shot command -- allocate, start, trash it.
 */
public class UpdateEventLightsCommand extends Command {

    private Robot.Sensor      sensor;
    private Robot.SensorState state;

    private UpdateEventLightsCommand() {
        requires(Robot.eventLightsSubsystem);
    }

    public UpdateEventLightsCommand(Robot.Sensor sensor, Robot.SensorState state) {
        this.sensor = sensor;
        this.state  = state;

        requires(Robot.eventLightsSubsystem);
    }

    protected void initialize() {
        switch (sensor) {
            case TOTE:
                Robot.eventLightsSubsystem.setTopState(state);
                break;
            case RAMP_LEFT:
                Robot.eventLightsSubsystem.setLeftState(state);
                break;
            case RAMP_RIGHT:
                Robot.eventLightsSubsystem.setRightState(state);
                break;
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
        end();
    }
}
