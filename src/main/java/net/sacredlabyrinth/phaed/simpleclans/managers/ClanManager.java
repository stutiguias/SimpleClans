package net.sacredlabyrinth.phaed.simpleclans.managers;

import net.sacredlabyrinth.phaed.simpleclans.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author phaed
 */
public final class ClanManager
{

    private final SimpleClans plugin;
    private final HashMap<String, Clan> clans = new HashMap<>();
    private final HashMap<String, ClanPlayer> clanPlayers = new HashMap<>();

    /**
     *
     */
    public ClanManager()
    {
        plugin = SimpleClans.getInstance();
    }

    /**
     * Deletes all clans and clan players in memory
     */
    public void cleanData()
    {
        clans.clear();
        clanPlayers.clear();
    }

    /**
     * Import a clan into the in-memory store
     *
     * @param clan
     */
    public void importClan(Clan clan)
    {
        this.clans.put(clan.getTag(), clan);
    }

    /**
     * Import a clan player into the in-memory store
     *
     * @param cp
     */
    public void importClanPlayer(ClanPlayer cp)
    {
        this.clanPlayers.put(cp.getCleanName(), cp);
    }

    /**
     * Create a new clan
     *
     * @param player
     * @param colorTag
     * @param name
     */
    public void createClan(Player player, String colorTag, String name)
    {
        ClanPlayer cp = getCreateClanPlayer(player.getName());

        Clan clan = new Clan(colorTag, name);
        clan.addPlayerToClan(cp);
        cp.setLeader(true);

        plugin.getStorageManager().insertClan(clan);
        importClan(clan);
        plugin.getStorageManager().updateClanPlayer(cp);

        SimpleClans.getInstance().getPermissionsManager().updateClanPermissions(clan);

    }

    /**
     * Delete a players data file
     *
     * @param cp
     */
    public void deleteClanPlayer(ClanPlayer cp)
    {
        clanPlayers.remove(cp.getCleanName());
        plugin.getStorageManager().deleteClanPlayer(cp);
    }

    /**
     * Remove a clan from memory
     *
     * @param tag
     */
    public void removeClan(String tag)
    {
        clans.remove(tag);
    }

    /**
     * Whether the tag belongs to a clan
     *
     * @param tag
     * @return
     */
    public boolean isClan(String tag)
    {
        return clans.containsKey(Helper.cleanTag(tag));

    }

    /**
     * Returns the clan the tag belongs to
     *
     * @param tag
     * @return
     */
    public Clan getClan(String tag)
    {
        return clans.get(Helper.cleanTag(tag));
    }

    /**
     * Get a player's clan
     *
     * @param playerName
     * @return null if not in a clan
     */
    public Clan getClanByPlayerName(String playerName)
    {
        ClanPlayer cp = getClanPlayer(playerName);

        if (cp != null)
        {
            return cp.getClan();
        }

        return null;
    }

    /**
     * @return the clans
     */
    public List<Clan> getClans()
    {
        return new ArrayList<>(clans.values());
    }

    /**
     * Returns the collection of all clan players, including the disabled ones
     *
     * @return
     */
    public List<ClanPlayer> getAllClanPlayers()
    {
        return new ArrayList<>(clanPlayers.values());
    }

    /**
     * Gets the clanPlayer data object if a player is currently in a clan, null
 if he's not in a clan
     *
     * @param player
     * @return
     */
    public ClanPlayer getClanPlayer(Player player)
    {
        return getClanPlayer(player.getName());
    }

    /**
     * Gets the ClanPlclanPlayerobject if a player is currently in a clan, null
     
 s not in a clan
     *
     * @param playerName
     * @return
     */
    public ClanPlayer getClanPlayer(String playerName)
    {
        ClanPlayer cp = clanPlayers.get(playerName.toLowerCase());

        if (cp == null)
        {
            return null;
        }

        if (cp.getClan() == null)
        {
            return null;
        }

        return cp;
    }

