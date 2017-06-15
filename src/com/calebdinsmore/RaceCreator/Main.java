package com.calebdinsmore.RaceCreator;

import com.calebdinsmore.RaceCreator.CommandRaceCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by calebdinsmore on 6/14/17.
 */
public class Main extends JavaPlugin {

    FileConfiguration mConfig = getConfig();

    @Override
    public void onEnable() {
        this.getCommand("racecreator").setExecutor(new CommandRaceCreator());
    }
}
