package org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Tracks a servo.
 */
public class AcREVServo extends AcREVComponent {

    private Servo servo;
    private double currentPos = 1;

    private boolean isContinuousRotation = false;
    private double motionlessPosition = 1;

    public AcREVServo(String servoName) {
        name = servoName;
    }

    public AcREVServo(String servoName, double zeroMotionPos) { //If you are setting a zero-motion position, then the servo must be continuous rotation
        name = servoName;
        isContinuousRotation = true;
        motionlessPosition = zeroMotionPos;
        currentPos = motionlessPosition;
    }

    public AcREVServo(String servoName, double defaultPos, boolean continuousRot) { //If you are setting a zero-motion position, then the servo must be continuous rotation
        name = servoName;
        isContinuousRotation = continuousRot;
        currentPos = defaultPos;
    }

    @Override
    public boolean init(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, name);
        servo.setPosition(currentPos);
        return (servo != null);
    }

    public void setPosition(double position) {
        servo.setPosition(position);
        currentPos = position; //Keep current pos updated
    }

    public double getPosition() {
        return servo.getPosition();
    }

    public void incrementPosition(double inc) {
        currentPos += inc;
        double MAX_POS = 1.0;
        currentPos = Math.min(MAX_POS, currentPos); //CAREFUL use min() on MAX
        double MIN_POS = 0.0;
        currentPos = Math.max(MIN_POS, currentPos); //...and max() on MIN

        setPosition(currentPos);
    }

    public double getMotionlessPosition() {
        return motionlessPosition;
    }

    @Override
    public void stop() {
        if (isContinuousRotation) {
            setPosition(motionlessPosition);
        } else {
            setPosition(servo.getPosition());
        }
        currentPos = servo.getPosition();
    }
}