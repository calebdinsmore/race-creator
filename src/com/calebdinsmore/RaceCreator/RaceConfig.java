package com.calebdinsmore.RaceCreator;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by calebdinsmore on 6/16/17.
 */
public class RaceConfig {

    YamlConfiguration mDataYaml;

    private JavaPlugin mMain;
    private File mDataFile;
    private String mName;
    private String mRaceDataPath;
    private Race mRace;

    RaceConfig(Race race, JavaPlugin main) {
        mMain = main;
        mName = race.getName();
        mRace = race;
        mRaceDataPath = "races/" + mName + "/data.yml";
        mDataFile = new File(mMain.getDataFolder(), mRaceDataPath);

        if (!mDataFile.exists()) {
            createNewConfig();
        } else {
            mDataYaml = YamlConfiguration.loadConfiguration(mDataFile);
        }
    }

    boolean addSpawn(Location spawn) {
        ArrayList<Location> spawns = getSpawns();

        spawns.add(spawn);
        setSpawns(spawns);
        return true;
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

    private ArrayList<Location> getSpawns() {
        if (!mDataYaml.contains("spawns")) {
            return new ArrayList<>();
        }

        ConfigurationSection spawnSection = mDataYaml.getConfigurationSection("spawns");
        ArrayList<Location> spawns = new ArrayList<>();

        for (String key : spawnSection.getKeys(false)) {
            spawns.add(new Location(mMain.getServer().getWorld("world"), spawnSection.getDouble(key + ".x"),
                    spawnSection.getDouble(key + ".y"), spawnSection.getDouble(key + ".z"),
                    (float) spawnSection.getDouble(key + ".yaw"), (float) spawnSection.getDouble(key + ".pitch")));
        }

        return spawns;
    }

    private void setSpawns(ArrayList<Location> spawns) {
        ConfigurationSection spawnSection;
        if (!mDataYaml.contains("spawns")) {
            spawnSection = mDataYaml.createSection("spawns");
        } else {
            spawnSection = mDataYaml.getConfigurationSection("spawns");
        }

        for (int i = 0; i < spawns.size(); i++) {
            spawnSection.set(i + ".x", spawns.get(i).getX());
            spawnSection.set(i + ".y", spawns.get(i).getY());
            spawnSection.set(i + ".z", spawns.get(i).getZ());
            spawnSection.set(i + ".yaw", spawns.get(i).getYaw());
            spawnSection.set(i + ".pitch", spawns.get(i).getPitch());
        }

        try {
            mDataYaml.save(mDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
