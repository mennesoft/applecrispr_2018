package org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy;

import android.graphics.Point;

import java.util.Locale;

/**
 * A data packet returned from the Pixy for a specific query. It consists of 5 bytes that describe
 * the largest detected block of the requested type. If no object of that type is
 * detected, all bytes will be zero (0).
 * <p>
 * The information for this class comes from the PDF here:
 * http://cmucam.org/attachments/1290/Pixy_LEGO_Protocol_1.0.pdf
 */
public class LegoPixySpecificType {
    /**
     * The query address that implements the request of object type signature 1.
     */
    public static final int Query1 = 0x51;

    /**
     * The query address that implements the request of object type signature 2.
     */
    public static final int Query2 = 0x52;

    /**
     * The query address that implements the request of object type signature 3.
     */
    public static final int Query3 = 0x53;

    /**
     * The query address that implements the request of object type signature 4.
     */
    public static final int Query4 = 0x54;

    /**
     * The query address that implements the request of object type signature 5.
     */
    public static final int Query5 = 0x55;

    /**
     * The query address that implements the request of object type signature 6.
     */
    public static final int Query6 = 0x56;

    /**
     * The query address that implements the request of object type signature 7.
     */
    public static final int Query7 = 0x57;


    /**
     * Specifies whether no object was detected.
     */
    private final boolean isNone;

    /**
     * Backing field for the number of matches property.
     */
    private final int matchCount;

    /**
     * Backing field for the location of the center of the largest matching object.
     */
    private final Point centerLargest;

    /**
     * Backing field for the specific signature of object that was requested, 1-7.
     */
    private final int objectType;

    /**
     * Backing field for the width of the largest object.
     */
    private final int width;

    /**
     * Backing field for the height of the largest object.
     */
    private final int height;

    /**
     * Constructor to define all fields. This class is immutable.
     *
     * @param type  The type of object requested (1 through 7).
     * @param count The number of matching blocks of the requested type.
     * @param x     X value of center of largest detected block, ranging between 0 and 255. An x value
     *              of 255 is the far right side of Pixy’s image.
     * @param y     Y value of center of largest detected block, ranging between 0 and 199. A value of
     *              199 is the far bottom side of Pixy’s image.
     * @param w     Width of largest block, ranging between 1 and 255. A width of 255 is the full
     *              width of Pixy’s image.
     * @param h     Height of largest block, ranging between 1 and 200. A height of 200 is the full
     *              height of Pixy’s image.
     */
    private LegoPixySpecificType(int type, int count, int x, int y, int w, int h) {
        matchCount = count;
        centerLargest = new Point(x, y);
        objectType = type;
        width = w;
        height = h;
        isNone = ((count == 0) && (x == 0) && (y == 0) && (w == 0) && (h == 0));
    }

    /**
     * Create a new instance of the specific type of block in the Pixy's image.
     *
     * @param signature The type of object requested (1 through 7).
     * @param data      The data returned from the Pixy, should be 5 bytes long.
     * @return The new instance, or <c>null</c> if the data is incorrect.
     */
    public static LegoPixySpecificType read(int signature, byte[] data) {
        // error handling
        if (data == null) return null;
        if (data.length < 6) return null;

        int count = data[0];
        int x = data[1];
        int y = data[2];
        int w = data[3];
        int h = data[4];

        return new LegoPixySpecificType(signature, count, x, y, w, h);
    }

    /**
     * Gets the number of blocks that match the specified signature.
     *
     * @return The number of matching blocks of the type.
     */
    public int getMatchCount() {
        return matchCount;
    }

    /**
     * Gets the center of the largest detected block. For the x coordinate, zero is the left and
     * 255 is the right side of Pixy's image. For the y coordinate, zero is the top and 199 is the
     * bottom of the image.
     *
     * @return The centerLargest of the largest detected block.
     */
    public Point getCenterLargest() {
        return centerLargest;
    }

    /**
     * Gets the signature of block that was requested, a number 1 through 7.
     *
     * @return The signature or object type, 1-7.
     */
    public int getSignature() {
        return objectType;
    }

    /**
     * Gets the height of the largest detected block, from 1 (small) to 200 (full image).
     *
     * @return The height of the largest detected block.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the largest detected block, from 1 (small) to 255 (full image).
     *
     * @return The width of the largest detected block.
     */
    public int getWidth() {
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
        return String.format(Locale.getDefault(), "%d [%d]: centerLargest (%d,%d) size [%d,%d]",
                objectType, matchCount, centerLargest.x, centerLargest.y, width, height);
    }
}
