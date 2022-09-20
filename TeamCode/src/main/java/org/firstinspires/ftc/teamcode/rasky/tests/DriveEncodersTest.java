package org.firstinspires.ftc.teamcode.rasky.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.rasky.utilities.DebugDriveMode;
import org.firstinspires.ftc.teamcode.rasky.utilities.DrivingMotors;


//Author: Lucian
public class DriveEncodersTest extends LinearOpMode {

    DrivingMotors motors = new DrivingMotors();
    DebugDriveMode debugDriveMode;

    @Override
    public void runOpMode() throws InterruptedException {
        motors.Init(hardwareMap, true, true);
        debugDriveMode = new DebugDriveMode(motors);

        //This while loop will run after initialization until the program starts or until stop
        //is pressed
        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Initialization Ready", null);
            telemetry.update();
        }

        //This catches the stop button before the program starts
        if (isStopRequested()) return;

        //Main while loop that runs during the match
        boolean debugMode = false;
        Gamepad gamepad = gamepad1;

        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad.right_bumper) {
                debugMode = true;
            }
            if (gamepad.left_bumper) {
                debugMode = false;
            }

            // TODO: Add a normal driving mode for the test

            if (debugMode) {
                debugDriveMode.run(gamepad);
            } else {

            }

            telemetry.addData("LeftRear Encoder: ", motors.leftRear.getCurrentPosition());
            telemetry.addData("RightRear Encoder: ", motors.rightRear.getCurrentPosition());
            telemetry.addData("LeftFront Encoder: ", motors.leftFront.getCurrentPosition());
            telemetry.addData("RightFront Encoder: ", motors.rightFront.getCurrentPosition());
            telemetry.update();
        }
    }
}
