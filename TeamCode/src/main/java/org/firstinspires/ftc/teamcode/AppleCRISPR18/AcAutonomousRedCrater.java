package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotorPair;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVPixy;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy.LegoPixyGeneral;

@Autonomous(name = "AcAutonomousRedCrater", group = "Autonomous")
public class AcAutonomousRedCrater extends LinearOpMode {
    AcREVController controller = new AcREVController();
    AcREVMotorPair driveLeft;
    AcREVMotorPair driveRight;
    AcREVPixy pixy;

    private void initialize() {
        driveLeft = (AcREVMotorPair) controller.add(new AcREVMotorPair("drive-fr:drive-br"));
        driveRight = (AcREVMotorPair) controller.add(new AcREVMotorPair("drive-fl:drive-bl"));
        pixy = (AcREVPixy) controller.add(new AcREVPixy("pixy"));
        telemetry.addData("Epic time", controller.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        sleep(1000);

        LegoPixyGeneral object = pixy.getLargestObjectLego();

        /*
        Pixy camera's far right is -1, far left is 1, and middle is 128 or -127

        Backwards
        driveLeft.setPower(-1);
        driveRight.setPower(0.8);

        Forwards
        driveLeft.setPower(1);
        driveRight.setPower(-0.8);

        Turn left
        driveLeft.setPower(1);
        driveRight.setPower(0.8);

        Turn right
        driveLeft.setPower(-1);
        driveRight.setPower(-0.8);
         */

        driveLeft.setPower(1);
        driveRight.setPower(-0.8);

        sleep(250);

        int x = object.getCenter().x;
        String position;

        if (x > -95 && x < 0) {
            position = "right";
            driveLeft.setPower(1);

            sleep(250);

            driveLeft.setPower(1);
            driveRight.setPower(-0.8);

            sleep(750);

            driveLeft.setPower(-1);
            driveRight.setPower(0.8);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(0.8);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(-0.8);

            sleep(500);

            driveLeft.stop();
            driveRight.stop();
        } else if (x <= -95 || x >= 95) {
            position = "middle";
            driveLeft.setPower(1);
            driveRight.setPower(-0.8);

            sleep(750);

            driveLeft.setPower(-1);
            driveRight.setPower(0.8);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(0.8);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(-0.8);

            sleep(250);

            driveLeft.stop();
            driveRight.stop();
        } else {
            position = "left";
            driveRight.setPower(-1);

            sleep(250);

            driveLeft.setPower(1);
            driveRight.setPower(-0.8);

            sleep(750);

            driveLeft.setPower(-1);
            driveRight.setPower(0.8);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(0.8);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(-0.8);

            sleep(500);

            driveLeft.stop();
            driveRight.stop();
        }

        telemetry.addData("X", x);
        telemetry.addData("Position", position);
        telemetry.update();

        driveLeft.setPower(1);
        driveRight.setPower(-0.8);

        sleep(1000);

        driveLeft.setPower(1);
        driveRight.setPower(0.8);

        sleep(250);

        driveLeft.setPower(1);
        driveRight.setPower(-0.8);

        sleep(2000);

        driveLeft.stop();
        driveRight.stop();
    }
}
