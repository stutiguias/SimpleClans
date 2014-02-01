package net.sacredlabyrinth.phaed.simpleclans.managers;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;

/**
 * @author Stutiguias
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
    public String colorDay;
    public String manyColorDays;
    public String none;
    public String serverAnnounce;
    public String accountDebited;
    public String notSufficientMoney;
    public String on;
    public String off;
    public String join;
    public String leave;
    public String create;
    public String list;
    public String profile;
    public String roster;
    public String lookup;
    public String leaderboard;
    public String alliances;
    public String rivalries;
    public String stats;
    public String ally;
    public String rival;
    public String bb;
    public String modtag;
    public String toggle;
    public String invite;
    public String kick;
    public String trust;
    public String untrust;
    public String promote;
    public String demote;
    public String clanff;
    public String ff;
    public String resign; 
    public String disband;
    public String reload;
    public String globalff;
    public String kills;
    public String mostkilled;
    public String war;
    public String home;
    public String setrank;
    public String doesNotMatch;
    public String CommandFailure;
    public String votedAccept;
    public String NothingAccept;
    public String NoLeaderPermission;
    public String AlreadyVoted;
    public String VotedDeny;
    public String NothingDeny;
    public String ViewNextPage;
    public String NothingMoreToSee;
    public String AskingDemotion;
    public String AskingPromotion;
    public String AskingDeletion;
    public String invitingJoin;
    public String proposingWar;
    public String proposingEndWar;
    public String proposingAlliance;
    public String proposingEndRivalry;
    public String joinedTheClan;
    public String hasJoined;
    public String membershipInvitation;
    public String youAtWar;
    public String deniedWarReq;
    public String endWarDenied;
    public String youNoLongerWar;
    public String acceptedAlliance;
    public String createdAlliance;
    public String deniedAlliance;
    public String hasDeniedAlliance;
    public String brokenRivalry;
    public String brokenRivalryWith;
    public String deniedMakePeace;
    public String peaceAgreementDenied;
    public String leaders;
    public String demotedBackMember;
    public String deniedDemotion;
    public String promotedtoLeader;
    public String deniedPromotion;
    public String hasDisbanded;
    public String clanDeletion;
    public String signedOffRequestCancelled;
    public String acceptOrDeny;
    public String clans;
    public String clanPlayers;
    public String purgingClan;
    public String purgingPlayerData;
    public String waitingTeleportStandStillSeconds;
    public String nowHomebase;
    public String youMovedTeleportCancelled;
    public String youAreWar;
    public String playerGotMoney;
    public String clan;
    public String allies;
    public String insufficientPermissions;
    public String UsageClanAlliances;
    public String notMemberAnyClan;
    public String usageAlly;
    public String minimumMakeAlliance;
    public String noClanMatched;
    public String add;
    public String leadersHaveBeenAskedAlliance;
    public String atLeastOneLeaderAcceptAlliance;
    public String yourClansAreAlreadyAllies;
    public String remove;
    public String hasBrokenAlliance;
    public String yourClansNotAllies;
    public String clearedBb;
    
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
        colorDay = getConfig().getString("color.day");
        manyColorDays = getConfig().getString("many.color.days");
        none = getConfig().getString("none");
        serverAnnounce = getConfig().getString("server.announce");
        accountDebited = getConfig().getString("account.has.been.debited");
        notSufficientMoney = getConfig().getString("not.sufficient.money");
        on = getConfig().getString("names.on");
        off = getConfig().getString("names.off");
        join = getConfig().getString("names.join");
        leave = getConfig().getString("names.leave");
        create = getConfig().getString("command.create");
        list = getConfig().getString("command.list");
        profile = getConfig().getString("command.profile");
        roster = getConfig().getString("command.roster");
        lookup = getConfig().getString("command.lookup");
        leaderboard = getConfig().getString("command.leaderboard");
        alliances = getConfig().getString("command.alliances");
        rivalries = getConfig().getString("command.rivalries");
        stats = getConfig().getString("command.stats");
        ally = getConfig().getString("command.ally");
        rival = getConfig().getString("command.rival");
        bb = getConfig().getString("command.bb");
        modtag = getConfig().getString("command.modtag");
        toggle = getConfig().getString("command.toggle");
        invite = getConfig().getString("command.invite");
        kick = getConfig().getString("command.kick");
        trust = getConfig().getString("command.trust");
        untrust = getConfig().getString("command.untrust");
        promote = getConfig().getString("command.promote");
        demote = getConfig().getString("command.demote");
        clanff = getConfig().getString("command.clanff");
        ff = getConfig().getString("command.ff");
        resign = getConfig().getString("command.resign");
        disband = getConfig().getString("command.disband");
        reload = getConfig().getString("command.reload");
        globalff = getConfig().getString("command.globalff");
        kills = getConfig().getString("command.kills");
        mostkilled = getConfig().getString("command.mostkilled");
        war = getConfig().getString("command.war");
        home = getConfig().getString("command.home");
        setrank = getConfig().getString("command.setrank");
        doesNotMatch = getConfig().getString("does.not.match");
        CommandFailure = getConfig().getString("simpleclans.command.failure");
        votedAccept = getConfig().getString("voted.to.accept");
        NothingAccept = getConfig().getString("deny.nothing.to.accept");
        NoLeaderPermission = getConfig().getString("deny.no.leader.permissions");
        AlreadyVoted = getConfig().getString("deny.you.have.already.voted");
        VotedDeny = getConfig().getString("deny.has.voted.to.deny");
        NothingDeny = getConfig().getString("deny.nothing.to.deny");
        ViewNextPage = getConfig().getString("view.next.page");
        NothingMoreToSee = getConfig().getString("nothing.more.to.see");
        AskingDemotion = getConfig().getString("messages.asking.for.the.demotion");
        AskingPromotion = getConfig().getString("messages.asking.for.the.promotion");
        AskingDeletion = getConfig().getString("messages.asking.for.the.deletion");
        invitingJoin = getConfig().getString("messages.inviting.you.to.join");
        proposingWar = getConfig().getString("proposing.war");
        proposingEndWar = getConfig().getString("proposing.to.end.the.war");
        proposingAlliance = getConfig().getString("proposing.an.alliance");
        proposingEndRivalry = getConfig().getString("proposing.to.end.the.rivalry");
        joinedTheClan = getConfig().getString("messages.joined.the.clan");
        hasJoined = getConfig().getString("messages.has.joined");
        membershipInvitation = getConfig().getString("messages.membership.invitation");
        youAtWar = getConfig().getString("you.are.at.war");
        deniedWarReq = getConfig().getString("denied.war.req");
        endWarDenied = getConfig().getString("end.war.denied");
        youAreNoLongerAtWar = getConfig().getString("you.are.no.longer.at.war");
        acceptedAlliance = getConfig().getString("messages.accepted.an.alliance");
        createdAlliance = getConfig().getString("messages.created.an.alliance");
        deniedAlliance = getConfig().getString("messages.denied.an.alliance");
        hasDeniedAlliance = getConfig().getString("messages.the.alliance.was.denied");
        brokenRivalry = getConfig().getString("messages.broken.the.rivalry");
        brokenRivalryWith = getConfig().getString("messages.broken.the.rivalry.with");
        deniedMakePeace = getConfig().getString("messages.denied.to.make.peace");
        peaceAgreementDenied = getConfig().getString("messages.peace.agreement.denied");
        leaders = getConfig().getString("names.leaders");
        demotedBackMember = getConfig().getString("messages.demoted.back.to.member");
        deniedDemotion = getConfig().getString("messages.denied.demotion");
        promotedtoLeader = getConfig().getString("messages.promoted.to.leader");
        deniedPromotion = getConfig().getString("messages.denied.the.promotion");
        hasDisbanded = getConfig().getString("messages.has.been.disbanded");
        clanDeletion = getConfig().getString("messages.clan.deletion");
        signedOffRequestCancelled = getConfig().getString("messages.signed.off.request.cancelled");
        acceptOrDeny = getConfig().getString("messages.accept.or.deny");
        clan = getConfig().getString("names.clan");
        clans = getConfig().getString("admin.clans");
        clanPlayers = getConfig().getString("admin.clan.players");
        purgingClan = getConfig().getString("admin.purging.clan");
        purgingPlayerData = getConfig().getString("admin.purging.player.data");
        waitingTeleportStandStillSeconds = getConfig().getString("waiting.for.teleport.stand.still.for.0.seconds");
        nowHomebase = getConfig().getString("now.at.homebase");
        youMovedTeleportCancelled = getConfig().getString("you.moved.teleport.cancelled");
        youAreWar = getConfig().getString("you.are.at.war");
        playerGotMoney = getConfig().getString("player.got.money");
        allies = getConfig().getString("names.allies");
        insufficientPermissions = getConfig().getString("insufficient.permissions");
        UsageClanAlliances = getConfig().getString("usage.clan.alliances");
        notMemberAnyClan = getConfig().getString("not.a.member.of.any.clan");
        usageAlly = getConfig().getString("usage.ally");
        minimumMakeAlliance = getConfig().getString("minimum.to.make.alliance");
        noClanMatched = getConfig().getString("no.clan.matched");
        add = getConfig().getString("names.add");
        leadersHaveBeenAskedAlliance = getConfig().getString("leaders.have.been.asked.for.an.alliance");
        atLeastOneLeaderAcceptAlliance = getConfig().getString("at.least.one.leader.accept.the.alliance");
        yourClansAreAlreadyAllies = getConfig().getString("your.clans.are.already.allies");
        remove = getConfig().getString("names.remove");
        hasBrokenAlliance = getConfig().getString("has.broken.the.alliance");
        yourClansNotAllies = getConfig().getString("your.clans.are.not.allies");
        clearedBb = getConfig().getString("cleared.bb");
        
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
