package net.sacredlabyrinth.phaed.simpleclans.managers;

import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sacredlabyrinth.phaed.simpleclans.ConfigAccessor;
import org.bukkit.configuration.InvalidConfigurationException;

/**
 * @author Stutiguias
 */
public final class LangManager
{
   
    private final SimpleClans plugin;
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
    public String noClansHaveBeenCreated;
    public String usageList;
    public String usageLookupTag;
    public String menuPlayerInfo;
    public String menuStatus;
    public String menuClan;
    public String menuKDR;
    public String menuRank;
    public String namesLeader;
    public String menuKillTotals;
    public String menuDeaths;
    public String menuJoinDate;
    public String menuKillType;
    public String menuNoPlayerDataFound;
    public String menuKillTypeCivilian;
    public String menuLastSeen;
    public String menuPastClans;
    public String menuInactive;
    public String thePlayerAlreadyLeader;
    public String youCannotPromoteYourself;
    public String namesAttacker;
    public String namesMostKilled;
    public String createPurchase;
    public String createCreate;
    public String helpList;
    public String usagePromoteMember;
    public String theMemberPromotedMustOnline;
    public String thePlayerNotPermissionsLead;
    public String namesDays;
    public String usageProfiletag;
    public String menuMembersOnline;
    public String namesAllies;
    public String menuFounded;
    public String helpProfileView;
    public String helpProfileTag;
    public String helpLookup;
    public String helpLookupPlayer;
    public String helpLeaderboardView;
    public String helpAlliances;
    public String helpRivalries;
    public String helpRoster;
    public String helpRosterTag;
    public String helpStats;
    public String tagChangedTo;
    public String OnlyModifyColorTag;
    public String usageModtagTag;
    public String helpKills;
    public String helpKillsplayer;
    public String helpAllyAddRemove;
    public String helpRivalAddRemove;
    public String helpHomeMenu;
    public String helpHomeSetMenu;
    public String helpHomeClearMenu;
    public String helpBb;
    public String helpBbMsg;
    public String helpModtagTag;
    public String helpReload;
    public String clanCommands;
    public String helpToggle;
    public String helpInvitePlayer;
    public String helpKickPlayer;
    public String helpTrust;
    public String helpUntrust;
    public String helpPromoteMember;
    public String helpDemoteLeader;
    public String helpSetrank;
    public String helpClanff;
    public String helpFf;
    public String helpResign;
    public String helpDisband;
    public String helpMostkilled;
    public String helpGlobalff;
    public String menuLeaders;
    public String menuName;
    public String menuAllies;
    public String menuRivals;
    public String helpDisbandAdm;
    
