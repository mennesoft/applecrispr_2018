package org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Jared on 27-Jan-18.
 */

public class AcREVColorSensor extends AcREVComponent {

    private ColorSensor sensor;

    public AcREVColorSensor(String componentName) {
        name = componentName;
    }

    @Override
    public boolean init(HardwareMap hardwareMap) {
        sensor = hardwareMap.get(ColorSensor.class, name);
        return (sensor != null);
    }

    public double getRed() {
        return sensor.red();
    }

    public double getGreen() {
        return sensor.green();
    }

    public double getBlue() {
        return sensor.blue();
    }
}
