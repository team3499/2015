package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.Robot;

/**
 *
 */
public class UpdateDashboardCommand extends Command {

    public UpdateDashboardCommand() {
        requires(Robot.dashboardSubsystem);
    }

    protected void initialize() {

    }

    protected void execute() {
        Robot.dashboardSubsystem.update();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}
