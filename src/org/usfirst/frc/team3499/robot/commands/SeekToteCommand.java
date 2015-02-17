package org.usfirst.frc.team3499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3499.robot.Robot;

/**
 * This command will manuver the robot forward and turn to envelope a tote
 * between the arms.  It assumes the tote is approached from the skinny side.
 */
public class SeekToteCommand extends Command {

    enum State {
        START,       // determining initial state by checking detectors
        SEEKING,     // robot is moving forward until any detector sets
        CENTERING,   // robot is turning to center -- R/L detectors clear
        ENVELOPING,  // robot is moving forward until center detector sets
        FINISHED;    // robot has the tote
    };

    Timer timer = new Timer();
    public double timeout = 10.0;
    State state = State.START;


    public SeekToteCommand() {
        requires(Robot.driveSubsystem);
        requires(Robot.toteProximitySubsystem);
    }

    protected void initialize() {
        timer.reset();
        timer.start();
        state = State.START;
    }

    protected void execute() {
        boolean left   = Robot.toteProximitySubsystem.getLeft();
        boolean center = Robot.toteProximitySubsystem.getCenter();
        boolean right  = Robot.toteProximitySubsystem.getRight();
        switch (state) {
            case START:
                if (center) { state = State.FINISHED; }
                else if (left || right) { state = State.CENTERING; }
                else { state = State.SEEKING; }
                break;
            case SEEKING:
                if (center) {
                    state = State.FINISHED;
                    Robot.driveSubsystem.stop();
                } else if (left || right) {
                    state = State.CENTERING;
                    Robot.driveSubsystem.stop();
                } else {
                    Robot.driveSubsystem.set(0.2, 0.0);
                }
                break;
            case CENTERING:
                if (left && right) {
                    state = State.ENVELOPING;
                    Robot.driveSubsystem.stop();
                } else if (left) {
                    Robot.driveSubsystem.set(0.0, 0.2);
                } else if (right) {
                    Robot.driveSubsystem.set(0.0, -0.2);
                } else {
                    state = State.ENVELOPING;
                    Robot.driveSubsystem.stop();
                }
                break;
            case ENVELOPING:
                if (center) {
                    state = State.FINISHED;
                    Robot.driveSubsystem.stop();
                } else {
                    Robot.driveSubsystem.set(0.2, 0.0);
                }
                break;
            case FINISHED:
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == State.FINISHED || timer.hasPeriodPassed(timeout);
    }

    // Called once after isFinished returns true
    protected void end() {
        state = State.START;
        Robot.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
