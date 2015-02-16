package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3499.robot.Metrics;
import org.usfirst.frc.team3499.robot.commands.UpdateDashboardCommand;

/**
 * Interface to the SmartDashboard
 */
public class DashboardSubsystem extends Subsystem {

    public void initDefaultCommand() {
        setDefaultCommand(new UpdateDashboardCommand());
    }

    public void update() {
        Metrics.read();
        SmartDashboard.putBoolean("Ramp Left", Metrics.sensorRampLeft);
        SmartDashboard.putBoolean("Ramp Right", Metrics.sensorRampRight);
        SmartDashboard.putBoolean("Tote Near", Metrics.sensorToteNear);
        SmartDashboard.putBoolean("Tote Far", Metrics.sensorToteFar);
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
        SmartDashboard.putDouble("Lift Motor", Metrics.liftMotorPercent);
        SmartDashboard.putDouble("Lift Scale", Metrics.liftMotorScale);
        SmartDashboard.putInt("Lift Encoder", Metrics.liftMotorEncoderPosition);
    }
}

