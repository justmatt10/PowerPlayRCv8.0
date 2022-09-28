package org.firstinspires.ftc.teamcode.rasky.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.rasky.components.DebugDriveMode;
import org.firstinspires.ftc.teamcode.rasky.utilities.DrivingMotors;
import org.firstinspires.ftc.teamcode.rasky.components.RobotCentricDrive;


//Author: Lucian
@TeleOp(name = "DriveEncodersTest", group = "tests")
public class DriveEncodersTest extends LinearOpMode {

    DrivingMotors motors = new DrivingMotors();
    DebugDriveMode debugDriveMode;
    RobotCentricDrive robotCentricDrive;
    Gamepad gamepad;

    @Override
    public void runOpMode() throws InterruptedException {
        gamepad = gamepad1;

        motors.Init(hardwareMap, true, true);
        debugDriveMode = new DebugDriveMode(motors, gamepad);
        robotCentricDrive = new RobotCentricDrive(motors, gamepad);

        //This while loop will run after initialization until the program starts or until stop
        //is pressed
        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Initialization Ready", " ");
            telemetry.update();
        }

        //This catches the stop button before the program starts
        if (isStopRequested()) return;

        boolean debugMode = false;
        //Main while loop that runs during the match
        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad.right_bumper) {
                debugMode = true;
            }
            if (gamepad.left_bumper) {
                debugMode = false;
            }

            if (debugMode) {
                debugDriveMode.run();
            } else {
                robotCentricDrive.run();
            }

            telemetry.addData("Is Debug Mode On? : ", debugMode);
            telemetry.addData("LeftRear Encoder: ", motors.leftRear.getCurrentPosition());
            telemetry.addData("RightRear Encoder: ", motors.rightRear.getCurrentPosition());
            telemetry.addData("LeftFront Encoder: ", motors.leftFront.getCurrentPosition());
            telemetry.addData("RightFront Encoder: ", motors.rightFront.getCurrentPosition());
            telemetry.update();
        }
    }
}
