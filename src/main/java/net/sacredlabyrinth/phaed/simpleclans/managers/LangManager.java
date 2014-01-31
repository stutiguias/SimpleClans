package net.sacredlabyrinth.phaed.simpleclans.managers;

import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.bukkit.configuration.InvalidConfigurationException;

/**
 * @author phaed
 */
public final class LangManager
{
   
    private final SimpleClans plugin;
    private final File main;
    private final FileConfiguration config;

    public String clanAnnounce;
    public String leaderAnnounce;
    public String bulletinBoardHeader;
    public String clanDisbanded;
    public String youAreNoLongerAtWar;
    public String hasBeenDisbandedRivalryEnded;
    public String hasBeenDisbandedAllianceEnded;
    public String today;
    
    public LangManager()
    {
        plugin = SimpleClans.getInstance();
        config = plugin.getConfig();
        main = new File(plugin.getDataFolder() + File.separator + "lang.yml");
        load();
    }

    /**
     * Load the configuration
     */
    @SuppressWarnings("unchecked")
    public void load()
    {
        boolean exists = (main).exists();

        if (exists)
        {
            try
            {
                getConfig().options().copyDefaults(true);
                getConfig().load(main);
            }
            catch (IOException | InvalidConfigurationException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            getConfig().options().copyDefaults(true);
        }

        
        clanAnnounce = getConfig().getString("clan.announce");
        leaderAnnounce = getConfig().getString("leader.announce");
        bulletinBoardHeader = getConfig().getString("bulletin.board.header");
        clanDisbanded = getConfig().getString("clan.disbanded");
        youAreNoLongerAtWar = getConfig().getString("you.are.no.longer.at.war");
        hasBeenDisbandedRivalryEnded = getConfig().getString("has.been.disbanded.rivalry.ended");
        hasBeenDisbandedAllianceEnded = getConfig().getString("has.been.disbanded.alliance.ended");
        today = getConfig().getString("names.today");
        save();
    }

    public void save()
    {
        try
        {
            getConfig().save(main);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return the plugin
     */
    public SimpleClans getPlugin()
    {
        return plugin;
    }

    /**
     * @return the config
     */
    public FileConfiguration getConfig() {
        return config;
    }

}
