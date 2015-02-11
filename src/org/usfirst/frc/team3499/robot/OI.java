package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3499.robot.commands.UpdateEventLightsCommand;
import org.usfirst.frc.team3499.robot.subsystems.EventLightsSubsystem;
import org.usfirst.frc.team3499.robot.Robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public static Joystick joystick = new Joystick(RobotMap.driveStickPort);

    public static Button button1 = new JoystickButton(joystick, 1);
    public static Button button2 = new JoystickButton(joystick, 2);
    public static Button button3 = new JoystickButton(joystick, 3);
    public static Button button4 = new JoystickButton(joystick, 4);
    public static Button button5 = new JoystickButton(joystick, 5);
    public static Button button6 = new JoystickButton(joystick, 6);
    public static Button button7 = new JoystickButton(joystick, 7);
    public static Button button8 = new JoystickButton(joystick, 8);

    public OI() {
        // Test lightshow PWM settings by attaching each lightshow to a button
        button2.whenPressed(new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.OFF));
        button3.whenPressed(new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.PARTIAL));
        button4.whenPressed(new UpdateEventLightsCommand(Robot.Sensor.TOTE, Robot.SensorState.FULL));
        button5.whenPressed(new UpdateEventLightsCommand(Robot.Sensor.RAMP_LEFT, Robot.SensorState.OFF));
        button6.whenPressed(new UpdateEventLightsCommand(Robot.Sensor.RAMP_LEFT, Robot.SensorState.FULL));
        button7.whenPressed(new UpdateEventLightsCommand(Robot.Sensor.RAMP_RIGHT, Robot.SensorState.OFF));
        button8.whenPressed(new UpdateEventLightsCommand(Robot.Sensor.RAMP_RIGHT, Robot.SensorState.FULL));
    }

}

