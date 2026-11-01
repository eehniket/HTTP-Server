package com.eehniketsjunk.httpserver.config;

import com.eehniketsjunk.httpserver.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    public static Configuration myCurrentConfiguration;

    private ConfigurationManager(){

    }
    public static ConfigurationManager getInstance(){
        if(instance == null){
            instance = new ConfigurationManager();
        }
        return instance;
    }

    /**
     * used to load config file
     */
    public void LoadConfigurationFile(String path){
        FileReader filereader = null;
        try {
            filereader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while((i = filereader.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error Parsing the Configuration File.",e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error Parsing the Configuration File, internal.",e);
        }
    }

    /**
     * used to load the current config
     */
    public Configuration getCurrentConfig(){
        if(myCurrentConfiguration == null){
            throw new HttpConfigurationException("No current configuration set.");
        }
        return myCurrentConfiguration;
    }
}

