package org.example.module08_1.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigUserReader {
    private String[] arr;

    public ConfigUserReader() {
        getCreds();
    }

    private void getCreds(){
        String csvFilePath = "src/test/resources/credentials.csv";
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while ((line = br.readLine()) != null){
                arr = line.split(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return arr[0];
    }

    public String getPassword() {
        return arr[1];
    }

    public String getExpectedEmail() {
        return arr[2];
    }

}
