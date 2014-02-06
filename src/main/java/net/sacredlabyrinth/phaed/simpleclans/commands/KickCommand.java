package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 *
 * @author phaed
 */
public class KickCommand
{
    public KickCommand()
    {
    }

    /**
     * Execute the command
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg)
    {
        SimpleClans plugin = SimpleClans.getInstance();

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.kick"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                if (clan.isLeader(player))
                {
                    if (arg.length == 1)
                    {
                        String kicked = arg[0];

                        if (kicked != null)
                        {
                            if (!kicked.equals(player.getName()))
                            {
                                if (clan.isMember(kicked))
                                {
                                    if (!clan.isLeader(kicked))
                                    {
                                        clan.addBb(player.getName(),  ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.hasBeenKickedBy, Helper.capitalize(kicked), player.getName()));
                                        clan.removePlayerFromClan(kicked);
                                    }
                                    else
                                    {
                                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.youCannotKickAnotherLeader);
                                    }
                                }
                                else
                                {
                                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.PlayerNotMemberOfYourClan);
                                }
                            }
                            else
                            {
                                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.youCannotKickYourself);
                            }
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoPlayerMatched);
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageKickPlayer, plugin.getSettingsManager().getCommandClan()));
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
