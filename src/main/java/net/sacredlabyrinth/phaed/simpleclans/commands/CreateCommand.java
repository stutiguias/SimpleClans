package net.sacredlabyrinth.phaed.simpleclans.commands;

import net.sacredlabyrinth.phaed.simpleclans.ChatBlock;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.Helper;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 *
 * @author phaed
 */
public class CreateCommand
{
    SimpleClans plugin;
    
    public CreateCommand()
    {
    }

    /**
     * Execute the command
     * @param player
     * @param arg
     */
    public void execute(Player player, String[] arg)
    {
        plugin = SimpleClans.getInstance();

        if (arg.length < 2)
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.usageCreateTag, plugin.getSettingsManager().getCommandClan()));
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.exampleClanCreate);
            return;
        }

        String tag = arg[0];
        String cleanTag = Helper.cleanTag(arg[0]);
        String name = Helper.toMessage(Helper.removeFirst(arg));
        
        if (CanCreateThisClan(player, tag, cleanTag, name))
        {
            ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

            if (isValidClan(cp, player, cleanTag) && plugin.getClanManager().purchaseCreation(player))
            {
                plugin.getClanManager().createClan(player, tag, name);

                Clan clan = plugin.getClanManager().getClan(tag);
                clan.addBb(player.getName(), ChatColor.AQUA + MessageFormat.format(SimpleClans.langManager.clanCreated, name));
                plugin.getStorageManager().updateClan(clan);
            }
        }
    }
    
    private boolean CanCreateThisClan(Player player,String tag,String cleanTag,String name)
    {
        boolean bypass = plugin.getPermissionsManager().has(player, "simpleclans.mod.bypass");
        
        if (!isValidCreateWithoutByPass(player, tag, cleanTag, name))
        {        
            return false;
        }
       
        return bypass || isValidCreateWithByPass(player, tag, cleanTag, name);
    }
    
    
    private boolean isValidClan(ClanPlayer clanPlayer,Player player,String cleanTag) 
    {
        if (clanPlayer != null)
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.youMustFirstResign, clanPlayer.getClan().getName()));
            return false;
        }
        
        if (plugin.getClanManager().isClan(cleanTag))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.clanWithTagAlreadyExists);
            return false;
        }
        
        return true;
    }
    
    private boolean isValidCreateWithoutByPass(Player player,String tag,String cleanTag,String name) 
    {
        if (!cleanTag.matches("[0-9a-zA-Z]*"))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.TagCanOnlyContainLettersNumbersColorCodes);
            return false;
        }
        
        if (name.contains("&"))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NameCannotContainColorCodes);
            return false;
        }
        
        return true;
    }
    
    private boolean isValidCreateWithByPass(Player player,String tag,String cleanTag,String name){
        
        if (!plugin.getPermissionsManager().has(player, "simpleclans.leader.create"))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.insufficientPermissions);
            return false;
        }
        
        if (cleanTag.length() > plugin.getSettingsManager().getTagMaxLength())
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.ClanTagCannotBeLongerCharacters, plugin.getSettingsManager().getTagMaxLength()));
            return false;
        }
        
        if (cleanTag.length() < plugin.getSettingsManager().getTagMinLength())
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.ClanTagMustBeLongerThanCharacters, plugin.getSettingsManager().getTagMinLength()));
            return false;
        }
        
        if(plugin.getSettingsManager().hasDisallowedColor(tag))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.TagCannotContainFollowingColors, plugin.getSettingsManager().getDisallowedColorString()));
            return false;
        }
        
        if(Helper.stripColors(name).length() > plugin.getSettingsManager().getClanMaxLength()) 
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.ClanNameCannotBeLongerThanCharacters, plugin.getSettingsManager().getClanMaxLength()));
            return false;
        }
        
        if(Helper.stripColors(name).length() < plugin.getSettingsManager().getClanMinLength())
        {
            ChatBlock.sendMessage(player, ChatColor.RED + MessageFormat.format(SimpleClans.langManager.ClanNameNustBeLongerThanCharacters, plugin.getSettingsManager().getClanMinLength()));
            return false;
        }
        
        if(plugin.getSettingsManager().isDisallowedWord(cleanTag.toLowerCase()))
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.TagNameDisallowed);
            return false;
        }
        
        return true;
    }
}
