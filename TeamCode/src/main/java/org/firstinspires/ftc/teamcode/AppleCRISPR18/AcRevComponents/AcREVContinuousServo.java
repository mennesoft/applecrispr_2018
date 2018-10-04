package org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Implement a continuous rotation servo
 * <p>
 * Created by Riley on 19-Mar-18.
 */

public class AcREVContinuousServo extends AcREVComponent {
    // Note that for some people, power inputs from -.76 to .76 work better

    private CRServo servo;
    private float servoZero = Float.MAX_VALUE;

    public AcREVContinuousServo(String servoName, String servoLabel) {
        name = servoName;
        servoZero = AcREVServoConstants.getServoZero(servoLabel);
    }

    public AcREVContinuousServo(String servoName) {
        name = servoName;
    }

    @Override
    public boolean init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(CRServo.class, name);
        setReversed(false);
        if (servoZero == Float.MAX_VALUE) {
            servoZero = AcREVServoConstants.getServoZero(servo.getDeviceName() + servo.getVersion());
        }
        return (servo != null);
    }

    /**
     * Sets the power level of the motor, expressed as a fraction of the maximum
     * possible power / speed supported according to the run mode in which the
     * motor is operating.
     *
     * <p>Setting a power level of zero will brake the motor</p>
     *
     * @param power the new power level of the motor, a value in the interval [0.0, 1.0]
     * @see DcMotorSimple#getPower()
     * @see DcMotor#setMode(DcMotor.RunMode)
     * @see DcMotor#setPowerFloat()
     * @see #getPower()
     */
    public void setPower(double power) {
        setReversed(power < 0);
        servo.setPower(power + servoZero - .5);
    }

    /**
     * Returns the current configured power level of the motor.
     *
     * @return the current level of the motor, a value in the interval [0.0, 1.0]
     * @see DcMotorSimple#setPower(double)
     * @see #setPower(double)
     */
    public double getPower() {
        return servo.getPower();
    }

    /**
     * Sets the logical direction in which this motor operates.
     *
     * @param reversed whether to set the motor to reverse
     * @see #getReversed()
     */
    public void setReversed(boolean reversed) {
        if (reversed) {
            servo.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            servo.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    /**
     * Returns whether the motor is set to reverse direction
     *
     * @return whether the current logical direction in which this motor is set as operating is Reverse
     * @see #setReversed(boolean)
     */
    public boolean getReversed() {
        DcMotorSimple.Direction direction = servo.getDirection();
        return direction == DcMotorSimple.Direction.REVERSE;
    }

    @Override
    public void stop() {
        servo.setPower(servoZero);
    }
}
