package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team3499.robot.commands.DetectRampCommand;
import org.usfirst.frc.team3499.robot.RobotMap;

/**
 *
 */
public class RampProximitySubsystem extends Subsystem {

    DigitalInput sensorLeft  = new DigitalInput(RobotMap.rampProximitySensorLeftPort);
    DigitalInput sensorRight = new DigitalInput(RobotMap.rampProximitySensorRightPort);

    public void initDefaultCommand() {
        new DetectRampCommand();
    }

    public boolean getLeft() {
        return !sensorLeft.get();
    }

    public boolean getRight() {
        return !sensorRight.get();
    }
}

