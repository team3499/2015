
package org.usfirst.frc.team3499.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3499.robot.subsystems.*;
import org.usfirst.frc.team3499.robot.commands.*;
import org.usfirst.frc.team3499.robot.DebugLED;
import org.usfirst.frc.team3499.robot.Sensors;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    // Robot Subsystems
    public static DriveSubsystem         driveSubsystem;
    public static LiftSubsystem          liftSubsystem;
    public static EventLightsSubsystem   eventLightsSubsystem;
    public static ToteProximitySubsystem toteProximitySubsystem;
    public static RampProximitySubsystem rampProximitySubsystem;
    public static DashboardSubsystem     dashboardSubsystem;

    // Operator Interface
    public static OI oi;

    // Sensor state
    public static Sensors sensors;

    public static DebugLED debugLED;

    AutonomousCommandGroup autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // autonomousCommand = new ExampleCommand();
        driveSubsystem         = new DriveSubsystem();
        liftSubsystem          = new LiftSubsystem();
        eventLightsSubsystem   = new EventLightsSubsystem();
        toteProximitySubsystem = new ToteProximitySubsystem();
        rampProximitySubsystem = new RampProximitySubsystem();
        dashboardSubsystem     = new DashboardSubsystem();

        oi = new OI();

        sensors = new Sensors();

        debugLED = new DebugLED();

        dashboardSubsystem.init();

        autonomousCommand = new AutonomousCommandGroup();  // TODO - configurable
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        driveSubsystem.init();
        liftSubsystem.init();

        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();

        driveSubsystem.init();
        liftSubsystem.init();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        driveSubsystem.stop();
        liftSubsystem.stop();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
