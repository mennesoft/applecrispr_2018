package org.firstinspires.ftc.teamcode.AppleCRISPR18.Pixy;

/**
 * Utilities to work with the Pixy camera module.
 */
public final class PixyUtil {

    /**
     * Build a word (16-bit value) from the Pixy data stream, which is in Little Endian mode, the
     * least significant byte is first.
     *
     * @param data  The data returned from the Pixy.
     * @param index The 0-based index into the array where the first byte of the word resides. This
     *              method will reference two bytes, one at index and the other at index + 1.
     * @return The 16-bit word in data, starting at index.
     */
    public static short getWord(byte[] data, int index) {
        // a little error handling
        if (data == null) return 0;
        if (index < 0) return 0;
        if (index > (data.length - 2)) {
            String message = "PixyUtil.getWord index " + index + " in data of length " + data.length;
            throw new IndexOutOfBoundsException(message);
        }

        // little endian means the least significant byte is at index, and the most significant is
        // at index + 1
        return (short) ((data[index + 1] << 8) + data[index]);
    }
}
