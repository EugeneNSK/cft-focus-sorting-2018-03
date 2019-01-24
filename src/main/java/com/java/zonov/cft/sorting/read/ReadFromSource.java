package com.java.zonov.cft.sorting.read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromSource {

    public static List<String> getData(String fileName){
        File file = new File(fileName);
        List <String> data = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()){
                String [] str = sc.nextLine().split(" ");
                if(str.length > 0 && !str[0].isEmpty()) {
                    data.add(str[0]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Something wrong with file: " + fileName);
        }
        return data;
    }
}
