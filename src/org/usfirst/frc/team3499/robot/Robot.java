
package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.Jaguar;
//import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.RobotDrive.MotorType;

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

    public static TalonSubsystem talonSubsystem;
    public static TalonCommand talonCommandUp, talonCommandDown, talonCommandUpMax, talonCommandDownMax;

    public static DriveSubsystem driveSubsystem;
    public static DriveCommand driveCommandMax, driveCommandCrawl, driveCommandInput;

    public static EventLightsSubsystem eventLightsSubsystem;
    public static ToteProximitySubsystem toteProximitySubsystem;
    public static RampProximitySubsystem rampProximitySubsystem;

    public static OI oi;

    Command autonomousCommand;

    // CameraServer server;

    public enum Sensor {
        TOTE,
        RAMP_LEFT,
        RAMP_RIGHT;
    };

    public enum SensorState {
        OFF,
        PARTIAL,
        FULL;
    };

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // autonomousCommand = new ExampleCommand();
        // server = CameraServer.getInstance();
        /// server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        // server.startAutomaticCapture("cam1");

        talonSubsystem = new TalonSubsystem();
        talonCommandUp = new TalonCommand();
        talonCommandDown = new TalonCommand();
        talonCommandUpMax = new TalonCommand();
        talonCommandDownMax = new TalonCommand();

        driveSubsystem = new DriveSubsystem();
        driveCommandMax = new DriveCommand();
        driveCommandCrawl = new DriveCommand();
        driveCommandInput = new DriveCommand();
        
        eventLightsSubsystem = new EventLightsSubsystem();
        toteProximitySubsystem = new ToteProximitySubsystem();
        rampProximitySubsystem = new RampProximitySubsystem();

        oi = new OI();

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
        driveCommandMax.setDriveSpeed(1.0);
        driveCommandCrawl.setDriveSpeed(0.1);

        talonCommandUp.settSpeed(0.2);
        talonCommandDown.settSpeed(-0.2);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        driveCommandInput.setDriveSpeed((-(OI.dJoystick.getAxis(AxisType.kZ)) + 1.1) / 2.2);
        OI.dButton1.whileHeld(driveCommandCrawl);
        OI.dButton1.whenReleased(driveCommandInput);
        OI.dButton2.whileHeld(driveCommandMax);
        OI.dButton2.whenReleased(driveCommandInput);

        OI.lButton2.whileHeld(talonCommandUp);
        OI.lButton3.whileHeld(talonCommandDown);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
