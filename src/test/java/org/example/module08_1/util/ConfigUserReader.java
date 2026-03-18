package org.example.module08_1.util;

import org.example.module08_1.model.Email;
import org.example.module08_1.model.User;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigUserReader {

    public static User getUserData(){
        String csvFilePath = "src/test/resources/credentials.csv";
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while ((line = br.readLine()) != null){
                String[] arr = line.split(",");
                return new User(arr[0],arr[1],arr[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to read user data");
    }

}
