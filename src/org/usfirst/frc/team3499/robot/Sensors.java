package org.usfirst.frc.team3499.robot;

import org.usfirst.frc.team3499.robot.commands.UpdateEventLightsCommand;

/**
 * Class to store the state of sensors received from periodic commands
 * like DetectRampCommand and DetectToteCommand.
 *
 * Will queue an UpdateEventLightsCommand when necessary.
 *
 * NOTE! Only call methods of this object from a command.  We use the
 * scheduler to preserve "atomicity".
 */
public class Sensors {

    public enum Location {
        TOTE_LEFT,
        TOTE_CENTER,
        TOTE_RIGHT,
        RAMP_LEFT,
        RAMP_RIGHT;
    }

    boolean toteLeft;
    boolean toteCenter;
    boolean toteRight;
    boolean rampLeft;
    boolean rampRight;

    boolean dirty;

    public Sensors() {
        toteLeft = false;
        toteRight = false;
        toteCenter = false;
        rampLeft = false;
        rampRight = false;
        dirty = true;
    }

    public boolean getState(Location location) {
        switch(location) {
            case TOTE_LEFT:   return toteLeft;
            case TOTE_CENTER: return toteCenter;
            case TOTE_RIGHT:  return toteRight;
            case RAMP_LEFT:   return rampLeft;
            case RAMP_RIGHT:  return rampRight;
        }

        return false;
    }

    public void setState(Location location, boolean state) {
        switch(location) {
            case TOTE_LEFT:
                if (toteLeft != state) { setDirty(); }
                toteLeft = state;
                break;
            case TOTE_CENTER:
                if (toteCenter != state) { setDirty(); }
                toteCenter = state;
                break;
            case TOTE_RIGHT:
                if (toteRight != state) { setDirty(); }
                toteRight = state;
                break;
            case RAMP_LEFT:
                if (rampLeft != state) { setDirty(); }
                rampLeft = state;
                break;
            case RAMP_RIGHT:
                if (rampRight != state) { setDirty(); }
                rampRight = state;
                break;
        }
        if (isDirty()) { new UpdateEventLightsCommand().start(); }
    }

    public void setDirty() {
        dirty = true;
    }

    public void clearDirty() {
        dirty = false;
    }

    public boolean isDirty() {
        return dirty;
    }
}
