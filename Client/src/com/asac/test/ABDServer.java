/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.test;

import com.muamalat.iso8583.PackagerFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author herry.suganda
 */
public class ABDServer {

    public static ABDServer getInstance() {
        if (ref == null) {
            setRef(new ABDServer());

        }
        return ref;
    }

    /**
     * @param aRef the ref to set
     */
    public static void setRef(ABDServer aRef) {
        ref = aRef;
    }
    private ServerSocket providerSocket;
    private Socket connection;
    private DataOutputStream out;
    private DataInputStream in;
//    private ObjectOutputStream out;
//    private ObjectInputStream in;

    public ABDServer() {
        TurnOnConnection();
    }

    public void TurnOnConnection() {
        try {
            System.out.println("ABD socket");
            //1. creating a server socket
//            setProviderSocket(new ServerSocket(3456, 10));
//            setProviderSocket(new ServerSocket(14135, 10));
            setProviderSocket(new ServerSocket(14000, 10));
//            //2. Wait for connection
//            System.out.println("Waiting for connection");
            connection = getProviderSocket().accept();
//            connection = new Socket("192.168.0.144", 14000);
//            connection = new Socket("10.81.24.13", 14000);
            connection = new Socket("127.0.0.1", 14000);
//            connection = new Socket("10.1.7.124", 57583);
            //connection = new Socket("10.10.52.200", 14000);
//            connection = new Socket("192.168.0.91", 14001);//syukur
            System.out.println("Connection received from " + getConnection().getPort() + " " + getConnection().getInetAddress().getHostName());
            //3. get Input and Output streams
            setOut(new DataOutputStream(getConnection().getOutputStream()));
            getOut().flush();
            setIn(new DataInputStream(getConnection().getInputStream()));
            ISOMsg msg = new ISOMsg();
            msg.setPackager(PackagerFactory.getPackager());
//            msg.setMTI("0800");
//            msg.set(3,"001");

            //BRIS
//            msg.setMTI("0800");
//            msg.set(7, "0616233355");
//            msg.set(11, "000177");
//            msg.set(70, "001");
//            sendMessage(new String(msg.pack()));
//            System.out.println("MSG "+ new String(msg.pack()));
            System.out.println("Server Ready : " + this.getClass().getName());
        } catch (IOException ioException) {
//            ioException.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        try {
            getOut().write(HeaderMessage.hexaDigitHeader(false, msg).getBytes());
            getOut().flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private static ABDServer ref;
    private String message;

    /**
     * @return the in
     */
    public DataInputStream getIn() {
        return in;
    }

    /**
     * @param out the out to set
     */
    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    /**
     * @param in the in to set
     */
    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void destroy() {
        try {
//            System.out.println("masuk destroy server");
            if (getIn() != null) {
                getIn().close();
            }
            if (getOut() != null) {
                getOut().close();
            }
            if (getConnection() != null) {
                getConnection().close();
            }
            if (getProviderSocket() != null) {
                getProviderSocket().close();
            }
            setRef(null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the providerSocket
     */
    public ServerSocket getProviderSocket() {
        return providerSocket;
    }

    /**
     * @param providerSocket the providerSocket to set
     */
    public void setProviderSocket(ServerSocket providerSocket) {
        this.providerSocket = providerSocket;
    }

    /**
     * @return the connection
     */
    public Socket getConnection() {
        return connection;
    }

    /**
     * @return the out
     */
    public DataOutputStream getOut() {
        return out;
    }
}
