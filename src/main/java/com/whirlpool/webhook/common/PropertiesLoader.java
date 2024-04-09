package com.whirlpool.webhook.common;

import com.whirlpool.webhook.exception.UtilityException;
import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class PropertiesLoader {
    public static Properties loadProperties(String configFile) throws UtilityException {

      Properties configuration = new Properties();
      System.out.println("config file="+configFile);
      try (FileInputStream fileInputStream = new FileInputStream(configFile)){
          configuration.load(fileInputStream);
      }catch (IOException e){
          throw new UtilityException("Error loading properties from file: " + configFile);
      }
      return configuration;
    }
}
