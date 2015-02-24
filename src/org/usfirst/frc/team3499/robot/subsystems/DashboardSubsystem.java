package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Metrics;
import org.usfirst.frc.team3499.robot.commands.UpdateDashboardCommand;

/**
 * Interface to the SmartDashboard
 */
public class DashboardSubsystem extends Subsystem {

    public void initDefaultCommand() {
        setDefaultCommand(new UpdateDashboardCommand());
    }

    public void init() {
        // Establish default values for lift PID controller on dashboard
        double v = SmartDashboard.getNumber("Lift P", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Lift P", Robot.liftSubsystem.P); }
        v = SmartDashboard.getNumber("Lift I", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Lift I", Robot.liftSubsystem.I); }
        v = SmartDashboard.getNumber("Lift D", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Lift D", Robot.liftSubsystem.D); }
    }

    public void update() {
        Metrics.read();
        SmartDashboard.putBoolean("Ramp Left", Metrics.sensorRampLeft);
        SmartDashboard.putBoolean("Ramp Right", Metrics.sensorRampRight);
        SmartDashboard.putBoolean("Tote Left", Metrics.sensorToteLeft);
        SmartDashboard.putBoolean("Tote Center", Metrics.sensorToteCenter);
        SmartDashboard.putBoolean("Tote Right", Metrics.sensorToteRight);
        SmartDashboard.putBoolean("Lift Upper", Metrics.liftMotorLimitSwitchUpper);
        SmartDashboard.putBoolean("Lift Lower", Metrics.liftMotorLimitSwitchLower);
        SmartDashboard.putDouble("Drive Motor LF", Metrics.driveMotorPercent[0]);
        SmartDashboard.putDouble("Drive Motor LR", Metrics.driveMotorPercent[1]);
        SmartDashboard.putDouble("Drive Motor RF", Metrics.driveMotorPercent[2]);
        SmartDashboard.putDouble("Drive Motor RR", Metrics.driveMotorPercent[3]);
        SmartDashboard.putDouble("Drive Scale", Metrics.driveMotorScale);
        SmartDashboard.putString("Drive Mode", Metrics.driveMode);
        SmartDashboard.putBoolean("Drive Throttling Left", Metrics.driveThrottleLeft);
        SmartDashboard.putBoolean("Drive Throttling Right", Metrics.driveThrottleRight);
        SmartDashboard.putDouble("Master Lift Motor", Metrics.liftMotorMasterSpeed);
        SmartDashboard.putDouble("Follower Lift Motor", Metrics.liftMotorFollowerSpeed);
        SmartDashboard.putDouble("Avg of Lift Motors", Metrics.liftMotorAvg);
        SmartDashboard.putDouble("Lift Scale", Metrics.liftMotorScale);
        SmartDashboard.putInt("Lift Encoder", Metrics.liftMotorEncoderPosition);

        Metrics.liftMotorP = SmartDashboard.getNumber("Lift P", Robot.liftSubsystem.P);
        Metrics.liftMotorI = SmartDashboard.getNumber("Lift I", Robot.liftSubsystem.I);
        Metrics.liftMotorD = SmartDashboard.getNumber("Lift D", Robot.liftSubsystem.D);
        Metrics.sync();
    }
}

