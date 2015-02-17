package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Robot.SensorState;

/**
 * Cycle through the available EventLightsSubsystem states and end.
 */
public class TestEventLightsCommand extends Command {

    Timer timer  = new Timer();
    boolean done = false;
    int state    = 0;

    public TestEventLightsCommand() {
        requires(Robot.eventLightsSubsystem);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
        state = 0;
    }

    protected void execute() {
        if (state <= 12) { Robot.eventLightsSubsystem.setRaw(state * 20); }
        else {
            switch (state) {
                case 13:
                    updateEventLights(SensorState.OFF, SensorState.OFF, SensorState.OFF);
                    break;
                case 14:
                    updateEventLights(SensorState.OFF, SensorState.OFF, SensorState.OFF);
                    break;
                default:
                    done = true;
                    break;
            }
        }
        if (timer.hasPeriodPassed(0.5)) { state++; }
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
        updateEventLights(SensorState.OFF, SensorState.OFF, SensorState.OFF);
    }

    protected void interrupted() {
        end();
    }

    private void updateEventLights(SensorState top, SensorState left, SensorState right) {
        Robot.eventLightsSubsystem.setTopState(top);
        Robot.eventLightsSubsystem.setLeftState(top);
        Robot.eventLightsSubsystem.setRightState(top);
    }
}
