package org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;


/**
 * Manages the robot components. 'At' stands for Apple Tau!
 * <p>
 * Although the name refers to a singular REV expansion hub, this object can (and should) contain
 * all components anyways.
 */
public class AcREVController {
    // Important list of components
    private ArrayList<AcREVComponent> components = new ArrayList<>();

    /**
     * Initialize the components.
     *
     * @param hardwareMap The map of attached hardware devices.
     * @return True if all components are found, False otherwise.
     */
    public boolean initialize(HardwareMap hardwareMap) {
        boolean success = true;
        for (int ii = 0; ii < components.size(); ii++) {
            success &= components.get(ii).init(hardwareMap);
        }
        return success;
    }

    /**
     * Convenient all-stop function in case of emergency
     * <p>
     * calls stop() to every component in list
     */
    public void allStop() {
        for (AcREVComponent component : components) {
            component.stop();
        }
    }

    /**
     * Get a component from the list of components
     *
     * @param name Name of the component, as it matches to the config on robot controller phone
     * @return the component you searhed for. Returns null if name doesn't match the list
     */
    public AcREVComponent get(String name) {
        for (int ii = 0; ii < components.size(); ii++) {
            if (components.get(ii).name.equals(name)) {
                return components.get(ii);
            }
        }
        return null;
    }

    /**
     * Adds a component to the list of components
     *
     * @param component component you want to add
     * @return the component you added. If you store the returned AcREVComponent as a local field ('variable') in your OpMode object,
     * you don't have to use get() to retrieve the object later!
     */
    public AcREVComponent add(AcREVComponent component) {
        components.add(component);
        return component;
    }
}
