/**
 * 
 */
package com.wipro.igrs.aadhar.domain.authentication.subservice;

import java.io.Serializable;


/**
 * @author ARNAV.NEGI
 *
 */
public enum BioMetricType implements Serializable {

    FMR,
    FIR,
    IIR;

    public String value() {
        return name();
    }

    public static BioMetricType fromValue(String v) {
        return valueOf(v);
    }

}
