package net.sacredlabyrinth.phaed.simpleclans.managers;

import net.sacredlabyrinth.phaed.simpleclans.*;
import net.sacredlabyrinth.phaed.simpleclans.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * @author phaed
 */
public final class CommandManager
{
    private final SimpleClans plugin;
    private final CreateCommand createCommand;
    private final ListCommand listCommand;
    private final ProfileCommand profileCommand;
    private final RosterCommand rosterCommand;
    private final LookupCommand lookupCommand;
    private final LeaderboardCommand leaderboardCommand;
    private final AlliancesCommand alliancesCommand;
    private final RivalriesCommand rivalriesCommand;
    private final StatsCommand statsCommand;
    private final AllyCommand allyCommand;
    private final RivalCommand rivalCommand;
    private final BbCommand bbCommand;
    private final ModtagCommand modtagCommand;
    private final ToggleCommand toggleCommand;
    private final InviteCommand inviteCommand;
    private final KickCommand kickCommand;
    private final TrustCommand trustCommand;
    private final UntrustCommand untrustCommand;
    private final PromoteCommand promoteCommand;
    private final DemoteCommand demoteCommand;
    private final ClanffCommand clanffCommand;
    private final FfCommand ffCommand;
    private final ResignCommand resignCommand;
    private final DisbandCommand disbandCommand;
    private final ReloadCommand reloadCommand;
    private final GlobalffCommand globalffCommand;
    private final MenuCommand menuCommand;
    private final WarCommand warCommand;
    private final HomeCommand homeCommand;
    private final KillsCommand killsCommand;
    private final MostKilledCommand mostKilledCommand;
    private final SetRankCommand setRankCommand;

    /**
     *
     */
    public CommandManager()
    {
        plugin = SimpleClans.getInstance();
        menuCommand = new MenuCommand();
        createCommand = new CreateCommand();
        listCommand = new ListCommand();
        profileCommand = new ProfileCommand();
        rosterCommand = new RosterCommand();
        lookupCommand = new LookupCommand();
        leaderboardCommand = new LeaderboardCommand();
        alliancesCommand = new AlliancesCommand();
        rivalriesCommand = new RivalriesCommand();
        statsCommand = new StatsCommand();
        allyCommand = new AllyCommand();
        rivalCommand = new RivalCommand();
        bbCommand = new BbCommand();
        modtagCommand = new ModtagCommand();
        toggleCommand = new ToggleCommand();
        inviteCommand = new InviteCommand();
        kickCommand = new KickCommand();
        trustCommand = new TrustCommand();
        untrustCommand = new UntrustCommand();
        promoteCommand = new PromoteCommand();
        demoteCommand = new DemoteCommand();
        clanffCommand = new ClanffCommand();
        ffCommand = new FfCommand();
        resignCommand = new ResignCommand();
        disbandCommand = new DisbandCommand();
        reloadCommand = new ReloadCommand();
        globalffCommand = new GlobalffCommand();
        warCommand = new WarCommand();
        homeCommand = new HomeCommand();
        killsCommand = new KillsCommand();
        mostKilledCommand = new MostKilledCommand();
        setRankCommand = new SetRankCommand();
    }

