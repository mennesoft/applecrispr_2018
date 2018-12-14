package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotorPair;

@Autonomous (name = "AcAutonomousDepot", group = "Autonomous")
public class AcAutonomousDepot extends LinearOpMode {
    AcREVController controller = new AcREVController();
    private AcREVMotorPair driveLeft;
    private AcREVMotorPair driveRight;

    private void initialize() {
        driveLeft = (AcREVMotorPair) controller.add(new AcREVMotorPair("drive-fl:drive-bl"));
        driveRight = (AcREVMotorPair) controller.add(new AcREVMotorPair("drive-fr:drive-br"));

        telemetry.addData("Epic time", controller.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
    }
}
