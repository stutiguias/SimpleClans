package net.sacredlabyrinth.phaed.simpleclans.managers;

import net.sacredlabyrinth.phaed.simpleclans.*;
import net.sacredlabyrinth.phaed.simpleclans.storage.DBCore;
import net.sacredlabyrinth.phaed.simpleclans.storage.MySQLCore;
import net.sacredlabyrinth.phaed.simpleclans.storage.SQLiteCore;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/**
 * @author phaed
 */
public final class StorageManager
{

    private final SimpleClans plugin;
    private DBCore core;
    private final HashMap<String, ChatBlock> chatBlocks = new HashMap<>();

    /**
     *
     */
    public StorageManager()
    {
        plugin = SimpleClans.getInstance();
        initiateDB();
        updateDatabase();
        importFromDatabase();
    }

    /**
     * Retrieve a player's pending chat lines
     *
     * @param player
     * @return
     */
    public ChatBlock getChatBlock(Player player)
    {
        return chatBlocks.get(player.getName());
    }

    /**
     * Store pending chat lines for a player
     *
     * @param player
     * @param cb
     */
    public void addChatBlock(CommandSender player, ChatBlock cb)
    {
        chatBlocks.put(player.getName(), cb);
    }

    /**
     * Initiates the db
     */
    public void initiateDB()
    {
        if (plugin.getSettingsManager().isUseMysql())
        {
            core = new MySQLCore(plugin.getSettingsManager().getHost(), plugin.getSettingsManager().getDatabase(), plugin.getSettingsManager().getUsername(), plugin.getSettingsManager().getPassword());

            if (core.checkConnection())
            {
                SimpleClans.log("[SimpleClans] Connection with mysql SUCESS ! ");

                if (!core.existsTable("sc_clans"))
                {
                    SimpleClans.log("Creating table: sc_clans");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_clans` ( `id` bigint(20) NOT NULL auto_increment, `tag` varchar(25) NOT NULL, `color_tag` varchar(25) NOT NULL, `name` varchar(100) NOT NULL, `friendly_fire` tinyint(1) default '0', `founded` bigint NOT NULL, `last_used` bigint NOT NULL, `packed_allies` text NOT NULL, `packed_rivals` text NOT NULL, `packed_bb` mediumtext NOT NULL, `flags` text NOT NULL, PRIMARY KEY  (`id`), UNIQUE KEY `uq_simpleclans_1` (`tag`));";
                    core.execute(query);
                }

                if (!core.existsTable("sc_players"))
                {
                    SimpleClans.log("Creating table: sc_players");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_players` ( `id` bigint(20) NOT NULL auto_increment, `name` varchar(16) NOT NULL, `leader` tinyint(1) default '0', `tag` varchar(25) NOT NULL, `friendly_fire` tinyint(1) default '0', `neutral_kills` int(11) default NULL, `rival_kills` int(11) default NULL, `civilian_kills` int(11) default NULL, `deaths` int(11) default NULL, `last_seen` bigint NOT NULL, `join_date` bigint NOT NULL, `trusted` tinyint(1) default '0', `flags` text NOT NULL, `packed_past_clans` text, PRIMARY KEY  (`id`), UNIQUE KEY `uq_sc_players_1` (`name`));";
                    core.execute(query);
                }

                if (!core.existsTable("sc_kills"))
                {
                    SimpleClans.log("Creating table: sc_kills");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_kills` ( `kill_id` bigint(20) NOT NULL auto_increment, `attacker` varchar(16) NOT NULL, `attacker_tag` varchar(16) NOT NULL, `victim` varchar(16) NOT NULL, `victim_tag` varchar(16) NOT NULL, `kill_type` varchar(1) NOT NULL, PRIMARY KEY  (`kill_id`));";
                    core.execute(query);
                }
                if (!core.existsTable("sc_war"))
                {
                    SimpleClans.log("Creating table: sc_war");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_war` ( `clan_name` varchar(16) NOT NULL, PRIMARY KEY  (`clan_name`));";
                    core.execute(query);
                }
            } else
            {
                SimpleClans.getInstance().getServer().getConsoleSender().sendMessage("[SimpleClans] " + ChatColor.RED + "Mysql Fail to connect");
            }
        } else
        {
            core = new SQLiteCore(plugin.getDataFolder().getPath());

            if (core.checkConnection())
            {
                SimpleClans.log("[SimpleClans] Connection SQLite with sucess ! ");

                if (!core.existsTable("sc_clans"))
                {
                    SimpleClans.log("Creating table: sc_clans");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_clans` ( `id` bigint(20), `verified` tinyint(1) default '0', `tag` varchar(25) NOT NULL, `color_tag` varchar(25) NOT NULL, `name` varchar(100) NOT NULL, `friendly_fire` tinyint(1) default '0', `founded` bigint NOT NULL, `last_used` bigint NOT NULL, `packed_allies` text NOT NULL, `packed_rivals` text NOT NULL, `packed_bb` mediumtext NOT NULL, `flags` text NOT NULL,  PRIMARY KEY  (`id`), UNIQUE (`tag`));";
                    core.execute(query);
                }

                if (!core.existsTable("sc_players"))
                {
                    SimpleClans.log("Creating table: sc_players");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_players` ( `id` bigint(20), `name` varchar(16) NOT NULL, `leader` tinyint(1) default '0', `tag` varchar(25) NOT NULL, `friendly_fire` tinyint(1) default '0', `neutral_kills` int(11) default NULL, `rival_kills` int(11) default NULL, `civilian_kills` int(11) default NULL, `deaths` int(11) default NULL, `last_seen` bigint NOT NULL, `join_date` bigint NOT NULL, `trusted` tinyint(1) default '0', `flags` text NOT NULL, `packed_past_clans` text, PRIMARY KEY  (`id`), UNIQUE (`name`));";
                    core.execute(query);
                }

                if (!core.existsTable("sc_kills"))
                {
                    SimpleClans.log("Creating table: sc_kills");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_kills` ( `kill_id` bigint(20), `attacker` varchar(16) NOT NULL, `attacker_tag` varchar(16) NOT NULL, `victim` varchar(16) NOT NULL, `victim_tag` varchar(16) NOT NULL, `kill_type` varchar(1) NOT NULL, PRIMARY KEY  (`kill_id`));";
                    core.execute(query);
                }
                if (!core.existsTable("sc_war"))
                {
                    SimpleClans.log("Creating table: sc_war");

                    String query = "CREATE TABLE IF NOT EXISTS `sc_war` ( `clan_name` varchar(16) NOT NULL, PRIMARY KEY  (`clan_name`));";
                    core.execute(query);
                }
            } else
            {
                SimpleClans.getInstance().getServer().getConsoleSender().sendMessage("[SimpleClans] " + ChatColor.RED + "SQLite Fail to Connect");
            }
        }
    }

    /**
     * Closes DB connection
     */
    public void closeConnection()
    {
        core.close();
    }

    /**
     * Import all data from database to memory
     */
    public void importFromDatabase()
    {
        plugin.getClanManager().cleanData();

        List<Clan> clans = retrieveClans();
        purgeClans(clans);

        for (Clan clan : clans)
        {
            plugin.getClanManager().importClan(clan);
        }

        for (Clan clan : clans)
        {
            clan.validateWarring();
        }

        if (clans.size() > 0)
        {
            SimpleClans.log(MessageFormat.format("[SimpleClans] " + SimpleClans.langManager.clans, clans.size()));
        }

        List<ClanPlayer> cps = retrieveClanPlayers();
        purgeClanPlayers(cps);

        for (ClanPlayer cp : cps)
        {
            Clan tm = cp.getClan();

            if (tm != null)
            {
                tm.importMember(cp);
            }
            plugin.getClanManager().importClanPlayer(cp);
        }

        if (cps.size() > 0)
        {
            SimpleClans.log(MessageFormat.format("[SimpleClans] " + SimpleClans.langManager.clanPlayers, cps.size()));
        }
    }

    private void purgeClans(List<Clan> clans)
    {
        List<Clan> purge = new ArrayList<>();

        for (Clan clan : clans)
        {
            if (clan.getInactiveDays() > plugin.getSettingsManager().getPurgeClan())
            {
                purge.add(clan);
            }
        }

        for (Clan clan : purge)
        {
            SimpleClans.log("[SimpleClans] " + MessageFormat.format(SimpleClans.langManager.purgingClan, clan.getName()));
            deleteClan(clan);
            clans.remove(clan);
        }
    }

    private void purgeClanPlayers(List<ClanPlayer> cps)
    {
        List<ClanPlayer> purge = new ArrayList<>();

        for (ClanPlayer cp : cps)
        {
            if (cp.getInactiveDays() > plugin.getSettingsManager().getPurgePlayers())
            {
                if (!cp.isLeader())
                {
                    purge.add(cp);
                }
            }
        }

        for (ClanPlayer cp : purge)
        {
            SimpleClans.log("[SimpleClans] " + MessageFormat.format(SimpleClans.langManager.purgingPlayerData, cp.getName()));
            deleteClanPlayer(cp);
            cps.remove(cp);
        }
    }

    /**
     * Retrieves all simple clans from the database
     *
     * @return
     */
    public List<Clan> retrieveClans()
    {
        List<Clan> out = new ArrayList<>();

        String query = "SELECT * FROM  `sc_clans`;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        boolean friendly_fire = res.getBoolean("friendly_fire");
                        String tag = res.getString("tag");
                        String color_tag = Helper.parseColors(res.getString("color_tag"));
                        String name = res.getString("name");
                        String packed_allies = res.getString("packed_allies");
                        String packed_rivals = res.getString("packed_rivals");
                        String packed_bb = res.getString("packed_bb");
                        String flags = res.getString("flags");
                        long founded = res.getLong("founded");
                        long last_used = res.getLong("last_used");

                        if (founded == 0)
                        {
                            founded = (new Date()).getTime();
                        }

                        if (last_used == 0)
                        {
                            last_used = (new Date()).getTime();
                        }

                        Clan clan = new Clan();
                        clan.setFlags(flags);
                        clan.setFriendlyFire(friendly_fire);
                        clan.setTag(tag);
                        clan.setColorTag(color_tag);
                        clan.setName(name);
                        clan.setPackedAllies(packed_allies);
                        clan.setPackedRivals(packed_rivals);
                        clan.setPackedBb(packed_bb);
                        clan.setFounded(founded);
                        clan.setLastUsed(last_used);

                        out.add(clan);
                    } catch (SQLException ex)
                    {
                        for (StackTraceElement el : ex.getStackTrace())
                        {
                            System.out.print(el.toString());
                        }
                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Retrieves the strifes relativ to another clan
     *
     * @param attackerclan
     * @param victimclan
     * @return
     */
    public Integer retrieveStrifes(Clan attackerclan, Clan victimclan)
    {
        int strifes = 0;
        String query = null;
        String row = null;
        if (core.existsColumn("sc_war", attackerclan.getTag()))
        {
            query = "SELECT * FROM `sc_war` WHERE `sc_war`.`clan_name` =  '" + victimclan.getTag() + "';";
            row = attackerclan.getTag();
        } else if (core.existsColumn("sc_war", victimclan.getTag()))
        {
            row = victimclan.getTag();
            query = "SELECT * FROM `sc_war` WHERE `sc_war`.`clan_name` =  '" + attackerclan.getTag() + "';";
        }
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        strifes = res.getInt(row);
                    } catch (Exception ex)
                    {
                        for (StackTraceElement el : ex.getStackTrace())
                        {
                            SimpleClans.getLog().severe(el.toString());
                        }
                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }
        return strifes;

    }

    /**
     * Retrieves all clan players from the database
     *
     * @return
     */
    public List<ClanPlayer> retrieveClanPlayers()
    {
        List<ClanPlayer> out = new ArrayList<ClanPlayer>();

        String query = "SELECT * FROM  `sc_players`;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        String name = res.getString("name");
                        String tag = res.getString("tag");
                        boolean leader = res.getBoolean("leader");
                        boolean friendly_fire = res.getBoolean("friendly_fire");
                        boolean trusted = res.getBoolean("trusted");
                        int neutral_kills = res.getInt("neutral_kills");
                        int rival_kills = res.getInt("rival_kills");
                        int civilian_kills = res.getInt("civilian_kills");
                        int deaths = res.getInt("deaths");
                        long last_seen = res.getLong("last_seen");
                        long join_date = res.getLong("join_date");
                        String flags = res.getString("flags");
                        String packed_past_clans = Helper.parseColors(res.getString("packed_past_clans"));

                        if (last_seen == 0)
                        {
                            last_seen = (new Date()).getTime();
                        }

                        if (join_date == 0)
                        {
                            join_date = (new Date()).getTime();
                        }

                        ClanPlayer cp = new ClanPlayer();
                        cp.setFlags(flags);
                        cp.setName(name);
                        cp.setLeader(leader);
                        cp.setFriendlyFire(friendly_fire);
                        cp.setNeutralKills(neutral_kills);
                        cp.setRivalKills(rival_kills);
                        cp.setCivilianKills(civilian_kills);
                        cp.setDeaths(deaths);
                        cp.setLastSeen(last_seen);
                        cp.setJoinDate(join_date);
                        cp.setPackedPastClans(packed_past_clans);
                        cp.setTrusted(leader || trusted);

                        if (!tag.isEmpty())
                        {
                            Clan clan = SimpleClans.getInstance().getClanManager().getClan(tag);

                            if (clan != null)
                            {
                                cp.setClan(clan);
                            }
                        }

                        out.add(cp);
                    } catch (Exception ex)
                    {
                        for (StackTraceElement el : ex.getStackTrace())
                        {
                            System.out.print(el.toString());
                        }
                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Insert a clan into the database
     *
     * @param clan
     */
    public void insertClan(Clan clan)
    {
        String query = "INSERT INTO `sc_clans` ( `tag`, `color_tag`, `name`, `friendly_fire`, `founded`, `last_used`, `packed_allies`, `packed_rivals`, `packed_bb`, `flags`) ";
        String values = "VALUES ( '" + Helper.escapeQuotes(clan.getTag()) + "','" + Helper.escapeQuotes(clan.getColorTag()) + "','" + Helper.escapeQuotes(clan.getName()) + "'," + (clan.isFriendlyFire() ? 1 : 0) + ",'" + clan.getFounded() + "','" + clan.getLastUsed() + "','" + Helper.escapeQuotes(clan.getPackedAllies()) + "','" + Helper.escapeQuotes(clan.getPackedRivals()) + "','" + Helper.escapeQuotes(clan.getPackedBb()) + "','" + Helper.escapeQuotes(clan.getFlags()) + "');";
        core.insert(query + values);
    }

    /**
     * Insert a strife to a clan
     *
     * @param attackerclan
     * @param victimclan
     * @param amount
     */
    public void addStrife(Clan attackerclan, Clan victimclan, int amount)
    {
        try
        {
            String query = null;
            if (!core.existsColumn("sc_war", attackerclan.getTag()) && !core.existsColumn("sc_war", victimclan.getTag()))
            {
                String addcol = "ALTER TABLE sc_war ADD COLUMN " + attackerclan.getTag() + " int(255);";
                core.execute(addcol);
            }
            if (!Helper.existsEntry(core, "sc_war", "clan_name", victimclan.getTag()) && !Helper.existsEntry(core, "sc_war", "clan_name", attackerclan.getTag()))
            {
                String insert = "INSERT INTO  `sc_war` (`clan_name`) VALUES ('" + victimclan.getTag() + "');";
                core.insert(insert);
            }
            if (core.existsColumn("sc_war", attackerclan.getTag()) && Helper.existsEntry(core, "sc_war", "clan_name", victimclan.getTag()))
            {
                query = "UPDATE  `sc_war` SET  `" + attackerclan.getTag() + "` =  '" + (retrieveStrifes(attackerclan, victimclan) + amount) + "' WHERE  `sc_war`.`clan_name` =  '" + victimclan.getTag() + "';";
            } else if (core.existsColumn("sc_war", victimclan.getTag()) && Helper.existsEntry(core, "sc_war", "clan_name", attackerclan.getTag()))
            {
                query = "UPDATE  `sc_war` SET  `" + victimclan.getTag() + "` =  '" + (retrieveStrifes(attackerclan, victimclan) + amount) + "' WHERE  `sc_war`.`clan_name` =  '" + attackerclan.getTag() + "';";
            }
            core.execute(query);
        } catch (SQLException ex)
        {
            SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
            SimpleClans.getLog().log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update a clan to the database
     *
     * @param clan
     */
    public void updateClan(Clan clan)
    {
        clan.updateLastUsed();
        String query = "UPDATE `sc_clans` SET tag = '" + Helper.escapeQuotes(clan.getTag()) + "', color_tag = '" + Helper.escapeQuotes(clan.getColorTag()) + "', name = '" + Helper.escapeQuotes(clan.getName()) + "', friendly_fire = " + (clan.isFriendlyFire() ? 1 : 0) + ", founded = '" + clan.getFounded() + "', last_used = '" + clan.getLastUsed() + "', packed_allies = '" + Helper.escapeQuotes(clan.getPackedAllies()) + "', packed_rivals = '" + Helper.escapeQuotes(clan.getPackedRivals()) + "', packed_bb = '" + Helper.escapeQuotes(clan.getPackedBb()) + "', flags = '" + Helper.escapeQuotes(clan.getFlags()) + "' WHERE tag = '" + Helper.escapeQuotes(clan.getTag()) + "';";
        core.update(query);
    }

    /**
     * Delete a clan from the database
     *
     * @param clan
     */
    public void deleteClan(Clan clan)
    {
        String query = "DELETE FROM `sc_clans` WHERE tag = '" + clan.getTag() + "';";
        String war = null;
        if (core.existsColumn("sc_war", clan.getTag()))
        {
            String warc = "ALTER TABLE `sc_war` DROP COLUMN `" + clan.getTag() + "`;";
        }

        try
        {
            if (Helper.existsEntry(core, "sc_war", "clan_name", clan.getTag()))
            {
                war += "DELETE FROM `sc_war` WHERE clan_name = '" + clan.getTag() + "';";
            }
        } catch (SQLException ex)
        {
            SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
            SimpleClans.getLog().log(Level.SEVERE, null, ex);
        }
        if (war != null)
        {
            core.delete(war);
        }
        core.delete(query);

    }

    /**
     * Insert a clan player into the database
     *
     * @param cp
     */
    public void insertClanPlayer(ClanPlayer cp)
    {
        String query = "INSERT INTO `sc_players` (  `name`, `leader`, `tag`, `friendly_fire`, `neutral_kills`, `rival_kills`, `civilian_kills`, `deaths`, `last_seen`, `join_date`, `packed_past_clans`, `flags`) ";
        String values = "VALUES ( '" + cp.getName() + "'," + (cp.isLeader() ? 1 : 0) + ",'" + Helper.escapeQuotes(cp.getTag()) + "'," + (cp.isFriendlyFire() ? 1 : 0) + "," + cp.getNeutralKills() + "," + cp.getRivalKills() + "," + cp.getCivilianKills() + "," + cp.getDeaths() + ",'" + cp.getLastSeen() + "',' " + cp.getJoinDate() + "','" + Helper.escapeQuotes(cp.getPackedPastClans()) + "','" + Helper.escapeQuotes(cp.getFlags()) + "');";
        core.insert(query + values);
    }

    /**
     * Update a clan player to the database
     *
     * @param cp
     */
    public void updateClanPlayer(ClanPlayer cp)
    {
        cp.updateLastSeen();
        String query = "UPDATE `sc_players` SET leader = " + (cp.isLeader() ? 1 : 0) + ", tag = '" + Helper.escapeQuotes(cp.getTag()) + "' , friendly_fire = " + (cp.isFriendlyFire() ? 1 : 0) + ", neutral_kills = " + cp.getNeutralKills() + ", rival_kills = " + cp.getRivalKills() + ", civilian_kills = " + cp.getCivilianKills() + ", deaths = " + cp.getDeaths() + ", last_seen = '" + cp.getLastSeen() + "', packed_past_clans = '" + Helper.escapeQuotes(cp.getPackedPastClans()) + "', trusted = " + (cp.isTrusted() ? 1 : 0) + ", flags='" + Helper.escapeQuotes(cp.getFlags()) + "' WHERE name = '" + cp.getName() + "';";
        core.update(query);
    }

    /**
     * Delete a clan player from the database
     *
     * @param cp
     */
    public void deleteClanPlayer(ClanPlayer cp)
    {
        String query = "DELETE FROM `sc_players` WHERE name = '" + cp.getName() + "';";
        core.delete(query);

        deleteKills(cp.getName());
    }

    /**
     * Insert a kill into the database
     *
     * @param attacker
     * @param victim
     * @param type
     */
    public void insertKill(Player attacker, String attackerTag, Player victim, String victimTag, String type)
    {
        String query = "INSERT INTO `sc_kills` (  `attacker`, `attacker_tag`, `victim`, `victim_tag`, `kill_type`) ";
        String values = "VALUES ( '" + attacker.getName() + "','" + attackerTag + "','" + victim.getName() + "','" + victimTag + "','" + type + "');";
        core.insert(query + values);
    }

    /**
     * Delete a player's kill record form the database
     *
     * @param playerName
     */
    public void deleteKills(String playerName)
    {
        String query = "DELETE FROM `sc_kills` WHERE `attacker` = '" + playerName + "'";
        core.delete(query);
    }

    /**
     * Returns a map of victim->count of all kills that specific player did
     *
     * @param playerName
     * @return
     */
    public HashMap<String, Integer> getKillsPerPlayer(String playerName)
    {
        HashMap<String, Integer> out = new HashMap<String, Integer>();

        String query = "SELECT victim, count(victim) AS kills FROM `sc_kills` WHERE attacker = '" + playerName + "' GROUP BY victim ORDER BY count(victim) DESC;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        String victim = res.getString("victim");
                        int kills = res.getInt("kills");
                        out.put(victim, kills);
                    } catch (Exception ex)
                    {
                        SimpleClans.getLog().info(ex.getMessage());


                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Returns a map of tag->count of all kills
     *
     * @return
     */
    public HashMap<String, Integer> getMostKilled()
    {
        HashMap<String, Integer> out = new HashMap<String, Integer>();

        String query = "SELECT attacker, victim, count(victim) AS kills FROM `sc_kills` GROUP BY attacker, victim ORDER BY 3 DESC;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        String attacker = res.getString("attacker");
                        String victim = res.getString("victim");
                        int kills = res.getInt("kills");
                        out.put(attacker + " " + victim, kills);
                    } catch (Exception ex)
                    {
                        SimpleClans.getLog().info(ex.getMessage());


                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Returns a map of tag->count of all deaths by each clan
     *
     * @return
     */
    public HashMap<String, Integer> getTotalDeathsPerClan()
    {
        HashMap<String, Integer> out = new HashMap<String, Integer>();

        String query = "SELECT victim_tag, count(victim_tag) AS kills FROM `sc_kills` GROUP BY victim_tag ORDER BY 2 DESC;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        String victimTag = res.getString("victim_tag");
                        int kills = res.getInt("kills");
                        out.put(victimTag, kills);
                    } catch (Exception ex)
                    {
                        SimpleClans.getLog().info(ex.getMessage());


                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Returns a map of tag->count of all kills by each clan
     *
     * @return
     */
    public HashMap<String, Integer> getTotalKillsPerClan()
    {
        HashMap<String, Integer> out = new HashMap<String, Integer>();

        String query = "SELECT attacker_tag, count(attacker_tag) AS kills FROM `sc_kills` GROUP BY attacker_tag ORDER BY 2 DESC;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        String victimTag = res.getString("attacker_tag");
                        int kills = res.getInt("kills");
                        out.put(victimTag, kills);
                    } catch (Exception ex)
                    {
                        SimpleClans.getLog().info(ex.getMessage());


                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Returns a map of playerName->count of all kills by each player
     *
     * @return
     */
    public HashMap<String, Integer> getTotalKillsPerPlayer()
    {
        HashMap<String, Integer> out = new HashMap<String, Integer>();

        String query = "SELECT attacker, count(attacker) AS kills FROM `sc_kills` GROUP BY attacker ORDER BY 2 DESC;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        String attacker = res.getString("attacker");
                        int kills = res.getInt("kills");
                        out.put(attacker, kills);
                    } catch (Exception ex)
                    {
                        SimpleClans.getLog().info(ex.getMessage());


                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Returns a map of playerName->count of all kills by each player
     *
     * @return
     */
    public HashMap<String, Integer> getTotalDeathsPerPlayer()
    {
        HashMap<String, Integer> out = new HashMap<String, Integer>();

        String query = "SELECT victim, count(victim) AS kills FROM `sc_kills` GROUP BY victim ORDER BY 2 DESC;";
        ResultSet res = core.select(query);

        if (res != null)
        {
            try
            {
                while (res.next())
                {
                    try
                    {
                        String victim = res.getString("victim");
                        int kills = res.getInt("kills");
                        out.put(victim, kills);
                    } catch (Exception ex)
                    {
                        SimpleClans.getLog().info(ex.getMessage());


                    }
                }
            } catch (SQLException ex)
            {
                SimpleClans.getLog().severe(String.format("An Error occurred: %s", ex.getErrorCode()));
                SimpleClans.getLog().log(Level.SEVERE, null, ex);
            }
        }

        return out;
    }

    /**
     * Updates the database to the latest version
     *
     * @param
     */
    private void updateDatabase()
    {
        String query = null;

        //From 2.2.6.3 to 2.3
        if (!core.existsColumn("sc_clans", "balance"))
        {
            query = "ALTER TABLE sc_clans ADD COLUMN `balance` double(64,2);";

        }


        if (query != null)
        {
            core.execute(query);
        }
    }
}
