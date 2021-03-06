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

        boolean b = SmartDashboard.getBoolean("Simple Auto", Metrics.autoSimple);
        SmartDashboard.putBoolean("Simple Auto", b);
        b = SmartDashboard.getBoolean("Standard Auto", Metrics.autoStandard);
        SmartDashboard.putBoolean("Standard Auto", b);

        v = SmartDashboard.getNumber("Auto Speed Back", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Auto Speed Back", Metrics.autoSpeedBack); }
        v = SmartDashboard.getNumber("Auto Speed Turn", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Auto Speed Turn", Metrics.autoSpeedTurn); }
        v = SmartDashboard.getNumber("Auto Speed Forward", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Auto Speed Forward", Metrics.autoSpeedForward); }
        v = SmartDashboard.getNumber("Auto Speed Ramp", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Auto Speed Ramp", Metrics.autoSpeedRamp); }

        v = SmartDashboard.getNumber("Auto Timer Back", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Auto Timer Back", Metrics.autoTimerBack); }
        v = SmartDashboard.getNumber("Auto Timer Turn", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Auto Timer Turn", Metrics.autoTimerTurn); }
        v = SmartDashboard.getNumber("Auto Timer Forward", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Auto Timer Forward", Metrics.autoTimerForward); }

        v = SmartDashboard.getNumber("Derate Left Drive", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Derate Left Drive", Metrics.derateLeftDrive); }
        v = SmartDashboard.getNumber("Derate Right Drive", -1001.0);
        if (v < -1000.0) { SmartDashboard.putNumber("Derate Right Drive", Metrics.derateRightDrive); }
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
        SmartDashboard.putNumber("Drive Motor LF", Metrics.driveMotorPercent[0]);
        SmartDashboard.putNumber("Drive Motor LR", Metrics.driveMotorPercent[1]);
        SmartDashboard.putNumber("Drive Motor RF", Metrics.driveMotorPercent[2]);
        SmartDashboard.putNumber("Drive Motor RR", Metrics.driveMotorPercent[3]);
        SmartDashboard.putNumber("Drive Scale", Metrics.driveMotorScale);
        SmartDashboard.putString("Drive Mode", Metrics.driveMode);
        SmartDashboard.putBoolean("Drive Throttling Left", Metrics.driveThrottleLeft);
        SmartDashboard.putBoolean("Drive Throttling Right", Metrics.driveThrottleRight);
        SmartDashboard.putNumber("Master Lift Motor", Metrics.liftMotorMasterSpeed);
        SmartDashboard.putNumber("Follower Lift Motor", Metrics.liftMotorFollowerSpeed);
        SmartDashboard.putNumber("Avg of Lift Motors", Metrics.liftMotorAvg);
        SmartDashboard.putNumber("Lift Scale", Metrics.liftMotorScale);
        SmartDashboard.putNumber("Lift Encoder", Metrics.liftMotorEncoderPosition);

        Metrics.liftMotorP = SmartDashboard.getNumber("Lift P", Robot.liftSubsystem.P);
        Metrics.liftMotorI = SmartDashboard.getNumber("Lift I", Robot.liftSubsystem.I);
        Metrics.liftMotorD = SmartDashboard.getNumber("Lift D", Robot.liftSubsystem.D);

        Metrics.autoSimple   = SmartDashboard.getBoolean("Simple Auto", Metrics.autoSimple);
        Metrics.autoStandard = SmartDashboard.getBoolean("Standard Auto", Metrics.autoStandard);

        Metrics.autoSpeedBack    = SmartDashboard.getNumber("Auto Speed Back", Metrics.autoSpeedBack);
        Metrics.autoSpeedTurn    = SmartDashboard.getNumber("Auto Speed Turn", Metrics.autoSpeedTurn);
        Metrics.autoSpeedForward = SmartDashboard.getNumber("Auto Speed Forward", Metrics.autoSpeedForward);
        Metrics.autoSpeedRamp    = SmartDashboard.getNumber("Auto Speed Ramp", Metrics.autoSpeedRamp);

        Metrics.autoTimerBack    = SmartDashboard.getNumber("Auto Timer Back", Metrics.autoTimerBack);
        Metrics.autoTimerTurn    = SmartDashboard.getNumber("Auto Timer Turn", Metrics.autoTimerTurn);
        Metrics.autoTimerForward = SmartDashboard.getNumber("Auto Timer Forward", Metrics.autoTimerForward);

        Metrics.derateLeftDrive  = SmartDashboard.getNumber("Derate Left Drive", Metrics.derateLeftDrive);
        Metrics.derateRightDrive = SmartDashboard.getNumber("Derate Right Drive", Metrics.derateRightDrive);

        Metrics.sync();
    }
}

