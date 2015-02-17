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

    PWM arduinoPWM = new PWM(RobotMap.ledArduinoPort);
    Robot.SensorState top   = Robot.SensorState.OFF;
    Robot.SensorState left  = Robot.SensorState.OFF;
    Robot.SensorState right = Robot.SensorState.OFF;

    public void initDefaultCommand() {

    }

    public void setState(Robot.Sensor sensor, Robot.SensorState state) {
      switch (sensor) {
          case TOTE:       setTopState(state);   break;
          case RAMP_LEFT:  setLeftState(state);  break;
          case RAMP_RIGHT: setRightState(state); break;
      }
    }

    public Robot.SensorState getState(Robot.Sensor sensor) {
      switch (sensor) {
          case TOTE:       return getTopState();
          case RAMP_LEFT:  return getLeftState();
          case RAMP_RIGHT: return getRightState();
      }

      return null;
    }

    public void setTopState(Robot.SensorState state) {
        top = state;
        updatePWM();
    }

    public Robot.SensorState getTopState() {
        return top;
    }

    public void setLeftState(Robot.SensorState state) {
        if (state == Robot.SensorState.PARTIAL) { state = Robot.SensorState.FULL; }

        left = state;
        updatePWM();
    }

    public Robot.SensorState getLeftState() {
        return left;
    }

    public void setRightState(Robot.SensorState state) {
        if (state == Robot.SensorState.PARTIAL) { state = Robot.SensorState.FULL; }

        right = state;
        updatePWM();
    }

    public Robot.SensorState getRightState() {
        return right;
    }

    //
    //   See github.com/team3499/eventlights
    //
    //    PWM        TOP        LEFT       RIGHT
    //    ---------  ---------  ---------  ----------
    //     01        OFF        OFF        OFF
    //     20        RED        RED        RED
    //     40        RED        RED        GREEN
    //     60        RED        GREEN      RED
    //     80        RED        GREEN      GREEN
    //    100        VIOLET     RED        RED
    //    120        VIOLET     RED        GREEN
    //    140        VIOLET     GREEN      RED
    //    160        VIOLET     GREEN      GREEN
    //    180        GREEN      RED        RED
    //    200        GREEN      RED        GREEN
    //    220        GREEN      GREEN      RED
    //    240        GREEN      GREEN      GREEN
    //
    public void updatePWM() {
        int pwm = 20;

        if (right == Robot.SensorState.FULL) { pwm += 20; }
        else { pwm += 0; }
        if (left == Robot.SensorState.FULL) { pwm += 20; }
        else { pwm += 0; }
        if (top == Robot.SensorState.FULL) { pwm += 160; }
        else if (top == Robot.SensorState.PARTIAL) { pwm += 80; }
        else { pwm += 0; }

        setRaw(pwm);
    }

    public void setRaw(int pwm) {
        arduinoPWM.setRaw(pwm);
    }
}

