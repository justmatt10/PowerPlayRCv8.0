package org.firstinspires.ftc.teamcode.rasky.components;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.rasky.utilities.DrivingMotors;

/**
 * Class meant for individually testing each of the motors on the robot for problems.
 *
 * @author Lucian
 * @version 1.0
 */
public class DebugDriveMode {

    DrivingMotors motors;
    Gamepad gamepad;

    public DebugDriveMode(DrivingMotors motors, Gamepad gamepad) {
        this.motors = motors;
        this.gamepad = gamepad;
    }

    /**
     * Call this function asynchronously from the while in the OpMode
     */
    public void run() {
        if (gamepad.y) {
            motors.rightFront.setPower(1.0);
        } else {
            motors.rightFront.setPower(0.0);
        }

        if (gamepad.x) {
            motors.leftFront.setPower(1.0);
        } else {
            motors.leftFront.setPower(0.0);
        }

        if (gamepad.b) {
            motors.rightRear.setPower(1.0);
        } else {
            motors.rightRear.setPower(0.0);
        }

        if (gamepad.a) {
            motors.leftRear.setPower(1.0);
        } else {
            motors.leftRear.setPower(0.0);
        }
    }

}
