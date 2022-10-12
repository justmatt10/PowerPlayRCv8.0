package org.firstinspires.ftc.teamcode.rasky.components;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.rasky.utilities.Button;
import org.firstinspires.ftc.teamcode.rasky.utilities.WrappedMotor;

/**
 * The class that operates the lift.
 *
 * @author Lucian
 * @version 1.0
 */
public class LiftSystem {
    Gamepad gamepad;
    HardwareMap hardwareMap;
    WrappedMotor liftMotor;

    double tolerance = 10;
    double speed = 0.5;

    public LiftSystem(HardwareMap hardwareMap, Gamepad gamepad) {
        this.gamepad = gamepad;
        this.hardwareMap = hardwareMap;
    }

    /**
     * Call this method before using the object.
     */
    public void Init() {
        liftMotor = new WrappedMotor(hardwareMap);
        liftMotor.Init("liftMotor", false, false, true);
        liftMotor.setTolerance(tolerance);
        liftMotor.setSpeed(speed);
        liftMotor.holdMode(true);
    }

    //Lift positions in encoder ticks
    enum LiftPositions {
        HIGH_JUNCTION(400),
        MEDIUM_JUNCTION(300),
        LOW_JUNCTION(200),
        GROUND_JUNCTION(100),
        STARTING_POS(0);

        double position = 0;

        LiftPositions(double value) {
            this.position = value;
        }
    }

    Button resetButton = new Button();
    Button liftButton = new Button();
    LiftPositions state;
    int toggleStates = 0;

    public void run() {
        resetButton.updateButton(gamepad.a);
        liftButton.updateButton(gamepad.y);

        if (resetButton.press())
            toggleStates = 0;


        if (liftButton.toggle())
            toggleStates++;

        switch (toggleStates % 5) {
            case 1:
                state = LiftPositions.GROUND_JUNCTION;
                break;
            case 2:
                state = LiftPositions.LOW_JUNCTION;
                break;
            case 3:
                state = LiftPositions.MEDIUM_JUNCTION;
                break;
            case 4:
                state = LiftPositions.HIGH_JUNCTION;
                break;
            default:
                state = LiftPositions.STARTING_POS;
                break;
        }

        liftMotor.setTargetPosition(state.position);
        liftMotor.updatePosition();
    }

    public void showInfo(Telemetry telemetry) {
        telemetry.addData("Lift State: ", state);
        telemetry.addData("Lift Position: ", liftMotor.motor.getCurrentPosition());
        telemetry.addData("Motor Speed: ", speed);
        telemetry.addData("Position Tolerance: ", tolerance);
    }

}
