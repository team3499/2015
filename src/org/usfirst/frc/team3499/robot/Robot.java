
package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

import org.usfirst.frc.team3499.robot.commands.*;
import org.usfirst.frc.team3499.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static LEDSubsystem ledSubsystem;
    public static LEDCommand ledCommand;

    public static TalonSubsystem talonSubsystem;
    public static TalonCommand talonCommand1;
    public static TalonCommand talonCommand2;
    
    public static Jaguar motor1;
    public static Jaguar motor2;
    public static Jaguar motor3;
    public static Jaguar motor4;
    public static RobotDrive robotDrive;
    public static OI oi;

    Command autonomousCommand;
    
    CameraServer server;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // autonomousCommand = new ExampleCommand();
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam1");
        ledSubsystem = new LEDSubsystem();
        ledCommand = new LEDCommand();
        talonSubsystem = new TalonSubsystem();
        talonCommand1 = new TalonCommand();
        talonCommand2 = new TalonCommand();
        oi = new OI();

        motor1 = new Jaguar(RobotMap.driveMotorLFPort);   // Left Front
        motor2 = new Jaguar(RobotMap.driveMotorLRPort);   // Left Rear
        motor3 = new Jaguar(RobotMap.driveMotorRFPort);   // Right Front
        motor4 = new Jaguar(RobotMap.driveMotorRRPort);   // Right Rear

        robotDrive = new RobotDrive(motor1, motor2, motor3, motor4);
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(MotorType.kRearRight, true);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        robotDrive.stopMotor();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	//System.out.println(OI.joystick.getAxis(AxisType.kZ));
        //robotDrive.arcadeDrive(OI.joystick);
		//System.out.println(OI.joystick.getAxis(AxisType.kZ));
        talonCommand1.settSpeed(OI.joystick.getAxis(AxisType.kZ));
		OI.button1.whileHeld(talonCommand1);//new TalonCommand(OI.joystick.getAxis(AxisType.kZ)));
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
