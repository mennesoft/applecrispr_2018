package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotor;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVPixy;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy.LegoPixyGeneral;

@Autonomous (name = "AcAutonomousDepot", group = "Autonomous")
public class AcAutonomousDepot extends LinearOpMode {
    private AcREVController controller = new AcREVController();
    private AcREVMotor driveLeft;
    private AcREVMotor driveRight;
    private AcREVPixy pixy;
    private AcREVMotor lifter;
    private AcREVMotor collector;

    private void initialize() {
        driveLeft = (AcREVMotor) controller.add(new AcREVMotor("drive-l"));
        driveRight = (AcREVMotor) controller.add(new AcREVMotor("drive-r"));
        pixy = (AcREVPixy) controller.add(new AcREVPixy("pixy"));
        lifter = (AcREVMotor) controller.add(new AcREVMotor("lifter"));
        collector = (AcREVMotor) controller.add(new AcREVMotor("collector"));

        telemetry.addData("Epic time", controller.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

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

        sleep(13000);

        lifter.stop();

        sleep(1000);

        driveLeft.setPower(-1);
        driveRight.setPower(1);

        sleep(500);

        driveLeft.setPower(0.5);
        driveRight.setPower(0.5);

        sleep(1);

        driveLeft.setPower(1);
        driveRight.setPower(-1);

        sleep(650);

        driveLeft.setPower(0.5);
        driveRight.setPower(0.5);

        sleep(100);

        driveLeft.setPower(-1);
        driveRight.setPower(1);

        sleep(200);

        driveLeft.stop();
        driveRight.stop();

        int x = object.getCenter().x;

        if (x > -95 && x <0) {
            telemetry.addData("X", x);
            telemetry.addData("Position", "Right");
            telemetry.update();

            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(250);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1400);

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(700);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(300);

            driveLeft.stop();
            driveRight.stop();
        } else if (x <= -95 || x >= 95) {
            telemetry.addData("X", x);
            telemetry.addData("Position", "Middle");
            telemetry.update();

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1600);

            driveLeft.stop();
            driveRight.stop();
        } else {
            telemetry.addData("X", x);
            telemetry.addData("Position", "Left");
            telemetry.update();

            driveLeft.setPower(-1);
            driveRight.setPower(1);

            sleep(250);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(1400);

            driveLeft.setPower(1);
            driveRight.setPower(-1);

            sleep(450);

            driveLeft.setPower(0.5);
            driveRight.setPower(0.5);

            sleep(300);

            driveLeft.stop();
            driveRight.stop();
        }



        collector.setPower(1);

        sleep(500);

        collector.stop();

        driveLeft.setPower(-1);
        driveRight.setPower(1);

        sleep(300);

        driveLeft.setPower(0.5);
        driveRight.setPower(0.5);

        sleep(300);

        collector.stop();
        driveLeft.stop();
        driveRight.stop();
    }
}
