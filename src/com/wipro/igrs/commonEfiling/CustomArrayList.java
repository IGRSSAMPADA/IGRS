package com.wipro.igrs.commonEfiling;

import java.util.ArrayList;



public class CustomArrayList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {

        int size = size();

        StringBuilder output = new StringBuilder();

        String delimiter = CommonConstant.CUSTOM_ARRAYLIST_SEPARATOR;

        for (int i = 0; i < size; i++) {

            output.append( get( i ) );
            if ( i < size - 1 ) {

                output.append( delimiter );
            }
        }

        return output.toString();
    }
}