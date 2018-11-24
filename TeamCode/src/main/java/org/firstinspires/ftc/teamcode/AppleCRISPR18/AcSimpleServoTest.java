package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVServo;

@Autonomous(name = "AcServoTest", group = "Autonomous")
public class AcSimpleServoTest extends LinearOpMode {
    AcREVController revController = new AcREVController();

    AcREVServo servo1;

    private void initialize() {
        servo1 = (AcREVServo) revController.add(new AcREVServo("servo1"));

        telemetry.addData("Init successful", revController.initialize(hardwareMap));
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        sleep(1000);

        servo1.setPosition(180);

        sleep(1000);

        servo1.setPosition(0);

        sleep(1000);

        servo1.setPosition(180);

        sleep(1000);

        servo1.stop();
        stop();
    }
}
