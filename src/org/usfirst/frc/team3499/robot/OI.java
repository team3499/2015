package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3499.robot.RobotMap;
import org.usfirst.frc.team3499.robot.Drive.Mode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public static Joystick driveStick = new Joystick(RobotMap.driveStickPort);
    public static Joystick liftStick  = new Joystick(RobotMap.liftStickPort);

    public static Button driveCrawlButton = new JoystickButton(driveStick, RobotMap.TRIGGER);
    public static Button driveRampLButton = new JoystickButton(driveStick, RobotMap.HAT4);
    public static Button driveRampRButton = new JoystickButton(driveStick, RobotMap.HAT5);
    public static Button driveRawLButton  = new JoystickButton(driveStick, RobotMap.BASE6);
    public static Button driveRawRButton  = new JoystickButton(driveStick, RobotMap.BASE11);
    
    public static Button pReleaseButton   = new JoystickButton(liftStick, RobotMap.TRIGGER);
    public static Button pPushButton      = new JoystickButton(liftStick, RobotMap.HAT3);
    public static Button pCompButton      = new JoystickButton(liftStick, RobotMap.HAT2);

    public static double getDriveMove() {
        return -driveStick.getY();
    }

    public static double getDriveRotate() {
        return -driveStick.getX();
    }

    public static Mode getDriveMode() {
        if (driveRawLButton.get() || driveRawRButton.get()) { return Mode.RAW; }
        if (driveRampLButton.get() || driveRampRButton.get()) { return Mode.RAMP; }
        if (driveCrawlButton.get()) { return Mode.CRAWL; }

        return Mode.NORMAL;
    }

    public static double getDriveScale() {
        return (-driveStick.getZ() + 1.5) / 2.5;   // invert and scale Z axis to 0.2 - 1.0
    }

    public static boolean isRampLeftEnable() {
        return driveRampLButton.get();
    }

    public static boolean isRampRightEnable() {
        return driveRampRButton.get();
    }

    public static double getLiftMove() {
        return liftStick.getY();
    }

    public static double getLiftScale() {
        return (-liftStick.getZ() + 1.5) / 2.5;    // invert and scale Z axis to 0.2 - 1.0
    }
    
    public static boolean isDropBox() {
    	return pReleaseButton.get();
    }
    
    public static boolean isPushBox() {
    	return pPushButton.get();
    }

    public static boolean isCompEnable() {
       return pCompButton.get();
    }

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
}

