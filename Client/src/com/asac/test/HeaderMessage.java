/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.test;

import com.function.StringFunction;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

/**
 *
 * @author herrysuganda
 */
public class HeaderMessage {

    private static Logger log = Logger.getLogger(HeaderMessage.class);

    public static String stxGtx(String msg) {
        return "\02" + msg + "\03";
    }

    public static String digitHeader(boolean lengthIncl, String msg) {
//        log.info("HeaderMessage : " + StringFunction.pad(String.valueOf(msg.length()), 4, "0", "Right") + "  " + msg);
        if (lengthIncl) {
            return StringFunction.pad(String.valueOf(msg.length() + 4), 4, "0", "Right") + msg;
        } else {
            return StringFunction.pad(String.valueOf(msg.length()), 4, "0", "Right") + msg;
        }
    }

    public static String hexaDigitHeader(boolean lengthIncl, String msg) {
        try {
            String hexLength;
            if (lengthIncl) {
                hexLength = StringFunction.pad(Integer.toHexString(msg.length() + 2), 4, "0", "Right");
            } else {
                hexLength = StringFunction.pad(Integer.toHexString(msg.length()), 4, "0", "Right");
            }
            log.info("sent message with length : " + msg.length());
            log.info("sent message with hex length : " + hexLength);
            byte[] bytes = Hex.decodeHex(hexLength.toCharArray());
            log.info("msg : " + new String(bytes) + msg);
            return new String(bytes) + msg;

        } catch (DecoderException ex) {
//            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return msg;
    }

    public static String getFinallyMessage(boolean lengthIncl, int headerType, String msg) {
        if (headerType == 1) {
            return digitHeader(lengthIncl,msg);
        } else if (headerType == 2) {
            return stxGtx(msg);
        } else if (headerType == 3) {
            return hexaDigitHeader(lengthIncl,msg);
        }
        return null;
    }
}
