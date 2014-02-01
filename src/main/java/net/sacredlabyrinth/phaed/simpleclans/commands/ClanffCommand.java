package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 *
 * @author phaed
 */
public class ClanffCommand
{
    public ClanffCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.ff"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                if (clan.isLeader(player))
                {
                    if (arg.length == 1)
                    {
                        String action = arg[0];

                        if (action.equalsIgnoreCase(SimpleClans.langManager.allow))
                        {
                            clan.addBb(player.getName(), ChatColor.AQUA + SimpleClans.langManager.clanWideFriendlyFireAllowed);
                            clan.setFriendlyFire(true);
                            plugin.getStorageManager().updateClan(clan);
                        }
                        else if (action.equalsIgnoreCase(SimpleClans.langManager.block))
                        {
                            clan.addBb(player.getName(), ChatColor.AQUA + SimpleClans.langManager.clanWideFriendlyFireBlocked);
                            clan.setFriendlyFire(false);
                            plugin.getStorageManager().updateClan(clan);
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageClanff, plugin.getSettingsManager().getCommandClan()));
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageClanff, plugin.getSettingsManager().getCommandClan()));
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
