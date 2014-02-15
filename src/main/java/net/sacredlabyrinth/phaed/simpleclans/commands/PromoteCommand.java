package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * @author phaed
 */
public class PromoteCommand
{
    public PromoteCommand()
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

        if (!plugin.getPermissionsManager().has(player, "simpleclans.leader.promote"))
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

        if (arg.length != 1)
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(plugin.getLang("usage.0.promote.member"), plugin.getSettingsManager().getCommandClan()));return;

        }

        Player promoted = Helper.matchOnePlayer(arg[0]);

        if (promoted == null)
        {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("the.member.to.be.promoted.must.be.online"));
            return;
        }

        if (!plugin.getPermissionsManager().has(promoted, "simpleclans.leader.promotable"))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("the.player.does.not.have.the.permissions.to.lead.a.clan"));
            return;
        }

        if (promoted.getName().equals(player.getName()))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.youCannotPromoteYourself);
            return;
        }

        if (!clan.isMember(promoted))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.PlayerNotMemberOfYourClan);
            return;
        }

        if (clan.isLeader(promoted) && plugin.getSettingsManager().isConfirmationForPromote())
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.thePlayerAlreadyLeader);
            return;
        }

        clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.promotedtoLeader, Helper.capitalize(promoted.getName())));
        clan.promote(promoted.getName());
    }
}
