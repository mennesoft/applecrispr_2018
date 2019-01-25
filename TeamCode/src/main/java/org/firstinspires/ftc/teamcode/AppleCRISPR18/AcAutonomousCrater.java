package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotor;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVPixy;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy.LegoPixyGeneral;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy.LegoPixySpecificType;

@Autonomous (name = "AcAutonomousCrater", group = "Autonomous")
public class AcAutonomousCrater extends LinearOpMode {
    private AcREVController controller = new AcREVController();
    private AcREVMotor driveLeft;
    private AcREVMotor driveRight;
    private AcREVPixy pixy;
    private AcREVMotor lifter;
    private AcREVMotor collector;

    private void initialize() {
        driveLeft = (AcREVMotor) controller.add(new AcREVMotor("drive-l"));
        driveRight = (AcREVMotor) controller.add(new AcREVMotor("drive-r"));
        lifter = (AcREVMotor) controller.add(new AcREVMotor("lifter"));
        collector = (AcREVMotor) controller.add(new AcREVMotor("collector"));

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

        //LegoPixySpecificType object = pixy.getBlock1();

        /*
        //Forwards
        driveLeft.setPower(1);
        driveRight.setPower(1);

        //Backwards
        driveLeft.setPower(-1);
        driveRight.setPower(-1);

        //Turn left
        driveLeft.setPower(1);
        driveRight.setPower(-1);

        //Turn right
        driveLeft.setPower(-1);
        driveRight.setPower(1);
         */

        lifter.setPower(1);

        sleep(12500);

        lifter.stop();

        driveLeft.setPower(-1);
        driveRight.setPower(1);

        sleep(500);

        driveLeft.setPower(0.5);
        driveRight.setPower(0.5);

        sleep(100);

        driveLeft.setPower(1);
        driveRight.setPower(-1);

        sleep(700);

        driveLeft.stop();
        driveRight.stop();

        sleep(500);

        driveLeft.stop();
        driveRight.stop();

        sleep(500);

        int x = object.getCenter().x;
        String position;

        if (x > -95 && x <0) {
            position = "right";
            driveLeft.setPower(1);

            sleep(500);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1500);

            driveLeft.setPower(-0.5);
            driveRight.setPower(-0.5);

            sleep(400);

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(500);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1000);

            driveLeft.stop();
            driveRight.stop();
        } else if (x <= -95 || x >= 95) {
            position = "middle";
            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1500);

            driveLeft.setPower(-0.5);
            driveRight.setPower(-0.5);

            sleep(400);

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(500);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(500);

            driveLeft.stop();
            driveRight.stop();
        } else {
            position = "left";
            driveRight.setPower(1);

            sleep(500);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1500);

            driveLeft.setPower(-0.5);
            driveRight.setPower(-0.5);

            sleep(400);

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(500);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1000);

            driveLeft.stop();
            driveRight.stop();
        }

        telemetry.addData("X", x);
        telemetry.addData("Position", position);
        telemetry.update();

        driveLeft.setPower(0.5);
        driveRight.setPower(0.5);

        sleep(2000);

        driveLeft.setPower(-1);
        driveRight.setPower(1);

        sleep(1000);

        driveLeft.setPower(0.5);
        driveRight.setPower(0.5);

        sleep(4000);

        driveLeft.stop();
        driveRight.stop();

        collector.setPower(1);

        sleep(2000);

        collector.stop();
    }
}
