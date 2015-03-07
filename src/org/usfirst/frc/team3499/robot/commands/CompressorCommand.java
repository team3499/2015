package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.OI;

/**
 *
 */
public class CompressorCommand extends Command {

    public CompressorCommand() {
        requires(Robot.compressorSubsystem);
    }

    protected void initialize() {

    }

    protected void execute() {
        if (OI.isCompEnable()) { Robot.compressorSubsystem.start(); }
        else { Robot.compressorSubsystem.stop(); }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.compressorSubsystem.stop();
    }

    protected void interrupted() {
        end();
    }
}