    /**
     * @param sender
     * @param args
     */
    public void processClan(CommandSender sender, String[] args)
    {
        try
        {
            if (sender instanceof Player)
            {
                Player player = (Player) sender;

                if (plugin.getSettingsManager().isBlacklistedWorld(player.getLocation().getWorld().getName()))
                {
                    return;
                }

                if (args.length == 0)
                {
                    menuCommand.execute(player);
                }
                else
                {
                    String subcommand = args[0];
                    String[] subargs = Helper.removeFirst(args);

                    if (subcommand.equalsIgnoreCase(SimpleClans.langManager.create))
                    {
                        createCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.list))
                    {
                        listCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.profile))
                    {
                        profileCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.roster))
                    {
                        rosterCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.lookup))
                    {
                        lookupCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.home))
                    {
                        homeCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.leaderboard))
                    {
                        leaderboardCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.alliances))
                    {
                        alliancesCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.rivalries))
                    {
                        rivalriesCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.stats))
                    {
                        statsCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.ally))
                    {
                        allyCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.rival))
                    {
                        rivalCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.bb))
                    {
                        bbCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.modtag))
                    {
                        modtagCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.toggle))
                    {
                        toggleCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.invite))
                    {
                        inviteCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.kick))
                    {
                        kickCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.trust))
                    {
                        trustCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.untrust))
                    {
                        untrustCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.promote))
                    {
                        promoteCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.demote))
                    {
                        demoteCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.clanff))
                    {
                        clanffCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.ff))
                    {
                        ffCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.resign))
                    {
                        resignCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.disband))
                    {
                        disbandCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.reload))
                    {
                        reloadCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.globalff))
                    {
                        globalffCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.war))
                    {
                        warCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.kills))
                    {
                        killsCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.mostkilled))
                    {
                        mostKilledCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(SimpleClans.langManager.setrank))
                    {
                        setRankCommand.execute(player, subargs);
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.doesNotMatch);
                    }
                }
            }
            else
            {
                if (args.length == 0)
                {
                    menuCommand.executeSender(sender);
                }
                else
                {
                    String subcommand = args[0];
                    String[] subargs = Helper.removeFirst(args);

                    if (subcommand.equalsIgnoreCase(SimpleClans.langManager.reload))
                    {
                        reloadCommand.execute(sender, subargs);
                    }
                    else
                    {
                        ChatBlock.sendMessage(sender, ChatColor.RED + SimpleClans.langManager.doesNotMatch);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            SimpleClans.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + MessageFormat.format( SimpleClans.langManager.CommandFailure , ex.getMessage()));
            for (StackTraceElement el : ex.getStackTrace())
            {
                System.out.print(el.toString());
            }
        }
    }

    /**
     * Process the accept command
     *
     * @param player
     */
    public void processAccept(Player player)
    {

        ClanPlayer clanPlayer = plugin.getClanManager().getClanPlayer(player);

        if (clanPlayer != null)
        {
            Clan clan = clanPlayer.getClan();

            if (isValidVote(player, clan, clanPlayer,SimpleClans.langManager.NothingAccept)) {
                plugin.getRequestManager().accept(clanPlayer);
                clan.leaderAnnounce(ChatColor.GREEN + MessageFormat.format(SimpleClans.langManager.votedAccept, Helper.capitalize(player.getName())));
            }
            
        }
        else
        {
            if (plugin.getRequestManager().hasRequest(player.getName().toLowerCase()))
            {
                clanPlayer = plugin.getClanManager().getCreateClanPlayer(player.getName());
                plugin.getRequestManager().accept(clanPlayer);
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NothingAccept);
            }
        }
    }


    /**
     * Process the deny command
     *
     * @param player
     */
    public void processDeny(Player player)
    {

        ClanPlayer clanPlayer = plugin.getClanManager().getClanPlayer(player);

        if (clanPlayer != null)
        {
            Clan clan = clanPlayer.getClan();

            if(isValidVote(player, clan, clanPlayer,SimpleClans.langManager.NothingDeny)) {
                plugin.getRequestManager().deny(clanPlayer);
                clan.leaderAnnounce(ChatColor.RED + MessageFormat.format( SimpleClans.langManager.VotedDeny , Helper.capitalize(player.getName())));
            }
        }
        else
        {
            if (plugin.getRequestManager().hasRequest(player.getName().toLowerCase()))
            {
                clanPlayer = plugin.getClanManager().getCreateClanPlayer(player.getName());
                plugin.getRequestManager().deny(clanPlayer);
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NothingDeny);
            }
        }
    }
    
    private boolean isValidVote(Player player,Clan clan,ClanPlayer clanPlayer,String nothing) {
        
        if(!clan.isLeader(player)) {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NoLeaderPermission);
            return false;
        }
            
        if (!plugin.getRequestManager().hasRequest(clan.getTag())) {
            ChatBlock.sendMessage(player, ChatColor.RED + nothing);
            return false;
        }
                
        if (clanPlayer.getVote() != null) {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.AlreadyVoted);
            return false;
        }
        
        return true;        
    }
    
    /**
     * Process the more command
     *
     * @param player
     */
    public void processMore(Player player)
    {

        ChatBlock chatBlock = plugin.getStorageManager().getChatBlock(player);

        if (chatBlock != null && chatBlock.size() > 0)
        {
            chatBlock.sendBlock(player, plugin.getSettingsManager().getPageSize());

            if (chatBlock.size() > 0)
            {
                ChatBlock.sendBlank(player);
                ChatBlock.sendMessage(player, plugin.getSettingsManager().getPageHeadingsColor() + MessageFormat.format(SimpleClans.langManager.ViewNextPage, plugin.getSettingsManager().getCommandMore()));
            }
            ChatBlock.sendBlank(player);
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + SimpleClans.langManager.NothingMoreToSee);
        }
    }
}
