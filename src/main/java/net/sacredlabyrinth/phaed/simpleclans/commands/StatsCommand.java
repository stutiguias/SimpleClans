package net.sacredlabyrinth.phaed.simpleclans.commands;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.List;
import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author phaed
 */
public class StatsCommand
{
    public StatsCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.member.stats"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                    if (cp.isTrusted())
                    {
                        if (arg.length == 0)
                        {
                            ChatBlock chatBlock = new ChatBlock();


                            ChatBlock.saySingle(player, plugin.getSettingsManager().getPageClanNameColor() + Helper.capitalize(clan.getName()) + subColor+ " " + SimpleClans.langManager.NamesStats + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
                            ChatBlock.sendBlank(player);

                            ChatBlock.sendMessage(player, headColor + SimpleClans.langManager.kdr + " = " + subColor + SimpleClans.langManager.killDeathRatio);
                            ChatBlock.sendMessage(player, headColor + SimpleClans.langManager.weights + " = " + SimpleClans.langManager.NamesRival + ": " + subColor + plugin.getSettingsManager().getKwRival() + headColor + " " + SimpleClans.langManager.neutral + ": " + subColor + plugin.getSettingsManager().getKwNeutral() + headColor + " " + SimpleClans.langManager.civilian + ": " + subColor + plugin.getSettingsManager().getKwCivilian());
                            ChatBlock.sendBlank(player);

                            chatBlock.setFlexibility(true, false, false, false, false, false, false);
                            chatBlock.setAlignment("l", "c", "c", "c", "c", "c", "c");

                            chatBlock.addRow("  " + headColor + SimpleClans.langManager.name,SimpleClans.langManager.kdr, SimpleClans.langManager.NamesRival, SimpleClans.langManager.neutral, SimpleClans.langManager.civilianAbbreviation, SimpleClans.langManager.NamesDeath);

                            List<ClanPlayer> leaders = clan.getLeaders();
                            plugin.getClanManager().sortClanPlayersByKDR(leaders);

                            List<ClanPlayer> members = clan.getNonLeaders();
                            plugin.getClanManager().sortClanPlayersByKDR(members);

                            for (ClanPlayer cpm : leaders)
                            {
                                String name = (cpm.isLeader() ? plugin.getSettingsManager().getPageLeaderColor() : ((cpm.isTrusted() ? plugin.getSettingsManager().getPageTrustedColor() : plugin.getSettingsManager().getPageUnTrustedColor()))) + cpm.getName();
                                String rival = NumberFormat.getInstance().format(cpm.getRivalKills());
                                String neutral = NumberFormat.getInstance().format(cpm.getNeutralKills());
                                String civilian = NumberFormat.getInstance().format(cpm.getCivilianKills());
                                String deaths = NumberFormat.getInstance().format(cpm.getDeaths());
                                String kdr = formatter.format(cpm.getKDR());

                                chatBlock.addRow("  " + name, ChatColor.YELLOW + kdr, ChatColor.WHITE + rival, ChatColor.GRAY + neutral, ChatColor.DARK_GRAY + civilian, ChatColor.DARK_RED + deaths);
                            }

                            for (ClanPlayer cpm : members)
                            {
                                String name = (cpm.isLeader() ? plugin.getSettingsManager().getPageLeaderColor() : ((cpm.isTrusted() ? plugin.getSettingsManager().getPageTrustedColor() : plugin.getSettingsManager().getPageUnTrustedColor()))) + cpm.getName();
                                String rival = NumberFormat.getInstance().format(cpm.getRivalKills());
                                String neutral = NumberFormat.getInstance().format(cpm.getNeutralKills());
                                String civilian = NumberFormat.getInstance().format(cpm.getCivilianKills());
                                String deaths = NumberFormat.getInstance().format(cpm.getDeaths());
                                String kdr = formatter.format(cpm.getKDR());

                                chatBlock.addRow("  " + name, ChatColor.YELLOW + kdr, ChatColor.WHITE + rival, ChatColor.GRAY + neutral, ChatColor.DARK_GRAY + civilian, ChatColor.DARK_RED + deaths);
                            }

                            boolean more = chatBlock.sendBlock(player, plugin.getSettingsManager().getPageSize());

                            if (more)
                            {
                                plugin.getStorageManager().addChatBlock(player, chatBlock);
                                ChatBlock.sendBlank(player);
                                ChatBlock.sendMessage(player, headColor + MessageFormat.format(SimpleClans.langManager.ViewNextPage, plugin.getSettingsManager().getCommandMore()));
                            }

                            ChatBlock.sendBlank(player);
                        }
                        else
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageStats, plugin.getSettingsManager().getCommandClan()));
                        }
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.OnlyTrustedPlayersCanAccessClanStats);
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
