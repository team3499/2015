package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Metrics;

/**
 * Drives the robot into the auto zone, either turning 90 degrees with
 * a tote in the lift or straight to allow another team to manipulate
 * our tote.
 */
public class DriveAutoCommand extends Command {

    enum State {
        START,
        BACKING,
        TURNING,
        FORWARD,
        STOP;
    };

    Timer timer = new Timer();
    State state = State.START;

    public DriveAutoCommand() {
        requires(Robot.driveSubsystem);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
        state = State.START;
    }

    protected void execute() {
        switch (state) {
            case START:
                if (Metrics.autoSimple && !Metrics.autoStandard) {
                    // Dashboard selecting simple auto routine (drive forard)
                    state = State.FORWARD;
                } else {
                    // Dashboard selecting standard auto routine
                    state = State.BACKING;
                }
                timer.reset();
                timer.start();
                break;
            case BACKING:
                Robot.driveSubsystem.set(-Metrics.autoSpeedBack, 0.0);
                if (timer.hasPeriodPassed(Metrics.autoTimerBack)) {
                    state = State.TURNING;
                    timer.reset();
                    timer.start();
                }
                break;
            case TURNING:
                Robot.driveSubsystem.set(0.0, Metrics.autoSpeedTurn);
                if (timer.hasPeriodPassed(Metrics.autoTimerTurn)) {
                    state = State.FORWARD;
                    timer.reset();
                    timer.start();
                }
                break;
            case FORWARD:
                Robot.driveSubsystem.set(Metrics.autoSpeedForward, 0.0);
                if (timer.hasPeriodPassed(Metrics.autoTimerForward)) {
                    state = State.STOP;
                }
                break;
        }
    }

    protected boolean isFinished() {
        return state == State.STOP;
    }

    protected void end() {
        Robot.driveSubsystem.stop();
    }

    protected void interrupted() {
        end();
    }
}
