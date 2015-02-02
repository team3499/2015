package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc.team3499.robot.RobotMap;

/**
 *  The tote proximity subsystem consists of two IR proximity sensors with
 *  different trip distances.
 */
public class ToteProximitySubsystem extends Subsystem {

    DigitalInput sensorNear = new DigitalInput(RobotMap.toteProximitySensorNearPort);
    DigitalInput sensorFar  = new DigitalInput(RobotMap.toteProximitySensorFarPort);

    public enum Location { NONE, NEAR, FAR, BOTH }

    public void initDefaultCommand() {

    }

    public Location get() {
        boolean near = sensorNear.get();
        boolean far  = sensorFar.get();

        if (near && far) { return Location.BOTH; }
        else if (far && !near) { return Location.FAR; }
        else if (near && !far) { return Location.NEAR; }

        return Location.NONE;
    }
}

