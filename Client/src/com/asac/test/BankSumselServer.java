/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asac.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author herry.suganda
 */
public class BankSumselServer {

    public static BankSumselServer getInstance() {
        if (ref == null) {
            setRef(new BankSumselServer());

        }
        return ref;
    }

    /**
     * @param aRef the ref to set
     */
    public static void setRef(BankSumselServer aRef) {
        ref = aRef;
    }
    private ServerSocket providerSocket;
    private Socket connection;
    private DataOutputStream out;
    private DataInputStream in;
//    private ObjectOutputStream out;
//    private ObjectInputStream in;

    public BankSumselServer() {
        TurnOnConnection();
    }

    public void TurnOnConnection() {
        try {
            System.out.println("bank socket");
            //1. creating a server socket
//            setProviderSocket(new ServerSocket(3456, 10));
//            setProviderSocket(new ServerSocket(14135, 10));
            setProviderSocket(new ServerSocket(1503, 10));
//            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = getProviderSocket().accept();
//            connection = new Socket("127.0.0.1", 6667);
            System.out.println("Connection received from " + getConnection().getPort()+ " " + getConnection().getInetAddress().getHostName());
            //3. get Input and Output streams
            setOut(new DataOutputStream(getConnection().getOutputStream()));
            getOut().flush();
            setIn(new DataInputStream(getConnection().getInputStream()));
            System.out.println("Server Ready : " + this.getClass().getName());
        } catch (IOException ioException) {
//            ioException.printStackTrace();
        }
    }

//    public void sendMessage(String msg) {
//        try {
//            getOut().write(("\02"+msg+"\03").getBytes());
//            getOut().flush();
//            System.out.println("server>" + msg);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//    }
    private static BankSumselServer ref;
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
