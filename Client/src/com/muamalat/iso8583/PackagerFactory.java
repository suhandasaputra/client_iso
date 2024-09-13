/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muamalat.iso8583;

/**
 *
 * @author herry.suganda
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;

public class PackagerFactory {

    public static ISOPackager getPackager() {
        ISOPackager packager = null;
        {
            FileInputStream is = null;
            try {
//                File f = new File("/opt/asacgateway/iso87ascii.xml");
//                File f = new File("/opt/asacgateway/isobcdisobinaryisoascii/iso87BCD.xml");//syukur
//                File f = new File("c:\\propertiesfile\\asacgateway\\iso87ascii-bcd.xml");
//                File f = new File("c:\\propertiesfile\\asacgateway\\iso87binary.xml");
//                File f = new File("c:\\Users\\HP\\Documents\\suhanda\\iso87ascii.xml");
                  File f = new File("D:\\opt\\asacgateway\\iso87ascii.xml");


                is = new FileInputStream(f);
                packager = new GenericPackager(is);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PackagerFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ISOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(PackagerFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return packager;
    }
}
