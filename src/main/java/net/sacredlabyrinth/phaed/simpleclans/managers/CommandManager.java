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
    private final VitalsCommand vitalsCommand;
    private final CoordsCommand coordsCommand;
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
        vitalsCommand = new VitalsCommand();
        coordsCommand = new CoordsCommand();
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
     * @param args
     * @return
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

                    if (subcommand.equalsIgnoreCase(plugin.getLang("create.command")))
                    {
                        createCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("list.command")))
                    {
                        listCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("profile.command")))
                    {
                        profileCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("roster.command")))
                    {
                        rosterCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("lookup.command")))
                    {
                        lookupCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("home.command")))
                    {
                        homeCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("leaderboard.command")))
                    {
                        leaderboardCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("alliances.command")))
                    {
                        alliancesCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("rivalries.command")))
                    {
                        rivalriesCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("vitals.command")))
                    {
                        vitalsCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("coords.command")))
                    {
                        coordsCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("stats.command")))
                    {
                        statsCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("ally.command")))
                    {
                        allyCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("rival.command")))
                    {
                        rivalCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("bb.command")))
                    {
                        bbCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("modtag.command")))
                    {
                        modtagCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("toggle.command")))
                    {
                        toggleCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("invite.command")))
                    {
                        inviteCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("kick.command")))
                    {
                        kickCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("trust.command")))
                    {
                        trustCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("untrust.command")))
                    {
                        untrustCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("promote.command")))
                    {
                        promoteCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("demote.command")))
                    {
                        demoteCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("clanff.command")))
                    {
                        clanffCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("ff.command")))
                    {
                        ffCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("resign.command")))
                    {
                        resignCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("disband.command")))
                    {
                        disbandCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("reload.command")))
                    {
                        reloadCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("globalff.command")))
                    {
                        globalffCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("war.command")))
                    {
                        warCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("kills.command")))
                    {
                        killsCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("mostkilled.command")))
                    {
                        mostKilledCommand.execute(player, subargs);
                    }
                    else if (subcommand.equalsIgnoreCase(plugin.getLang("setrank.command")))
                    {
                        setRankCommand.execute(player, subargs);
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("does.not.match"));
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

                    if (subcommand.equalsIgnoreCase(plugin.getLang("reload.command")))
                    {
                        reloadCommand.execute(sender, subargs);
                    }
                    else
                    {
                        ChatBlock.sendMessage(sender, ChatColor.RED + plugin.getLang("does.not.match"));
                    }
                }
            }
        }
        catch (Exception ex)
        {
            SimpleClans.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + MessageFormat.format(plugin.getLang("simpleclans.command.failure"), ex.getMessage()));
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

        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

        if (cp != null)
        {
            Clan clan = cp.getClan();

            if (clan.isLeader(player))
            {
                if (plugin.getRequestManager().hasRequest(clan.getTag()))
                {
                    if (cp.getVote() == null)
                    {
                        plugin.getRequestManager().accept(cp);
                        clan.leaderAnnounce(ChatColor.GREEN + MessageFormat.format(plugin.getLang("voted.to.accept"), Helper.capitalize(player.getName())));
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("you.have.already.voted"));
                    }
                }
                else
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("nothing.to.accept"));
                }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("no.leader.permissions"));
            }
        }
        else
        {
            if (plugin.getRequestManager().hasRequest(player.getName().toLowerCase()))
            {
                cp = plugin.getClanManager().getCreateClanPlayer(player.getName());
                plugin.getRequestManager().accept(cp);
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("nothing.to.accept"));
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

        ClanPlayer cp = plugin.getClanManager().getClanPlayer(player);

        if (cp != null)
        {
            Clan clan = cp.getClan();

            if (clan.isLeader(player))
            {
                if (plugin.getRequestManager().hasRequest(clan.getTag()))
                {
                    if (cp.getVote() == null)
                    {
                        plugin.getRequestManager().deny(cp);
                        clan.leaderAnnounce(ChatColor.RED + MessageFormat.format(plugin.getLang("has.voted.to.deny"), Helper.capitalize(player.getName())));
                    }
                    else
                    {
                        ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("you.have.already.voted"));
                    }
                }
                else
                {
                    ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("nothing.to.deny"));
                }
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("no.leader.permissions"));
            }
        }
        else
        {
            if (plugin.getRequestManager().hasRequest(player.getName().toLowerCase()))
            {
                cp = plugin.getClanManager().getCreateClanPlayer(player.getName());
                plugin.getRequestManager().deny(cp);
            }
            else
            {
                ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("nothing.to.deny"));
            }
        }
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
                ChatBlock.sendMessage(player, plugin.getSettingsManager().getPageHeadingsColor() + MessageFormat.format(plugin.getLang("view.next.page"), plugin.getSettingsManager().getCommandMore()));
            }
            ChatBlock.sendBlank(player);
        }
        else
        {
            ChatBlock.sendMessage(player, ChatColor.RED + plugin.getLang("nothing.more.to.see"));
        }
    }
}
