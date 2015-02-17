package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.commands.UpdateEventLightsCommand;
import org.usfirst.frc.team3499.robot.subsystems.ToteProximitySubsystem;
import org.usfirst.frc.team3499.robot.Robot;

/**
 *
 */
public class DetectToteCommand extends Command {

    UpdateEventLightsCommand lightsOffCommand;
    UpdateEventLightsCommand lightsSideCommand;
    UpdateEventLightsCommand lightsCenterCommand;

    public DetectToteCommand() {
        requires(Robot.toteProximitySubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        lightsOffCommand    = new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.OFF);
        lightsSideCommand   = new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.PARTIAL);
        lightsCenterCommand = new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.FULL);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean left   = Robot.toteProximitySubsystem.getLeft();
        boolean center = Robot.toteProximitySubsystem.getCenter();
        boolean right  = Robot.toteProximitySubsystem.getRight();

        if (center) {
            if (!lightsCenterCommand.isRunning()) {
                lightsOffCommand.cancel();
                lightsSideCommand.cancel();
                lightsCenterCommand.start();
            }
        } else if (left || right) {
            if (!lightsSideCommand.isRunning()) {
                lightsOffCommand.cancel();
                lightsCenterCommand.cancel();
                lightsSideCommand.start();
            }
        } else {
            if (!lightsOffCommand.isRunning()) {
                lightsSideCommand.cancel();
                lightsCenterCommand.cancel();
                lightsOffCommand.start();
            }
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
