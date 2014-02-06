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
    public String allow;
    public String clanWideFriendlyFireAllowed;
    public String block;
    public String clanWideFriendlyFireBlocked;
    public String usageClanff;
    public String clanCreated;
    public String clanWithTagAlreadyExists;
    public String youMustFirstResign;
    public String TagNameDisallowed;
    public String NameCannotContainColorCodes;
    public String TagCanOnlyContainLettersNumbersColorCodes;
    public String ClanNameNustBeLongerThanCharacters;
    public String ClanTagCannotBeLongerCharacters;
    public String ClanTagMustBeLongerThanCharacters;
    public String TagCannotContainFollowingColors;
    public String ClanNameCannotBeLongerThanCharacters;
    public String usageCreateTag;
    public String exampleClanCreate;
    public String demotionVoteBeenRequestedFromAllLeaders;
    public String playerNotLeaderYourClan;
    public String leadersMustOnlineVoteDemotion;
    public String usageDemoteLeader;
    public String clanHasBeenDisbanded;
    public String clanDisbandVoteHasBeenRequestedAllLeaders;
    public String UsageDisband;
    public String auto;
    public String personalFriendlyFireSetAllowed;
    public String friendyFireNowManagedYourClan;
    public String UsageffAllowAuto;
    public String globalFriendlyFireAlreadyBeingAllowed;
    public String globalFriendlyFireSetAllowed;
    public String globalFriendyFireAlreadyManagedEachClan;
    public String globalFriendyFireNowManagedEachClan;
    public String usageGlobalffAllowAuto;
    public String helpWar;
    public String youCanOnlyStartWarWithRivals;
    public String clansNotAtWar;
    public String leadersAskedEndRivalry;
    public String end;
    public String clansAlreadyAtWar;
    public String start;
    public String leadersHaveBeenAskedAcceptWarRequest;
    public String AtLeastOneLeaderAcceptAlliance;
    public String UsageUntrustPlayer;
    public String NoPlayerMatched;
    public String YouCannotUntrustYourselft;
    public String PlayerNotMemberOfYourClan;
    public String leadersCannotBeUntrusted;
    public String PlayerAlreadyUntrusted;
    public String HasBeenGivenUntrustedStatusBy;
    public String usageTrustPlayer;
    public String youCannotTrustYourself;
    public String leadersAreAlreadyTrusted;
    public String PlayerAlreadyTrusted;
    public String hasBeenGivenTrustedStatusBy;
    public String bboff;
    public String bbon;
    public String tagoff;
    public String tagon;
    public String OnlyTrustedPlayersCanAccessClanStats;
    public String usageStats;
    public String NamesStats;
    public String kdr;
    public String killDeathRatio;
    public String weights;
    public String NamesRival;
    public String neutral;
    public String civilian;
    public String name;
    public String civilianAbbreviation;
    public String NamesDeath;
    public String usageSetRank;
    public String PlayerRankChanged;
    public String usageRosterTag;
    public String NamesRoster;
    public String MenuLegend;
    public String trusted;
    public String untrusted;
    public String player;
    public String rank;
    public String seen;
    public String online;
    public String usageRivalries;
    public String NamesRivals;
    public String NamesRivalries;
    public String NamesWar;
    public String yourClanCannotCreateRivals;
    public String usageRival;
    public String minPlayersRivalries;
    public String ClanCannotBeRivaled;
    public String yourClansAreNotRivals;
    public String hasInitiatedRivalry;
    public String yourClansAreAlreadyRivals;
    public String rivalLimitReached;
    public String LastLeaderCannotResignYouMust;
    public String hasResigned;
    public String configReload;
    public String hombaseSet;
    public String hombaseCleared;
    public String homeBaseOnlyOnce;
    public String hombaseNotSet;
    public String theClanDoesNotExist;
    public String hombaseModSet;
    public String usageInvitePlayer;
    public String thePlayerDoesnNotHavePermissionsJoin;
    public String youCannotInviteYourself;
    public String hasBeenAskedToJoin;
    public String PlayerAlreadyMemberAnotherClan;
    public String usageKickPlayer;
    public String youCannotKickYourself;
    public String youCannotKickAnotherLeader;
    public String hasBeenKickedBy;
    public String namesVictim;
    public String namesKillcount;
    public String nokillsfound;
    public String namesKills;
    public String totalClanPlayers;
    public String freeAgent;
    public String usageLeaderboard;
    public String clansLower;
    public String menuTotalClans;
    public String namesMembers;
    
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
        allow = getConfig().getString("names.allow");
        clanWideFriendlyFireAllowed = getConfig().getString("clan.wide.friendly.fire.is.allowed");
        block = getConfig().getString("names.block");
        clanWideFriendlyFireBlocked = getConfig().getString("clan.wide.friendly.fire.blocked");
        usageClanff = getConfig().getString("usage.clanff");
        clanCreated = getConfig().getString("clan.created");
        clanWithTagAlreadyExists = getConfig().getString("clan.with.this.tag.already.exists");
        youMustFirstResign = getConfig().getString("you.must.first.resign");
        TagNameDisallowed = getConfig().getString("that.tag.name.is.disallowed");
        NameCannotContainColorCodes = getConfig().getString("your.clan.name.cannot.contain.color.codes");
        TagCanOnlyContainLettersNumbersColorCodes = getConfig().getString("your.clan.tag.can.only.contain.letters.numbers.and.color.codes");
        ClanNameNustBeLongerThanCharacters = getConfig().getString("your.clan.name.must.be.longer.than.characters");
        ClanTagCannotBeLongerCharacters = getConfig().getString("your.clan.tag.cannot.be.longer.than.characters");
        ClanTagMustBeLongerThanCharacters = getConfig().getString("your.clan.tag.must.be.longer.than.characters");
        TagCannotContainFollowingColors = getConfig().getString("your.tag.cannot.contain.the.following.colors");
        ClanNameCannotBeLongerThanCharacters = getConfig().getString("your.clan.name.cannot.be.longer.than.characters");
        usageCreateTag = getConfig().getString("usage.create.tag");
        exampleClanCreate = getConfig().getString("help.example.clan.create");
        demotionVoteBeenRequestedFromAllLeaders = getConfig().getString("demotion.vote.has.been.requested.from.all.leaders");
        playerNotLeaderYourClan = getConfig().getString("player.is.not.a.leader.of.your.clan");
        leadersMustOnlineVoteDemotion = getConfig().getString("leaders.must.be.online.to.vote.on.demotion");
        usageDemoteLeader = getConfig().getString("usage.demote.leader");
        clanHasBeenDisbanded = getConfig().getString("clan.has.been.disbanded");
        clanDisbandVoteHasBeenRequestedAllLeaders = getConfig().getString("clan.disband.vote.has.been.requested.from.all.leaders");
        UsageDisband = getConfig().getString("usage.disband");
        auto = getConfig().getString("names.auto");
        personalFriendlyFireSetAllowed = getConfig().getString("personal.friendly.fire.is.set.to.allowed");
        friendyFireNowManagedYourClan = getConfig().getString("friendy.fire.is.now.managed.by.your.clan");
        UsageffAllowAuto = getConfig().getString("usage.ff.allow.auto");
        globalFriendlyFireAlreadyBeingAllowed = getConfig().getString("global.friendly.fire.is.already.being.allowed");
        globalFriendlyFireSetAllowed = getConfig().getString("global.friendly.fire.is.set.to.allowed");
        globalFriendyFireAlreadyManagedEachClan = getConfig().getString("global.friendy.fire.is.already.being.managed.by.each.clan");
        globalFriendyFireNowManagedEachClan = getConfig().getString("global.friendy.fire.is.now.managed.by.each.clan");
        usageGlobalffAllowAuto = getConfig().getString("usage.globalff.allow.auto");
        helpWar = getConfig().getString("help.war");
        youCanOnlyStartWarWithRivals = getConfig().getString("you.can.only.start.war.with.rivals");
        clansNotAtWar = getConfig().getString("clans.not.at.war");
        leadersAskedEndRivalry = getConfig().getString("leaders.asked.to.end.rivalry");
        end = getConfig().getString("names.end");
        clansAlreadyAtWar = getConfig().getString("clans.already.at.war");
        start = getConfig().getString("names.start");
        leadersHaveBeenAskedAcceptWarRequest = getConfig().getString("leaders.have.been.asked.to.accept.the.war.request");
        AtLeastOneLeaderAcceptAlliance = getConfig().getString("at.least.one.leader.accept.the.alliance");
        UsageUntrustPlayer = getConfig().getString("usage.untrust.player");
        NoPlayerMatched = getConfig().getString("no.player.matched");
        YouCannotUntrustYourselft = getConfig().getString("you.cannot.untrust.yourself");
        PlayerNotMemberOfYourClan = getConfig().getString("the.player.is.not.a.member.of.your.clan");
        leadersCannotBeUntrusted = getConfig().getString("leaders.cannot.be.untrusted");
        PlayerAlreadyUntrusted = getConfig().getString("this.player.is.already.untrusted");
        HasBeenGivenUntrustedStatusBy = getConfig().getString("has.been.given.untrusted.status.by");
        usageTrustPlayer = getConfig().getString("usage.trust.player");
        youCannotTrustYourself = getConfig().getString("you.cannot.trust.yourself");
        leadersAreAlreadyTrusted = getConfig().getString("leaders.are.already.trusted");
        PlayerAlreadyTrusted = getConfig().getString("this.player.is.already.trusted");
        hasBeenGivenTrustedStatusBy = getConfig().getString("has.been.given.trusted.status.by");
        bboff = getConfig().getString("bboff");
        bbon = getConfig().getString("bbon");
        tagoff = getConfig().getString("tagoff");
        tagon = getConfig().getString("tagon");
        OnlyTrustedPlayersCanAccessClanStats = getConfig().getString("only.trusted.players.can.access.clan.stats");
        usageStats = getConfig().getString("usage.stats");
        NamesStats = getConfig().getString("names.stats");
        kdr = getConfig().getString("names.kdr");
        killDeathRatio = getConfig().getString("kill.death.ratio");
        weights = getConfig().getString("names.weights");
        NamesRival = getConfig().getString("names.rival");
        neutral = getConfig().getString("names.neutral");
        civilian = getConfig().getString("names.civilian");
        name = getConfig().getString("names.name");
        civilianAbbreviation = getConfig().getString("civilian.abbreviation");
        NamesDeath = getConfig().getString("names.deaths");
        usageSetRank = getConfig().getString("usage.setrank");
        PlayerRankChanged = getConfig().getString("player.rank.changed");
        usageRosterTag = getConfig().getString("usage.roster.tag");
        NamesRoster = getConfig().getString("names.roster");
        MenuLegend = getConfig().getString("menu.legend");
        trusted = getConfig().getString("names.trusted");
        untrusted = getConfig().getString("names.untrusted");
        player = getConfig().getString("names.player");
        rank = getConfig().getString("names.rank");
        seen = getConfig().getString("names.seen");
        online = getConfig().getString("names.online");
        usageRivalries = getConfig().getString("usage.rivalries");
        NamesRivals = getConfig().getString("names.rivals");
        NamesRivalries = getConfig().getString("names.rivalries");
        NamesWar = getConfig().getString("names.war");
        yourClanCannotCreateRivals = getConfig().getString("your.clan.cannot.create.rivals");
        usageRival = getConfig().getString("usage.rival");
        minPlayersRivalries = getConfig().getString("min.players.rivalries");
        ClanCannotBeRivaled = getConfig().getString("the.clan.cannot.be.rivaled");
        yourClansAreNotRivals = getConfig().getString("your.clans.are.not.rivals");
        hasInitiatedRivalry = getConfig().getString("has.initiated.a.rivalry");
        yourClansAreAlreadyRivals = getConfig().getString("your.clans.are.already.rivals");
        rivalLimitReached = getConfig().getString("rival.limit.reached");
        LastLeaderCannotResignYouMust = getConfig().getString("last.leader.cannot.resign.you.must");
        hasResigned = getConfig().getString("0.has.resigned");
        configReload = getConfig().getString("configuration.reloaded");
        hombaseSet = getConfig().getString("hombase.set");
        hombaseCleared = getConfig().getString("hombase.cleared");
        homeBaseOnlyOnce = getConfig().getString("home.base.only.once");
        hombaseNotSet = getConfig().getString("hombase.not.set");
        theClanDoesNotExist = getConfig().getString("the.clan.does.not.exist");
        hombaseModSet = getConfig().getString("hombase.mod.set");
        usageInvitePlayer = getConfig().getString("usage.invite.player");
        thePlayerDoesnNotHavePermissionsJoin = getConfig().getString("the.player.doesn.t.not.have.the.permissions.to.join.clans");
        youCannotInviteYourself = getConfig().getString("you.cannot.invite.yourself");
        hasBeenAskedToJoin = getConfig().getString("has.been.asked.to.join");
        PlayerAlreadyMemberAnotherClan = getConfig().getString("the.player.is.already.member.of.another.clan");
        usageKickPlayer = getConfig().getString("usage.kick.player");
        youCannotKickYourself = getConfig().getString("you.cannot.kick.yourself");
        youCannotKickAnotherLeader = getConfig().getString("you.cannot.kick.another.leader");
        hasBeenKickedBy = getConfig().getString("has.been.kicked.by");
        namesVictim = getConfig().getString("names.victim");
        namesKillcount = getConfig().getString("names.killcount");
        nokillsfound = getConfig().getString("messages.nokillsfound");
        namesKills = getConfig().getString("names.kills");
        totalClanPlayers = getConfig().getString("total.clan.players.0");
        freeAgent = getConfig().getString("names.free.agent");
        usageLeaderboard = getConfig().getString("usage.leaderboard");
        clansLower = getConfig().getString("clans.lower");
        menuTotalClans = getConfig().getString("menu.total.clans");
        namesMembers = getConfig().getString("names.members");
        
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
