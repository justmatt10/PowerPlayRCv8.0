package org.firstinspires.ftc.teamcode.rasky.components;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.rasky.utilities.DrivingMotors;
import org.firstinspires.ftc.teamcode.rasky.utilities.Gyroscope;

/**
 * FieldCentric version of the class RobotCentricDrive.java
 *
 * @author Lucian
 * @version 1.1
 */
public class FieldCentricDrive {

    DrivingMotors motors;
    Gamepad gamepad;

    Gyroscope gyroscope;

    // Speed Multiplier
    double speed = 1.0;
    // If the Joystick has a lower value than this one the robot will not move
    final double controllerDeadzone = 0.15;

    public FieldCentricDrive(DrivingMotors motors, Gamepad gamepad, Gyroscope gyroscope) {
        this.motors = motors;
        this.gamepad = gamepad;
        this.gyroscope = gyroscope;
    }

    /**
     * Call this function asynchronously from the while in the OpMode
     */
    public void run() {

        double x = gamepad.left_stick_x; // Strafe, Horizontal Axis
        double y = -gamepad.left_stick_y; // Forward, Vertical Axis (joystick has +/- flipped)
        double r = gamepad.right_stick_x; // Rotation, Horizontal Axis

        double neededOffset = -gyroscope.getHeading();

        // See this to understand the formulas: https://matthew-brett.github.io/teaching/rotation_2d.html
        double rotatedX = x * Math.cos(neededOffset) - y * Math.sin(neededOffset);
        double rotatedY = x * Math.sin(neededOffset) + y * Math.cos(neededOffset);

        rotatedX = addons(rotatedX);
        rotatedY = addons(rotatedY);
        r = addons(r);

        /*
        If the rotation and forward direction are both engaged the value can go past 1.0 (100%).
        To prevent that we implement a denominator that normalizes the values to 100% max.
         */
        double normalizer = Math.max(Math.abs(rotatedX) + Math.abs(rotatedY) + Math.abs(r), 1.0);

        double leftFrontPower = (rotatedY + rotatedX + r) / normalizer;
        double rightFrontPower = (rotatedY - rotatedX - r) / normalizer;
        double leftRearPower = (rotatedY - rotatedX + r) / normalizer;
        double rightRearPower = (rotatedY + rotatedX - r) / normalizer;

        motors.leftFront.setPower(leftFrontPower);
        motors.rightFront.setPower(rightFrontPower);
        motors.leftRear.setPower(leftRearPower);
        motors.rightRear.setPower(rightRearPower);
    }

    /**
     * Gets the coordinate of a joystick and verifies if it's after the controller deadzone.
     * Also calculates the result after the speed multiplier and returns it.
     *
     * @param coord The coordinate to make modifications to
     * @return The result after modifications
     */
    private double addons(double coord) {
        if (Math.abs(coord) < controllerDeadzone) {
            coord = 0;
        }
        coord = coord * speed;

        return coord;
    }

}
