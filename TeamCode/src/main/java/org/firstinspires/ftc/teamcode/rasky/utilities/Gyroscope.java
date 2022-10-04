package org.firstinspires.ftc.teamcode.rasky.utilities;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Wrapper class for the Control Hub Gyroscope.
 *
 * @author Lucian
 * @version 1.0
 */
public class Gyroscope {
    BNO055IMU imu;

    public void Init(HardwareMap hardwareMap) {

        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        // Technically this is the default, however specifying it is clearer
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        // Without this, data retrieving from the IMU throws an exception
        imu.initialize(parameters);

    }

    public BNO055IMU getGyro() {
        return imu;
    }

    public double getHeading() {
        return imu.getAngularOrientation().firstAngle;
    }


}
