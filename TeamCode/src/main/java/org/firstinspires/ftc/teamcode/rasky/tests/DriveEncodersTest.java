package org.firstinspires.ftc.teamcode.rasky.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


//Auth: Lucian
public class DriveEncodersTest extends LinearOpMode {

    DrivingMotors motors;

    @Override
    public void runOpMode() throws InterruptedException {
        DrivingMotors.Init(hardwareMap, true, true);

        //This while loop will run after initialization until the program starts or until stop
        //is pressed
        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Initialization Ready", null);
            telemetry.update();
        }

        //This catches the stop button before the program starts
        if (isStopRequested())
            return;

        //Main while loop that runs during the match
        while (opModeIsActive() && !isStopRequested()) {

            telemetry.addData("LeftRear Encoder: ", DrivingMotors.leftRear.getCurrentPosition());
            telemetry.addData("RightRear Encoder: ", DrivingMotors.rightRear.getCurrentPosition());
            telemetry.addData("LeftFront Encoder: ", DrivingMotors.leftFront.getCurrentPosition());
            telemetry.addData("RightFront Encoder: ", DrivingMotors.rightFront.getCurrentPosition());
            telemetry.update();
        }
    }
}
