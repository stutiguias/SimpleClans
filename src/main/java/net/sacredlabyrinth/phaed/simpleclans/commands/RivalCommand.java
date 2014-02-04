package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * @author phaed
 */
public class RivalCommand
{
    public RivalCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.rival"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                    if (!clan.isUnrivable())
                    {
                        if (clan.isLeader(player))
                        {
                            if (arg.length == 2)
                            {
                                if (clan.getSize() >= plugin.getSettingsManager().getClanMinSizeToRival())
                                {
                                    String action = arg[0];
                                    Clan rival = plugin.getClanManager().getClan(arg[1]);

                                    if (rival != null)
                                    {
                                        if (!plugin.getSettingsManager().isUnrivable(rival.getTag()))
                                        {
                                                if (action.equals(SimpleClans.langManager.add))
                                                {
                                                    if (!clan.reachedRivalLimit())
                                                    {
                                                        if (!clan.isRival(rival.getTag()))
                                                        {
                                                            clan.addRival(rival);
                                                            rival.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.hasInitiatedRivalry, Helper.capitalize(clan.getName()), rival.getName()));
                                                            clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.hasInitiatedRivalry, Helper.capitalize(player.getName()), Helper.capitalize(rival.getName())));
                                                        }
                                                        else
                                                        {
                                                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.yourClansAreAlreadyRivals);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.rivalLimitReached);
                                                    }

                                                }
                                                else if (action.equals(SimpleClans.langManager.remove))
                                                {
                                                    if (clan.isRival(rival.getTag()))
                                                    {
                                                        plugin.getRequestManager().addRivalryBreakRequest(cp, rival, clan);
                                                        ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.leadersAskedEndRivalry, Helper.capitalize(rival.getName())));
                                                    }
                                                    else
                                                    {
                                                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.yourClansAreNotRivals);
                                                    }
                                                }
                                                else
                                                {
                                                    ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageAlly, plugin.getSettingsManager().getCommandClan()));
                                                }
                                        }
                                        else
                                        {
                                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.ClanCannotBeRivaled);
                                        }
                                    }
                                    else
                                    {
                                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.noClanMatched);
                                    }
                                }
                                else
                                {
                                    ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.minPlayersRivalries, plugin.getSettingsManager().getClanMinSizeToRival()));
                                }
                            }
                            else
                            {
                                ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageRival, plugin.getSettingsManager().getCommandClan()));
                            }
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.yourClanCannotCreateRivals);
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
