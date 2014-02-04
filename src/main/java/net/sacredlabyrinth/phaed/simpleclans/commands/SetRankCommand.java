package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class SetRankCommand
{
    public SetRankCommand()
    {
    }

    /**
     * Execute the command
     *
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg)
    {
        SimpleClans plugin = SimpleClans.getInstance();

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.setrank"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                    if (clan.isLeader(player))
                    {
                        if (arg.length >= 1)
                        {
                            String playerName = arg[0];
                            String rank = Helper.toMessage(Helper.removeFirst(arg));

                            if (clan.isMember(playerName) || clan.isLeader(playerName))
                            {
                                ClanPlayer cpm = plugin.getClanManager().getClanPlayer(playerName);
                                cpm.setRank(rank);
                                plugin.getStorageManager().updateClanPlayer(cpm);

                                ChatBlock.sendMessage(player, ChatColor.AQUA + SimpleClans.langManager.PlayerRankChanged);
                            }
                            else
                            {
                                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoPlayerMatched);
                            }
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageSetRank, plugin.getSettingsManager().getCommandClan()));
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
                    }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.notMemberAnyClan);
            }
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
        }
    }
}
