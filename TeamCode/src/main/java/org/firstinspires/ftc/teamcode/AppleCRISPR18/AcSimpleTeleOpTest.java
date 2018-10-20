package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotorPair;

@TeleOp(name = "AcSimpleTeleOpTest", group = "TeleOp")
public class AcSimpleTeleOpTest extends OpMode {
    private AcREVController revController = new AcREVController();

    private AcREVMotorPair driveLeft;
    private AcREVMotorPair driveRight;

    @Override
    public void init() {
        driveLeft = (AcREVMotorPair) revController.add(new AcREVMotorPair("drive-fl:drive-bl"));
        driveRight = (AcREVMotorPair) revController.add(new AcREVMotorPair("drive-fr:drive-br"));
        telemetry.addData("Init successful: ", revController.initialize(hardwareMap));
        telemetry.update();
    }

    private void move() {
        if (gamepad1.right_stick_y == 0) {
            driveLeft.stop();
        }
        if (gamepad1.left_stick_y == 0) {
            driveRight.stop();
        }
        if (gamepad1.right_stick_y > 0.1 || gamepad1.right_stick_y < -0.1) {
            driveLeft.setPower(-1 * gamepad1.right_stick_y);
        }
        if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1) {
            driveRight.setPower(0.8 * gamepad1.left_stick_y);
        }
    }

    @Override
    public void loop() {
        move();
        telemetry.clearAll();
        telemetry.addLine("Left stick value: " + gamepad1.left_stick_y);
        telemetry.addLine("Right stick value: " + gamepad1.right_stick_y);
        telemetry.update();
    }
}
