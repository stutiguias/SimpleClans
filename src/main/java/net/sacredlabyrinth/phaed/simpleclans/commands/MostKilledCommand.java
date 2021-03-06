package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class MostKilledCommand
{
    public MostKilledCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.mod.mostkilled"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                    if (cp.isTrusted())
                    {
                        ChatBlock chatBlock = new ChatBlock();

                        chatBlock.setFlexibility(true, false, false);
                        chatBlock.setAlignment("l", "c", "l");

                        chatBlock.addRow("  " + headColor + SimpleClans.langManager.namesVictim, headColor + SimpleClans.langManager.namesKillcount, headColor + SimpleClans.langManager.namesAttacker);

                        HashMap<String, Integer> killsPerPlayerUnordered = plugin.getStorageManager().getMostKilled();

                        if (killsPerPlayerUnordered.isEmpty())
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.nokillsfound);
                            return;
                        }

                        Map<String, Integer> killsPerPlayer = Helper.sortByValue(killsPerPlayerUnordered);

                        for (String attackerVictim : killsPerPlayer.keySet())
                        {
                            String[] split = attackerVictim.split(" ");

                            if (split.length < 2)
                            {
                                continue;
                            }

                            int count = killsPerPlayer.get(attackerVictim);
                            String attacker = split[0];
                            String victim = split[1];

                            chatBlock.addRow("  " + ChatColor.WHITE + victim, ChatColor.AQUA + "" + count, ChatColor.YELLOW + attacker);
                        }

                        ChatBlock.saySingle(player, plugin.getSettingsManager().getServerName() + subColor + " " + SimpleClans.langManager.namesMostKilled + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
                        ChatBlock.sendBlank(player);

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
