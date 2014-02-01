package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author phaed
 */
public class AllyCommand
{
    public AllyCommand()
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

        if (!plugin.getPermissionsManager().has(player, "simpleclans.leader.ally"))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            return;
        }

        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

        if (cp == null)
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.notMemberAnyClan);
            return;
        }

        Clan clan = cp.getClan();

        if (!clan.isLeader(player))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
            return;
        }

        if (arg.length != 2)
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageAlly, plugin.getSettingsManager().getCommandClan()));
            return;
        }

        if (clan.getSize() < plugin.getSettingsManager().getClanMinSizeToAlly())
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.minimumMakeAlliance, plugin.getSettingsManager().getClanMinSizeToAlly()));
            return;
        }

        String action = arg[0];
        Clan ally = plugin.getClanManager().getClan(arg[1]);

        if (ally == null)
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.noClanMatched);
            return;
        }

        if (action.equals(SimpleClans.langManager.add))
        {
            if (!clan.isAlly(ally.getTag()))
            {
                List<ClanPlayer> onlineLeaders = Helper.stripOffLinePlayers(clan.getLeaders());

                if (!onlineLeaders.isEmpty())
                {
                    plugin.getRequestManager().addAllyRequest(cp, ally, clan);
                    ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.leadersHaveBeenAskedAlliance, Helper.capitalize(ally.getName())));
                }
                else
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.atLeastOneLeaderAcceptAlliance);
                }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.yourClansAreAlreadyAllies);
            }
        }
        else if (action.equals(SimpleClans.langManager.remove))
        {
            if (clan.isAlly(ally.getTag()))
            {
                clan.removeAlly(ally);
                ally.addBb(cp.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.hasBrokenAlliance, Helper.capitalize(clan.getName()), ally.getName()));
                clan.addBb(cp.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.hasBrokenAlliance, Helper.capitalize(cp.getName()), Helper.capitalize(ally.getName())));
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.yourClansNotAllies);
            }
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageAlly, plugin.getSettingsManager().getCommandClan()));
        }
    }
}