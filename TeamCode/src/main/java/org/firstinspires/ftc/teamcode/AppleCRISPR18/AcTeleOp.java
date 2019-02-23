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
    private boolean canBeSwitched = false;
    private boolean isStop = false;
    private int clock = 0;

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
    Left stick = left side wheel movement
    Right stick = right side wheel movement

    PRESS B
    Slow mode
    RIGHT TRIGGER
    Pick up
    LEFT TRIGGER
    Drop
     */
    private void move() {
        if (!canBeSwitched) {
            clock += 1;
        }
        if (clock > 500) {
            clock = 0;
            canBeSwitched = true;
        }
        if (gamepad1.b) {
            if (canBeSwitched) {
                isSlow = !isSlow;
                canBeSwitched = false;
            }
        }
        if (gamepad1.right_stick_y <= 0.4) {
            driveLeft.stop();
        }
        if (gamepad1.left_stick_y <= 0.4) {
            driveRight.stop();
        }
        if (gamepad1.right_stick_y > 0.4 || gamepad1.right_stick_y < -0.4) {
            if (isSlow) {
                driveRight.setPower(gamepad1.right_stick_y / -2);
            } else driveRight.setPower(gamepad1.right_stick_y * -1);
        }
        if (gamepad1.left_stick_y > 0.4 || gamepad1.left_stick_y < -0.4) {
            if (isSlow) {
                driveLeft.setPower(gamepad1.left_stick_y / -2);
            } else driveLeft.setPower(gamepad1.left_stick_y * -1);
        }
    }

    private void collect() {
        if (gamepad1.left_trigger > 0.5) {
            collector.setPower(-1);
        } else collector.stop();
        if (gamepad1.right_trigger > 0.5) {
            collector.setPower(1);
        } else collector.stop();
    }

    private void lift() {
        if (gamepad1.dpad_up) {
            lifter.setPower(1);
        } else lifter.stop();
        if (gamepad1.dpad_down) {
            lifter.setPower(-1);
        } else lifter.stop();
    }

    private void allStop() {
        if (gamepad1.start && gamepad1.back) {
            stop();
            isStop = true;
        }
    }

    @Override
    public void loop() {
        if (!isStop) {
            move();
            collect();
            lift();
            allStop();
        }

    }
}