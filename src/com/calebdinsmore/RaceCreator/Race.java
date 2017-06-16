package com.calebdinsmore.RaceCreator;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static com.sun.tools.doclint.Entity.copy;

/**
 * Created by calebdinsmore on 6/15/17.
 */
public class Race {
    private JavaPlugin mMain;
    private String mName;
    private RaceConfig mRaceConfig;

    public Race(String raceName, Player raceCreator, JavaPlugin main) {
        mMain = main;
        mName = raceName;
        createRaceDir(raceName, raceCreator);
        mRaceConfig = new RaceConfig(raceName, main);
    }

    public Race(String raceName, JavaPlugin main) {
        mMain = main;
        mName = raceName;
        mRaceConfig = new RaceConfig(raceName, main);
    }

    public String getName() {
        return mName;
    }

    private void createRaceDir(String raceName, Player raceCreator) {
        File raceDir = new File("plugins/RaceCreator/races/" + raceName);
        if (!raceDir.exists()) {
            raceDir.mkdir();
        } else {
            MessageUtilities.SendErrorMessageToPlayer(raceCreator, "That race already exists!");
        }
    }

    public String getNameFromConfig() {
        return mRaceConfig.mDataYaml.getString("name");
    }
}
