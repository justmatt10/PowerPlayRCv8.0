package org.firstinspires.ftc.teamcode.rasky.tests;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.Arrays;

//Author: Lucian
public class DrivingMotors {
    public static DcMotorEx leftRear, rightRear, leftFront, rightFront;
    public static ArrayList<DcMotorEx> motors;

    /**
     * This is a factory method that initializes each driving motor to be easily accessed from this
     * class when testing.
     *
     * @param hardwareMap The hardware map passed from the OpMode calling
     * @param encoders    Boolean value that checks if motor encoders should be used or not
     * @param brakes      Boolean value that checks if the motors should brake or stop on their own
     */
    public static void Init(HardwareMap hardwareMap, Boolean encoders, Boolean brakes) {
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");

        motors = new ArrayList<DcMotorEx>(Arrays.asList(leftRear, rightRear, leftFront, rightFront));
        //This initializes the motors with the given settings
        for (DcMotorEx motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            if (brakes)
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            else
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

            if (encoders)
                motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            else
                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        //Reverse any motors if needed here:
        //leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
