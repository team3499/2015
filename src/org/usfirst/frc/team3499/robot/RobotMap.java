package org.usfirst.frc.team3499.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // Digital IO ports
    public static int DIO0 = 0;
    public static int DIO1 = 1;
    public static int DIO2 = 2;
    public static int DIO3 = 3;
    public static int DIO4 = 4;
    public static int DIO5 = 5;
    public static int DIO6 = 6;
    public static int DIO7 = 7;
    public static int DIO8 = 8;
    public static int DIO9 = 9;

    // PWM ports
    public static int PWM0 = 0;
    public static int PWM1 = 1;
    public static int PWM2 = 2;
    public static int PWM3 = 3;
    public static int PWM4 = 4;
    public static int PWM5 = 5;
    public static int PWM6 = 6;
    public static int PWM7 = 7;
    public static int PWM8 = 8;
    public static int PWM9 = 9;

    // Analog ports
    public static int ANALOG0 = 0;
    public static int ANALOG1 = 1;
    public static int ANALOG2 = 2;
    public static int ANALOG3 = 3;

    // Relay ports
    public static int RELAY0 = 0;
    public static int RELAY1 = 1;
    public static int RELAY2 = 2;
    public static int RELAY3 = 3;

    // USB ports
    public static int USB0 = 0;
    public static int USB1 = 1;

    // Joytick ports (Driver Station)
    public static int JOY0 = 0;
    public static int JOY1 = 1;
    public static int JOY2 = 2;
    public static int JOY3 = 3;
    public static int JOY4 = 4;
    public static int JOY5 = 5;
    public static int JOY6 = 6;
    public static int JOY7 = 7;

    // Joystick buttons
    public static int TRIGGER = 1;
    public static int HAT2    = 2;
    public static int HAT3    = 3;
    public static int HAT4    = 4;
    public static int HAT5    = 5;
    public static int BASE6   = 6;
    public static int BASE7   = 7;
    public static int BASE8   = 8;
    public static int BASE9   = 9;
    public static int BASE10  = 10;
    public static int BASE11  = 11;

    ////////////////////////////////////////////////////////////

    // Joystick port assignments from Drive Station
    public static int driveStickPort = JOY0;
    public static int liftStickPort  = JOY1;

    // Drive motor PWM assignments
    public static int driveMotorLFPort = PWM2;
    public static int driveMotorLRPort = PWM3;
    public static int driveMotorRFPort = PWM4;
    public static int driveMotorRRPort = PWM5;

    // TalonSRX assignment
    public static int liftMotorMasterCanId   = 50;
    public static int liftMotorFollowerCanId = 51;

    // Tote proximity sensor assignments
    public static int toteProximitySensorLeftPort   = DIO0;
    public static int toteProximitySensorCenterPort = DIO1;
    public static int toteProximitySensorRightPort  = DIO2;

    // Ramp proximity sensor assignments
    public static int rampProximitySensorLeftPort  = DIO3;
    public static int rampProximitySensorRightPort = DIO4;

    // LED Arduino
    public static int ledArduinoPort = PWM9;

    // Debug LED
    public static int debugLedPort = DIO9;
    
    // PCM (pneumatics) assignments
    public static int pControlCAN = 60;
    public static int pControlPortLift = 1;
    public static int pControlPortPush = 0;
}
