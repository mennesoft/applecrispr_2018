package org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Jared on 27-Jan-18.
 */

public class AcREVDistanceSensor extends AcREVComponent {

    private DistanceSensor distanceSensor;
    private OpticalDistanceSensor ods;

    private AnalogInput analogSensor;

    public double defaultVoltsPerInch = 3.3 / 512.0;

    public AcREVDistanceSensor(String componentName) {
        name = componentName;
    }

    @Override
    public boolean init(HardwareMap hardwareMap) {
        analogSensor = hardwareMap.get(AnalogInput.class, name);
        defaultVoltsPerInch = analogSensor.getMaxVoltage() / 512.0;
        return (analogSensor != null);
    }

    public double reportRawVoltage() {
        return analogSensor.getVoltage();
    }

    public double reportDistance(double voltage, double voltsPerInch) {
        return 2 * voltage / voltsPerInch;
    }

    public double reportDistance() {
        return reportDistance(reportRawVoltage(), defaultVoltsPerInch);
    }
}
