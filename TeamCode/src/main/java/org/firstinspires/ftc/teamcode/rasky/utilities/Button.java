package org.firstinspires.ftc.teamcode.rasky.utilities;

import com.qualcomm.robotcore.util.ElapsedTime;

//Author: Lucian
public class Button {

    boolean buttonValue = false;

    public void updateButton(boolean input) {
        buttonValue = input;
    }

    /**
     * Asynchronous function that returns current button state.
     *
     * @return Is pressed status
     */
    public boolean press() {
        return buttonValue;
    }

    boolean lastIteration = false;
    boolean currentIteration = false;
    boolean toggleStatus = false;

    /**
     * Asynchronous function that returns true if toggling changes
     *
     * @return Toggle status
     */
    public boolean toggle() {
        lastIteration = currentIteration;
        currentIteration = buttonValue;

        if (!lastIteration && currentIteration) {
            toggleStatus = !toggleStatus;
            return true;
        }

        return false;
    }

    double longPressTime = 2000;
    boolean longPressLastIteration = false;
    boolean longPressCurrentIteration = false;
    boolean longToggle = false;
    boolean locked = false;
    ElapsedTime longPressTimer = new ElapsedTime();

    /**
     * Asynchronous function that returns true ONLY ONCE if the button is pressed
     * for a certain amount of time uninterrupted.
     *
     * @return Long pressed status
     */
    public boolean longPress() {
        longPressLastIteration = longPressCurrentIteration;
        longPressCurrentIteration = buttonValue;

        if (!longPressLastIteration && longPressCurrentIteration) {
            longPressTimer.reset();
        }

        if (!longPressCurrentIteration) {
            longPressTimer.reset();
        }

        if (longPressLastIteration && longPressCurrentIteration
                && longPressTimer.milliseconds() > longPressTime && !locked) {
            locked = true;
            longToggle = !longToggle;
            return true;
        }

        return false;
    }

    boolean shortLastIteration = false;
    boolean shortCurrentIteration = false;
    boolean shortToggle = false;
    ElapsedTime shortTimer = new ElapsedTime();
    double shortPressTime = 500;

    /**
     * Asynchronous function that returns true ONLY ONCE if the button is pressed
     * for LESS than a certain amount of time uninterrupted.
     *
     * @return Short pressed status
     */
    public boolean shortPress() {
        shortLastIteration = shortCurrentIteration;
        shortCurrentIteration = buttonValue;

        if (!shortLastIteration && shortCurrentIteration) {
            shortTimer.reset();
        }
        if (shortLastIteration && !shortCurrentIteration
                && shortTimer.milliseconds() < shortPressTime) {
            shortTimer.reset();
            shortToggle = !shortToggle;
            return true;
        }

        return false;
    }

    public boolean getToggleStatus() {
        return toggleStatus;
    }

    public boolean getShortToggle() {
        return shortToggle;
    }

    public boolean getLongToggle() {
        return longToggle;
    }

    public void setLongPressTime(double milliseconds) {
        longPressTime = milliseconds;
    }

    public void setShortPressTime(double milliseconds) {
        shortPressTime = milliseconds;
    }

}
