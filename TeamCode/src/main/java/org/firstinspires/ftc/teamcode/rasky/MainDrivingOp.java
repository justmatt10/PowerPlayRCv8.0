package org.firstinspires.ftc.teamcode.rasky;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.rasky.utilities.DrivingMotors;
import org.firstinspires.ftc.teamcode.rasky.utilities.MecanumDriveMode;

//Author: Lucian
@TeleOp(name = "MainDriving", group = "main")
public class MainDrivingOp extends LinearOpMode {

    DrivingMotors motors = new DrivingMotors();
    MecanumDriveMode mecanumDriveMode;
    Gamepad drivingGamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        //Set the driving gamepad to the desired gamepad
        drivingGamepad = gamepad1;

        motors.Init(hardwareMap, false, true);
        mecanumDriveMode = new MecanumDriveMode(motors, drivingGamepad);

        //This while loop will run after initialization until the program starts or until stop
        //is pressed
        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Initialization Ready", " ");
            telemetry.update();
        }

        //This catches the stop button before the program starts
        if (isStopRequested()) return;

        //Main while loop that runs during the match
        while (opModeIsActive() && !isStopRequested()) {

            mecanumDriveMode.run();

            telemetry.update();
        }
    }
}
