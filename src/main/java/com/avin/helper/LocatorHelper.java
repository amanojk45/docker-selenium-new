package com.avin.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * @author Naveena US
 */
public class LocatorHelper {

        private final Properties properties = new Properties();
        private  String filePath = null;

    public LocatorHelper(String path){
        this.filePath = path;
        initPool();
    }
    private void initPool () {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.filePath);
            properties.load(fileInputStream);
        }catch (FileNotFoundException e){
            System.out.println("File is not present in the given path i.e : "+filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getValue(String key){ return  this.properties.getProperty(key);
    }}
