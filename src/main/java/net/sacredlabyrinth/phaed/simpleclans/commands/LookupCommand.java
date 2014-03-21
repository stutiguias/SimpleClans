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
public class LookupCommand
{
    public LookupCommand()
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

        String playerName = null;

        if (arg.length == 0)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.member.lookup"))
            {
                playerName = player.getName();
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            }
        }
        else if (arg.length == 1)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.anyone.lookup"))
            {
                playerName = arg[0];
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            }
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageLookupTag, plugin.getSettingsManager().getCommandClan()));
        }

        if (playerName != null)
        {
            ClanPlayer targetCp = plugin.getClanManager().getAnyClanPlayer(playerName);
            ClanPlayer myCp = plugin.getClanManager().getClanPlayer(player.getName());
            Clan myClan = myCp == null ? null : myCp.getClan();

            if (targetCp != null)
            {
                Clan targetClan = targetCp.getClan();

                ChatBlock.sendBlank(player);
                ChatBlock.saySingle(player, MessageFormat.format(SimpleClans.langManager.menuPlayerInfo, plugin.getSettingsManager().getPageClanNameColor() + targetCp.getName() + subColor) + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
                ChatBlock.sendBlank(player);

                String clanName = ChatColor.WHITE + SimpleClans.langManager.none;

                if (targetClan != null)
                {
                    clanName = plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketLeft() + plugin.getSettingsManager().getTagDefaultColor() + targetClan.getColorTag() + plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketRight() + " " + plugin.getSettingsManager().getPageClanNameColor() + targetClan.getName();
                }

                String status = targetClan == null ? ChatColor.WHITE + SimpleClans.langManager.freeAgent : (targetCp.isLeader() ? plugin.getSettingsManager().getPageLeaderColor() + SimpleClans.langManager.namesLeader : (targetCp.isTrusted() ? plugin.getSettingsManager().getPageTrustedColor() + SimpleClans.langManager.trusted : plugin.getSettingsManager().getPageUnTrustedColor() + SimpleClans.langManager.untrusted));
                String rank = ChatColor.WHITE + "" + Helper.parseColors(targetCp.getRank());
                String joinDate = ChatColor.WHITE + "" + targetCp.getJoinDateString();
                String lastSeen = ChatColor.WHITE + "" + targetCp.getLastSeenString();
                String inactive = ChatColor.WHITE + "" + targetCp.getInactiveDays() + subColor + "/" + ChatColor.WHITE + plugin.getSettingsManager().getPurgePlayers() + " days";
                String rival = ChatColor.WHITE + "" + targetCp.getRivalKills();
                String neutral = ChatColor.WHITE + "" + targetCp.getNeutralKills();
                String civilian = ChatColor.WHITE + "" + targetCp.getCivilianKills();
                String deaths = ChatColor.WHITE + "" + targetCp.getDeaths();
                String kdr = ChatColor.YELLOW + "" + formatter.format(targetCp.getKDR());
                String pastClans = ChatColor.WHITE + "" + targetCp.getPastClansString(headColor + ", ");

                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuClan, clanName));
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuRank, rank));
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuStatus, status));
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuKDR, kdr));
                ChatBlock.sendMessage(player, "  " + subColor + SimpleClans.langManager.menuKillTotals + " " + headColor + "[ " + SimpleClans.langManager.rival + ": " + rival + " " + headColor + "" + SimpleClans.langManager.neutral + ": " + neutral + " " + headColor + "" + SimpleClans.langManager.civilian + ": " + civilian + headColor + " ]");
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuDeaths, deaths));
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuJoinDate, joinDate));
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuLastSeen, lastSeen));
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuPastClans, pastClans));
                ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuInactive, inactive));

                if (arg.length == 1 && targetClan != null)
                {
                    if (!targetCp.equals(myCp))
                    {
                        String killType = ChatColor.GRAY + SimpleClans.langManager.neutral;

                        if (targetClan == null)
                        {
                            killType = ChatColor.DARK_GRAY + SimpleClans.langManager.civilian;
                        }
                        else if (myClan != null && myClan.isRival(targetClan.getTag()))
                        {
                            killType = ChatColor.WHITE + SimpleClans.langManager.rival;
                        }

                        ChatBlock.sendMessage(player, "  " + subColor + MessageFormat.format(SimpleClans.langManager.menuKillType, killType));
                    }
                }

                ChatBlock.sendBlank(player);
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.menuNoPlayerDataFound);

                if (arg.length == 1 && myClan != null)
                {
                    ChatBlock.sendBlank(player);
                    ChatBlock.sendMessage(player, MessageFormat.format(SimpleClans.langManager.menuKillTypeCivilian, ChatColor.DARK_GRAY));
                }
            }
        }
    }
}
