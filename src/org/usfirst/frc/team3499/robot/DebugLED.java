package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.DigitalOutput;

import org.usfirst.frc.team3499.robot.RobotMap;

/**
 * Debug LED plugged into DIO 9
 */
public class DebugLED {

    private static DigitalOutput dio = new DigitalOutput(RobotMap.debugLedPort);
    private boolean state;    // LED on/off state

    public void DebugLED() {
        off();
    }

    public void set(boolean state) {
        this.state = state;
        dio.set(state);
    }

    public void on() {
        set(true);
    }

    public void off() {
        set(false);
    }

    public void toggle() {
        set(!state);
    }
}
