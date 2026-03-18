package org.example.module08_1.util;

import org.example.module08_1.model.Email;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigEmailReader {

    public static Email getEmailData(){
        String csvFilePath = "src/test/resources/emaildata.csv";
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while ((line = br.readLine()) != null){
                String[] arr = line.split(",");
                return new Email(arr[0],arr[1],arr[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to read user data");
    }
}
