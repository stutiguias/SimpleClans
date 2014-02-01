package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author phaed
 */
public class BbCommand {

    public BbCommand() {
    }

    /**
     * Execute the command
     *
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg) {
        SimpleClans plugin = SimpleClans.getInstance();

        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

        if (cp != null) {
            Clan clan = cp.getClan();

                if (arg.length == 0) {
                    if (plugin.getPermissionsManager().has(player, "simpleclans.member.bb")) {
                        clan.displayBb(player);
                    } else {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
                    }
                } else if (arg.length == 1 && arg[0].equalsIgnoreCase("clear")) {
                    if (plugin.getPermissionsManager().has(player, "simpleclans.leader.bb-clear")) {
                        if (cp.isTrusted() && cp.isLeader()) {
                            cp.getClan().clearBb();
                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.clearedBb);
                        } else {
                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
                        }
                    } else {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
                    }
                } else if (plugin.getPermissionsManager().has(player, "simpleclans.member.bb-add")) {
                    if (cp.isTrusted()) {
                        String msg = Helper.toMessage(arg);
                        clan.addBb(player.getName(), ChatColor.AQUA + player.getName() + ": " + ChatColor.WHITE + msg);
                        plugin.getStorageManager().updateClan(clan);
                    } else {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
                    }
                } else {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
                }
        } else {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.notMemberAnyClan);
        }
    }
}