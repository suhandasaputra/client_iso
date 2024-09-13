/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.test;

import com.function.StringFunction;
import com.muamalat.iso8583.PackagerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Hex;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author herry.suganda
 */
public class CoreBankingSimulator extends Thread {

    private String message;

    @Override
    @SuppressWarnings("static-access")
    public void run() {
        // mendengarkan message yang akan dikirimkan oleh server
        int intData;
        boolean bRec = false;
        StringBuffer s = new StringBuffer("");
        int jmlMsg = 0;
        while (true) {
            try {
                while (true) {
                    message = "";
                    if (CoreBankingServer.getInstance().getIn() != null) {
                        bRec = false;
                        s.delete(0, s.length());
                        System.out.println("ready for listener message from client");
                        CoreBankingServer.getInstance();
//                        intData = CoreBankingServer.getInstance().getIn().read();

//                        if (intData > 0) {
//                            while (CoreBankingServer.getInstance().getIn().available() > 0) {
//                                if (intData == 2) {
//                                    bRec = true;
//                                }
//                                intData = CoreBankingServer.getInstance().getIn().read();
//                                if (intData == 3) {
//                                    message = s.toString();
//                                    break;
//                                } else {
//                                    if (bRec) {
//                                        s.append((char) intData);
//                                    }
//                                }
//
//                            }
                        message = grabMessageHeaderLengthByte();
//                        message = grabMessageHeaderLengthHexaByte();
                        jmlMsg++;
                        System.out.print("2. From ASAC Socket " + StringFunction.getCurrentDateForLog() + " : " + jmlMsg + " : ");
                        System.out.println(message);
                        if (message.equals("connection down")) {
                            CoreBankingServer.getInstance().destroy();
                        } else {
                        if (!message.equals("")) {
//                            sendMessage(message);
                            ISOMsg isoMsg = unpackRequest(message);
                            if (isoMsg.getMTI().equals("0200")) {
                                if (isoMsg.getString(3).equals("301000")) {
                                    isoMsg.setMTI("0210");
                                    isoMsg.set(39, "00");
                                    isoMsg.set(48, "CIF0009/ABC/01 301  12345678901360MR  HERRY SUGANDA                                     000003476800000JAKARTA MAIN BRANCH           S");
                                    isoMsg.set(54, "01360C000003476800000");
                                } else if (isoMsg.getString(3).equals("341000") || isoMsg.getString(3).equals("341002")) {
                                    isoMsg.setMTI("0210");
                                    isoMsg.set(39, "00");
                                    isoMsg.set(48, "2015010120150130C02120150130000000000RYCIANDA ARKHA RAFIIZKY       20150130");
                                    isoMsg.set(54, "01360C000010000000000");
                                    isoMsg.set(120,"20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C");
                                    isoMsg.set(121,"20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C");
                                    isoMsg.set(122,"20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C");
                                    isoMsg.set(123,"20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C20150107121314000000000TESTING TRANSAKSI UNTUK ACCOUNT STATEMENT    000000010000000C000000000000000C");
                                } else if (isoMsg.getString(3).equals("471000")) {
                                    isoMsg.setMTI("0210");
                                    isoMsg.set(39, "00");
                                } else if (isoMsg.getString(3).equals("500000")) {
                                    isoMsg.setMTI("0210");
                                    isoMsg.set(39, "00");
                                    isoMsg.set(48, "HERRY SUGANDA");
                                    isoMsg.set(54, "1000360C000100000000");
                                }
                                
                                String txt = new String(isoMsg.pack());
//                                Thread.sleep(15000);
                                sendMessage(txt);
                            }
                            if (isoMsg.getMTI().equals("0800")) {
                                isoMsg.setMTI("0810");
                                isoMsg.set(39, "00");
                                String txt = new String(isoMsg.pack());
//                                txt = generateHexFromMsgLength(txt) + txt;
                                sendMessage(txt);
                            }
//                            if (isoMsg.getMTI().equals("0400")) {
//                                System.out.println("masuk 0400");
//                                isoMsg.setMTI("0410");
//                                isoMsg.set(39, "00");
//                                String txt = new String(isoMsg.pack());
////                                txt = generateHexFromMsgLength(txt) + txt;
//                                sendMessage(txt);
//                            }
//                            if (isoMsg.getMTI().equals("0200")) {
//                                isoMsg.setMTI("0210");
//                                if (isoMsg.getString(3).equals("908001")) {
//                                    isoMsg.set(39, "00");
//                                    isoMsg.set(47, "Herry Suganda");
//                                    isoMsg.set(48, isoMsg.getString(48) + "Muhammad Alwi                 000000100000000");
//                                    isoMsg.set(63, "Pembayaran Uang Sekolah            ");
//                                } else if (isoMsg.getString(3).equals("918001")) {
//                                    isoMsg.set(39, "00");
//
//                                } else if (isoMsg.getString(3).equals("928001")) {
//                                    isoMsg.set(39, "00");
//
//                                } else if (isoMsg.getString(3).equals("938001")) {
//                                    isoMsg.set(39, "00");
//                                    isoMsg.set(48, isoMsg.getString(48) + "Muhammad Alwi                 000000100000000");
//                                    isoMsg.set(63, "Pembayaran Uang Sekolah            ");
//                                }
//                                String txt = new String(isoMsg.pack());
//                                sendMessage(txt);
////                                ISOMsg msg = new ISOMsg();
////                                msg.setPackager(PackagerFactory.getPackager());
////                                msg.setMTI("0210");
////                                msg.set(2, "1234561234561234");
////                                msg.set(3, "900000");
////                                msg.set(4, "111111111111");
////                                msg.set(7, "1111111111");
////                                msg.set(11, "111111");
////                                msg.set(12, "111111");
////                                msg.set(13, "1111");
////                                msg.set(18, "1111");
////                                msg.set(37, "111111111111");
////                                msg.set(41, "11111111");
////                                msg.set(43, StringFunction.pad("Herry Suganda", 40, " ", "Left"));
////                                msg.set(48, "111111111111");
////                                msg.set(49, "360");
////                                msg.set(103, "200981158");
////                            isoMsg.setMTI("0210");
////                            isoMsg.set(39, "00");
////                            isoMsg.set(48, isoMsg.getString(48) + pad("Herry Suganda", 30, " ", "Left") + pad("1000000", 12, "0", "Right"));
////                                String txt = new String(msg.pack());
//                                System.out.println("send : " + txt);
////                                String txt2 = StringFunction.generateHexFromMsgLength(txt.trim()) + txt.trim();
////                            String txt2 = " é0200F238400008A18000000000000200000016601923123456789090010000000000000009190423450000230423450919601004234500002300010001sudirman                                054tralala     Herry Suganda                 00000100000036009200981158";
////                            String txt2 = "0200F238400008A18000000000000200000016601923123456789090010000000000000009190423450000230423450919601004234500002300010001sudirman                                054tralala     Herry Suganda                 00000100000036009200981158";
////                                System.out.println(txt2.length() + " - " + txt2);
////                                StartUpServer.getInstance().getOut().write(txt2.getBytes(), 0, txt2.length());
////                                StartUpServer.getInstance().getOut().flush();
////                                int lengthData = StartUpServer.getInstance().getIn().read();
//                            }
//                            }
//                            ConnectToServer.getInstance()ectToServer.getInstance().getInStream().reset();

//                        } else {
////                        status = ConnectToClient.getInstance().isSignon();
////                        ConnectToClient.getInstance().destroy();
                        }
                        }
                    }
                }
//            } catch (ISOException ex) {
//                Logger.getLogger(ReceiverMessage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CoreBankingSimulator.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                CoreBankingServer.getInstance().destroy();
            }

        }

    }

