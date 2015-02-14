package org.usfirst.frc.team3499.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3499.robot.*;

/**
 *
 */
public class TalonSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    double tSpeed = 0.0;
    CANTalon talonMotor = new CANTalon(RobotMap.tMotor1);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void move(double speed) {
        talonMotor.changeControlMode(ControlMode.PercentVbus);
        talonMotor.enableControl();
        tSpeed = speed;
        talonMotor.set(tSpeed);
    }

    public void getSpeed(){
        System.out.println(talonMotor.getEncVelocity());
    }
}

