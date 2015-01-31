
package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.CANTalon;

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

    public static Jaguar motor1, motor2, motor3, motor4;
    
    public static CANTalon tMotor1, tMotor2;
    
    public static RobotDrive robotDrive, tRobotDrive;
    
    public static OI oi;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // autonomousCommand = new ExampleCommand();
    	
    	// Instantiate subsystems
        ledSubsystem = new LEDSubsystem();
        
        // Instantiate commands
        ledCommand = new LEDCommand();
        oi = new OI();
        
        // Instantiate Jaguar motors
        motor1 = new Jaguar(RobotMap.driveMotorLFPort);   // Left Front
        motor2 = new Jaguar(RobotMap.driveMotorLRPort);   // Left Rear
        motor3 = new Jaguar(RobotMap.driveMotorRFPort);   // Right Front
        motor4 = new Jaguar(RobotMap.driveMotorRRPort);   // Right Rear
        
        // Instantiate CAN TalonSRX motors
        tMotor1 = new CANTalon(RobotMap.tDriveMotorLPort);
        tMotor2 = new CANTalon(RobotMap.tDriveMotorRPort);

        // Set up robot drive with Jaguar motors
        robotDrive = new RobotDrive(motor1, motor2, motor3, motor4);
        robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
        robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
        robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(MotorType.kRearRight, true);
        
        // Set up robot drive with CAN TalonSRX motors
        tRobotDrive = new RobotDrive(tMotor1, tMotor2);
        
        // TO DO: SET MOTORS, NOT SURE HOW TO DO WITH CAN
        
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

        // Robot drive with Jaguars
        robotDrive.arcadeDrive(OI.joystick);
        
        // Robot drive with TalonSRXs
        //tRobotDrive.arcadeDrive(OI.joystick);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
