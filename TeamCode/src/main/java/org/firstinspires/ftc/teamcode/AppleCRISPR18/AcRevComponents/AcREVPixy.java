package org.firstinspires.ftc.teamcode.AppleCRISPR18.AcRevComponents;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

/**
 * Created by Zach on 2/4/2018.
 */

/**
 * I used the pixy's I2C lego protocol, because it was simple and had what we needed (KISS)
 * In this protocol you query the pixy for data. depending on the registered query it will
 * return an array with 5 or 6 value describing only the LARGEST object detected. this
 * can be done for all signatures at once or for any individual signature. The values retured
 * are the coordinates and size of the object in pixy pixels. Additionally querying all signatures
 * will return the signature and querying a specific signature will return the number of objects
 * of that signature detected as well as data on the largest detected object of that signature.
 * There is also functionality for color codes, but I did not spend time implementing it as we do not
 * need it. I implemented all the queries I think people might want to make in the future - even though
 * we only need X position - for the sake of our posterity. See ATJewelSensor for our usage.
 * <p>
 * link to pixy reference: http://cmucam.org/attachments/1290/Pixy_LEGO_Protocol_1.0.pdf
 * <p>
 * reference to I2C can be found in java docs (applecrispr_2017\doc\javadoc)
 */
public class AcREVPixy extends AcREVComponent {

    //I2C classes
    private I2cDeviceSynch pixy;
    //private I2cDeviceSynchImpl dataQuerier;
    private I2cAddr pixyAddress = I2cAddr.create8bit(0x54);

    //Constants
    public final int MAX_X = 255;
    public final int MAX_Y = 199;
    public final int MAX_W = 255;
    public final int MAX_H = 200;

    public AcREVPixy(String i2cname) {
        name = i2cname;
    }

    @Override
    public boolean init(HardwareMap hardwareMap) {
        pixy = hardwareMap.i2cDeviceSynch.get(name);
        //dataQuerier = new I2cDeviceSynchImpl(pixy, pixyAddress, false);
        //dataQuerier.engage();
        pixy.engage();
        return (pixy != null);
    }

    private byte[] getLargestDetectedObj(int signature) {
        //querying 0x51-57 returns data for largest object of signature as an array: [num of sig. detected,X,Y,Width,Height]
        if (signature > 0 && signature < 6) {
            return pixy.read(0x50 + signature, 5);
        }
        //Not a valid signature
        else {
            return null;
        }
    }

    //querying 0x50 returns data for largest object as an array: [signature,",X,Y,Width,Height]
    private byte[] getLargestDetectedObj() {
        return pixy.read(0x50, 6);
    }

    public int getNumberDetectedObj(int signature) {
        return getLargestDetectedObj(signature)[0];
    }

    public int getLargestDetectedObjX(int signature) {
        return getLargestDetectedObj(signature)[1];
    }

    public int getLargestDetectedObjY(int signature) {
        return getLargestDetectedObj(signature)[2];
    }

    public int getLargestDetectedObjW(int signature) {
        return getLargestDetectedObj(signature)[3];
    }

    public int getLargestDetectedObjH(int signature) {
        return getLargestDetectedObj(signature)[4];
    }


    public int getLargestDetectedObjSig() {
        //this should combine the two bytes into an int signature, but I might have gotten it backwards (if it is not in big edian?)
        return ((int) getLargestDetectedObj()[0] << 8) | getLargestDetectedObj()[1];
    }

    public int getLargestDetectedObjX() {
        return getLargestDetectedObj()[2];
    }

    public int getLargestDetectedObjY() {
        return getLargestDetectedObj()[3];
    }

    public int getLargestDetectedObjW() {
        return getLargestDetectedObj()[4];
    }

    public int getLargestDetectedObjH() {
        return getLargestDetectedObj()[5];
    }

    public int[] getLargestDectedObjInfo(int signature) {
        //fill array with array returned by pixy, then reuturn that array
        byte[] objBytes = getLargestDetectedObj(signature);
        int[] objInfo = new int[5];
        for (int i = 0; i < 5; i++) {
            objInfo[i] = objBytes[i];
        }
        return objInfo;
    }

    public int[] getLargestDectedObjInfo() {
        byte[] objBytes = getLargestDetectedObj();
        int[] objInfo = new int[5];
        objInfo[0] = ((int) objBytes[0] << 8) | objBytes[1];
        for (int i = 1; i < 6; i++) {
            objInfo[i] = objBytes[i];
        }
        return objInfo;
    }

    public int[] getLargestDectedObjXY(int signature) {
        byte[] objBytes = getLargestDetectedObj(signature);
        int[] objInfo = new int[2];
        for (int i = 1; i < 3; i++) {
            objInfo[i] = objBytes[i];
        }
        return objInfo;
    }

    public int[] getLargestDectedObjXY() {
        byte[] objBytes = getLargestDetectedObj();
        int[] objInfo = new int[2];
        for (int i = 2; i < 4; i++) {
            objInfo[i] = objBytes[i];
        }
        return objInfo;
    }

    public int[] getLargestDectedObjWH(int signature) {
        byte[] objBytes = getLargestDetectedObj(signature);
        int[] objInfo = new int[2];
        for (int i = 3; i < 5; i++) {
            objInfo[i] = objBytes[i];
        }
        return objInfo;
    }

    public int[] getLargestDectedObjWH() {
        byte[] objBytes = getLargestDetectedObj();
        int[] objInfo = new int[2];
        for (int i = 4; i < 6; i++) {
            objInfo[i] = objBytes[i];
        }
        return objInfo;
    }

    public int getLargestDectedObjArea(int signature) {
        byte[] objBytes = getLargestDetectedObj(signature);
        int W = objBytes[3];
        int H = objBytes[4];
        return H * W;
    }

    public int getLargestDectedObjArea() {
        byte[] objBytes = getLargestDetectedObj();
        int W = objBytes[4];
        int H = objBytes[5];
        return H * W;
    }

    public int getLargestDectedObjWHRatio(int signature) {
        byte[] objBytes = getLargestDetectedObj(signature);
        int W = objBytes[3];
        int H = objBytes[4];
        return W / H;
    }

    public int getLargestDectedObjWHRatio() {
        byte[] objBytes = getLargestDetectedObj();
        int W = objBytes[4];
        int H = objBytes[5];
        return W / H;
    }
}
