package org.firstinspires.ftc.teamcode.rasky;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//Author: Lucian
@TeleOp(name = "MainDriving", group = "main")
public class MainDrivingOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        //This while loop will run after initialization until the program starts or until stop
        //is pressed
        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Test:", "");
            telemetry.update();
        }

        //This catches the stop button before the program starts
        if (isStopRequested()) return;

        //Main while loop that runs during the match
        while (opModeIsActive() && !isStopRequested()) {

            telemetry.update();
        }
    }
}
