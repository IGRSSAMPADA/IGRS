/**
 * 
 */
package com.wipro.igrs.aadhar.domain.authentication.subservice;

import java.io.Serializable;


/**
 * @author ARNAV.NEGI
 *
 */
public enum Gender implements Serializable {

    M,
    F,
    T;

    public String value() {
        return name();
    }

    public static Gender fromValue(String v) {
        return valueOf(v);
    }

}
