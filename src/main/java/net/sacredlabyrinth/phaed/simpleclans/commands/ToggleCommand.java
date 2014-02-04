package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author phaed
 */
public class ToggleCommand {

    public ToggleCommand() {
    }

    /**
     * Execute the command
     *
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg) {
        SimpleClans plugin = SimpleClans.getInstance();

        if (arg.length == 0) {
            return;
        }

        String cmd = arg[0];
        
        if (cmd.equalsIgnoreCase("bb")) {
            if (plugin.getPermissionsManager().has(player, "simpleclans.member.bb-toggle")) {
                ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

                if (cp != null) {
                    if (cp.isBbEnabled()) {
                        ChatBlock.sendMessage(player, ChatColor.AQUA + SimpleClans.langManager.bboff);
                        cp.setBbEnabled(false);
                    } else {
                        ChatBlock.sendMessage(player, ChatColor.AQUA + SimpleClans.langManager.bbon);
                        cp.setBbEnabled(true);
                    }
                    plugin.getStorageManager().updateClanPlayer(cp);
                }
            }
        }

        if (cmd.equalsIgnoreCase("tag")) {
            if (plugin.getPermissionsManager().has(player, "simpleclans.member.tag-toggle")) {
                ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

                if (cp != null) {
                    if (cp.isTagEnabled()) {
                        ChatBlock.sendMessage(player, ChatColor.AQUA + SimpleClans.langManager.tagoff);
                        cp.setTagEnabled(false);
                    } else {
                        ChatBlock.sendMessage(player, ChatColor.AQUA + SimpleClans.langManager.tagon);
                        cp.setTagEnabled(true);
                    }
                    plugin.getStorageManager().updateClanPlayer(cp);
                }
            }
        }

    }
}
