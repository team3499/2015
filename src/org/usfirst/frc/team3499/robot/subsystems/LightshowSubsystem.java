package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWM;

import org.usfirst.frc.team3499.robot.RobotMap;

/**
 *  Select and display a preset LED color pattern on the front
 *  and rear ring lights for driver feedback.
 */
public class LightshowSubsystem extends Subsystem {

    // Preset patterns available -- the values show coorrespond to
    // the pulse widths defined in lightshow/lightshow.ino running
    // on the LED Arduino.
    public enum Pattern {
        BLACKOUT   (1),
        RAMP_NONE  (42),
        RAMP_LEFT  (84),
        RAMP_RIGHT (126),
        RAMP_BOTH  (168),
        AUTONOMOUS (210),
        CAMERA     (252);

        private final int pwmValue;

        Pattern(int pwmValue) { this.pwmValue = pwmValue; }

        private int pwmValue() { return pwmValue; }
    };

    PWM arduinoPWM = new PWM(RobotMap.ledArduinoPort);
    Pattern show;

    public void initDefaultCommand() {

    }

    public void setShow(Pattern show) {
        if (this.show != show) {
            this.show = show;
            setPWM(show.pwmValue());
        }
    }

    public Pattern getShow() {
        return show;
    }

    public void setPWM(int value) {
        arduinoPWM.setRaw(value);
    }
}

