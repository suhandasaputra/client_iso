/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.muamalat.iso8583;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;

/**
 *
 * @author User
 */
public class IsoListerner implements ISORequestListener {

    public boolean process(ISOSource arg0, ISOMsg arg1) {
        MessageHandler proc = new MessageHandler();
        try {
//            ISOMsg reply = process.processMessage(msg);
            proc.process(arg1);
//            msg.getSource().send(reply);
        } catch (Exception ex) {
            Logger.getLogger(IsoListerner.class.getName()).log(Level.SEVERE, null, ex);
        }

//            msg.getSource().send(reply);
        return true;
    }

}
