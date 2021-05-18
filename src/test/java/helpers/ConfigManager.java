package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Sandeep
 *
 */
public class ConfigManager {

    private Properties properties;
    private static final String GLOBAL_CONFIG_PATH = System.getProperty("user.dir")+"/src/test/resources/config.properties";

    public String getProperty(String key) {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty(key);
    }

    private void readProperties() {
        try {
            File propertiesFile = new File(GLOBAL_CONFIG_PATH);
            FileInputStream propertiesFileInputStream = new FileInputStream(propertiesFile);
            properties = new Properties();
            properties.load(propertiesFileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
