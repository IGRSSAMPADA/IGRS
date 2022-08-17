/**
 * 
 */
package com.wipro.igrs.aadhar.domain.authentication.subservice;

import java.io.Serializable;


/**
 * @author ARNAV.NEGI
 *
 */
public enum MatchingStrategy implements Serializable {

    E,
    P,
    F;

    public String value() {
        return name();
    }

    public static MatchingStrategy fromValue(String v) {
        return valueOf(v);
    }

}
