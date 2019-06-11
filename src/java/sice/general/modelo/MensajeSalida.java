/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.general.modelo;

import java.io.Serializable;

/**
 *
 * @author vplei
 */
public class MensajeSalida implements Serializable{
    
    private int numErr;
    private String errMen;
    private String Control;
    private int consecutivo;

    public int getNumErr() {
        return numErr;
    }

    public void setNumErr(int numErr) {
        this.numErr = numErr;
    }

    public String getErrMen() {
        return errMen;
    }

    public void setErrMen(String errMen) {
        this.errMen = errMen;
    }

    public String getControl() {
        return Control;
    }

    public void setControl(String Control) {
        this.Control = Control;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }
    
}