    /**
     * Gets the ClanPlayer dclanPlayer for the player, will retrieve disabled
     * clan
  as well, these are players who used to be in a clan but are
     * not curren
 ne, their data file persists and can be accessed. their
     * clan will be nul
 .
     *
     * @param playerName
     * @return
     */
    public ClanPlayer getAnyClanPlayer(String playerName)
    {
        return clanPlayers.get(playerName.toLowerCase());
    }

    /**
     * Gets the ClanPlayer object for the playclanPlayers one if not found
     *
     * @param playerName
     * @return
     */
    public ClanPlayer getCreateClanPlayer(String playerName)
    {
        if (clanPlayers.containsKey(playerName.toLowerCase()))
        {
            return clanPlayers.get(playerName.toLowerCase());
        }

        ClanPlayer cp = new ClanPlayer(playerName);

        plugin.getStorageManager().insertClanPlayer(cp);
        importClanPlayer(cp);

        return cp;
    }

    /**
     * Announce message to the server
     *
     * @param msg
     */
    public void serverAnnounce(String msg)
    {
        Player[] players = plugin.getServer().getOnlinePlayers();

        for (Player player : players)
        {
            ChatBlock.sendMessage(player, ChatColor.DARK_GRAY + "* " + ChatColor.AQUA + msg);
        }

        SimpleClans.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[" + SimpleClans.langManager.serverAnnounce + "] " + ChatColor.WHITE + msg);
    }

    /**
     * Update the players display name with his clan's tag
     *
     * @param player
     */
    public void updateDisplayName(Player player)
    {
        // do not update displayname if in compat mode

        if (plugin.getSettingsManager().isCompatMode())
        {
            return;
        }

        if (player == null)
        {
            return;
        }

        if (plugin.getSettingsManager().isChatTags())
        {
            String prefix = plugin.getPermissionsManager().getPrefix(player);
            String suffix = plugin.getPermissionsManager().getSuffix(player);
            String lastColor = plugin.getSettingsManager().isUseColorCodeFromPrefix() ? Helper.getLastColorCode(prefix) : ChatColor.WHITE + "";
            String fullName = player.getName();

            ClanPlayer cp = plugin.getClanManager().getAnyClanPlayer(player.getName());

            if (cp == null)
            {
                return;
            }

            if (cp.isTagEnabled())
            {
                Clan clan = cp.getClan();

                if (clan != null)
                {
                    fullName = clan.getTagLabel() + lastColor + fullName + ChatColor.WHITE;
                }

                player.setDisplayName(fullName);
            }
            else
            {
                player.setDisplayName(lastColor + fullName + ChatColor.WHITE);
            }
            
        }
    }

    /**
     * Process a player and his clan's last seen date
     *
     * @param player
     */
    public void updateLastSeen(Player player)
    {
        ClanPlayer cp = getAnyClanPlayer(player.getName());

        if (cp != null)
        {
            cp.updateLastSeen();
            plugin.getStorageManager().updateClanPlayer(cp);

            Clan clan = cp.getClan();

            if (clan != null)
            {
                clan.updateLastUsed();
                plugin.getStorageManager().updateClan(clan);
            }
        }
    }

    /**
     * Get a count of rivable clans
     *
     * @return
     */
    public int getRivableClanCount()
    {
        int clanCount = 0;

        for (Clan tm : clans.values())
        {
            if (!SimpleClans.getInstance().getSettingsManager().isUnrivable(tm.getTag()))
            {
                clanCount++;
            }
        }

        return clanCount;
    }

