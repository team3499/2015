package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team3499.robot.commands.DetectToteCommand;
import org.usfirst.frc.team3499.robot.RobotMap;

/**
 *  The tote proximity subsystem consists of two IR proximity sensors with
 *  different trip distances.
 */
public class ToteProximitySubsystem extends Subsystem {

    DigitalInput sensorLeft   = new DigitalInput(RobotMap.toteProximitySensorLeftPort);
    DigitalInput sensorCenter = new DigitalInput(RobotMap.toteProximitySensorCenterPort);
    DigitalInput sensorRight  = new DigitalInput(RobotMap.toteProximitySensorRightPort);

    public void initDefaultCommand() {
        setDefaultCommand(new DetectToteCommand());
    }

    public boolean get() {
        return getLeft() || getCenter() || getRight();
    }

    public boolean getLeft() {
        return !sensorLeft.get();
    }

    public boolean getCenter() {
        return !sensorCenter.get();
    }

    public boolean getRight() {
        return !sensorRight.get();
    }
}

