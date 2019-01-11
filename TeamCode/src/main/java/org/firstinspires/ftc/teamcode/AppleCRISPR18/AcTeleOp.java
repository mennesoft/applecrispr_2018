package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotor;

@TeleOp (name = "AcTeleOp", group = "TeleOp")
public class AcTeleOp extends OpMode {
    private AcREVController controller = new AcREVController();
    private AcREVMotor driveLeft;
    private AcREVMotor driveRight;
    private AcREVMotor lifter;
    private AcREVMotor collector;
    private boolean isSlow = false;

    @Override
    public void init() {
        driveLeft = (AcREVMotor) controller.add(new AcREVMotor("drive-l"));
        driveRight = (AcREVMotor) controller.add(new AcREVMotor("drive-r"));
        lifter = (AcREVMotor) controller.add(new AcREVMotor("lifter"));
        collector = (AcREVMotor) controller.add(new AcREVMotor("collector"));

        telemetry.addData("Epic time", controller.initialize(hardwareMap));
        telemetry.update();
    }

    /*
    TANK DRIVE
    Left stick = left wheel movement
    Right stick = right wheel movement

    PRESS B
    Slow mode

    RIGHT TRIGGER
    Suck

    LEFT TRIGGER
    Unsuck
     */

    private void move() {
        if (gamepad1.b) {
            if (isSlow) {
                isSlow = false;
            } else {
                isSlow = true;
            }
        }
        if (gamepad1.right_stick_y == 0) {
            driveLeft.stop();
        }
        if (gamepad1.left_stick_y == 0) {
            driveRight.stop();
        }
        if (gamepad1.right_stick_y > 0.1 || gamepad1.right_stick_y < -0.1) {
            if (isSlow) {
                driveRight.setPower(gamepad1.right_stick_y / -5);
            } else driveRight.setPower(gamepad1.right_stick_y * -1);
        }
        if (gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1) {
            if (isSlow) {
                driveLeft.setPower(gamepad1.left_stick_y / -5);
            } else driveLeft.setPower(gamepad1.left_stick_y * -1);
        }
    }

    private void collect() {
        if (gamepad1.left_trigger > 0.1) {
            collector.setPower(-1);
        } else collector.stop();
        if (gamepad1.right_trigger > 0.1) {
            collector.setPower(1);
        } else collector.stop();
    }

    private void lift() {
        if (gamepad1.dpad_up) {
            lifter.setPower(0.2);
        } else lifter.stop();
        if (gamepad1.dpad_down) {
            lifter.setPower(-0.2);
        } else lifter.stop();
    }

    private void allStop() {
        if (gamepad1.start && gamepad1.back) {
            controller.allStop();
        }
    }

    @Override
    public void loop() {
        move();
        collect();
        lift();
        allStop();
    }
}