    /**
     * Sort clans by KDR
     *
     * @param clans
     */
    public void sortClansByKDR(List<Clan> clans)
    {
        Collections.sort(clans, new Comparator<Clan>()
        {

            @Override
            public int compare(Clan c1, Clan c2)
            {
                Float o1 = c1.getTotalKDR();
                Float o2 = c2.getTotalKDR();

                return o2.compareTo(o1);
            }
        });
    }
    /**
     * Sort clans by KDR
     *
     * @param clans
     * @return
     */
    public void sortClansBySize(List<Clan> clans)
    {
        Collections.sort(clans, new Comparator<Clan>()
        {

            @Override
            public int compare(Clan c1, Clan c2)
            {
                Integer o1 = c1.getAllMembers().size();
                Integer o2 = c2.getAllMembers().size();

                return o2.compareTo(o1);
            }
        });
    }

    /**
     * Sort clan players by KDR
     *
     * @param cps
     */
    public void sortClanPlayersByKDR(List<ClanPlayer> cps)
    {
        Collections.sort(cps, new Comparator<ClanPlayer>()
        {

            @Override
            public int compare(ClanPlayer c1, ClanPlayer c2)
            {
                Float o1 = c1.getKDR();
                Float o2 = c2.getKDR();

                return o2.compareTo(o1);
            }
        });
    }

    /**
     * Sort clan players by last seen days
     *
     * @param cps
     */
    public void sortClanPlayersByLastSeen(List<ClanPlayer> cps)
    {
        Collections.sort(cps, new Comparator<ClanPlayer>()
        {

            @Override
            public int compare(ClanPlayer c1, ClanPlayer c2)
            {
                Double o1 = c1.getLastSeenDays();
                Double o2 = c2.getLastSeenDays();

                return o1.compareTo(o2);
            }
        });
    }

    /**
     * Purchase clan creation
     *
     * @param player
     * @return
     */
    public boolean purchaseCreation(Player player)
    {
        if (!plugin.getSettingsManager().isePurchaseCreation())
        {
            return true;
        }

        double price = plugin.getSettingsManager().getCreationPrice();

        if (plugin.getPermissionsManager().hasEconomy())
        {
            if (plugin.getPermissionsManager().playerHasMoney(player, price))
            {
                plugin.getPermissionsManager().playerChargeMoney(player, price);
                player.sendMessage(ChatColor.RED + MessageFormat.format(SimpleClans.langManager.accountDebited, price));
            }
            else
            {
                player.sendMessage(ChatColor.RED + SimpleClans.langManager.notSufficientMoney);
                return false;
            }
        }

        return true;
    }

    /**
     * Purchase invite
     *
     * @param player
     * @return
     */
    public boolean purchaseInvite(Player player)
    {
        if (!plugin.getSettingsManager().isePurchaseInvite())
        {
            return true;
        }

        double price = plugin.getSettingsManager().getInvitePrice();

        if (plugin.getPermissionsManager().hasEconomy())
        {
            if (plugin.getPermissionsManager().playerHasMoney(player, price))
            {
                plugin.getPermissionsManager().playerChargeMoney(player, price);
                player.sendMessage(ChatColor.RED + MessageFormat.format(SimpleClans.langManager.accountDebited, price));
            }
            else
            {
                player.sendMessage(ChatColor.RED + SimpleClans.langManager.notSufficientMoney);
                return false;
            }
        }

        return true;
    }

    /**
     * Purchase Home Teleport
     *
     * @param player
     * @return
     */
    public boolean purchaseHomeTeleport(Player player)
    {
        if (!plugin.getSettingsManager().isePurchaseHomeTeleport())
        {
            return true;
        }

        double price = plugin.getSettingsManager().getHomeTeleportPrice();

        if (plugin.getPermissionsManager().hasEconomy())
        {
            if (plugin.getPermissionsManager().playerHasMoney(player, price))
            {
                plugin.getPermissionsManager().playerChargeMoney(player, price);
                player.sendMessage(ChatColor.RED + MessageFormat.format(SimpleClans.langManager.accountDebited, price));
            }
            else
            {
                player.sendMessage(ChatColor.RED + SimpleClans.langManager.notSufficientMoney);
                return false;
            }
        }

        return true;
    }

