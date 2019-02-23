package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVPixy;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy.LegoPixyGeneral;

@Autonomous(name = "AcPixyTest", group = "Autonomous")
public class AcPixyTest extends LinearOpMode {

    AcREVController controller = new AcREVController();

    AcREVPixy pixy = new AcREVPixy("pixy");

    private void initialize() {
        pixy = (AcREVPixy) controller.add(new AcREVPixy("pixy"));
        telemetry.addData("Epic time: ", controller.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        LegoPixyGeneral largest = pixy.getLargestObjectLego();
        int x;
        for (int i = 0; i < 60; i++) {
            x = largest.getCenter().x;
            telemetry.addData("X", x);
            telemetry.update();
            sleep(1000);
        }
        /*
        Pixy camera's far right is -1, far left is 1, and middle is 128 or -127
         */
        stop();
    }
}
