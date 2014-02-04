package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class KillsCommand
{
    public KillsCommand()
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

        if (plugin.getPermissionsManager().has(player, "simpleclans.member.kills"))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (cp != null)
            {
                Clan clan = cp.getClan();

                    if (cp.isTrusted())
                    {
                        String polledPlayerName = player.getName();

                        if (arg.length == 1)
                        {
                            polledPlayerName = arg[0];
                        }

                        ChatBlock chatBlock = new ChatBlock();

                        chatBlock.setFlexibility(true, false);
                        chatBlock.setAlignment("l", "c");

                        chatBlock.addRow("  " + headColor + plugin.getLang("victim"), plugin.getLang("killcount"));

                        HashMap<String, Integer> killsPerPlayerUnordered = plugin.getStorageManager().getKillsPerPlayer(polledPlayerName);

                        if (killsPerPlayerUnordered.isEmpty())
                        {
                            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("nokillsfound"));
                            return;
                        }

                         Map<String, Integer> killsPerPlayer = Helper.sortByValue(killsPerPlayerUnordered);

                        for (String playerName : killsPerPlayer.keySet())
                        {
                            int count = killsPerPlayer.get(playerName);

                            chatBlock.addRow("  " + playerName, ChatColor.AQUA + "" + count);
                        }

                        ChatBlock.saySingle(player, plugin.getSettingsManager().getPageClanNameColor() + Helper.capitalize(polledPlayerName) + subColor + " " + plugin.getLang("kills") + " " + headColor + Helper.generatePageSeparator(plugin.getSettingsManager().getPageSep()));
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
                        ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("only.trusted.players.can.access.clan.stats"));
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
