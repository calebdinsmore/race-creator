package com.calebdinsmore.RaceCreator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by calebdinsmore on 6/15/17.
 */
public class MessageUtilities {

    private static String ErrorMessage(String errorMsg) {
        return ChatColor.DARK_RED + errorMsg;
    }

    private static String SuccessMessage(String successMsg) {
        return ChatColor.GREEN + successMsg;
    }

    public static void SendErrorMessageToPlayer(Player player, String msg) {
        player.sendMessage(ErrorMessage(msg));
    }

    public static void SendSuccessMessageToPlayer(Player player, String msg) {
        player.sendMessage(SuccessMessage(msg));
    }
}
