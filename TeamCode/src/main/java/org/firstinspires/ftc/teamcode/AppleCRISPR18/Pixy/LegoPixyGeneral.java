package org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy;

import android.graphics.Point;

import java.util.Locale;

/**
 * A data packet returned from the Pixy for a General query. It consists of 6 bytes that describe
 * the largest detected block within all signatures, including color coded blocks. If no object is
 * detected, all bytes will be zero (0).
 * <p>
 * The information for this class comes from the PDF here:
 * http://cmucam.org/attachments/1290/Pixy_LEGO_Protocol_1.0.pdf
 */
public class LegoPixyGeneral {
    /**
     * The query address that implements the “General Mode” in the Pixy LEGO documentation.
     */
    public static final int Query = 0x50;

    /**
     * Specifies whether no object was detected.
     */
    private final boolean isNone;

    /**
     * Backing field for the signature property.
     */
    private final short signature;

    /**
     * Backing field for the location of the center of the object.
     */
    private final Point center;

    /**
     * Backing field for the width of the largest object.
     */
    private final byte width;

    /**
     * Backing field for the height of the largest object.
     */
    private final byte height;

    /**
     * Constructor to define all fields. This class is immutable.
     *
     * @param sig A 16-bit value that describes the signature of the largest block. A value of 1
     *            thru 7 corresponds to signatures 1 thru 7. A value greater than 7 is a color code
     *            encoded in octal (base8).
     * @param x   X value of center of largest detected block, ranging between 0 and 255. An x value
     *            of 255 is the far right side of Pixy’s image.
     * @param y   Y value of center of largest detected block, ranging between 0 and 199. A value of
     *            199 is the far bottom side of Pixy’s image.
     * @param w   Width of largest block, ranging between 1 and 255. A width of 255 is the full width
     *            of Pixy’s image.
     * @param h   Height of largest block, ranging between 1 and 200. A height of 200 is the full
     *            height of Pixy’s image.
     */
    private LegoPixyGeneral(short sig, byte x, byte y, byte w, byte h) {
        signature = sig;
        center = new Point(x, y);
        width = w;
        height = h;
        isNone = ((sig == 0) && (x == 0) && (y == 0) && (w == 0) && (h == 0));
    }

    /**
     * Create a new instance of the largest detected block in the Pixy's image.
     *
     * @param data The data returned from the Pixy, should be 6 bytes long.
     * @return The new instance, or <c>null</c> if the data is incorrect.
     */
    public static LegoPixyGeneral read(byte[] data) {
        // error handling
        if (data == null) return null;
        if (data.length < 6) return null;

        short sig = PixyUtil.getWord(data, 0);
        byte x = data[2];
        byte y = data[3];
        byte w = data[4];
        byte h = data[5];

        return new LegoPixyGeneral(sig, x, y, w, h);
    }

    /**
     * Gets a 6-bit value that describes the signature of the largest block. A value of 1 thru 7
     * corresponds to signatures 1 thru 7. A value greater than 7 is a color code encoded in octal
     * (base 8).
     *
     * @return The signature.
     */
    public short getSignature() {
        return signature;
    }

    /**
     * Gets the center of the largest detected block. For the x coordinate, zero is the left and
     * 255 is the right side of Pixy's image. For the y coordinate, zero is the top and 199 is the
     * bottom of the image.
     *
     * @return The center of the largest detected block.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gets the height of the largest detected block, from 1 (small) to 200 (full image).
     *
     * @return The height of the largest detected block.
     */
    public byte getHeight() {
        return height;
    }

    /**
     * Gets the width of the largest detected block, from 1 (small) to 255 (full image).
     *
     * @return The width of the largest detected block.
     */
    public byte getWidth() {
        return width;
    }

    /**
     * Override to provide a useful string for debugging and logging purposes.
     *
     * @return A string representing this object.
     */
    @Override
    public String toString() {
        if (isNone) {
            return "(none)";
        }
        return String.format(Locale.getDefault(), "%d: center (%d,%d) size [%d,%d]",
                signature, center.x, center.y, width, height);
    }
}
