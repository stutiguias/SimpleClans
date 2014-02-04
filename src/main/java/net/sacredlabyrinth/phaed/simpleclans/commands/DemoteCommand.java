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
public class DemoteCommand
{
    SimpleClans plugin;
    
    public DemoteCommand()
    {
    }

    /**
     * Execute the command
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg)
    {
        plugin = SimpleClans.getInstance();

        if (isValid(player))
        {
            ClanPlayer clanPlayer = plugin.getClanManager().getClanPlayer(player);

            if (clanPlayer == null)
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.notMemberAnyClan);
                return;
            }
            
            Clan clan = clanPlayer.getClan();

            if (!clan.isLeader(player))
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
                return;
            }
            
            if (arg.length == 1)
            {
                String demotedName = arg[0];

                if (clan.allOtherLeadersOnline(demotedName))
                {
                    if (clan.isLeader(demotedName))
                    {
                        if (clan.getLeaders().size() == 1|| !plugin.getSettingsManager().isConfirmationForDemote())
                        {
                            clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.demotedBackMember, Helper.capitalize(demotedName)));
                            clan.demote(demotedName);
                        }
                        else
                        {
                            plugin.getRequestManager().addDemoteRequest(clanPlayer, demotedName, clan);
                            ChatBlock.sendMessage(player, ChatColor.AQUA + SimpleClans.langManager.demotionVoteBeenRequestedFromAllLeaders);
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.playerNotLeaderYourClan);
                    }
                }
                else
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.leadersMustOnlineVoteDemotion);
                }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageDemoteLeader, plugin.getSettingsManager().getCommandClan()));
            }
        }
    }
    
    public boolean isValid(Player player) {
        
        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.demote"))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            return false;
        }
        
        return true;
    }
}
