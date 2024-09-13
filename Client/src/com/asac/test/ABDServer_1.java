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
public class ABDServer_1 {

    public static ABDServer_1 getInstance() {
        if (ref == null) {
            setRef(new ABDServer_1());

        }
        return ref;
    }

    /**
     * @param aRef the ref to set
     */
    public static void setRef(ABDServer_1 aRef) {
        ref = aRef;
    }
    private ServerSocket providerSocket;
    private Socket connection;
    private DataOutputStream out;
    private DataInputStream in;
//    private ObjectOutputStream out;
//    private ObjectInputStream in;

    public ABDServer_1() {
        TurnOnConnection();
    }

    public void TurnOnConnection() {
        try {
            System.out.println("ABD socket");
            //1. creating a server socket
//            setProviderSocket(new ServerSocket(3456, 10));
//            setProviderSocket(new ServerSocket(14135, 10));
//            setProviderSocket(new ServerSocket(14000, 10));
//            //2. Wait for connection
            System.out.println("Waiting for connection");
//            connection = getProviderSocket().accept();
            connection = new Socket("127.0.0.1", 14002);
            System.out.println("Connection received from " + getConnection().getPort()+ " " + getConnection().getInetAddress().getHostName());
            //3. get Input and Output streams
            setOut(new DataOutputStream(getConnection().getOutputStream()));
            getOut().flush();
            setIn(new DataInputStream(getConnection().getInputStream()));
            ISOMsg msg = new ISOMsg();
            msg.setPackager(PackagerFactory.getPackager());
            msg.setMTI("0800");
            msg.set(3,"001");
            sendMessage(new String(msg.pack()));
            System.out.println("Server Ready : " + this.getClass().getName());
        } catch (IOException ioException) {
//            ioException.printStackTrace();
        } catch (ISOException ex) {
            Logger.getLogger(ABDServer_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String msg) {
        try {
            getOut().write(HeaderMessage.digitHeader(false, msg).getBytes());
            getOut().flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private static ABDServer_1 ref;
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
