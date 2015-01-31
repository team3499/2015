package org.usfirst.frc.team3499.robot.subsystems;

import org.usfirst.frc.team3499.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    DigitalOutput led = new DigitalOutput(RobotMap.ledPort);
    boolean ledState  = false;

    public void initDefaultCommand() {
        
    }

    public void set(boolean state) {
        ledState = state;
        led.set(ledState);
    }

    public void on() {
        set(true);
    }

    public void off() {
        set(false);
    }

    public void toggle() {
        set(!ledState);
    }
}

