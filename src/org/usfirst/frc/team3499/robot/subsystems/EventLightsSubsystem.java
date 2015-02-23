package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWM;

import org.usfirst.frc.team3499.robot.RobotMap;
import org.usfirst.frc.team3499.robot.Robot;

/**
 *  Select and display a preset LED color pattern on the front
 *  and rear ring lights for driver feedback.
 */
public class EventLightsSubsystem extends Subsystem {

    public enum Color {
        OFF,
        RED,
        GREEN,
        BLUE,
        WHITE;
    };

    PWM arduinoPWM = new PWM(RobotMap.ledArduinoPort);

    Color top   = Color.RED;
    Color left  = Color.RED;
    Color right = Color.RED;

    public void initDefaultCommand() {

    }

    public void setTopColor(Color color) {
        top = color;
        updatePWM();
    }

    public Color getTopColor() {
        return top;
    }

    public void setLeftColor(Color color) {
        left = color;
        updatePWM();
    }

    public Color getLeftColor() {
        return left;
    }

    public void setRightColor(Color color) {
        right = color;
        updatePWM();
    }

    public Color getRightColor() {
        return right;
    }

    //
    //   See github.com/team3499/eventlights
    //
    //    PWM        TOP        LEFT       RIGHT
    //    ---------  ---------  ---------  ----------
    //    000        OFF        OFF        OFF
    //    001        RED        RED        RED
    //    021        RED        RED        GREEN
    //    041        RED        GREEN      RED
    //    061        RED        GREEN      GREEN
    //    081        BLUE       RED        RED
    //    101        BLUE       RED        GREEN
    //    121        BLUE       GREEN      RED
    //    141        BLUE       GREEN      GREEN
    //    161        GREEN      RED        RED
    //    181        GREEN      RED        GREEN
    //    201        GREEN      GREEN      RED
    //    221        GREEN      GREEN      GREEN
    //    241        WHITE      WHITE      WHITE
    //
    public void updatePWM() {
        int pwm = 1;

        if (right == Color.GREEN) { pwm += 20; }
        else { pwm += 0; }
        if (left == Color.GREEN) { pwm += 40; }
        else { pwm += 0; }
        if (top == Color.GREEN) { pwm += 160; }
        else if (top == Color.BLUE) { pwm += 80; }
        else { pwm += 0; }

        setRaw(pwm);
    }

    public void setRaw(int pwm) {
        arduinoPWM.setRaw(pwm);
    }
}

