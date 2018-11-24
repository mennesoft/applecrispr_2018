package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVContinuousServo;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;

@Autonomous(name = "AcDuckArm", group = "Autonomous")
public class AcDuckArm extends LinearOpMode {
    AcREVController controller = new AcREVController();

    AcREVContinuousServo servo1;

    private void initialize() {
        servo1 = (AcREVContinuousServo) controller.add(new AcREVContinuousServo("servo1"));

        telemetry.addData("Epic time: ", controller.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        servo1.setPower(1);

        sleep(5000);

        servo1.setPower(-0.5);

        sleep(1000);

        servo1.stop();
        stop();
    }
}
