package com.calebdinsmore.RaceCreator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by calebdinsmore on 6/14/17.
 */
public class CommandRaceCreator implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length < 1) return false;

        Player player = (Player) commandSender;

        switch (args[0].toLowerCase()) {
            case "setlobby":
                processSetLobby(args, player);
                break;
            case "race":
                processRaceArgs(args, player);

        }
        return false;
    }

    private void processSetLobby(String[] args, Player player) {
        player.sendRawMessage("Set lobby called!");
    }

    private void processRaceArgs(String[] args, Player player) {
        switch(args[1]) {
            case "create":
                if (args.length == 3) {
                    createRace(args[2]);
                }
        }
    }

    private void createRace(String raceName) {
        File testFile = new File("plugins/RaceCreator/test");
        if (!testFile.exists()) {
            testFile.mkdir();
        }
    }
}
