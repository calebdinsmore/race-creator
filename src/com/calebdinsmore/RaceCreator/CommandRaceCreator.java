package com.calebdinsmore.RaceCreator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by calebdinsmore on 6/14/17.
 */
public class CommandRaceCreator implements CommandExecutor {

    private Main mMain;
    private int mArgLength;

    CommandRaceCreator(Main main) {
        mMain = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player player = (Player) commandSender;
        String usage = "Usage: /racecreator <setlobby|race>";
        mArgLength = args.length;

        if (mArgLength < 1) {
            MessageUtilities.SendErrorMessageToPlayer(player, usage);
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "setlobby":
                processSetLobby(args, player);
                break;
            case "race":
                processRaceArgs(args, player);

        }
        return true;
    }

    private boolean processSetLobby(String[] args, Player player) {
        player.sendRawMessage("Set lobby called!");
        return true;
    }

    private void processRaceArgs(String[] args, Player player) {
        if (mArgLength < 2) {
            MessageUtilities.SendErrorMessageToPlayer(player, "Usage: /racecreator race <create 'race name'>");
            return;
        }
        switch(args[1]) {
            case "create":
                if (mArgLength == 3) {
                    createRace(args[2], player);
                    return;
                }
            case "setspawn":
                if (mArgLength == 3) {
                    setSpawn(args[2], player);
                }
        }
        MessageUtilities.SendErrorMessageToPlayer(player, "Usage: /racecreator race <create>");
    }

    private void createRace(String raceName, Player player) {
        Race race = new Race(raceName, player, mMain);
        mMain.addRaceToMap(race);
    }

    private void setSpawn(String race, Player player) {
        if (!mMain.getRaceMap().containsKey(race)) {
            MessageUtilities.SendErrorMessageToPlayer(player, "Error: race doesn't exist.");
            return;
        }

        Race raceToChange = mMain.getRaceMap().get(race);
        raceToChange.addSpawn(player.getLocation(), player);
    }
}
