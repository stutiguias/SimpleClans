package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author phaed
 */
public class RosterCommand
{

    public RosterCommand()
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
        String headColor = plugin.getSettingsManager().getPageHeadingsColor();
        String subColor = plugin.getSettingsManager().getPageSubTitleColor();

        Clan clan = null;

        if (arg.length == 0)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.member.roster"))
            {
                ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

                if (cp == null)
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.notMemberAnyClan);
                } else
                {
                    clan = cp.getClan();
                }
            } else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            }
        } else if (arg.length == 1)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.roster"))
            {
                clan = plugin.getClanManager().getClan(arg[0]);

                if (clan == null)
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.noClanMatched);
                }
            } else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            }
        } else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageRosterTag, plugin.getSettingsManager().getCommandClan()));
        }

        if (clan != null)
        {
                ChatBlock chatBlock = new ChatBlock();

                ChatBlock.sendBlank(player);
                ChatBlock.saySingle(player, plugin.getSettingsManager().getPageClanNameColor() + Helper.capitalize(clan.getName()) + subColor + " " + SimpleClans.langManager.NamesRoster + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
                ChatBlock.sendBlank(player);
                ChatBlock.sendMessage(player, headColor + SimpleClans.langManager.MenuLegend + " " + plugin.getSettingsManager().getPageLeaderColor() + SimpleClans.langManager.leaders + headColor + ", " + plugin.getSettingsManager().getPageTrustedColor() + SimpleClans.langManager.trusted + headColor + ", " + plugin.getSettingsManager().getPageUnTrustedColor() + SimpleClans.langManager.untrusted);
                ChatBlock.sendBlank(player);

                chatBlock.setFlexibility(false, true, false, true);
                chatBlock.addRow("  " + headColor + SimpleClans.langManager.player, SimpleClans.langManager.rank, SimpleClans.langManager.seen);

                List<ClanPlayer> leaders = clan.getLeaders();
                plugin.getClanManager().sortClanPlayersByLastSeen(leaders);

                List<ClanPlayer> members = clan.getNonLeaders();
                plugin.getClanManager().sortClanPlayersByLastSeen(members);

                for (ClanPlayer cp : leaders)
                {

                    Player p = plugin.getServer().getPlayer(cp.getName());

                    String name = plugin.getSettingsManager().getPageLeaderColor() + cp.getName();
                    String lastSeen = (p != null && p.isOnline() && !Helper.isVanished(p) ? ChatColor.GREEN + SimpleClans.langManager.online : ChatColor.WHITE + cp.getLastSeenDaysString());

                    chatBlock.addRow("  " + name, ChatColor.YELLOW + Helper.parseColors(cp.getRank()), lastSeen);

                }

                for (ClanPlayer cp : members)
                {
                    Player p = plugin.getServer().getPlayer(cp.getName());

                    String name = (cp.isTrusted() ? plugin.getSettingsManager().getPageTrustedColor() : plugin.getSettingsManager().getPageUnTrustedColor()) + cp.getName();
                    String lastSeen = (p != null && p.isOnline() && !Helper.isVanished(p) ? ChatColor.GREEN + SimpleClans.langManager.online : ChatColor.WHITE + cp.getLastSeenDaysString());

                    chatBlock.addRow("  " + name, ChatColor.YELLOW + Helper.parseColors(cp.getRank()), lastSeen);
                }

                boolean more = chatBlock.sendBlock(player, plugin.getSettingsManager().getPageSize());

                if (more)
                {
                    plugin.getStorageManager().addChatBlock(player, chatBlock);
                    ChatBlock.sendBlank(player);
                    ChatBlock.sendMessage(player, headColor + MessageFormat.format(SimpleClans.langManager.ViewNextPage, plugin.getSettingsManager().getCommandMore()));
                }

                ChatBlock.sendBlank(player);
        } else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageRosterTag, plugin.getSettingsManager().getCommandClan()));
        }
    }
}
