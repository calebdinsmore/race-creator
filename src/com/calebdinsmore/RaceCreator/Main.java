package com.calebdinsmore.RaceCreator;

import com.calebdinsmore.RaceCreator.CommandRaceCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

/**
 * Created by calebdinsmore on 6/14/17.
 */
public class Main extends JavaPlugin {

    private FileConfiguration mConfig = getConfig();
    private File mRacesDir;
    private HashMap<String, Race> mRaceMap;

    @Override
    public void onEnable() {
        this.getCommand("racecreator").setExecutor(new CommandRaceCreator(this));
        mRaceMap = new HashMap<>();

        createRacesDir();
        setUpRaceMap();
    }

    public void addRaceToMap(Race race) {
        mRaceMap.put(race.getName(), race);
    }

    private void createRacesDir() {
        mRacesDir = new File("plugins/RaceCreator/races");
        if (!mRacesDir.exists()) {
            mRacesDir.mkdir();
        }
    }

    private void setUpRaceMap() {
        File[] listOfRaces = mRacesDir.listFiles();

        if (listOfRaces != null) {
            for (File raceDir : listOfRaces) {
                mRaceMap.put(raceDir.getName(), new Race(raceDir.getName(), this));
            }
        }
        getLogger().info(mRaceMap.get("Test").getNameFromConfig());
    }
}
