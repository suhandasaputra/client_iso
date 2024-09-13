/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankdummyserver;

import com.asac.test.ABDSimulator;
import com.asac.test.BankSimulator;
import com.asac.test.BankSumselSimulator;

/**
 *
 * @author herrysuganda
 */
public class BankDummyServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        new BankSimulator().start();
        new ABDSimulator().start();
//        new BankSumselSimulator().start();

//        new ABDSimulator_1().start();
        new MainSimmulator().setVisible(true);
//        new CoreBankingSimulator().start();
    }

}