    public ISOMsg unpackRequest(String message) throws ISOException, Exception {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(PackagerFactory.getPackager());
        isoMsg.unpack(message.getBytes());
        return isoMsg;
    }
    public ISOMsg unpackRequest(byte[] message) throws ISOException, Exception {
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(PackagerFactory.getPackager());
        isoMsg.unpack(message);
        return isoMsg;
    }

    private void sendMessage(String msg) {
        boolean sendingSuccess = false;
//        int start = TimeFunction.getSecondInDay();
        try {

//            while (!sendingSuccess) {
//                if ((TimeFunction.getSecondInDay() - start) < 20) {
            if (CoreBankingServer.getInstance().getOut() != null) {
//                CoreBankingServer.getInstance().getOut().write(("\02" + msg + "\03").getBytes());

                System.out.println(msg);
                CoreBankingServer.getInstance().getOut().write((digitHeader(msg)).getBytes());
                CoreBankingServer.getInstance().getOut().flush();
//                        sendingSuccess = true;
            }
//                } else {
//                    sendingSuccess = true;
//                }

//            }
        } catch (IOException ioException) {
//            log.error(ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    public static String digitHeader(String msg) {
//        log.info("HeaderMessage : " + StringFunction.pad(String.valueOf(msg.length()), 4, "0", "Right") + "  " + msg);
        return StringFunction.pad(String.valueOf(msg.length()), 4, "0", "Right") + msg;
    }

    private String grabMessageHeaderLengthByte() {
        int intData;
        boolean bRec = false;
        StringBuffer s = new StringBuffer("");
        try {
            s.delete(0, s.length());
//            System.out.println("listen data from socket");
            intData = CoreBankingServer.getInstance().getIn().read();
//            System.out.println("get listen data from socket");
            if (intData < 0) {
//                RebuildSocketConnection.rebuildConnection(sce);
                return "connection down";
            } else {
                s.append((char) intData);
                for (int i = 0; i < 3; i++) {
                    intData = CoreBankingServer.getInstance().getIn().read();
                    s.append((char) intData);
                }
                int msgLength = Integer.valueOf(s.toString());
                s.delete(0, s.length());
                for (int i = 0; i < msgLength; i++) {
                    intData = CoreBankingServer.getInstance().getIn().read();
                    s.append((char) intData);
                }
            }
        } catch (IOException ex) {
//            log.error("IOException grabMessageHeaderLengthByte : " + ex.getMessage());
//            if (!sce.isStatusStart()) {
//                killThread = true;
//            }else{
//                killThread = false;
//            }
//            System.out.println("killthread : " + killThread);
//            ex.printStackTrace();
//            return "connection down";
//        } catch (Exception ex) {
//            log.error("Exception grabMessageHeaderLengthByte : " + ex.getMessage());
            ex.printStackTrace();
//            return "connection down";
        }
//        log.info(Thread.currentThread().getId() + " - port " + port + " - " + termID + " grabMessageHeaderLengthByte : " + s.toString());
//        System.out.println(Thread.currentThread().getId() + " - " + port + " - " + termID + " grabMessageHeaderLengthByte : " + s.toString());
        return s.toString();
    }
    
    private String grabMessageHeaderLengthHexaByte() {
        int intData;
        int msgLength = 0;
        StringBuffer s = new StringBuffer("");
        BigInteger value;
        try {
            s.delete(0, s.length());
//            System.out.println("listen data from siskohat");
            intData = CoreBankingServer.getInstance().getIn().read();
            System.out.println("intData : " + intData + " : -" + String.valueOf((char) intData) + "-");
            msgLength += intData;
            intData = CoreBankingServer.getInstance().getIn().read();
            System.out.println("intData : " + intData + " : -" + String.valueOf((char) intData) + "-");
            msgLength += intData;

            if (intData < 0) {
                return "connection down";
            } else {
                value = new BigInteger(Hex.encodeHexString(String.valueOf((char) msgLength).getBytes()), 16);
                msgLength = value.intValue();                
            System.out.println("hexa message length : " + msgLength);
//                if (true){
//                    msgLength = msgLength-2;
//                }
                byte[] msgbyte = new byte[msgLength];
                for (int i = 0; i < msgLength; i++) {
//                    intData = CoreBankingServer.getInstance().getIn().read();
//                    System.out.println((char) intData);
//                    s.append((char) intData);
                    msgbyte[i] = CoreBankingServer.getInstance().getIn().readByte();
                }
                try {
                    System.out.println(new String(msgbyte));
                    System.out.println(msgbyte.length);
                    ISOMsg iso = unpackRequest(msgbyte);
                    System.out.println(iso.getMTI());
                    System.out.println(iso.getString(7));
                    System.out.println(iso.getString(11));
                    System.out.println(iso.getString(33));
                    System.out.println(iso.getString(70));
                } catch (Exception ex) {
                }
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return "connection down";
        } finally{
            value=null;
        }
        System.out.println("grabMessageHeaderLengthHexaByte : " + s.toString());
        return s.toString();
    }
}
