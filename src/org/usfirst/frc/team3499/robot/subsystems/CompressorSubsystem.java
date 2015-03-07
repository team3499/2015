package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

import org.usfirst.frc.team3499.robot.RobotMap;
import org.usfirst.frc.team3499.robot.commands.CompressorCommand;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {

    Compressor compressor = new Compressor(RobotMap.pControlCAN);

    public void initDefaultCommand() {
        setDefaultCommand(new CompressorCommand());
    }

    public void start() {
        compressor.start();
    }

    public void stop() {
        compressor.stop();
    }
}

