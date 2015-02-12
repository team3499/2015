package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.commands.UpdateEventLightsCommand;
import org.usfirst.frc.team3499.robot.subsystems.ToteProximitySubsystem;
import org.usfirst.frc.team3499.robot.Robot;

/**
 *
 */
public class DetectToteCommand extends Command {

    public DetectToteCommand() {
        requires(Robot.toteProximitySubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        ToteProximitySubsystem.Location location = Robot.toteProximitySubsystem.get();

        switch (location) {
            case FAR:
                new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.PARTIAL).start();
                break;
            case NEAR:
                new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.PARTIAL).start();
                break;
            case BOTH:
                new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.FULL).start();
                break;
            default:
                new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.OFF).start();
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
