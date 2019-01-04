package org.firstinspires.ftc.teamcode.AppleCRISPR18;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVController;
import org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents.AcREVMotor;

/*
 * Created by Jared/Riley on 07-Jan-18. Modified
 */

/**
 * This class is intended to demonstrate autonomous mode, a simple class for a 4-motor tank drive
 * system.
 * <p>
 * This class expects the following equipment to be assigned in the configuration file on the robot
 * (the Robot Controller phone):
 * 1. 'drive-fl' - the front left wheel motor
 * 2. 'drive-fr' - the front right wheel motor
 * 3. 'drive-bl' - the back left wheel motor
 * 4. 'drive-br' - the back right wheel motor
 * <p>
 * The Annotation '@Autonomous' below specifies the name that will appear on the Driver Station
 * pick list to select this OpMode to run.
 */
@Autonomous(name = "SimpleAutoTest", group = "Autonomous")
public class AcSimpleAutonomousTest extends LinearOpMode {
    // The master robot control object!
    private AcREVController revModule = new AcREVController();

    // Drive motors, paired for tank driving
    private AcREVMotor driveLeft;
    private AcREVMotor driveRight;

    /**
     * This method performs initialization for our specific robot.
     */
    private void ourInit() {

        driveLeft = (AcREVMotor) revModule.add(new AcREVMotor("drive-l"));
        driveRight = (AcREVMotor) revModule.add(new AcREVMotor("drive-r"));

        telemetry.addData("Epic time: ", revModule.initialize(hardwareMap));
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        // Init
        ourInit();
        waitForStart();

        //Forwards
        driveLeft.setPower(1);
        driveRight.setPower(1);

        sleep(2000);

        //Backwards
        driveLeft.setPower(-1);
        driveRight.setPower(-1);

        sleep(2000);

        //Turn left
        driveLeft.setPower(1);
        driveRight.setPower(-1);

        sleep(4000);

        //Turn right
        driveLeft.setPower(-1);
        driveRight.setPower(1);

        sleep(4000);

        revModule.allStop();
        stop();
    }

}
