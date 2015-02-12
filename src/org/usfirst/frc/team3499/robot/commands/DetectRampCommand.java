package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.commands.UpdateEventLightsCommand;
import org.usfirst.frc.team3499.robot.subsystems.RampProximitySubsystem;
import org.usfirst.frc.team3499.robot.Robot;

/**
 *
 */
public class DetectRampCommand extends Command {

    public DetectRampCommand() {
        requires(Robot.rampProximitySubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.rampProximitySubsystem.getLeft()) {
            new UpdateEventLightsCommand(Robot.Sensor.RAMP_LEFT, Robot.SensorState.FULL);
        } else {
            new UpdateEventLightsCommand(Robot.Sensor.RAMP_LEFT, Robot.SensorState.OFF);
        }
        if (Robot.rampProximitySubsystem.getRight()) {
            new UpdateEventLightsCommand(Robot.Sensor.RAMP_RIGHT, Robot.SensorState.FULL);
        } else {
            new UpdateEventLightsCommand(Robot.Sensor.RAMP_RIGHT, Robot.SensorState.OFF);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
