package org.firstinspires.ftc.teamcode.rasky.autonomy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rasky.detection.AutonomyDetection;
import org.openftc.apriltag.AprilTagDetection;

/**
 * Visually see and test Road Runner trajectories using MeepMeep.
 *
 * @author Lucian
 * @version 1.0
 */
@Autonomous(name = "Test Autonomy", group = "tests")
public class TestAutonomy extends LinearOpMode {

    AutonomyDetection detectionSystem;

    @Override
    public void runOpMode() throws InterruptedException {
        detectionSystem = new AutonomyDetection(hardwareMap);
        detectionSystem.init();

        while (!isStopRequested() && !opModeIsActive()) {
            AprilTagDetection detectedTag = detectionSystem.detect();
            if (detectedTag != null) {
                telemetry.addData("Detected Tag ID: " + detectedTag.id, " ");
            }

            telemetry.update();
        }


        return;
    }
}
