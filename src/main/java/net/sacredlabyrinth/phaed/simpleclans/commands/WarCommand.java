package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author phaed
 */
public class WarCommand
{
    public WarCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.war"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                    if (clan.isLeader(player))
                    {
                        if (arg.length == 2)
                        {
                            String action = arg[0];
                            Clan war = plugin.getClanManager().getClan(arg[1]);

                            if (war != null)
                            {
                                if (clan.isRival(war.getTag()))
                                {
                                    if (action.equals(SimpleClans.langManager.start))
                                    {
                                        if (!clan.isWarring(war.getTag()))
                                        {
                                            List<ClanPlayer> onlineLeaders = Helper.stripOffLinePlayers(clan.getLeaders());

                                            if (!onlineLeaders.isEmpty())
                                            {
                                                plugin.getRequestManager().addWarStartRequest(cp, war, clan);
                                                ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.leadersHaveBeenAskedAcceptWarRequest, Helper.capitalize(war.getName())));
                                            }
                                            else
                                            {
                                                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.AtLeastOneLeaderAcceptAlliance);
                                            }
                                        }
                                        else
                                        {
                                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.clansAlreadyAtWar);
                                        }
                                    }
                                    else if (action.equals(SimpleClans.langManager.end))
                                    {
                                        if (clan.isWarring(war.getTag()))
                                        {
                                            plugin.getRequestManager().addWarEndRequest(cp, war, clan);
                                            ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.leadersAskedEndRivalry, Helper.capitalize(war.getName())));
                                        }
                                        else
                                        {
                                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.clansNotAtWar);
                                        }
                                    }
                                    else
                                    {
                                        ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.helpWar, plugin.getSettingsManager().getCommandClan()));
                                    }
                                }
                                else
                                {
                                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.youCanOnlyStartWarWithRivals);
                                }
                            }
                            else
                            {
                                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.noClanMatched);
                            }
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.helpWar, plugin.getSettingsManager().getCommandClan()));
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
