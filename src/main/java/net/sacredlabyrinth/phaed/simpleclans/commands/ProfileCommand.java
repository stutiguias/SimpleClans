package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;

/**
 * @author phaed
 */
public class ProfileCommand
{
    public ProfileCommand()
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
        NumberFormat formatter = new DecimalFormat("#.#");

        Clan clan = null;

        if (arg.length == 0)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.member.profile"))
            {
                ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

                if (cp == null)
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.notMemberAnyClan);
                }
                else
                {
                    clan = cp.getClan();
                }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            }
        }
        else if (arg.length == 1)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.profile"))
            {
                clan = plugin.getClanManager().getClan(arg[0]);

                if (clan == null)
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.noClanMatched);
                }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            }
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageProfiletag, plugin.getSettingsManager().getCommandClan()));
        }

        if (clan != null)
        {
            ChatBlock.sendBlank(player);
            ChatBlock.saySingle(player, plugin.getSettingsManager().getPageClanNameColor() + Helper.capitalize(clan.getName()) + subColor+ " " + SimpleClans.langManager.profile + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
            ChatBlock.sendBlank(player);

            String name = plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketLeft() + plugin.getSettingsManager().getTagDefaultColor() + clan.getColorTag() + plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketRight() + " " + plugin.getSettingsManager().getPageClanNameColor() + clan.getName();
            String leaders = clan.getLeadersString(plugin.getSettingsManager().getPageLeaderColor(), subColor + ", ");
            String onlineCount = ChatColor.WHITE + "" + Helper.stripOffLinePlayers(clan.getMembers()).size();
            String membersOnline = onlineCount + subColor + "/" + ChatColor.WHITE + clan.getSize();
            String inactive = ChatColor.WHITE + "" + clan.getInactiveDays() + subColor + "/" + ChatColor.WHITE + plugin.getSettingsManager().getPurgeClan() + " " + SimpleClans.langManager.namesDays;
            String founded = ChatColor.WHITE + "" + clan.getFoundedString();
            String allies = ChatColor.WHITE + "" + clan.getAllyString(subColor + ", ");
            String rivals = ChatColor.WHITE + "" + clan.getRivalString(subColor + ", ");
            String kdr = ChatColor.YELLOW + "" + formatter.format(clan.getTotalKDR());
            String deaths = ChatColor.WHITE + "" + clan.getTotalDeaths();
            String rival = ChatColor.WHITE + "" + clan.getTotalRival();
            String neutral = ChatColor.WHITE + "" + clan.getTotalNeutral();
            String civ = ChatColor.WHITE + "" + clan.getTotalCivilian();

            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuName, name));
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuLeaders, leaders));
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuMembersOnline, membersOnline));
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuKDR, kdr));
            ChatBlock.sendMessage(player, "  " + subColor + SimpleClans.langManager.menuKillTotals + " " + headColor + "[" + SimpleClans.langManager.rival + ":" + rival + " " + headColor + "" + SimpleClans.langManager.neutral + ":" + neutral + " " + headColor + "" + SimpleClans.langManager.civilian + ":" + civ + headColor + "]");
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuDeaths, deaths));
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuAllies, allies));
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuRivals, rivals));
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuFounded, founded));
            ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuInactive, inactive));

            ChatBlock.sendBlank(player);
        }
    }
}
