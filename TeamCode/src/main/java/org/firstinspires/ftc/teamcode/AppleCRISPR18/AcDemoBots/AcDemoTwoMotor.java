package org.firstinspires.ftc.teamcode.AppleCRISPR18.AcDemoBots;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotor;

@TeleOp (name = "AcDemoTwoMotor", group = "TeleOp")
public class AcDemoTwoMotor extends OpMode {
    private AcREVController controller = new AcREVController();
    private AcREVMotor left;
    private AcREVMotor right;

    @Override
    public void init() {
        left = (AcREVMotor) controller.add(new AcREVMotor("left"));
        right = (AcREVMotor) controller.add(new AcREVMotor("right"));

        telemetry.addData("Epic time", controller.initialize(hardwareMap));
        telemetry.update();
    }

    private void move() {
        if (gamepad1.left_stick_y > 0.5 || gamepad1.left_stick_y < -0.5) {
            left.setPower(gamepad1.left_stick_y);
        } else left.stop();
        if (gamepad1.right_stick_y > 0.5 || gamepad1.right_stick_y < -0.5) {
            right.setPower(gamepad1.right_stick_y);
        } else right.stop();
    }

    @Override
    public void loop() {
        move();
    }
}