    /**
     * Purchase Home Teleport Set
     *
     * @param player
     * @return
     */
    public boolean purchaseHomeTeleportSet(Player player)
    {
        if (!plugin.getSettingsManager().isePurchaseHomeTeleportSet())
        {
            return true;
        }

        double price = plugin.getSettingsManager().getHomeTeleportPriceSet();

        if (plugin.getPermissionsManager().hasEconomy())
        {
            if (plugin.getPermissionsManager().playerHasMoney(player, price))
            {
                plugin.getPermissionsManager().playerChargeMoney(player, price);
                player.sendMessage(ChatColor.RED + MessageFormat.format(SimpleClans.langManager.accountDebited, price));
            }
            else
            {
                player.sendMessage(ChatColor.RED + SimpleClans.langManager.notSufficientMoney);
                return false;
            }
        }

        return true;
    }

    /**
     * Processes a clan chat command
     *
     * @param player
     * @param tag
     * @param msg
     */
    public void processClanChat(Player player, String tag, String msg)
    {
        Clan clan = plugin.getClanManager().getClan(tag);

        if (clan == null || !clan.isMember(player))
        {
            return;
        }

        processClanChat(player, msg);
    }

    /**
     * Processes a clan chat command
     *
     * @param player
     * @param msg
     */
    public void processClanChat(Player player, String msg)
    {
        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player.getName());

        if (cp == null)
        {
            return;
        }

        String[] split = msg.split(" ");

        if (split.length == 0)
        {
            return;
        }

        String command = split[0];

