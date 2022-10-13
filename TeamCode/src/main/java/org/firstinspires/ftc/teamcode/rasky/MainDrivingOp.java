package org.firstinspires.ftc.teamcode.rasky;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.rasky.components.FieldCentricDrive;
import org.firstinspires.ftc.teamcode.rasky.components.LiftSystem;
import org.firstinspires.ftc.teamcode.rasky.utilities.Button;
import org.firstinspires.ftc.teamcode.rasky.utilities.Constants;
import org.firstinspires.ftc.teamcode.rasky.utilities.DrivingMotors;
import org.firstinspires.ftc.teamcode.rasky.components.RobotCentricDrive;
import org.firstinspires.ftc.teamcode.rasky.utilities.Gyroscope;

/**
 * The main TeleOP program for the driving period of the game.
 *
 * @author Lucian
 * @version 1.2
 */
@TeleOp(name = "Main Driving", group = Constants.mainGroup)
public class MainDrivingOp extends LinearOpMode {

    DrivingMotors motors;
    Gyroscope gyroscope;

    RobotCentricDrive robotCentricDrive;
    FieldCentricDrive fieldCentricDrive;
    LiftSystem liftSystem;

    Gamepad drivingGamepad;
    Gamepad utilityGamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        //Set the gamepads to the desired gamepad
        drivingGamepad = gamepad1;
        utilityGamepad = gamepad2;

        motors = new DrivingMotors(hardwareMap);
        motors.Init(false, true);

        liftSystem = new LiftSystem(hardwareMap, utilityGamepad);
        liftSystem.Init();

        gyroscope = new Gyroscope(hardwareMap);
        gyroscope.Init();

        robotCentricDrive = new RobotCentricDrive(motors, drivingGamepad);
        fieldCentricDrive = new FieldCentricDrive(motors, drivingGamepad, gyroscope);

        //This while loop will run after initialization until the program starts or until stop
        //is pressed
        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addLine("Initialization Ready");
            telemetry.update();
        }

        //This catches the stop button before the program starts
        if (isStopRequested()) return;

        Button driveModeButton = new Button();

        //Main while loop that runs during the match
        while (opModeIsActive() && !isStopRequested()) {
            driveModeButton.updateButton(drivingGamepad.x);
            driveModeButton.shortPress();
            driveModeButton.longPress();

            robotCentricDrive.setReverse(driveModeButton.getShortToggle());

            if (!driveModeButton.getLongToggle()) {
                robotCentricDrive.run();
            } else {
                fieldCentricDrive.run();
            }

            liftSystem.run();
            liftSystem.showInfo(telemetry);

            telemetry.update();
        }
    }
}
