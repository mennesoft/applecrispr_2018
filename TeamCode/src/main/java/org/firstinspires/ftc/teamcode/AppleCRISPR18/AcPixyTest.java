package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVPixy;

@Autonomous(name = "AcPixyTest", group = "Autonomous")
public class AcPixyTest extends LinearOpMode {

    AcREVController controller = new AcREVController();

    AcREVPixy pixy = new AcREVPixy("pixy");

    private void initialize() {
        pixy = (AcREVPixy) controller.add(new AcREVPixy("pixy"));
        telemetry.addData("Init successful: ", controller.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        telemetry.addLine(Integer.toString(pixy.getLargestDetectedObjSig()));
        telemetry.update();
        stop();
    }
}
