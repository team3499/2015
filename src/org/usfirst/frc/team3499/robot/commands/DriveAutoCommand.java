package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3499.robot.Robot;
import org.usfirst.frc.team3499.robot.Metrics;
import org.usfirst.frc.team3499.robot.Drive.Mode;

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
        requires(Robot.rampProximitySubsystem);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
        state = State.START;
    }

    protected void execute() {
        switch (state) {
            case START:
                if (Metrics.autoDisable) {
                    // Dashboard selecting auto disable
                    state = State.STOP;
                } else if (Metrics.autoStandard) {
                    // Dashboard selecting standard auto routine
                    state = State.BACKING;
                } else {
                    // Dashboard defaulting to simple auto routine
                    state = State.FORWARD;
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
                Robot.driveSubsystem.set(0.0, Metrics.autoSpeedTurn, Mode.RAW);
                if (timer.hasPeriodPassed(Metrics.autoTimerTurn)) {
                    state = State.FORWARD;
                    timer.reset();
                    timer.start();
                }
                break;
            case FORWARD:
                // if (Robot.rampProximitySubsystem.getLeft() || Robot.rampProximitySubsystem.getRight()) {
                //     Robot.driveSubsystem.set(Metrics.autoSpeedRamp, 0.0);
                //     timer.reset();
                //     timer.start();
                // } else {
                    Robot.driveSubsystem.set(Metrics.autoSpeedForward, 0.0, Mode.RAW);
                // }
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
