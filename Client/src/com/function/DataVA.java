/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.function;

/**
 *
 * @author herrysuganda
 */
public class DataVA {

    private String nama;
    private long saldo;

    public DataVA(String nama, long saldo){
        this.setNama(nama);
        this.setSaldo(saldo);
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the saldo
     */
    public long getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }
}
