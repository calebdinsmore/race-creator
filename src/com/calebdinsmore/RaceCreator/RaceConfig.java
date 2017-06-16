package com.calebdinsmore.RaceCreator;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by calebdinsmore on 6/16/17.
 */
public class RaceConfig {

    YamlConfiguration mDataYaml;

    private JavaPlugin mMain;
    private File mDataFile;
    private String mName;
    private String mRaceDataPath;

    RaceConfig(String raceName, JavaPlugin main) {
        mMain = main;
        mName = raceName;
        mRaceDataPath = "races/" + mName + "/data.yml";
        mDataFile = new File(mMain.getDataFolder(), mRaceDataPath);

        if (!mDataFile.exists()) {
            createNewConfig();
        } else {
            mDataYaml = YamlConfiguration.loadConfiguration(mDataFile);
        }
    }

    private void createNewConfig() {
        mMain.getLogger().info("Creating data.yml for " + mName);
        mDataFile.getParentFile().mkdirs();
        mDataYaml = YamlConfiguration.loadConfiguration(mDataFile);

        mDataYaml.addDefault("name", mName);
        mDataYaml.addDefault("enabled", true);

        try {
            mDataYaml.save(mDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
