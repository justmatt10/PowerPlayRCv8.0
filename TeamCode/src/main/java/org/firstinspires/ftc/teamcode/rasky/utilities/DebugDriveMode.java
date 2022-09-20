package org.firstinspires.ftc.teamcode.rasky.utilities;

import com.qualcomm.robotcore.hardware.Gamepad;

//Author: Lucian
public class DebugDriveMode {

    private final DrivingMotors motors;

    public DebugDriveMode(DrivingMotors motors) {
        this.motors = motors;
    }

    /**
     * Call this function asynchronously from the while in the OpMode
     *
     * @param gamepad The gamepad from the current OpMode
     */
    public void run(Gamepad gamepad) {
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
