package com.java.zonov.cft.sorting.verify;

import java.util.ArrayList;
import java.util.List;

public class Verification {

    public static List<Integer> check(List<String> list){
        List<Integer> data = new ArrayList<>();

        for(int i=0; i<list.size(); i++){
            try{
                data.add(Integer.valueOf(list.get(i)));
            }catch (NumberFormatException e){
                System.err.println("Wrong data format: \"" + list.get(i) + "\" is not correct" );
            }
        }
        return data;
    }
}
