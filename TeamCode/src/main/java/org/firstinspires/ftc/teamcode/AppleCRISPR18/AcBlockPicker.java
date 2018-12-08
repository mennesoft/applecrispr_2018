package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotorPair;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVPixy;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy.LegoPixyGeneral;

@Autonomous (name = "AcBlockPicker", group = "Autonomous")
public class AcBlockPicker extends LinearOpMode {
    AcREVController controller = new AcREVController();
    AcREVPixy pixy = new AcREVPixy("pixy");
    AcREVMotorPair driveRight;
    AcREVMotorPair driveLeft;

    private void initialize() {
        pixy = (AcREVPixy) controller.add(new AcREVPixy("pixy"));
        driveRight = (AcREVMotorPair) controller.add(new AcREVMotorPair("drive-fr:drive-br"));
        driveLeft = (AcREVMotorPair) controller.add(new AcREVMotorPair("drive-fl:drive-bl"));
        telemetry.addData("Epic time", controller.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        sleep(1000);

        LegoPixyGeneral largest = pixy.getLargestObjectLego();

        /*
        Pixy camera's far right is -1, far left is 1, and middle is 128 or -127
         */

        int x = largest.getCenter().x;
        String position;

        if (x > -95 && x <0) {
            position = "right";
            driveLeft.setPower(1);
            sleep(250);
            driveLeft.stop();
            driveLeft.setPower(1);
            driveRight.setPower(-0.8);
            sleep(750);
            driveLeft.stop();
            driveRight.stop();
        }
        else if (x <= -95 || x >= 95) {
            position = "middle";
            driveLeft.setPower(1);
            driveRight.setPower(-0.8);
            sleep(750);
            driveRight.stop();
            driveLeft.stop();
        }
        else {
            position = "left";
            driveRight.setPower(-1);
            sleep(250);
            driveLeft.setPower(1);
            driveRight.setPower(-0.8);
            sleep(750);
            driveRight.stop();
            driveLeft.stop();
        }

        telemetry.addData("X", x);
        telemetry.addData("Position", position);
        telemetry.update();

        sleep(2000);
        stop();
    }
}
