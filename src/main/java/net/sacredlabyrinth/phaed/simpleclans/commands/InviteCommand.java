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
public class InviteCommand
{
    public InviteCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.leader.invite"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                if (clan.isLeader(player))
                {
                    if (arg.length == 1)
                    {
                        Player invited = Helper.matchOnePlayer(arg[0]);

                        if (invited != null)
                        {
                            if (plugin.getPermissionsManager().has(invited, "simpleclans.member.can-join"))
                            {
                                if (!invited.getName().equals(player.getName()))
                                {
                                    ClanPlayer cpInv = plugin.getClanManager().getClanPlayer(invited);

                                    if (cpInv == null)
                                    {
                                        if (plugin.getClanManager().purchaseInvite(player))
                                        {
                                            plugin.getRequestManager().addInviteRequest(cp, invited.getName(), clan);
                                            ChatBlock.sendMessage(player, ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.hasBeenAskedToJoin, Helper.capitalize(invited.getName()), clan.getName()));
                                        }
                                    }
                                    else
                                    {
                                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.PlayerAlreadyMemberAnotherClan);
                                    }
                                }
                                else
                                {
                                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.youCannotInviteYourself);
                                }
                            }
                            else
                            {
                                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.thePlayerDoesnNotHavePermissionsJoin);
                            }
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoPlayerMatched);
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageInvitePlayer, plugin.getSettingsManager().getCommandClan()));
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