    public LangManager()
    {
        plugin = SimpleClans.getInstance();
                
        ConfigAccessor acessor = new ConfigAccessor(plugin,"lang.yml");
        try {
            acessor.setupConfig();
        } catch (IOException ex) {
            Logger.getLogger(LangManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        config = acessor.getConfig();   
        load();
    }

    /**
     * Load the configuration
     */
    @SuppressWarnings("unchecked")
    public void load()
    {
        clanAnnounce = config.getString("clan.announce");
        leaderAnnounce = config.getString("leader.announce");
        bulletinBoardHeader = config.getString("bulletin.board.header");
        clanDisbanded = config.getString("clan.disbanded");
        youAreNoLongerAtWar = config.getString("you.are.no.longer.at.war");
        hasBeenDisbandedRivalryEnded = config.getString("has.been.disbanded.rivalry.ended");
        hasBeenDisbandedAllianceEnded = config.getString("has.been.disbanded.alliance.ended");
        today = config.getString("names.today");
        colorDay = config.getString("color.day");
        manyColorDays = config.getString("many.color.days");
        none = config.getString("names.none");
        serverAnnounce = config.getString("server.announce");
        accountDebited = config.getString("account.has.been.debited");
        notSufficientMoney = config.getString("not.sufficient.money");
        on = config.getString("names.on");
        off = config.getString("names.off");
        join = config.getString("names.join");
        leave = config.getString("names.leave");
        create = config.getString("command.create");
        list = config.getString("command.list");
        profile = config.getString("command.profile");
        roster = config.getString("command.roster");
        lookup = config.getString("command.lookup");
        leaderboard = config.getString("command.leaderboard");
        alliances = config.getString("command.alliances");
        rivalries = config.getString("command.rivalries");
        stats = config.getString("command.stats");
        ally = config.getString("command.ally");
        rival = config.getString("command.rival");
        bb = config.getString("command.bb");
        modtag = config.getString("command.modtag");
        toggle = config.getString("command.toggle");
        invite = config.getString("command.invite");
        kick = config.getString("command.kick");
        trust = config.getString("command.trust");
        untrust = config.getString("command.untrust");
        promote = config.getString("command.promote");
        demote = config.getString("command.demote");
        clanff = config.getString("command.clanff");
        ff = config.getString("command.ff");
        resign = config.getString("command.resign");
        disband = config.getString("command.disband");
        reload = config.getString("command.reload");
        globalff = config.getString("command.globalff");
        kills = config.getString("command.kills");
        mostkilled = config.getString("command.mostkilled");
        war = config.getString("command.war");
        home = config.getString("command.home");
        setrank = config.getString("command.setrank");
        doesNotMatch = config.getString("does.not.match");
        CommandFailure = config.getString("simpleclans.command.failure");
        votedAccept = config.getString("voted.to.accept");
        NothingAccept = config.getString("deny.nothing.to.accept");
        NoLeaderPermission = config.getString("deny.no.leader.permissions");
        AlreadyVoted = config.getString("deny.you.have.already.voted");
        VotedDeny = config.getString("deny.has.voted.to.deny");
        NothingDeny = config.getString("deny.nothing.to.deny");
        ViewNextPage = config.getString("view.next.page");
        NothingMoreToSee = config.getString("nothing.more.to.see");
        AskingDemotion = config.getString("messages.asking.for.the.demotion");
        AskingPromotion = config.getString("messages.asking.for.the.promotion");
        AskingDeletion = config.getString("messages.asking.for.the.deletion");
        invitingJoin = config.getString("messages.inviting.you.to.join");
        proposingWar = config.getString("proposing.war");
        proposingEndWar = config.getString("proposing.to.end.the.war");
        proposingAlliance = config.getString("proposing.an.alliance");
        proposingEndRivalry = config.getString("proposing.to.end.the.rivalry");
        joinedTheClan = config.getString("messages.joined.the.clan");
        hasJoined = config.getString("messages.has.joined");
        membershipInvitation = config.getString("messages.membership.invitation");
        youAtWar = config.getString("you.are.at.war");
        deniedWarReq = config.getString("denied.war.req");
        endWarDenied = config.getString("end.war.denied");
        youAreNoLongerAtWar = config.getString("you.are.no.longer.at.war");
        acceptedAlliance = config.getString("messages.accepted.an.alliance");
        createdAlliance = config.getString("messages.created.an.alliance");
        deniedAlliance = config.getString("messages.denied.an.alliance");
        hasDeniedAlliance = config.getString("messages.the.alliance.was.denied");
        brokenRivalry = config.getString("messages.broken.the.rivalry");
        brokenRivalryWith = config.getString("messages.broken.the.rivalry.with");
        deniedMakePeace = config.getString("messages.denied.to.make.peace");
        peaceAgreementDenied = config.getString("messages.peace.agreement.denied");
        leaders = config.getString("names.leaders");
        demotedBackMember = config.getString("messages.demoted.back.to.member");
        deniedDemotion = config.getString("messages.denied.demotion");
        promotedtoLeader = config.getString("messages.promoted.to.leader");
        deniedPromotion = config.getString("messages.denied.the.promotion");
        hasDisbanded = config.getString("messages.has.been.disbanded");
        clanDeletion = config.getString("messages.clan.deletion");
        signedOffRequestCancelled = config.getString("messages.signed.off.request.cancelled");
        acceptOrDeny = config.getString("messages.accept.or.deny");
        clan = config.getString("names.clan");
        clans = config.getString("admin.clans");
        clanPlayers = config.getString("admin.clan.players");
        purgingClan = config.getString("admin.purging.clan");
        purgingPlayerData = config.getString("admin.purging.player.data");
        waitingTeleportStandStillSeconds = config.getString("waiting.for.teleport.stand.still.for.0.seconds");
        nowHomebase = config.getString("now.at.homebase");
        youMovedTeleportCancelled = config.getString("you.moved.teleport.cancelled");
        youAreWar = config.getString("you.are.at.war");
        playerGotMoney = config.getString("player.got.money");
        allies = config.getString("names.allies");
        insufficientPermissions = config.getString("insufficient.permissions");
        UsageClanAlliances = config.getString("usage.clan.alliances");
        notMemberAnyClan = config.getString("not.a.member.of.any.clan");
        usageAlly = config.getString("usage.ally");
        minimumMakeAlliance = config.getString("minimum.to.make.alliance");
        noClanMatched = config.getString("no.clan.matched");
        add = config.getString("names.add");
        leadersHaveBeenAskedAlliance = config.getString("leaders.have.been.asked.for.an.alliance");
        atLeastOneLeaderAcceptAlliance = config.getString("at.least.one.leader.accept.the.alliance");
        yourClansAreAlreadyAllies = config.getString("your.clans.are.already.allies");
        remove = config.getString("names.remove");
        hasBrokenAlliance = config.getString("has.broken.the.alliance");
        yourClansNotAllies = config.getString("your.clans.are.not.allies");
        clearedBb = config.getString("cleared.bb");
        allow = config.getString("names.allow");
        clanWideFriendlyFireAllowed = config.getString("clan.wide.friendly.fire.is.allowed");
        block = config.getString("names.block");
        clanWideFriendlyFireBlocked = config.getString("clan.wide.friendly.fire.blocked");
        usageClanff = config.getString("usage.clanff");
        clanCreated = config.getString("clan.created");
        clanWithTagAlreadyExists = config.getString("clan.with.this.tag.already.exists");
        youMustFirstResign = config.getString("you.must.first.resign");
        TagNameDisallowed = config.getString("that.tag.name.is.disallowed");
        NameCannotContainColorCodes = config.getString("your.clan.name.cannot.contain.color.codes");
        TagCanOnlyContainLettersNumbersColorCodes = config.getString("your.clan.tag.can.only.contain.letters.numbers.and.color.codes");
        ClanNameNustBeLongerThanCharacters = config.getString("your.clan.name.must.be.longer.than.characters");
        ClanTagCannotBeLongerCharacters = config.getString("your.clan.tag.cannot.be.longer.than.characters");
        ClanTagMustBeLongerThanCharacters = config.getString("your.clan.tag.must.be.longer.than.characters");
        TagCannotContainFollowingColors = config.getString("your.tag.cannot.contain.the.following.colors");
        ClanNameCannotBeLongerThanCharacters = config.getString("your.clan.name.cannot.be.longer.than.characters");
        usageCreateTag = config.getString("usage.create.tag");
        exampleClanCreate = config.getString("help.example.clan.create");
        demotionVoteBeenRequestedFromAllLeaders = config.getString("demotion.vote.has.been.requested.from.all.leaders");
        playerNotLeaderYourClan = config.getString("player.is.not.a.leader.of.your.clan");
        leadersMustOnlineVoteDemotion = config.getString("leaders.must.be.online.to.vote.on.demotion");
        usageDemoteLeader = config.getString("usage.demote.leader");
        clanHasBeenDisbanded = config.getString("clan.has.been.disbanded");
        clanDisbandVoteHasBeenRequestedAllLeaders = config.getString("clan.disband.vote.has.been.requested.from.all.leaders");
        UsageDisband = config.getString("usage.disband");
        auto = config.getString("names.auto");
        personalFriendlyFireSetAllowed = config.getString("personal.friendly.fire.is.set.to.allowed");
        friendyFireNowManagedYourClan = config.getString("friendy.fire.is.now.managed.by.your.clan");
        UsageffAllowAuto = config.getString("usage.ff.allow.auto");
        globalFriendlyFireAlreadyBeingAllowed = config.getString("global.friendly.fire.is.already.being.allowed");
        globalFriendlyFireSetAllowed = config.getString("global.friendly.fire.is.set.to.allowed");
        globalFriendyFireAlreadyManagedEachClan = config.getString("global.friendy.fire.is.already.being.managed.by.each.clan");
        globalFriendyFireNowManagedEachClan = config.getString("global.friendy.fire.is.now.managed.by.each.clan");
        usageGlobalffAllowAuto = config.getString("usage.globalff.allow.auto");
        helpWar = config.getString("help.war");
        youCanOnlyStartWarWithRivals = config.getString("you.can.only.start.war.with.rivals");
        clansNotAtWar = config.getString("clans.not.at.war");
        leadersAskedEndRivalry = config.getString("leaders.asked.to.end.rivalry");
        end = config.getString("names.end");
        clansAlreadyAtWar = config.getString("clans.already.at.war");
        start = config.getString("names.start");
        leadersHaveBeenAskedAcceptWarRequest = config.getString("leaders.have.been.asked.to.accept.the.war.request");
        AtLeastOneLeaderAcceptAlliance = config.getString("at.least.one.leader.accept.the.alliance");
        UsageUntrustPlayer = config.getString("usage.untrust.player");
        NoPlayerMatched = config.getString("no.player.matched");
        YouCannotUntrustYourselft = config.getString("you.cannot.untrust.yourself");
        PlayerNotMemberOfYourClan = config.getString("the.player.is.not.a.member.of.your.clan");
        leadersCannotBeUntrusted = config.getString("leaders.cannot.be.untrusted");
        PlayerAlreadyUntrusted = config.getString("this.player.is.already.untrusted");
        HasBeenGivenUntrustedStatusBy = config.getString("has.been.given.untrusted.status.by");
        usageTrustPlayer = config.getString("usage.trust.player");
        youCannotTrustYourself = config.getString("you.cannot.trust.yourself");
        leadersAreAlreadyTrusted = config.getString("leaders.are.already.trusted");
        PlayerAlreadyTrusted = config.getString("this.player.is.already.trusted");
        hasBeenGivenTrustedStatusBy = config.getString("has.been.given.trusted.status.by");
        bboff = config.getString("bboff");
        bbon = config.getString("bbon");
        tagoff = config.getString("tagoff");
        tagon = config.getString("tagon");
        OnlyTrustedPlayersCanAccessClanStats = config.getString("only.trusted.players.can.access.clan.stats");
        usageStats = config.getString("usage.stats");
        NamesStats = config.getString("names.stats");
        kdr = config.getString("names.kdr");
        killDeathRatio = config.getString("messages.kill.death.ratio");
        weights = config.getString("names.weights");
        NamesRival = config.getString("names.rival");
        neutral = config.getString("names.neutral");
        civilian = config.getString("names.civilian");
        name = config.getString("names.name");
        civilianAbbreviation = config.getString("names.civilianabbreviation");
        NamesDeath = config.getString("names.deaths");
        usageSetRank = config.getString("usage.setrank");
        PlayerRankChanged = config.getString("player.rank.changed");
        usageRosterTag = config.getString("usage.roster.tag");
        NamesRoster = config.getString("names.roster");
        MenuLegend = config.getString("menu.legend");
        trusted = config.getString("names.trusted");
        untrusted = config.getString("names.untrusted");
        player = config.getString("names.player");
        rank = config.getString("names.rank");
        seen = config.getString("names.seen");
        online = config.getString("names.online");
        usageRivalries = config.getString("usage.rivalries");
        NamesRivals = config.getString("names.rivals");
        NamesRivalries = config.getString("names.rivalries");
        NamesWar = config.getString("names.war");
        yourClanCannotCreateRivals = config.getString("your.clan.cannot.create.rivals");
        usageRival = config.getString("usage.rival");
        minPlayersRivalries = config.getString("min.players.rivalries");
        ClanCannotBeRivaled = config.getString("the.clan.cannot.be.rivaled");
        yourClansAreNotRivals = config.getString("your.clans.are.not.rivals");
        hasInitiatedRivalry = config.getString("has.initiated.a.rivalry");
        yourClansAreAlreadyRivals = config.getString("your.clans.are.already.rivals");
        rivalLimitReached = config.getString("rival.limit.reached");
        LastLeaderCannotResignYouMust = config.getString("last.leader.cannot.resign.you.must");
        hasResigned = config.getString("0.has.resigned");
        configReload = config.getString("configuration.reloaded");
        hombaseSet = config.getString("hombase.set");
        hombaseCleared = config.getString("hombase.cleared");
        homeBaseOnlyOnce = config.getString("home.base.only.once");
        hombaseNotSet = config.getString("hombase.not.set");
        theClanDoesNotExist = config.getString("the.clan.does.not.exist");
        hombaseModSet = config.getString("hombase.mod.set");
        usageInvitePlayer = config.getString("usage.invite.player");
        thePlayerDoesnNotHavePermissionsJoin = config.getString("the.player.doesn.t.not.have.the.permissions.to.join.clans");
        youCannotInviteYourself = config.getString("you.cannot.invite.yourself");
        hasBeenAskedToJoin = config.getString("has.been.asked.to.join");
        PlayerAlreadyMemberAnotherClan = config.getString("the.player.is.already.member.of.another.clan");
        usageKickPlayer = config.getString("usage.kick.player");
        youCannotKickYourself = config.getString("you.cannot.kick.yourself");
        youCannotKickAnotherLeader = config.getString("you.cannot.kick.another.leader");
        hasBeenKickedBy = config.getString("has.been.kicked.by");
        namesVictim = config.getString("names.victim");
        namesKillcount = config.getString("names.killcount");
        nokillsfound = config.getString("messages.nokillsfound");
        namesKills = config.getString("names.kills");
        totalClanPlayers = config.getString("total.clan.players.0");
        freeAgent = config.getString("names.free.agent");
        usageLeaderboard = config.getString("usage.leaderboard");
        clansLower = config.getString("clans.lower");
        menuTotalClans = config.getString("menu.total.clans");
        namesMembers = config.getString("names.members");
        noClansHaveBeenCreated = config.getString("no.clans.have.been.created");
        usageList = config.getString("usage.list");
        usageLookupTag = config.getString("usage.lookup.tag");
        menuPlayerInfo = config.getString("menu.player.info");
        menuStatus = config.getString("menu.status");
        menuClan = config.getString("menu.clan");
        menuRank = config.getString("menu.rank");
        menuKDR = config.getString("menu.kdr");
        namesLeader = config.getString("names.leader");
        menuKillTotals = config.getString("menu.kill.totals");
        menuDeaths = config.getString("menu.deaths");
        menuJoinDate = config.getString("menu.join.date");
        menuKillType = config.getString("menu.kill.type");
        menuNoPlayerDataFound = config.getString("menu.no.player.data.found");
        menuKillTypeCivilian = config.getString("menu.kill.type.civilian");
        menuLastSeen = config.getString("menu.last.seen");
        menuPastClans = config.getString("menu.past.clans");
        menuInactive = config.getString("menu.inactive");
        thePlayerAlreadyLeader = config.getString("the.player.is.already.a.leader");
        youCannotPromoteYourself = config.getString("you.cannot.promote.yourself");
        namesAttacker = config.getString("names.attacker");
        namesMostKilled = config.getString("names.mostkilled");
        createPurchase = config.getString("help.createPurchase");
        createCreate = config.getString("help.createCreate");
        helpList = config.getString("help.list");
        usagePromoteMember = config.getString("usage.promoteMember");
        theMemberPromotedMustOnline = config.getString("the.member.to.be.promoted.must.be.online");
        thePlayerNotPermissionsLead = config.getString("the.player.does.not.have.the.permissions.to.lead.a.clan");
        namesDays = config.getString("names.days");
        usageProfiletag = config.getString("usage.profileTag");
        menuMembersOnline = config.getString("menu.members.online");
        namesAllies = config.getString("names.allies");
        menuFounded = config.getString("menu.founded");
        helpProfileView = config.getString("help.profileView");
        helpProfileTag = config.getString("help.profileTag");
        helpLookup = config.getString("help.lookup");
        helpLookupPlayer = config.getString("help.lookupPlayer");
        helpLeaderboardView = config.getString("help.leaderboardView");
        helpAlliances = config.getString("help.alliances");
        helpRivalries = config.getString("help.rivalries");
        helpRoster = config.getString("help.roster");
        helpRosterTag = config.getString("help.rosterTag");
        helpStats = config.getString("help.stats");
        tagChangedTo = config.getString("tag.changed.to");
        OnlyModifyColorTag = config.getString("you.can.only.modify.the.color.and.case.of.the.tag");
        usageModtagTag = config.getString("usage.modtagTag");
        helpKills = config.getString("help.kills");
        helpKillsplayer = config.getString("help.killsplayer");
        helpAllyAddRemove = config.getString("help.allyAddRemove");
        helpRivalAddRemove = config.getString("help.rivalAddRemove");
        helpHomeMenu = config.getString("help.homeMenu");
        helpHomeSetMenu = config.getString("help.homeSetMenu");
        helpHomeClearMenu = config.getString("help.helpHomeClearMenu");
        helpBb = config.getString("help.bb");
        helpBbMsg = config.getString("help.bbMsg");
        helpModtagTag = config.getString("help.modtagTag");
        helpReload = config.getString("help.reload");
        clanCommands = config.getString("clan.commands");
        helpToggle = config.getString("help.toggle");
        helpInvitePlayer = config.getString("help.invitePlayer");
        helpKickPlayer = config.getString("help.kickPlayer");
        helpTrust = config.getString("help.trust");
        helpUntrust = config.getString("help.untrust");
        helpPromoteMember = config.getString("help.promoteMember");
        helpDemoteLeader = config.getString("help.demoteLeader");
        helpSetrank = config.getString("help.setrank");
        helpClanff = config.getString("help.clanff");
        helpFf = config.getString("help.ff");
        helpResign = config.getString("help.resign");
        helpDisband = config.getString("help.disband");
        helpMostkilled = config.getString("help.mostkilled");
        helpGlobalff = config.getString("help.globalff");
        menuLeaders = config.getString("menu.leaders");
        menuName = config.getString("menu.name"); 
        menuAllies = config.getString("menu.allies");
        menuRivals = config.getString("menu.rivals");
        helpDisbandAdm = config.getString("help.disbandAdm");
        
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
