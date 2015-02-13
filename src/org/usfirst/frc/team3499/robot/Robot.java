
package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Joystick.AxisType;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.*;

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
    
    int session;
    Image frame;

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
    	
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
    	
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
        NIVision.IMAQdxStartAcquisition(session);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        //NIVision.IMAQdxGrab(session, frame, 1);
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        driveCommandMax.setDriveSpeed(1.0);
        driveCommandCrawl.setDriveSpeed(0.1);
        driveCommandInput.setDriveSpeed((-(OI.dJoystick.getAxis(AxisType.kZ)) + 1.1) / 2.2);
        
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
        OI.dButton1.whileHeld(driveCommandCrawl);
        OI.dButton2.whileHeld(driveCommandMax);
        
        OI.dButton1.whenReleased(driveCommandInput);
        OI.dButton2.whenReleased(driveCommandInput);

        OI.lButton2.whileHeld(talonCommandUp);
        OI.lButton3.whileHeld(talonCommandDown);
        
        while (driveCommandCrawl.isRunning()) {
    		do {
    			driveCommandCrawl.setDriveSpeed(driveCommandCrawl.getDriveSpeed() - 0.05);
    		} while (driveCommandCrawl.getDriveSpeed() > 0.1);
        	driveCommandInput.setDriveSpeed(driveCommandCrawl.getDriveSpeed());
        };
        while (driveCommandMax.isRunning()) {
        		do {
        			driveCommandMax.setDriveSpeed(driveCommandMax.getDriveSpeed() + 0.05);
        		} while (driveCommandMax.getDriveSpeed() < 1.0);
        	driveCommandInput.setDriveSpeed(driveCommandMax.getDriveSpeed());
        };
        while (driveCommandInput.isRunning()) {
        	if (driveCommandInput.getDriveSpeed() == 1.0) {
        		do {
        			driveCommandInput.setDriveSpeed(driveCommandInput.getDriveSpeed() - 0.05);
        		} while (driveCommandInput.getDriveSpeed() > ((-(OI.dJoystick.getAxis(AxisType.kZ)) + 1.1) / 2.2));
        	};
        	if (driveCommandInput.getDriveSpeed() == 0.1) {
        		do {
        			driveCommandInput.setDriveSpeed(driveCommandInput.getDriveSpeed() + 0.05);
        		} while (driveCommandInput.getDriveSpeed() < ((-(OI.dJoystick.getAxis(AxisType.kZ)) + 1.1) / 2.2));
        	};
        	driveCommandMax.setDriveSpeed(driveCommandInput.getDriveSpeed());
        	driveCommandCrawl.setDriveSpeed(driveCommandInput.getDriveSpeed());
        };
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
