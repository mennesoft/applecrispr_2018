package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotor;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVPixy;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy.LegoPixyGeneral;

@Autonomous (name = "AcAutonomousCrater", group = "Autonomous")
public class AcAutonomousCrater extends LinearOpMode {
    private AcREVController controller = new AcREVController();
    private AcREVMotor driveLeft;
    private AcREVMotor driveRight;
    private AcREVPixy pixy;
    private AcREVMotor lifter;

    private void initialize() {
        driveLeft = (AcREVMotor) controller.add(new AcREVMotor("drive-l"));
        driveRight = (AcREVMotor) controller.add(new AcREVMotor("drive-r"));
        lifter = (AcREVMotor) controller.add(new AcREVMotor("lifter"));

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
        driveRight.setPower(1);

        Forwards
        driveLeft.setPower(1);
        driveRight.setPower(-1);

        Turn left
        driveLeft.setPower(1);
        driveRight.setPower(1);

        Turn right
        driveLeft.setPower(-1);
        driveRight.setPower(-1);
         */

        lifter.setPower(1);

        sleep(500);

        lifter.stop();

        driveLeft.setPower(1);
        driveRight.setPower(-1);

        sleep(1000);

        driveLeft.stop();
        driveRight.stop();

        sleep(500);

        int x = object.getCenter().x;
        String position;

        if (x > -95 && x <0) {
            position = "right";
            driveLeft.setPower(1);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(1500);

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(1000);

            driveLeft.setPower(1);
            driveRight.setPower(1);

            sleep(1000);

            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(1000);

            driveLeft.stop();
            driveRight.stop();
        } else if (x <= -95 || x >= 95) {
            position = "middle";
            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(1500);

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(1000);

            driveLeft.setPower(1);
            driveRight.setPower(1);

            sleep(1000);

            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(500);

            driveLeft.stop();
            driveRight.stop();
        } else {
            position = "left";
            driveRight.setPower(-1);

            sleep(500);

            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(1500);

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(1000);

            driveLeft.setPower(1);
            driveRight.setPower(1);

            sleep(1000);

            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(1000);

            driveLeft.stop();
            driveRight.stop();
        }

        telemetry.addData("X", x);
        telemetry.addData("Position", position);
        telemetry.update();

        driveLeft.setPower(1);
        driveRight.setPower(-1);

        sleep(2000);

        driveLeft.setPower(1);
        driveRight.setPower(1);

        sleep(1000);

        driveLeft.setPower(1);
        driveRight.setPower(-1);

        sleep(4000);

        driveLeft.stop();
        driveRight.stop();
    }
}
