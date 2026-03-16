package org.example.module08_1.Util;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigEmailReader {
    private String[] arr;

    public ConfigEmailReader() {
        getEmailData();
    }

    private void getEmailData(){
        String csvFilePath = "src/test/resources/emaildata.csv";
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while ((line = br.readLine()) != null){
                arr = line.split(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getDestinationEmail(){
        return arr[0];
    }
    public String getSubject() {
        return arr[1];
    }
    public String getBody() {
        return arr[2];
    }
}