        if (command.equals(SimpleClans.langManager.on))
        {
            cp.setClanChat(true);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have enabled clan chat");
        }
        else if (command.equals(SimpleClans.langManager.off))
        {
            cp.setClanChat(false);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have disabled clan chat");
        }
        else if (command.equals(SimpleClans.langManager.join))
        {
            cp.setChannel(ClanPlayer.Channel.CLAN);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have joined clan chat");
        }
        else if (command.equals(SimpleClans.langManager.leave))
        {
            cp.setChannel(ClanPlayer.Channel.NONE);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have left clan chat");
        }
        else
        {
            String code = "" + ChatColor.RED + ChatColor.WHITE + ChatColor.RED + ChatColor.BLACK;
            String tag;

            if (cp.getRank() != null && !cp.getRank().isEmpty())
            {
                tag = plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketLeft() + plugin.getSettingsManager().getClanChatRankColor() + cp.getRank() + plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketRight() + " ";
            }
            else
            {
                tag = plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketLeft() + plugin.getSettingsManager().getTagDefaultColor() + cp.getClan().getColorTag() + plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketRight() + " ";
            }

            String message = code + Helper.parseColors(tag) + plugin.getSettingsManager().getClanChatNameColor() + plugin.getSettingsManager().getClanChatPlayerBracketLeft() + player.getName() + plugin.getSettingsManager().getClanChatPlayerBracketRight() + " " + plugin.getSettingsManager().getClanChatMessageColor() + msg;
            String eyeMessage = code + plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketLeft() + plugin.getSettingsManager().getTagDefaultColor() + cp.getClan().getColorTag() + plugin.getSettingsManager().getClanChatBracketColor() + plugin.getSettingsManager().getClanChatTagBracketRight() + " " + plugin.getSettingsManager().getClanChatNameColor() + plugin.getSettingsManager().getClanChatPlayerBracketLeft() + player.getName() + plugin.getSettingsManager().getClanChatPlayerBracketRight() + " " + plugin.getSettingsManager().getClanChatMessageColor() + msg;

            plugin.getServer().getConsoleSender().sendMessage(eyeMessage);

            List<ClanPlayer> cps = cp.getClan().getMembers();

            for (ClanPlayer cpp : cps)
            {
                Player member = plugin.getServer().getPlayer(cpp.getName());

                ChatBlock.sendMessage(member, message);
            }

            sendToAllSeeing(eyeMessage, cps);
        }
    }

    public void sendToAllSeeing(String msg, List<ClanPlayer> cps)
    {
        Player[] players = plugin.getServer().getOnlinePlayers();

        for (Player player : players)
        {
            if (plugin.getPermissionsManager().has(player, "simpleclans.admin.all-seeing-eye"))
            {
                boolean alreadySent = false;

                for (ClanPlayer cpp : cps)
                {
                    if (cpp.getName().equalsIgnoreCase(player.getName()))
                    {
                        alreadySent = true;
                    }
                }

                if (!alreadySent)
                {
                    ChatBlock.sendMessage(player, ChatColor.DARK_GRAY + Helper.stripColors(msg));
                }
            }
        }
    }

    /**
     * Processes a ally chat command
     *
     * @param player
     * @param msg
     */
    public void processAllyChat(Player player, String msg)
    {
        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

        if (cp == null)
        {
            return;
        }

        String[] split = msg.split(" ");

        if (split.length == 0)
        {
            return;
        }

        String command = split[0];

        if (command.equals(SimpleClans.langManager.on))
        {
            cp.setAllyChat(true);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have enabled ally chat");
        }
        else if (command.equals(SimpleClans.langManager.off))
        {
            cp.setAllyChat(false);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have disabled ally chat");
        }
        else if (command.equals(SimpleClans.langManager.join))
        {
            cp.setChannel(ClanPlayer.Channel.ALLY);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have joined ally chat");
        }
        else if (command.equals(SimpleClans.langManager.leave))
        {
            cp.setChannel(ClanPlayer.Channel.NONE);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have left ally chat");
        }
        else
        {
            String code = "" + ChatColor.AQUA + ChatColor.WHITE + ChatColor.AQUA + ChatColor.BLACK;
            String message = code + plugin.getSettingsManager().getAllyChatBracketColor() + plugin.getSettingsManager().getAllyChatTagBracketLeft() + plugin.getSettingsManager().getAllyChatTagColor() + plugin.getSettingsManager().getCommandAlly() + plugin.getSettingsManager().getAllyChatBracketColor() + plugin.getSettingsManager().getAllyChatTagBracketRight() + " " + plugin.getSettingsManager().getAllyChatNameColor() + plugin.getSettingsManager().getAllyChatPlayerBracketLeft() + player.getName() + plugin.getSettingsManager().getAllyChatPlayerBracketRight() + " " + plugin.getSettingsManager().getAllyChatMessageColor() + msg;
            SimpleClans.log(message);

            Player self = plugin.getServer().getPlayer(player.getName());
            ChatBlock.sendMessage(self, message);

            Set<ClanPlayer> allies = cp.getClan().getAllAllyMembers();
            allies.addAll(cp.getClan().getMembers());

            for (ClanPlayer ally : allies)
            {
                if (player.getName().equalsIgnoreCase(ally.getName()))
                {
                    continue;
                }

                Player member = plugin.getServer().getPlayer(ally.getName());
                ChatBlock.sendMessage(member, message);
            }
        }
    }

    /**
     * Processes a global chat command
     *
     * @param player
     * @param msg
     * @return 
     */
    public boolean processGlobalChat(Player player, String msg)
    {
        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player.getName());

        if (cp == null)
        {
            return false;
        }

        String[] split = msg.split(" ");

        if (split.length == 0)
        {
            return false;
        }

        String command = split[0];

        if (command.equals(SimpleClans.langManager.on))
        {
            cp.setGlobalChat(true);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have enabled global chat");
        }
        else if (command.equals(SimpleClans.langManager.off))
        {
            cp.setGlobalChat(false);
            plugin.getStorageManager().updateClanPlayer(cp);
            ChatBlock.sendMessage(player, ChatColor.AQUA + "You have disabled global chat");
        }
        else
        {
            return true;
        }

        return false;
    }
}
