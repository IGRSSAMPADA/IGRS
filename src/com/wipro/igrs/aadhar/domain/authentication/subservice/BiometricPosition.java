/**
 * 
 */
package com.wipro.igrs.aadhar.domain.authentication.subservice;

import java.io.Serializable;


/**
 * @author ARNAV.NEGI
 *
 */
public enum BiometricPosition implements Serializable {

    LEFT_IRIS,
    RIGHT_IRIS,
    LEFT_INDEX,
    LEFT_LITTLE,
    LEFT_MIDDLE,
    LEFT_RING,
    LEFT_THUMB,
    RIGHT_INDEX,
    RIGHT_LITTLE,
    RIGHT_MIDDLE,
    RIGHT_RING,
    RIGHT_THUMB,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static BiometricPosition fromValue(String v) {
        return valueOf(v);
    }

}
