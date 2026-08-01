package space.meowcats.breadfarmer.game.utils;

import java.io.IOException;
import java.util.Properties;

public class VersionInfo {
    private static String version = "unknown";
    private static String buildDate = "unknown";

    static {
        Properties props = new Properties();
        try {
            props.load(VersionInfo.class.getClassLoader().getResourceAsStream("version.properties"));
            version = props.getProperty("version", "unknown");
            buildDate = props.getProperty("buildDate", "unknown");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static String getVersion() {
        return version;
    }

    public static String getBuildDate() {
        return buildDate;
    }
}
