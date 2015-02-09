package info.daviot.hearthstats.model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = ActiveAdminComments.ddl ++ Activities.ddl ++ AdminUsers.ddl ++ Annoucements.ddl ++ Announcements.ddl ++ ArenaRunCards.ddl ++ ArenaRuns.ddl ++ Arenas.ddl ++ BlindDraftCards.ddl ++ BlindDrafts.ddl ++ CardMechanics.ddl ++ Cards.ddl ++ CardSets.ddl ++ Comments.ddl ++ Constructeds.ddl ++ Conversations.ddl ++ Decks.ddl ++ DeckVersions.ddl ++ DelayedJobs.ddl ++ Impressions.ddl ++ Klasses.ddl ++ MatchDecks.ddl ++ Matches.ddl ++ MatchRanks.ddl ++ MatchResults.ddl ++ MatchRuns.ddl ++ MatchUniqueDecks.ddl ++ Mechanics.ddl ++ Modes.ddl ++ Notifications.ddl ++ Patches.ddl ++ Profiles.ddl ++ Races.ddl ++ Ranks.ddl ++ Rarities.ddl ++ Receipts.ddl ++ Roles.ddl ++ SchemaMigrations.ddl ++ Seasons.ddl ++ Sessions.ddl ++ ShortenedUrls.ddl ++ Subscriptions.ddl ++ Taggings.ddl ++ Tags.ddl ++ Teams.ddl ++ TeamUsers.ddl ++ Tournaments.ddl ++ TournDecks.ddl ++ Tournies.ddl ++ TournMatches.ddl ++ TournPairs.ddl ++ TournUsers.ddl ++ Types.ddl ++ UniqueDeckCards.ddl ++ UniqueDecks.ddl ++ UniqueDeckTypes.ddl ++ Users.ddl ++ UsersRoles.ddl
  
  /** Entity class storing rows of table ActiveAdminComments
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param resourceId Database column resource_id DBType(VARCHAR), Length(255,true)
   *  @param resourceType Database column resource_type DBType(VARCHAR), Length(255,true)
   *  @param authorId Database column author_id DBType(INT), Default(None)
   *  @param authorType Database column author_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param body Database column body DBType(TEXT), Length(65535,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param namespace Database column namespace DBType(VARCHAR), Length(255,true), Default(None) */
  case class ActiveAdminCommentsRow(id: Int, resourceId: String, resourceType: String, authorId: Option[Int] = None, authorType: Option[String] = None, body: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, namespace: Option[String] = None)
  /** GetResult implicit for fetching ActiveAdminCommentsRow objects using plain SQL queries */
  implicit def GetResultActiveAdminCommentsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]], e3: GR[Option[String]], e4: GR[java.sql.Timestamp]): GR[ActiveAdminCommentsRow] = GR{
    prs => import prs._
    ActiveAdminCommentsRow.tupled((<<[Int], <<[String], <<[String], <<?[Int], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table active_admin_comments. Objects of this class serve as prototypes for rows in queries. */
  class ActiveAdminComments(_tableTag: Tag) extends Table[ActiveAdminCommentsRow](_tableTag, "active_admin_comments") {
    def * = (id, resourceId, resourceType, authorId, authorType, body, createdAt, updatedAt, namespace) <> (ActiveAdminCommentsRow.tupled, ActiveAdminCommentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, resourceId.?, resourceType.?, authorId, authorType, body, createdAt.?, updatedAt.?, namespace).shaped.<>({r=>import r._; _1.map(_=> ActiveAdminCommentsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7.get, _8.get, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column resource_id DBType(VARCHAR), Length(255,true) */
    val resourceId: Column[String] = column[String]("resource_id", O.Length(255,varying=true))
    /** Database column resource_type DBType(VARCHAR), Length(255,true) */
    val resourceType: Column[String] = column[String]("resource_type", O.Length(255,varying=true))
    /** Database column author_id DBType(INT), Default(None) */
    val authorId: Column[Option[Int]] = column[Option[Int]]("author_id", O.Default(None))
    /** Database column author_type DBType(VARCHAR), Length(255,true), Default(None) */
    val authorType: Column[Option[String]] = column[Option[String]]("author_type", O.Length(255,varying=true), O.Default(None))
    /** Database column body DBType(TEXT), Length(65535,true), Default(None) */
    val body: Column[Option[String]] = column[Option[String]]("body", O.Length(65535,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column namespace DBType(VARCHAR), Length(255,true), Default(None) */
    val namespace: Column[Option[String]] = column[Option[String]]("namespace", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (authorType,authorId) (database name index_active_admin_comments_on_author_type_and_author_id) */
    val index1 = index("index_active_admin_comments_on_author_type_and_author_id", (authorType, authorId))
    /** Index over (namespace) (database name index_active_admin_comments_on_namespace) */
    val index2 = index("index_active_admin_comments_on_namespace", namespace)
    /** Index over (resourceType,resourceId) (database name index_admin_notes_on_resource_type_and_resource_id) */
    val index3 = index("index_admin_notes_on_resource_type_and_resource_id", (resourceType, resourceId))
  }
  /** Collection-like TableQuery object for table ActiveAdminComments */
  lazy val ActiveAdminComments = new TableQuery(tag => new ActiveAdminComments(tag))
  
  /** Entity class storing rows of table Activities
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param trackableId Database column trackable_id DBType(INT), Default(None)
   *  @param trackableType Database column trackable_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param ownerId Database column owner_id DBType(INT), Default(None)
   *  @param ownerType Database column owner_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param key Database column key DBType(VARCHAR), Length(255,true), Default(None)
   *  @param parameters Database column parameters DBType(TEXT), Length(65535,true), Default(None)
   *  @param recipientId Database column recipient_id DBType(INT), Default(None)
   *  @param recipientType Database column recipient_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class ActivitiesRow(id: Int, trackableId: Option[Int] = None, trackableType: Option[String] = None, ownerId: Option[Int] = None, ownerType: Option[String] = None, key: Option[String] = None, parameters: Option[String] = None, recipientId: Option[Int] = None, recipientType: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ActivitiesRow objects using plain SQL queries */
  implicit def GetResultActivitiesRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[ActivitiesRow] = GR{
    prs => import prs._
    ActivitiesRow.tupled((<<[Int], <<?[Int], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table activities. Objects of this class serve as prototypes for rows in queries. */
  class Activities(_tableTag: Tag) extends Table[ActivitiesRow](_tableTag, "activities") {
    def * = (id, trackableId, trackableType, ownerId, ownerType, key, parameters, recipientId, recipientType, createdAt, updatedAt) <> (ActivitiesRow.tupled, ActivitiesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, trackableId, trackableType, ownerId, ownerType, key, parameters, recipientId, recipientType, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> ActivitiesRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10.get, _11.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column trackable_id DBType(INT), Default(None) */
    val trackableId: Column[Option[Int]] = column[Option[Int]]("trackable_id", O.Default(None))
    /** Database column trackable_type DBType(VARCHAR), Length(255,true), Default(None) */
    val trackableType: Column[Option[String]] = column[Option[String]]("trackable_type", O.Length(255,varying=true), O.Default(None))
    /** Database column owner_id DBType(INT), Default(None) */
    val ownerId: Column[Option[Int]] = column[Option[Int]]("owner_id", O.Default(None))
    /** Database column owner_type DBType(VARCHAR), Length(255,true), Default(None) */
    val ownerType: Column[Option[String]] = column[Option[String]]("owner_type", O.Length(255,varying=true), O.Default(None))
    /** Database column key DBType(VARCHAR), Length(255,true), Default(None) */
    val key: Column[Option[String]] = column[Option[String]]("key", O.Length(255,varying=true), O.Default(None))
    /** Database column parameters DBType(TEXT), Length(65535,true), Default(None) */
    val parameters: Column[Option[String]] = column[Option[String]]("parameters", O.Length(65535,varying=true), O.Default(None))
    /** Database column recipient_id DBType(INT), Default(None) */
    val recipientId: Column[Option[Int]] = column[Option[Int]]("recipient_id", O.Default(None))
    /** Database column recipient_type DBType(VARCHAR), Length(255,true), Default(None) */
    val recipientType: Column[Option[String]] = column[Option[String]]("recipient_type", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (ownerId,ownerType) (database name index_activities_on_owner_id_and_owner_type) */
    val index1 = index("index_activities_on_owner_id_and_owner_type", (ownerId, ownerType))
    /** Index over (recipientId,recipientType) (database name index_activities_on_recipient_id_and_recipient_type) */
    val index2 = index("index_activities_on_recipient_id_and_recipient_type", (recipientId, recipientType))
    /** Index over (trackableId,trackableType) (database name index_activities_on_trackable_id_and_trackable_type) */
    val index3 = index("index_activities_on_trackable_id_and_trackable_type", (trackableId, trackableType))
  }
  /** Collection-like TableQuery object for table Activities */
  lazy val Activities = new TableQuery(tag => new Activities(tag))
  
  /** Entity class storing rows of table AdminUsers
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param email Database column email DBType(VARCHAR), Length(255,true), Default()
   *  @param encryptedPassword Database column encrypted_password DBType(VARCHAR), Length(255,true), Default()
   *  @param resetPasswordToken Database column reset_password_token DBType(VARCHAR), Length(255,true), Default(None)
   *  @param resetPasswordSentAt Database column reset_password_sent_at DBType(DATETIME), Default(None)
   *  @param rememberCreatedAt Database column remember_created_at DBType(DATETIME), Default(None)
   *  @param signInCount Database column sign_in_count DBType(INT), Default(0)
   *  @param currentSignInAt Database column current_sign_in_at DBType(DATETIME), Default(None)
   *  @param lastSignInAt Database column last_sign_in_at DBType(DATETIME), Default(None)
   *  @param currentSignInIp Database column current_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None)
   *  @param lastSignInIp Database column last_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class AdminUsersRow(id: Int, email: String = "", encryptedPassword: String = "", resetPasswordToken: Option[String] = None, resetPasswordSentAt: Option[java.sql.Timestamp] = None, rememberCreatedAt: Option[java.sql.Timestamp] = None, signInCount: Int = 0, currentSignInAt: Option[java.sql.Timestamp] = None, lastSignInAt: Option[java.sql.Timestamp] = None, currentSignInIp: Option[String] = None, lastSignInIp: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching AdminUsersRow objects using plain SQL queries */
  implicit def GetResultAdminUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]], e4: GR[java.sql.Timestamp]): GR[AdminUsersRow] = GR{
    prs => import prs._
    AdminUsersRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table admin_users. Objects of this class serve as prototypes for rows in queries. */
  class AdminUsers(_tableTag: Tag) extends Table[AdminUsersRow](_tableTag, "admin_users") {
    def * = (id, email, encryptedPassword, resetPasswordToken, resetPasswordSentAt, rememberCreatedAt, signInCount, currentSignInAt, lastSignInAt, currentSignInIp, lastSignInIp, createdAt, updatedAt) <> (AdminUsersRow.tupled, AdminUsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, email.?, encryptedPassword.?, resetPasswordToken, resetPasswordSentAt, rememberCreatedAt, signInCount.?, currentSignInAt, lastSignInAt, currentSignInIp, lastSignInIp, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> AdminUsersRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7.get, _8, _9, _10, _11, _12.get, _13.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email DBType(VARCHAR), Length(255,true), Default() */
    val email: Column[String] = column[String]("email", O.Length(255,varying=true), O.Default(""))
    /** Database column encrypted_password DBType(VARCHAR), Length(255,true), Default() */
    val encryptedPassword: Column[String] = column[String]("encrypted_password", O.Length(255,varying=true), O.Default(""))
    /** Database column reset_password_token DBType(VARCHAR), Length(255,true), Default(None) */
    val resetPasswordToken: Column[Option[String]] = column[Option[String]]("reset_password_token", O.Length(255,varying=true), O.Default(None))
    /** Database column reset_password_sent_at DBType(DATETIME), Default(None) */
    val resetPasswordSentAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("reset_password_sent_at", O.Default(None))
    /** Database column remember_created_at DBType(DATETIME), Default(None) */
    val rememberCreatedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("remember_created_at", O.Default(None))
    /** Database column sign_in_count DBType(INT), Default(0) */
    val signInCount: Column[Int] = column[Int]("sign_in_count", O.Default(0))
    /** Database column current_sign_in_at DBType(DATETIME), Default(None) */
    val currentSignInAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("current_sign_in_at", O.Default(None))
    /** Database column last_sign_in_at DBType(DATETIME), Default(None) */
    val lastSignInAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_sign_in_at", O.Default(None))
    /** Database column current_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None) */
    val currentSignInIp: Column[Option[String]] = column[Option[String]]("current_sign_in_ip", O.Length(255,varying=true), O.Default(None))
    /** Database column last_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None) */
    val lastSignInIp: Column[Option[String]] = column[Option[String]]("last_sign_in_ip", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Uniqueness Index over (email) (database name index_admin_users_on_email) */
    val index1 = index("index_admin_users_on_email", email, unique=true)
    /** Uniqueness Index over (resetPasswordToken) (database name index_admin_users_on_reset_password_token) */
    val index2 = index("index_admin_users_on_reset_password_token", resetPasswordToken, unique=true)
  }
  /** Collection-like TableQuery object for table AdminUsers */
  lazy val AdminUsers = new TableQuery(tag => new AdminUsers(tag))
  
  /** Entity class storing rows of table Annoucements
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param description Database column description DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class AnnoucementsRow(id: Int, description: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching AnnoucementsRow objects using plain SQL queries */
  implicit def GetResultAnnoucementsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[AnnoucementsRow] = GR{
    prs => import prs._
    AnnoucementsRow.tupled((<<[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table annoucements. Objects of this class serve as prototypes for rows in queries. */
  class Annoucements(_tableTag: Tag) extends Table[AnnoucementsRow](_tableTag, "annoucements") {
    def * = (id, description, createdAt, updatedAt) <> (AnnoucementsRow.tupled, AnnoucementsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, description, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> AnnoucementsRow.tupled((_1.get, _2, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column description DBType(VARCHAR), Length(255,true), Default(None) */
    val description: Column[Option[String]] = column[Option[String]]("description", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Annoucements */
  lazy val Annoucements = new TableQuery(tag => new Annoucements(tag))
  
  /** Entity class storing rows of table Announcements
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param body Database column body DBType(TEXT), Length(65535,true), Default(None)
   *  @param heading Database column heading DBType(TEXT), Length(65535,true), Default(None)
   *  @param `type` Database column type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class AnnouncementsRow(id: Int, body: Option[String] = None, heading: Option[String] = None, `type`: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching AnnouncementsRow objects using plain SQL queries */
  implicit def GetResultAnnouncementsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[AnnouncementsRow] = GR{
    prs => import prs._
    AnnouncementsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table announcements. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class Announcements(_tableTag: Tag) extends Table[AnnouncementsRow](_tableTag, "announcements") {
    def * = (id, body, heading, `type`, createdAt, updatedAt) <> (AnnouncementsRow.tupled, AnnouncementsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, body, heading, `type`, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> AnnouncementsRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column body DBType(TEXT), Length(65535,true), Default(None) */
    val body: Column[Option[String]] = column[Option[String]]("body", O.Length(65535,varying=true), O.Default(None))
    /** Database column heading DBType(TEXT), Length(65535,true), Default(None) */
    val heading: Column[Option[String]] = column[Option[String]]("heading", O.Length(65535,varying=true), O.Default(None))
    /** Database column type DBType(VARCHAR), Length(255,true), Default(None)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Column[Option[String]] = column[Option[String]]("type", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Announcements */
  lazy val Announcements = new TableQuery(tag => new Announcements(tag))
  
  /** Entity class storing rows of table ArenaRunCards
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param arenaRunId Database column arena_run_id DBType(INT), Default(None)
   *  @param cardId Database column card_id DBType(INT), Default(None)
   *  @param golden Database column golden DBType(BIT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class ArenaRunCardsRow(id: Int, arenaRunId: Option[Int] = None, cardId: Option[Int] = None, golden: Option[Boolean] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ArenaRunCardsRow objects using plain SQL queries */
  implicit def GetResultArenaRunCardsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[Boolean]], e3: GR[java.sql.Timestamp]): GR[ArenaRunCardsRow] = GR{
    prs => import prs._
    ArenaRunCardsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Boolean], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table arena_run_cards. Objects of this class serve as prototypes for rows in queries. */
  class ArenaRunCards(_tableTag: Tag) extends Table[ArenaRunCardsRow](_tableTag, "arena_run_cards") {
    def * = (id, arenaRunId, cardId, golden, createdAt, updatedAt) <> (ArenaRunCardsRow.tupled, ArenaRunCardsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, arenaRunId, cardId, golden, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> ArenaRunCardsRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column arena_run_id DBType(INT), Default(None) */
    val arenaRunId: Column[Option[Int]] = column[Option[Int]]("arena_run_id", O.Default(None))
    /** Database column card_id DBType(INT), Default(None) */
    val cardId: Column[Option[Int]] = column[Option[Int]]("card_id", O.Default(None))
    /** Database column golden DBType(BIT), Default(None) */
    val golden: Column[Option[Boolean]] = column[Option[Boolean]]("golden", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (arenaRunId) (database name index_arena_run_cards_on_arena_run_id) */
    val index1 = index("index_arena_run_cards_on_arena_run_id", arenaRunId)
    /** Index over (cardId) (database name index_arena_run_cards_on_card_id) */
    val index2 = index("index_arena_run_cards_on_card_id", cardId)
  }
  /** Collection-like TableQuery object for table ArenaRunCards */
  lazy val ArenaRunCards = new TableQuery(tag => new ArenaRunCards(tag))
  
  /** Entity class storing rows of table ArenaRuns
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param gold Database column gold DBType(INT), Default(Some(0))
   *  @param dust Database column dust DBType(INT), Default(Some(0))
   *  @param complete Database column complete DBType(BIT), Default(Some(false))
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param notes Database column notes DBType(TEXT), Length(65535,true), Default(None)
   *  @param patch Database column patch DBType(VARCHAR), Length(255,true), Default(Some(current))
   *  @param klassId Database column klass_id DBType(INT), Default(None)
   *  @param deckId Database column deck_id DBType(INT), Default(None) */
  case class ArenaRunsRow(id: Int, userId: Option[Int] = None, gold: Option[Int] = Some(0), dust: Option[Int] = Some(0), complete: Option[Boolean] = Some(false), createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, notes: Option[String] = None, patch: Option[String] = Some("current"), klassId: Option[Int] = None, deckId: Option[Int] = None)
  /** GetResult implicit for fetching ArenaRunsRow objects using plain SQL queries */
  implicit def GetResultArenaRunsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[Boolean]], e3: GR[java.sql.Timestamp], e4: GR[Option[String]]): GR[ArenaRunsRow] = GR{
    prs => import prs._
    ArenaRunsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Boolean], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[Int], <<?[Int]))
  }
  /** Table description of table arena_runs. Objects of this class serve as prototypes for rows in queries. */
  class ArenaRuns(_tableTag: Tag) extends Table[ArenaRunsRow](_tableTag, "arena_runs") {
    def * = (id, userId, gold, dust, complete, createdAt, updatedAt, notes, patch, klassId, deckId) <> (ArenaRunsRow.tupled, ArenaRunsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, userId, gold, dust, complete, createdAt.?, updatedAt.?, notes, patch, klassId, deckId).shaped.<>({r=>import r._; _1.map(_=> ArenaRunsRow.tupled((_1.get, _2, _3, _4, _5, _6.get, _7.get, _8, _9, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column gold DBType(INT), Default(Some(0)) */
    val gold: Column[Option[Int]] = column[Option[Int]]("gold", O.Default(Some(0)))
    /** Database column dust DBType(INT), Default(Some(0)) */
    val dust: Column[Option[Int]] = column[Option[Int]]("dust", O.Default(Some(0)))
    /** Database column complete DBType(BIT), Default(Some(false)) */
    val complete: Column[Option[Boolean]] = column[Option[Boolean]]("complete", O.Default(Some(false)))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column notes DBType(TEXT), Length(65535,true), Default(None) */
    val notes: Column[Option[String]] = column[Option[String]]("notes", O.Length(65535,varying=true), O.Default(None))
    /** Database column patch DBType(VARCHAR), Length(255,true), Default(Some(current)) */
    val patch: Column[Option[String]] = column[Option[String]]("patch", O.Length(255,varying=true), O.Default(Some("current")))
    /** Database column klass_id DBType(INT), Default(None) */
    val klassId: Column[Option[Int]] = column[Option[Int]]("klass_id", O.Default(None))
    /** Database column deck_id DBType(INT), Default(None) */
    val deckId: Column[Option[Int]] = column[Option[Int]]("deck_id", O.Default(None))
    
    /** Index over (deckId) (database name index_arena_runs_on_deck_id) */
    val index1 = index("index_arena_runs_on_deck_id", deckId)
    /** Index over (userId) (database name index_arena_runs_on_user_id) */
    val index2 = index("index_arena_runs_on_user_id", userId)
  }
  /** Collection-like TableQuery object for table ArenaRuns */
  lazy val ArenaRuns = new TableQuery(tag => new ArenaRuns(tag))
  
  /** Entity class storing rows of table Arenas
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param userclass Database column userclass DBType(VARCHAR), Length(255,true), Default(Some(N/A))
   *  @param oppclass Database column oppclass DBType(VARCHAR), Length(255,true), Default(Some(N/A))
   *  @param win Database column win DBType(BIT), Default(Some(false))
   *  @param gofirst Database column gofirst DBType(BIT), Default(Some(true))
   *  @param arenaRunId Database column arena_run_id DBType(INT), Default(None)
   *  @param notes Database column notes DBType(TEXT), Length(65535,true), Default(None)
   *  @param oppname Database column oppname DBType(VARCHAR), Length(255,true), Default(None) */
  case class ArenasRow(id: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, userId: Option[Int] = None, userclass: Option[String] = Some("N/A"), oppclass: Option[String] = Some("N/A"), win: Option[Boolean] = Some(false), gofirst: Option[Boolean] = Some(true), arenaRunId: Option[Int] = None, notes: Option[String] = None, oppname: Option[String] = None)
  /** GetResult implicit for fetching ArenasRow objects using plain SQL queries */
  implicit def GetResultArenasRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Option[Int]], e3: GR[Option[String]], e4: GR[Option[Boolean]]): GR[ArenasRow] = GR{
    prs => import prs._
    ArenasRow.tupled((<<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[String], <<?[String], <<?[Boolean], <<?[Boolean], <<?[Int], <<?[String], <<?[String]))
  }
  /** Table description of table arenas. Objects of this class serve as prototypes for rows in queries. */
  class Arenas(_tableTag: Tag) extends Table[ArenasRow](_tableTag, "arenas") {
    def * = (id, createdAt, updatedAt, userId, userclass, oppclass, win, gofirst, arenaRunId, notes, oppname) <> (ArenasRow.tupled, ArenasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, createdAt.?, updatedAt.?, userId, userclass, oppclass, win, gofirst, arenaRunId, notes, oppname).shaped.<>({r=>import r._; _1.map(_=> ArenasRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8, _9, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column userclass DBType(VARCHAR), Length(255,true), Default(Some(N/A)) */
    val userclass: Column[Option[String]] = column[Option[String]]("userclass", O.Length(255,varying=true), O.Default(Some("N/A")))
    /** Database column oppclass DBType(VARCHAR), Length(255,true), Default(Some(N/A)) */
    val oppclass: Column[Option[String]] = column[Option[String]]("oppclass", O.Length(255,varying=true), O.Default(Some("N/A")))
    /** Database column win DBType(BIT), Default(Some(false)) */
    val win: Column[Option[Boolean]] = column[Option[Boolean]]("win", O.Default(Some(false)))
    /** Database column gofirst DBType(BIT), Default(Some(true)) */
    val gofirst: Column[Option[Boolean]] = column[Option[Boolean]]("gofirst", O.Default(Some(true)))
    /** Database column arena_run_id DBType(INT), Default(None) */
    val arenaRunId: Column[Option[Int]] = column[Option[Int]]("arena_run_id", O.Default(None))
    /** Database column notes DBType(TEXT), Length(65535,true), Default(None) */
    val notes: Column[Option[String]] = column[Option[String]]("notes", O.Length(65535,varying=true), O.Default(None))
    /** Database column oppname DBType(VARCHAR), Length(255,true), Default(None) */
    val oppname: Column[Option[String]] = column[Option[String]]("oppname", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (arenaRunId) (database name index_arenas_on_arena_run_id) */
    val index1 = index("index_arenas_on_arena_run_id", arenaRunId)
    /** Index over (userId) (database name index_arenas_on_user_id) */
    val index2 = index("index_arenas_on_user_id", userId)
  }
  /** Collection-like TableQuery object for table Arenas */
  lazy val Arenas = new TableQuery(tag => new Arenas(tag))
  
  /** Entity class storing rows of table BlindDraftCards
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param cardId Database column card_id DBType(INT), Default(None)
   *  @param blindDraftId Database column blind_draft_id DBType(INT), Default(None)
   *  @param revealed Database column revealed DBType(BIT), Default(Some(false))
   *  @param createdAt Database column created_at DBType(DATETIME), Default(None)
   *  @param updatedAt Database column updated_at DBType(DATETIME), Default(None) */
  case class BlindDraftCardsRow(id: Int, userId: Option[Int] = None, cardId: Option[Int] = None, blindDraftId: Option[Int] = None, revealed: Option[Boolean] = Some(false), createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching BlindDraftCardsRow objects using plain SQL queries */
  implicit def GetResultBlindDraftCardsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[Boolean]], e3: GR[Option[java.sql.Timestamp]]): GR[BlindDraftCardsRow] = GR{
    prs => import prs._
    BlindDraftCardsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Boolean], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table blind_draft_cards. Objects of this class serve as prototypes for rows in queries. */
  class BlindDraftCards(_tableTag: Tag) extends Table[BlindDraftCardsRow](_tableTag, "blind_draft_cards") {
    def * = (id, userId, cardId, blindDraftId, revealed, createdAt, updatedAt) <> (BlindDraftCardsRow.tupled, BlindDraftCardsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, userId, cardId, blindDraftId, revealed, createdAt, updatedAt).shaped.<>({r=>import r._; _1.map(_=> BlindDraftCardsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column card_id DBType(INT), Default(None) */
    val cardId: Column[Option[Int]] = column[Option[Int]]("card_id", O.Default(None))
    /** Database column blind_draft_id DBType(INT), Default(None) */
    val blindDraftId: Column[Option[Int]] = column[Option[Int]]("blind_draft_id", O.Default(None))
    /** Database column revealed DBType(BIT), Default(Some(false)) */
    val revealed: Column[Option[Boolean]] = column[Option[Boolean]]("revealed", O.Default(Some(false)))
    /** Database column created_at DBType(DATETIME), Default(None) */
    val createdAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at DBType(DATETIME), Default(None) */
    val updatedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))
  }
  /** Collection-like TableQuery object for table BlindDraftCards */
  lazy val BlindDraftCards = new TableQuery(tag => new BlindDraftCards(tag))
  
  /** Entity class storing rows of table BlindDrafts
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param cardstring Database column cardstring DBType(VARCHAR), Length(255,true), Default(None)
   *  @param player1Id Database column player1_id DBType(INT), Default(None)
   *  @param player2Id Database column player2_id DBType(INT), Default(None)
   *  @param cardCap Database column card_cap DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME), Default(None)
   *  @param updatedAt Database column updated_at DBType(DATETIME), Default(None)
   *  @param klassString Database column klass_string DBType(VARCHAR), Length(255,true), Default(None)
   *  @param public Database column public DBType(BIT), Default(Some(false))
   *  @param complete Database column complete DBType(BIT), Default(Some(false)) */
  case class BlindDraftsRow(id: Int, cardstring: Option[String] = None, player1Id: Option[Int] = None, player2Id: Option[Int] = None, cardCap: Option[Int] = None, createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None, klassString: Option[String] = None, public: Option[Boolean] = Some(false), complete: Option[Boolean] = Some(false))
  /** GetResult implicit for fetching BlindDraftsRow objects using plain SQL queries */
  implicit def GetResultBlindDraftsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[java.sql.Timestamp]], e4: GR[Option[Boolean]]): GR[BlindDraftsRow] = GR{
    prs => import prs._
    BlindDraftsRow.tupled((<<[Int], <<?[String], <<?[Int], <<?[Int], <<?[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String], <<?[Boolean], <<?[Boolean]))
  }
  /** Table description of table blind_drafts. Objects of this class serve as prototypes for rows in queries. */
  class BlindDrafts(_tableTag: Tag) extends Table[BlindDraftsRow](_tableTag, "blind_drafts") {
    def * = (id, cardstring, player1Id, player2Id, cardCap, createdAt, updatedAt, klassString, public, complete) <> (BlindDraftsRow.tupled, BlindDraftsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, cardstring, player1Id, player2Id, cardCap, createdAt, updatedAt, klassString, public, complete).shaped.<>({r=>import r._; _1.map(_=> BlindDraftsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column cardstring DBType(VARCHAR), Length(255,true), Default(None) */
    val cardstring: Column[Option[String]] = column[Option[String]]("cardstring", O.Length(255,varying=true), O.Default(None))
    /** Database column player1_id DBType(INT), Default(None) */
    val player1Id: Column[Option[Int]] = column[Option[Int]]("player1_id", O.Default(None))
    /** Database column player2_id DBType(INT), Default(None) */
    val player2Id: Column[Option[Int]] = column[Option[Int]]("player2_id", O.Default(None))
    /** Database column card_cap DBType(INT), Default(None) */
    val cardCap: Column[Option[Int]] = column[Option[Int]]("card_cap", O.Default(None))
    /** Database column created_at DBType(DATETIME), Default(None) */
    val createdAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at DBType(DATETIME), Default(None) */
    val updatedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))
    /** Database column klass_string DBType(VARCHAR), Length(255,true), Default(None) */
    val klassString: Column[Option[String]] = column[Option[String]]("klass_string", O.Length(255,varying=true), O.Default(None))
    /** Database column public DBType(BIT), Default(Some(false)) */
    val public: Column[Option[Boolean]] = column[Option[Boolean]]("public", O.Default(Some(false)))
    /** Database column complete DBType(BIT), Default(Some(false)) */
    val complete: Column[Option[Boolean]] = column[Option[Boolean]]("complete", O.Default(Some(false)))
  }
  /** Collection-like TableQuery object for table BlindDrafts */
  lazy val BlindDrafts = new TableQuery(tag => new BlindDrafts(tag))
  
  /** Entity class storing rows of table CardMechanics
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param cardId Database column card_id DBType(INT), Default(None)
   *  @param mechanicId Database column mechanic_id DBType(INT), Default(None) */
  case class CardMechanicsRow(id: Int, cardId: Option[Int] = None, mechanicId: Option[Int] = None)
  /** GetResult implicit for fetching CardMechanicsRow objects using plain SQL queries */
  implicit def GetResultCardMechanicsRow(implicit e0: GR[Int], e1: GR[Option[Int]]): GR[CardMechanicsRow] = GR{
    prs => import prs._
    CardMechanicsRow.tupled((<<[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table card_mechanics. Objects of this class serve as prototypes for rows in queries. */
  class CardMechanics(_tableTag: Tag) extends Table[CardMechanicsRow](_tableTag, "card_mechanics") {
    def * = (id, cardId, mechanicId) <> (CardMechanicsRow.tupled, CardMechanicsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, cardId, mechanicId).shaped.<>({r=>import r._; _1.map(_=> CardMechanicsRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column card_id DBType(INT), Default(None) */
    val cardId: Column[Option[Int]] = column[Option[Int]]("card_id", O.Default(None))
    /** Database column mechanic_id DBType(INT), Default(None) */
    val mechanicId: Column[Option[Int]] = column[Option[Int]]("mechanic_id", O.Default(None))
  }
  /** Collection-like TableQuery object for table CardMechanics */
  lazy val CardMechanics = new TableQuery(tag => new CardMechanics(tag))
  
  /** Entity class storing rows of table Cards
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param description Database column description DBType(VARCHAR), Length(255,true), Default(None)
   *  @param attack Database column attack DBType(INT), Default(None)
   *  @param health Database column health DBType(INT), Default(None)
   *  @param rarityId Database column rarity_id DBType(INT), Default(None)
   *  @param klassId Database column klass_id DBType(INT), Default(None)
   *  @param mana Database column mana DBType(INT), Default(None)
   *  @param collectible Database column collectible DBType(BIT), Default(None)
   *  @param patchId Database column patch_id DBType(INT), Default(None)
   *  @param blizzId Database column blizz_id DBType(VARCHAR), Length(255,true), Default(None)
   *  @param cardSet Database column card_set DBType(VARCHAR), Length(255,true), Default(None)
   *  @param typeName Database column type_name DBType(VARCHAR), Length(255,true), Default(None) */
  case class CardsRow(id: Int, name: Option[String] = None, description: Option[String] = None, attack: Option[Int] = None, health: Option[Int] = None, rarityId: Option[Int] = None, klassId: Option[Int] = None, mana: Option[Int] = None, collectible: Option[Boolean] = None, patchId: Option[Int] = None, blizzId: Option[String] = None, cardSet: Option[String] = None, typeName: Option[String] = None)
  /** GetResult implicit for fetching CardsRow objects using plain SQL queries */
  implicit def GetResultCardsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[Boolean]]): GR[CardsRow] = GR{
    prs => import prs._
    CardsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Boolean], <<?[Int], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table cards. Objects of this class serve as prototypes for rows in queries. */
  class Cards(_tableTag: Tag) extends Table[CardsRow](_tableTag, "cards") {
    def * = (id, name, description, attack, health, rarityId, klassId, mana, collectible, patchId, blizzId, cardSet, typeName) <> (CardsRow.tupled, CardsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, description, attack, health, rarityId, klassId, mana, collectible, patchId, blizzId, cardSet, typeName).shaped.<>({r=>import r._; _1.map(_=> CardsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column description DBType(VARCHAR), Length(255,true), Default(None) */
    val description: Column[Option[String]] = column[Option[String]]("description", O.Length(255,varying=true), O.Default(None))
    /** Database column attack DBType(INT), Default(None) */
    val attack: Column[Option[Int]] = column[Option[Int]]("attack", O.Default(None))
    /** Database column health DBType(INT), Default(None) */
    val health: Column[Option[Int]] = column[Option[Int]]("health", O.Default(None))
    /** Database column rarity_id DBType(INT), Default(None) */
    val rarityId: Column[Option[Int]] = column[Option[Int]]("rarity_id", O.Default(None))
    /** Database column klass_id DBType(INT), Default(None) */
    val klassId: Column[Option[Int]] = column[Option[Int]]("klass_id", O.Default(None))
    /** Database column mana DBType(INT), Default(None) */
    val mana: Column[Option[Int]] = column[Option[Int]]("mana", O.Default(None))
    /** Database column collectible DBType(BIT), Default(None) */
    val collectible: Column[Option[Boolean]] = column[Option[Boolean]]("collectible", O.Default(None))
    /** Database column patch_id DBType(INT), Default(None) */
    val patchId: Column[Option[Int]] = column[Option[Int]]("patch_id", O.Default(None))
    /** Database column blizz_id DBType(VARCHAR), Length(255,true), Default(None) */
    val blizzId: Column[Option[String]] = column[Option[String]]("blizz_id", O.Length(255,varying=true), O.Default(None))
    /** Database column card_set DBType(VARCHAR), Length(255,true), Default(None) */
    val cardSet: Column[Option[String]] = column[Option[String]]("card_set", O.Length(255,varying=true), O.Default(None))
    /** Database column type_name DBType(VARCHAR), Length(255,true), Default(None) */
    val typeName: Column[Option[String]] = column[Option[String]]("type_name", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (blizzId) (database name index_cards_on_blizz_id) */
    val index1 = index("index_cards_on_blizz_id", blizzId)
    /** Index over (name) (database name index_cards_on_name) */
    val index2 = index("index_cards_on_name", name)
  }
  /** Collection-like TableQuery object for table Cards */
  lazy val Cards = new TableQuery(tag => new Cards(tag))
  
  /** Entity class storing rows of table CardSets
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param notes Database column notes DBType(TEXT), Length(65535,true), Default(None) */
  case class CardSetsRow(id: Int, name: Option[String] = None, notes: Option[String] = None)
  /** GetResult implicit for fetching CardSetsRow objects using plain SQL queries */
  implicit def GetResultCardSetsRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[CardSetsRow] = GR{
    prs => import prs._
    CardSetsRow.tupled((<<[Int], <<?[String], <<?[String]))
  }
  /** Table description of table card_sets. Objects of this class serve as prototypes for rows in queries. */
  class CardSets(_tableTag: Tag) extends Table[CardSetsRow](_tableTag, "card_sets") {
    def * = (id, name, notes) <> (CardSetsRow.tupled, CardSetsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, notes).shaped.<>({r=>import r._; _1.map(_=> CardSetsRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column notes DBType(TEXT), Length(65535,true), Default(None) */
    val notes: Column[Option[String]] = column[Option[String]]("notes", O.Length(65535,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table CardSets */
  lazy val CardSets = new TableQuery(tag => new CardSets(tag))
  
  /** Entity class storing rows of table Comments
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param ownerId Database column owner_id DBType(INT)
   *  @param commentableId Database column commentable_id DBType(INT)
   *  @param commentableType Database column commentable_type DBType(VARCHAR), Length(255,true)
   *  @param body Database column body DBType(TEXT), Length(65535,true)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class CommentsRow(id: Int, ownerId: Int, commentableId: Int, commentableType: String, body: String, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching CommentsRow objects using plain SQL queries */
  implicit def GetResultCommentsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[CommentsRow] = GR{
    prs => import prs._
    CommentsRow.tupled((<<[Int], <<[Int], <<[Int], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table comments. Objects of this class serve as prototypes for rows in queries. */
  class Comments(_tableTag: Tag) extends Table[CommentsRow](_tableTag, "comments") {
    def * = (id, ownerId, commentableId, commentableType, body, createdAt, updatedAt) <> (CommentsRow.tupled, CommentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, ownerId.?, commentableId.?, commentableType.?, body.?, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> CommentsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column owner_id DBType(INT) */
    val ownerId: Column[Int] = column[Int]("owner_id")
    /** Database column commentable_id DBType(INT) */
    val commentableId: Column[Int] = column[Int]("commentable_id")
    /** Database column commentable_type DBType(VARCHAR), Length(255,true) */
    val commentableType: Column[String] = column[String]("commentable_type", O.Length(255,varying=true))
    /** Database column body DBType(TEXT), Length(65535,true) */
    val body: Column[String] = column[String]("body", O.Length(65535,varying=true))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Comments */
  lazy val Comments = new TableQuery(tag => new Comments(tag))
  
  /** Entity class storing rows of table Constructeds
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param deckname Database column deckname DBType(VARCHAR), Length(255,true), Default(None)
   *  @param oppclass Database column oppclass DBType(VARCHAR), Length(255,true), Default(Some(N/A))
   *  @param win Database column win DBType(BIT), Default(Some(false))
   *  @param gofirst Database column gofirst DBType(BIT), Default(Some(true))
   *  @param deckId Database column deck_id DBType(INT), Default(None)
   *  @param notes Database column notes DBType(TEXT), Length(65535,true), Default(None)
   *  @param rank Database column rank DBType(VARCHAR), Length(255,true), Default(Some(Casual))
   *  @param patch Database column patch DBType(VARCHAR), Length(255,true), Default(Some(current))
   *  @param oppname Database column oppname DBType(VARCHAR), Length(255,true), Default(None)
   *  @param ranklvl Database column ranklvl DBType(INT), Default(None)
   *  @param legendary Database column legendary DBType(INT), Default(None) */
  case class ConstructedsRow(id: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, userId: Option[Int] = None, deckname: Option[String] = None, oppclass: Option[String] = Some("N/A"), win: Option[Boolean] = Some(false), gofirst: Option[Boolean] = Some(true), deckId: Option[Int] = None, notes: Option[String] = None, rank: Option[String] = Some("Casual"), patch: Option[String] = Some("current"), oppname: Option[String] = None, ranklvl: Option[Int] = None, legendary: Option[Int] = None)
  /** GetResult implicit for fetching ConstructedsRow objects using plain SQL queries */
  implicit def GetResultConstructedsRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Option[Int]], e3: GR[Option[String]], e4: GR[Option[Boolean]]): GR[ConstructedsRow] = GR{
    prs => import prs._
    ConstructedsRow.tupled((<<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[String], <<?[String], <<?[Boolean], <<?[Boolean], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[Int]))
  }
  /** Table description of table constructeds. Objects of this class serve as prototypes for rows in queries. */
  class Constructeds(_tableTag: Tag) extends Table[ConstructedsRow](_tableTag, "constructeds") {
    def * = (id, createdAt, updatedAt, userId, deckname, oppclass, win, gofirst, deckId, notes, rank, patch, oppname, ranklvl, legendary) <> (ConstructedsRow.tupled, ConstructedsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, createdAt.?, updatedAt.?, userId, deckname, oppclass, win, gofirst, deckId, notes, rank, patch, oppname, ranklvl, legendary).shaped.<>({r=>import r._; _1.map(_=> ConstructedsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column deckname DBType(VARCHAR), Length(255,true), Default(None) */
    val deckname: Column[Option[String]] = column[Option[String]]("deckname", O.Length(255,varying=true), O.Default(None))
    /** Database column oppclass DBType(VARCHAR), Length(255,true), Default(Some(N/A)) */
    val oppclass: Column[Option[String]] = column[Option[String]]("oppclass", O.Length(255,varying=true), O.Default(Some("N/A")))
    /** Database column win DBType(BIT), Default(Some(false)) */
    val win: Column[Option[Boolean]] = column[Option[Boolean]]("win", O.Default(Some(false)))
    /** Database column gofirst DBType(BIT), Default(Some(true)) */
    val gofirst: Column[Option[Boolean]] = column[Option[Boolean]]("gofirst", O.Default(Some(true)))
    /** Database column deck_id DBType(INT), Default(None) */
    val deckId: Column[Option[Int]] = column[Option[Int]]("deck_id", O.Default(None))
    /** Database column notes DBType(TEXT), Length(65535,true), Default(None) */
    val notes: Column[Option[String]] = column[Option[String]]("notes", O.Length(65535,varying=true), O.Default(None))
    /** Database column rank DBType(VARCHAR), Length(255,true), Default(Some(Casual)) */
    val rank: Column[Option[String]] = column[Option[String]]("rank", O.Length(255,varying=true), O.Default(Some("Casual")))
    /** Database column patch DBType(VARCHAR), Length(255,true), Default(Some(current)) */
    val patch: Column[Option[String]] = column[Option[String]]("patch", O.Length(255,varying=true), O.Default(Some("current")))
    /** Database column oppname DBType(VARCHAR), Length(255,true), Default(None) */
    val oppname: Column[Option[String]] = column[Option[String]]("oppname", O.Length(255,varying=true), O.Default(None))
    /** Database column ranklvl DBType(INT), Default(None) */
    val ranklvl: Column[Option[Int]] = column[Option[Int]]("ranklvl", O.Default(None))
    /** Database column legendary DBType(INT), Default(None) */
    val legendary: Column[Option[Int]] = column[Option[Int]]("legendary", O.Default(None))
    
    /** Index over (deckId) (database name index_constructeds_on_deck_id) */
    val index1 = index("index_constructeds_on_deck_id", deckId)
    /** Index over (userId) (database name index_constructeds_on_user_id) */
    val index2 = index("index_constructeds_on_user_id", userId)
  }
  /** Collection-like TableQuery object for table Constructeds */
  lazy val Constructeds = new TableQuery(tag => new Constructeds(tag))
  
  /** Entity class storing rows of table Conversations
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param subject Database column subject DBType(VARCHAR), Length(255,true), Default(Some())
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class ConversationsRow(id: Int, subject: Option[String] = Some(""), createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ConversationsRow objects using plain SQL queries */
  implicit def GetResultConversationsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[ConversationsRow] = GR{
    prs => import prs._
    ConversationsRow.tupled((<<[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table conversations. Objects of this class serve as prototypes for rows in queries. */
  class Conversations(_tableTag: Tag) extends Table[ConversationsRow](_tableTag, "conversations") {
    def * = (id, subject, createdAt, updatedAt) <> (ConversationsRow.tupled, ConversationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, subject, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> ConversationsRow.tupled((_1.get, _2, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column subject DBType(VARCHAR), Length(255,true), Default(Some()) */
    val subject: Column[Option[String]] = column[Option[String]]("subject", O.Length(255,varying=true), O.Default(Some("")))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Conversations */
  lazy val Conversations = new TableQuery(tag => new Conversations(tag))
  
  /** Entity class storing rows of table Decks
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param slug Database column slug DBType(VARCHAR), Length(255,true), Default(None)
   *  @param notes Database column notes DBType(TEXT), Length(65535,true), Default(None)
   *  @param slot Database column slot DBType(INT), Default(None)
   *  @param active Database column active DBType(BIT), Default(None)
   *  @param klassId Database column klass_id DBType(INT), Default(None)
   *  @param cardstring Database column cardstring DBType(VARCHAR), Length(255,true), Default(None)
   *  @param uniqueDeckId Database column unique_deck_id DBType(INT), Default(None)
   *  @param userNumMatches Database column user_num_matches DBType(INT), Default(None)
   *  @param userNumWins Database column user_num_wins DBType(INT), Default(None)
   *  @param userNumLosses Database column user_num_losses DBType(INT), Default(None)
   *  @param userWinrate Database column user_winrate DBType(FLOAT), Default(None)
   *  @param isPublic Database column is_public DBType(BIT), Default(None)
   *  @param archived Database column archived DBType(BIT), Default(Some(false))
   *  @param deckTypeId Database column deck_type_id DBType(INT), Default(Some(1)) */
  case class DecksRow(id: Int, name: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, userId: Option[Int] = None, slug: Option[String] = None, notes: Option[String] = None, slot: Option[Int] = None, active: Option[Boolean] = None, klassId: Option[Int] = None, cardstring: Option[String] = None, uniqueDeckId: Option[Int] = None, userNumMatches: Option[Int] = None, userNumWins: Option[Int] = None, userNumLosses: Option[Int] = None, userWinrate: Option[Float] = None, isPublic: Option[Boolean] = None, archived: Option[Boolean] = Some(false), deckTypeId: Option[Int] = Some(1))
  /** GetResult implicit for fetching DecksRow objects using plain SQL queries */
  implicit def GetResultDecksRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[java.sql.Timestamp], e3: GR[Option[Int]], e4: GR[Option[Boolean]], e5: GR[Option[Float]]): GR[DecksRow] = GR{
    prs => import prs._
    DecksRow.tupled((<<[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[String], <<?[String], <<?[Int], <<?[Boolean], <<?[Int], <<?[String], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Float], <<?[Boolean], <<?[Boolean], <<?[Int]))
  }
  /** Table description of table decks. Objects of this class serve as prototypes for rows in queries. */
  class Decks(_tableTag: Tag) extends Table[DecksRow](_tableTag, "decks") {
    def * = (id, name, createdAt, updatedAt, userId, slug, notes, slot, active, klassId, cardstring, uniqueDeckId, userNumMatches, userNumWins, userNumLosses, userWinrate, isPublic, archived, deckTypeId) <> (DecksRow.tupled, DecksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, createdAt.?, updatedAt.?, userId, slug, notes, slot, active, klassId, cardstring, uniqueDeckId, userNumMatches, userNumWins, userNumLosses, userWinrate, isPublic, archived, deckTypeId).shaped.<>({r=>import r._; _1.map(_=> DecksRow.tupled((_1.get, _2, _3.get, _4.get, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column slug DBType(VARCHAR), Length(255,true), Default(None) */
    val slug: Column[Option[String]] = column[Option[String]]("slug", O.Length(255,varying=true), O.Default(None))
    /** Database column notes DBType(TEXT), Length(65535,true), Default(None) */
    val notes: Column[Option[String]] = column[Option[String]]("notes", O.Length(65535,varying=true), O.Default(None))
    /** Database column slot DBType(INT), Default(None) */
    val slot: Column[Option[Int]] = column[Option[Int]]("slot", O.Default(None))
    /** Database column active DBType(BIT), Default(None) */
    val active: Column[Option[Boolean]] = column[Option[Boolean]]("active", O.Default(None))
    /** Database column klass_id DBType(INT), Default(None) */
    val klassId: Column[Option[Int]] = column[Option[Int]]("klass_id", O.Default(None))
    /** Database column cardstring DBType(VARCHAR), Length(255,true), Default(None) */
    val cardstring: Column[Option[String]] = column[Option[String]]("cardstring", O.Length(255,varying=true), O.Default(None))
    /** Database column unique_deck_id DBType(INT), Default(None) */
    val uniqueDeckId: Column[Option[Int]] = column[Option[Int]]("unique_deck_id", O.Default(None))
    /** Database column user_num_matches DBType(INT), Default(None) */
    val userNumMatches: Column[Option[Int]] = column[Option[Int]]("user_num_matches", O.Default(None))
    /** Database column user_num_wins DBType(INT), Default(None) */
    val userNumWins: Column[Option[Int]] = column[Option[Int]]("user_num_wins", O.Default(None))
    /** Database column user_num_losses DBType(INT), Default(None) */
    val userNumLosses: Column[Option[Int]] = column[Option[Int]]("user_num_losses", O.Default(None))
    /** Database column user_winrate DBType(FLOAT), Default(None) */
    val userWinrate: Column[Option[Float]] = column[Option[Float]]("user_winrate", O.Default(None))
    /** Database column is_public DBType(BIT), Default(None) */
    val isPublic: Column[Option[Boolean]] = column[Option[Boolean]]("is_public", O.Default(None))
    /** Database column archived DBType(BIT), Default(Some(false)) */
    val archived: Column[Option[Boolean]] = column[Option[Boolean]]("archived", O.Default(Some(false)))
    /** Database column deck_type_id DBType(INT), Default(Some(1)) */
    val deckTypeId: Column[Option[Int]] = column[Option[Int]]("deck_type_id", O.Default(Some(1)))
    
    /** Index over (deckTypeId) (database name index_decks_on_deck_type_id) */
    val index1 = index("index_decks_on_deck_type_id", deckTypeId)
    /** Index over (klassId) (database name index_decks_on_klass_id) */
    val index2 = index("index_decks_on_klass_id", klassId)
    /** Index over (slug) (database name index_decks_on_slug) */
    val index3 = index("index_decks_on_slug", slug)
    /** Index over (uniqueDeckId) (database name index_decks_on_unique_deck_id) */
    val index4 = index("index_decks_on_unique_deck_id", uniqueDeckId)
    /** Index over (userId) (database name index_decks_on_user_id) */
    val index5 = index("index_decks_on_user_id", userId)
  }
  /** Collection-like TableQuery object for table Decks */
  lazy val Decks = new TableQuery(tag => new Decks(tag))
  
  /** Entity class storing rows of table DeckVersions
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param deckId Database column deck_id DBType(INT), Default(None)
   *  @param notes Database column notes DBType(TEXT), Length(65535,true), Default(None)
   *  @param version Database column version DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param cardstring Database column cardstring DBType(VARCHAR), Length(255,true), Default(None) */
  case class DeckVersionsRow(id: Int, deckId: Option[Int] = None, notes: Option[String] = None, version: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, cardstring: Option[String] = None)
  /** GetResult implicit for fetching DeckVersionsRow objects using plain SQL queries */
  implicit def GetResultDeckVersionsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[DeckVersionsRow] = GR{
    prs => import prs._
    DeckVersionsRow.tupled((<<[Int], <<?[Int], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table deck_versions. Objects of this class serve as prototypes for rows in queries. */
  class DeckVersions(_tableTag: Tag) extends Table[DeckVersionsRow](_tableTag, "deck_versions") {
    def * = (id, deckId, notes, version, createdAt, updatedAt, cardstring) <> (DeckVersionsRow.tupled, DeckVersionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, deckId, notes, version, createdAt.?, updatedAt.?, cardstring).shaped.<>({r=>import r._; _1.map(_=> DeckVersionsRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column deck_id DBType(INT), Default(None) */
    val deckId: Column[Option[Int]] = column[Option[Int]]("deck_id", O.Default(None))
    /** Database column notes DBType(TEXT), Length(65535,true), Default(None) */
    val notes: Column[Option[String]] = column[Option[String]]("notes", O.Length(65535,varying=true), O.Default(None))
    /** Database column version DBType(VARCHAR), Length(255,true), Default(None) */
    val version: Column[Option[String]] = column[Option[String]]("version", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column cardstring DBType(VARCHAR), Length(255,true), Default(None) */
    val cardstring: Column[Option[String]] = column[Option[String]]("cardstring", O.Length(255,varying=true), O.Default(None))
    
    /** Index over (deckId) (database name index_deck_versions_on_deck_id) */
    val index1 = index("index_deck_versions_on_deck_id", deckId)
  }
  /** Collection-like TableQuery object for table DeckVersions */
  lazy val DeckVersions = new TableQuery(tag => new DeckVersions(tag))
  
  /** Entity class storing rows of table DelayedJobs
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param priority Database column priority DBType(INT), Default(0)
   *  @param attempts Database column attempts DBType(INT), Default(0)
   *  @param handler Database column handler DBType(TEXT), Length(65535,true)
   *  @param lastError Database column last_error DBType(TEXT), Length(65535,true), Default(None)
   *  @param runAt Database column run_at DBType(DATETIME), Default(None)
   *  @param lockedAt Database column locked_at DBType(DATETIME), Default(None)
   *  @param failedAt Database column failed_at DBType(DATETIME), Default(None)
   *  @param lockedBy Database column locked_by DBType(VARCHAR), Length(255,true), Default(None)
   *  @param queue Database column queue DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class DelayedJobsRow(id: Int, priority: Int = 0, attempts: Int = 0, handler: String, lastError: Option[String] = None, runAt: Option[java.sql.Timestamp] = None, lockedAt: Option[java.sql.Timestamp] = None, failedAt: Option[java.sql.Timestamp] = None, lockedBy: Option[String] = None, queue: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching DelayedJobsRow objects using plain SQL queries */
  implicit def GetResultDelayedJobsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]], e4: GR[java.sql.Timestamp]): GR[DelayedJobsRow] = GR{
    prs => import prs._
    DelayedJobsRow.tupled((<<[Int], <<[Int], <<[Int], <<[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table delayed_jobs. Objects of this class serve as prototypes for rows in queries. */
  class DelayedJobs(_tableTag: Tag) extends Table[DelayedJobsRow](_tableTag, "delayed_jobs") {
    def * = (id, priority, attempts, handler, lastError, runAt, lockedAt, failedAt, lockedBy, queue, createdAt, updatedAt) <> (DelayedJobsRow.tupled, DelayedJobsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, priority.?, attempts.?, handler.?, lastError, runAt, lockedAt, failedAt, lockedBy, queue, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> DelayedJobsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7, _8, _9, _10, _11.get, _12.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column priority DBType(INT), Default(0) */
    val priority: Column[Int] = column[Int]("priority", O.Default(0))
    /** Database column attempts DBType(INT), Default(0) */
    val attempts: Column[Int] = column[Int]("attempts", O.Default(0))
    /** Database column handler DBType(TEXT), Length(65535,true) */
    val handler: Column[String] = column[String]("handler", O.Length(65535,varying=true))
    /** Database column last_error DBType(TEXT), Length(65535,true), Default(None) */
    val lastError: Column[Option[String]] = column[Option[String]]("last_error", O.Length(65535,varying=true), O.Default(None))
    /** Database column run_at DBType(DATETIME), Default(None) */
    val runAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("run_at", O.Default(None))
    /** Database column locked_at DBType(DATETIME), Default(None) */
    val lockedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("locked_at", O.Default(None))
    /** Database column failed_at DBType(DATETIME), Default(None) */
    val failedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("failed_at", O.Default(None))
    /** Database column locked_by DBType(VARCHAR), Length(255,true), Default(None) */
    val lockedBy: Column[Option[String]] = column[Option[String]]("locked_by", O.Length(255,varying=true), O.Default(None))
    /** Database column queue DBType(VARCHAR), Length(255,true), Default(None) */
    val queue: Column[Option[String]] = column[Option[String]]("queue", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (priority,runAt) (database name delayed_jobs_priority) */
    val index1 = index("delayed_jobs_priority", (priority, runAt))
  }
  /** Collection-like TableQuery object for table DelayedJobs */
  lazy val DelayedJobs = new TableQuery(tag => new DelayedJobs(tag))
  
  /** Entity class storing rows of table Impressions
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param impressionableType Database column impressionable_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param impressionableId Database column impressionable_id DBType(INT), Default(None)
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param controllerName Database column controller_name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param actionName Database column action_name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param viewName Database column view_name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param requestHash Database column request_hash DBType(VARCHAR), Length(255,true), Default(None)
   *  @param ipAddress Database column ip_address DBType(VARCHAR), Length(255,true), Default(None)
   *  @param sessionHash Database column session_hash DBType(VARCHAR), Length(255,true), Default(None)
   *  @param message Database column message DBType(TEXT), Length(65535,true), Default(None)
   *  @param referrer Database column referrer DBType(TEXT), Length(65535,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class ImpressionsRow(id: Int, impressionableType: Option[String] = None, impressionableId: Option[Int] = None, userId: Option[Int] = None, controllerName: Option[String] = None, actionName: Option[String] = None, viewName: Option[String] = None, requestHash: Option[String] = None, ipAddress: Option[String] = None, sessionHash: Option[String] = None, message: Option[String] = None, referrer: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ImpressionsRow objects using plain SQL queries */
  implicit def GetResultImpressionsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[java.sql.Timestamp]): GR[ImpressionsRow] = GR{
    prs => import prs._
    ImpressionsRow.tupled((<<[Int], <<?[String], <<?[Int], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table impressions. Objects of this class serve as prototypes for rows in queries. */
  class Impressions(_tableTag: Tag) extends Table[ImpressionsRow](_tableTag, "impressions") {
    def * = (id, impressionableType, impressionableId, userId, controllerName, actionName, viewName, requestHash, ipAddress, sessionHash, message, referrer, createdAt, updatedAt) <> (ImpressionsRow.tupled, ImpressionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, impressionableType, impressionableId, userId, controllerName, actionName, viewName, requestHash, ipAddress, sessionHash, message, referrer, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> ImpressionsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13.get, _14.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column impressionable_type DBType(VARCHAR), Length(255,true), Default(None) */
    val impressionableType: Column[Option[String]] = column[Option[String]]("impressionable_type", O.Length(255,varying=true), O.Default(None))
    /** Database column impressionable_id DBType(INT), Default(None) */
    val impressionableId: Column[Option[Int]] = column[Option[Int]]("impressionable_id", O.Default(None))
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column controller_name DBType(VARCHAR), Length(255,true), Default(None) */
    val controllerName: Column[Option[String]] = column[Option[String]]("controller_name", O.Length(255,varying=true), O.Default(None))
    /** Database column action_name DBType(VARCHAR), Length(255,true), Default(None) */
    val actionName: Column[Option[String]] = column[Option[String]]("action_name", O.Length(255,varying=true), O.Default(None))
    /** Database column view_name DBType(VARCHAR), Length(255,true), Default(None) */
    val viewName: Column[Option[String]] = column[Option[String]]("view_name", O.Length(255,varying=true), O.Default(None))
    /** Database column request_hash DBType(VARCHAR), Length(255,true), Default(None) */
    val requestHash: Column[Option[String]] = column[Option[String]]("request_hash", O.Length(255,varying=true), O.Default(None))
    /** Database column ip_address DBType(VARCHAR), Length(255,true), Default(None) */
    val ipAddress: Column[Option[String]] = column[Option[String]]("ip_address", O.Length(255,varying=true), O.Default(None))
    /** Database column session_hash DBType(VARCHAR), Length(255,true), Default(None) */
    val sessionHash: Column[Option[String]] = column[Option[String]]("session_hash", O.Length(255,varying=true), O.Default(None))
    /** Database column message DBType(TEXT), Length(65535,true), Default(None) */
    val message: Column[Option[String]] = column[Option[String]]("message", O.Length(65535,varying=true), O.Default(None))
    /** Database column referrer DBType(TEXT), Length(65535,true), Default(None) */
    val referrer: Column[Option[String]] = column[Option[String]]("referrer", O.Length(65535,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (controllerName,actionName,ipAddress) (database name controlleraction_ip_index) */
    val index1 = index("controlleraction_ip_index", (controllerName, actionName, ipAddress))
    /** Index over (controllerName,actionName,requestHash) (database name controlleraction_request_index) */
    val index2 = index("controlleraction_request_index", (controllerName, actionName, requestHash))
    /** Index over (controllerName,actionName,sessionHash) (database name controlleraction_session_index) */
    val index3 = index("controlleraction_session_index", (controllerName, actionName, sessionHash))
    /** Index over (impressionableType,message,impressionableId) (database name impressionable_type_message_index) */
    val index4 = index("impressionable_type_message_index", (impressionableType, message, impressionableId))
    /** Index over (userId) (database name index_impressions_on_user_id) */
    val index5 = index("index_impressions_on_user_id", userId)
    /** Index over (impressionableType,impressionableId,ipAddress) (database name poly_ip_index) */
    val index6 = index("poly_ip_index", (impressionableType, impressionableId, ipAddress))
    /** Index over (impressionableType,impressionableId,requestHash) (database name poly_request_index) */
    val index7 = index("poly_request_index", (impressionableType, impressionableId, requestHash))
    /** Index over (impressionableType,impressionableId,sessionHash) (database name poly_session_index) */
    val index8 = index("poly_session_index", (impressionableType, impressionableId, sessionHash))
  }
  /** Collection-like TableQuery object for table Impressions */
  lazy val Impressions = new TableQuery(tag => new Impressions(tag))
  
  /** Entity class storing rows of table Klasses
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None) */
  case class KlassesRow(id: Int, name: Option[String] = None)
  /** GetResult implicit for fetching KlassesRow objects using plain SQL queries */
  implicit def GetResultKlassesRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[KlassesRow] = GR{
    prs => import prs._
    KlassesRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table klasses. Objects of this class serve as prototypes for rows in queries. */
  class Klasses(_tableTag: Tag) extends Table[KlassesRow](_tableTag, "klasses") {
    def * = (id, name) <> (KlassesRow.tupled, KlassesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name).shaped.<>({r=>import r._; _1.map(_=> KlassesRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Klasses */
  lazy val Klasses = new TableQuery(tag => new Klasses(tag))
  
  /** Entity class storing rows of table MatchDecks
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param deckId Database column deck_id DBType(INT), Default(None)
   *  @param matchId Database column match_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param deckVersionId Database column deck_version_id DBType(INT), Default(None) */
  case class MatchDecksRow(id: Int, deckId: Option[Int] = None, matchId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, deckVersionId: Option[Int] = None)
  /** GetResult implicit for fetching MatchDecksRow objects using plain SQL queries */
  implicit def GetResultMatchDecksRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp]): GR[MatchDecksRow] = GR{
    prs => import prs._
    MatchDecksRow.tupled((<<[Int], <<?[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int]))
  }
  /** Table description of table match_decks. Objects of this class serve as prototypes for rows in queries. */
  class MatchDecks(_tableTag: Tag) extends Table[MatchDecksRow](_tableTag, "match_decks") {
    def * = (id, deckId, matchId, createdAt, updatedAt, deckVersionId) <> (MatchDecksRow.tupled, MatchDecksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, deckId, matchId, createdAt.?, updatedAt.?, deckVersionId).shaped.<>({r=>import r._; _1.map(_=> MatchDecksRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column deck_id DBType(INT), Default(None) */
    val deckId: Column[Option[Int]] = column[Option[Int]]("deck_id", O.Default(None))
    /** Database column match_id DBType(INT), Default(None) */
    val matchId: Column[Option[Int]] = column[Option[Int]]("match_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column deck_version_id DBType(INT), Default(None) */
    val deckVersionId: Column[Option[Int]] = column[Option[Int]]("deck_version_id", O.Default(None))
    
    /** Index over (deckId) (database name index_match_decks_on_deck_id) */
    val index1 = index("index_match_decks_on_deck_id", deckId)
    /** Index over (deckVersionId) (database name index_match_decks_on_deck_version_id) */
    val index2 = index("index_match_decks_on_deck_version_id", deckVersionId)
    /** Index over (matchId) (database name index_match_decks_on_match_id) */
    val index3 = index("index_match_decks_on_match_id", matchId)
  }
  /** Collection-like TableQuery object for table MatchDecks */
  lazy val MatchDecks = new TableQuery(tag => new MatchDecks(tag))
  
  /** Entity class storing rows of table Matches
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param klassId Database column klass_id DBType(INT), Default(None)
   *  @param oppclassId Database column oppclass_id DBType(INT), Default(None)
   *  @param oppname Database column oppname DBType(VARCHAR), Length(255,true), Default(None)
   *  @param modeId Database column mode_id DBType(INT), Default(None)
   *  @param resultId Database column result_id DBType(INT), Default(None)
   *  @param notes Database column notes DBType(TEXT), Length(65535,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param coin Database column coin DBType(BIT), Default(None)
   *  @param numturns Database column numturns DBType(INT), Default(None)
   *  @param duration Database column duration DBType(INT), Default(None)
   *  @param patchId Database column patch_id DBType(INT), Default(None)
   *  @param seasonId Database column season_id DBType(INT), Default(None)
   *  @param appsubmit Database column appsubmit DBType(BIT), Default(None)
   *  @param oppArchtypeId Database column opp_archtype_id DBType(INT), Default(None) */
  case class MatchesRow(id: Int, userId: Option[Int] = None, klassId: Option[Int] = None, oppclassId: Option[Int] = None, oppname: Option[String] = None, modeId: Option[Int] = None, resultId: Option[Int] = None, notes: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, coin: Option[Boolean] = None, numturns: Option[Int] = None, duration: Option[Int] = None, patchId: Option[Int] = None, seasonId: Option[Int] = None, appsubmit: Option[Boolean] = None, oppArchtypeId: Option[Int] = None)
  /** GetResult implicit for fetching MatchesRow objects using plain SQL queries */
  implicit def GetResultMatchesRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[Boolean]]): GR[MatchesRow] = GR{
    prs => import prs._
    MatchesRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Int], <<?[String], <<?[Int], <<?[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Boolean], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Boolean], <<?[Int]))
  }
  /** Table description of table matches. Objects of this class serve as prototypes for rows in queries. */
  class Matches(_tableTag: Tag) extends Table[MatchesRow](_tableTag, "matches") {
    def * = (id, userId, klassId, oppclassId, oppname, modeId, resultId, notes, createdAt, updatedAt, coin, numturns, duration, patchId, seasonId, appsubmit, oppArchtypeId) <> (MatchesRow.tupled, MatchesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, userId, klassId, oppclassId, oppname, modeId, resultId, notes, createdAt.?, updatedAt.?, coin, numturns, duration, patchId, seasonId, appsubmit, oppArchtypeId).shaped.<>({r=>import r._; _1.map(_=> MatchesRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9.get, _10.get, _11, _12, _13, _14, _15, _16, _17)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column klass_id DBType(INT), Default(None) */
    val klassId: Column[Option[Int]] = column[Option[Int]]("klass_id", O.Default(None))
    /** Database column oppclass_id DBType(INT), Default(None) */
    val oppclassId: Column[Option[Int]] = column[Option[Int]]("oppclass_id", O.Default(None))
    /** Database column oppname DBType(VARCHAR), Length(255,true), Default(None) */
    val oppname: Column[Option[String]] = column[Option[String]]("oppname", O.Length(255,varying=true), O.Default(None))
    /** Database column mode_id DBType(INT), Default(None) */
    val modeId: Column[Option[Int]] = column[Option[Int]]("mode_id", O.Default(None))
    /** Database column result_id DBType(INT), Default(None) */
    val resultId: Column[Option[Int]] = column[Option[Int]]("result_id", O.Default(None))
    /** Database column notes DBType(TEXT), Length(65535,true), Default(None) */
    val notes: Column[Option[String]] = column[Option[String]]("notes", O.Length(65535,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column coin DBType(BIT), Default(None) */
    val coin: Column[Option[Boolean]] = column[Option[Boolean]]("coin", O.Default(None))
    /** Database column numturns DBType(INT), Default(None) */
    val numturns: Column[Option[Int]] = column[Option[Int]]("numturns", O.Default(None))
    /** Database column duration DBType(INT), Default(None) */
    val duration: Column[Option[Int]] = column[Option[Int]]("duration", O.Default(None))
    /** Database column patch_id DBType(INT), Default(None) */
    val patchId: Column[Option[Int]] = column[Option[Int]]("patch_id", O.Default(None))
    /** Database column season_id DBType(INT), Default(None) */
    val seasonId: Column[Option[Int]] = column[Option[Int]]("season_id", O.Default(None))
    /** Database column appsubmit DBType(BIT), Default(None) */
    val appsubmit: Column[Option[Boolean]] = column[Option[Boolean]]("appsubmit", O.Default(None))
    /** Database column opp_archtype_id DBType(INT), Default(None) */
    val oppArchtypeId: Column[Option[Int]] = column[Option[Int]]("opp_archtype_id", O.Default(None))
    
    /** Index over (userId,modeId,klassId,oppclassId,coin,createdAt) (database name index_for_search) */
    val index1 = index("index_for_search", (userId, modeId, klassId, oppclassId, coin, createdAt))
    /** Index over (klassId) (database name index_matches_on_klass_id) */
    val index2 = index("index_matches_on_klass_id", klassId)
    /** Index over (modeId) (database name index_matches_on_mode_id) */
    val index3 = index("index_matches_on_mode_id", modeId)
    /** Index over (oppclassId) (database name index_matches_on_oppclass_id) */
    val index4 = index("index_matches_on_oppclass_id", oppclassId)
    /** Index over (resultId) (database name index_matches_on_result_id) */
    val index5 = index("index_matches_on_result_id", resultId)
  }
  /** Collection-like TableQuery object for table Matches */
  lazy val Matches = new TableQuery(tag => new Matches(tag))
  
  /** Entity class storing rows of table MatchRanks
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param rankId Database column rank_id DBType(INT), Default(None)
   *  @param matchId Database column match_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param legendary Database column legendary DBType(INT), Default(None) */
  case class MatchRanksRow(id: Int, rankId: Option[Int] = None, matchId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, legendary: Option[Int] = None)
  /** GetResult implicit for fetching MatchRanksRow objects using plain SQL queries */
  implicit def GetResultMatchRanksRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp]): GR[MatchRanksRow] = GR{
    prs => import prs._
    MatchRanksRow.tupled((<<[Int], <<?[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int]))
  }
  /** Table description of table match_ranks. Objects of this class serve as prototypes for rows in queries. */
  class MatchRanks(_tableTag: Tag) extends Table[MatchRanksRow](_tableTag, "match_ranks") {
    def * = (id, rankId, matchId, createdAt, updatedAt, legendary) <> (MatchRanksRow.tupled, MatchRanksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, rankId, matchId, createdAt.?, updatedAt.?, legendary).shaped.<>({r=>import r._; _1.map(_=> MatchRanksRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column rank_id DBType(INT), Default(None) */
    val rankId: Column[Option[Int]] = column[Option[Int]]("rank_id", O.Default(None))
    /** Database column match_id DBType(INT), Default(None) */
    val matchId: Column[Option[Int]] = column[Option[Int]]("match_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column legendary DBType(INT), Default(None) */
    val legendary: Column[Option[Int]] = column[Option[Int]]("legendary", O.Default(None))
    
    /** Index over (matchId) (database name index_match_ranks_on_match_id) */
    val index1 = index("index_match_ranks_on_match_id", matchId)
    /** Index over (rankId) (database name index_match_ranks_on_rank_id) */
    val index2 = index("index_match_ranks_on_rank_id", rankId)
  }
  /** Collection-like TableQuery object for table MatchRanks */
  lazy val MatchRanks = new TableQuery(tag => new MatchRanks(tag))
  
  /** Entity class storing rows of table MatchResults
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param matchId Database column match_id DBType(INT), Default(None)
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class MatchResultsRow(id: Int, matchId: Option[Int] = None, name: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching MatchResultsRow objects using plain SQL queries */
  implicit def GetResultMatchResultsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[MatchResultsRow] = GR{
    prs => import prs._
    MatchResultsRow.tupled((<<[Int], <<?[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table match_results. Objects of this class serve as prototypes for rows in queries. */
  class MatchResults(_tableTag: Tag) extends Table[MatchResultsRow](_tableTag, "match_results") {
    def * = (id, matchId, name, createdAt, updatedAt) <> (MatchResultsRow.tupled, MatchResultsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, matchId, name, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> MatchResultsRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column match_id DBType(INT), Default(None) */
    val matchId: Column[Option[Int]] = column[Option[Int]]("match_id", O.Default(None))
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (matchId) (database name index_match_results_on_match_id) */
    val index1 = index("index_match_results_on_match_id", matchId)
  }
  /** Collection-like TableQuery object for table MatchResults */
  lazy val MatchResults = new TableQuery(tag => new MatchResults(tag))
  
  /** Entity class storing rows of table MatchRuns
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param arenaRunId Database column arena_run_id DBType(INT), Default(None)
   *  @param matchId Database column match_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class MatchRunsRow(id: Int, arenaRunId: Option[Int] = None, matchId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching MatchRunsRow objects using plain SQL queries */
  implicit def GetResultMatchRunsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp]): GR[MatchRunsRow] = GR{
    prs => import prs._
    MatchRunsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table match_runs. Objects of this class serve as prototypes for rows in queries. */
  class MatchRuns(_tableTag: Tag) extends Table[MatchRunsRow](_tableTag, "match_runs") {
    def * = (id, arenaRunId, matchId, createdAt, updatedAt) <> (MatchRunsRow.tupled, MatchRunsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, arenaRunId, matchId, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> MatchRunsRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column arena_run_id DBType(INT), Default(None) */
    val arenaRunId: Column[Option[Int]] = column[Option[Int]]("arena_run_id", O.Default(None))
    /** Database column match_id DBType(INT), Default(None) */
    val matchId: Column[Option[Int]] = column[Option[Int]]("match_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (arenaRunId) (database name index_match_runs_on_arena_run_id) */
    val index1 = index("index_match_runs_on_arena_run_id", arenaRunId)
    /** Index over (matchId) (database name index_match_runs_on_match_id) */
    val index2 = index("index_match_runs_on_match_id", matchId)
  }
  /** Collection-like TableQuery object for table MatchRuns */
  lazy val MatchRuns = new TableQuery(tag => new MatchRuns(tag))
  
  /** Entity class storing rows of table MatchUniqueDecks
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param uniqueDeckId Database column unique_deck_id DBType(INT), Default(None)
   *  @param matchId Database column match_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class MatchUniqueDecksRow(id: Int, uniqueDeckId: Option[Int] = None, matchId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching MatchUniqueDecksRow objects using plain SQL queries */
  implicit def GetResultMatchUniqueDecksRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp]): GR[MatchUniqueDecksRow] = GR{
    prs => import prs._
    MatchUniqueDecksRow.tupled((<<[Int], <<?[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table match_unique_decks. Objects of this class serve as prototypes for rows in queries. */
  class MatchUniqueDecks(_tableTag: Tag) extends Table[MatchUniqueDecksRow](_tableTag, "match_unique_decks") {
    def * = (id, uniqueDeckId, matchId, createdAt, updatedAt) <> (MatchUniqueDecksRow.tupled, MatchUniqueDecksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, uniqueDeckId, matchId, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> MatchUniqueDecksRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column unique_deck_id DBType(INT), Default(None) */
    val uniqueDeckId: Column[Option[Int]] = column[Option[Int]]("unique_deck_id", O.Default(None))
    /** Database column match_id DBType(INT), Default(None) */
    val matchId: Column[Option[Int]] = column[Option[Int]]("match_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (matchId) (database name index_match_unique_decks_on_match_id) */
    val index1 = index("index_match_unique_decks_on_match_id", matchId)
    /** Index over (uniqueDeckId) (database name index_match_unique_decks_on_unique_deck_id) */
    val index2 = index("index_match_unique_decks_on_unique_deck_id", uniqueDeckId)
  }
  /** Collection-like TableQuery object for table MatchUniqueDecks */
  lazy val MatchUniqueDecks = new TableQuery(tag => new MatchUniqueDecks(tag))
  
  /** Entity class storing rows of table Mechanics
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None) */
  case class MechanicsRow(id: Int, name: Option[String] = None)
  /** GetResult implicit for fetching MechanicsRow objects using plain SQL queries */
  implicit def GetResultMechanicsRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[MechanicsRow] = GR{
    prs => import prs._
    MechanicsRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table mechanics. Objects of this class serve as prototypes for rows in queries. */
  class Mechanics(_tableTag: Tag) extends Table[MechanicsRow](_tableTag, "mechanics") {
    def * = (id, name) <> (MechanicsRow.tupled, MechanicsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name).shaped.<>({r=>import r._; _1.map(_=> MechanicsRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Mechanics */
  lazy val Mechanics = new TableQuery(tag => new Mechanics(tag))
  
  /** Entity class storing rows of table Modes
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None) */
  case class ModesRow(id: Int, name: Option[String] = None)
  /** GetResult implicit for fetching ModesRow objects using plain SQL queries */
  implicit def GetResultModesRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[ModesRow] = GR{
    prs => import prs._
    ModesRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table modes. Objects of this class serve as prototypes for rows in queries. */
  class Modes(_tableTag: Tag) extends Table[ModesRow](_tableTag, "modes") {
    def * = (id, name) <> (ModesRow.tupled, ModesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name).shaped.<>({r=>import r._; _1.map(_=> ModesRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Modes */
  lazy val Modes = new TableQuery(tag => new Modes(tag))
  
  /** Entity class storing rows of table Notifications
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param `type` Database column type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param body Database column body DBType(TEXT), Length(65535,true), Default(None)
   *  @param subject Database column subject DBType(VARCHAR), Length(255,true), Default(Some())
   *  @param senderId Database column sender_id DBType(INT), Default(None)
   *  @param senderType Database column sender_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param conversationId Database column conversation_id DBType(INT), Default(None)
   *  @param draft Database column draft DBType(BIT), Default(Some(false))
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param notifiedObjectId Database column notified_object_id DBType(INT), Default(None)
   *  @param notifiedObjectType Database column notified_object_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param notificationCode Database column notification_code DBType(VARCHAR), Length(255,true), Default(None)
   *  @param attachment Database column attachment DBType(VARCHAR), Length(255,true), Default(None)
   *  @param global Database column global DBType(BIT), Default(Some(false))
   *  @param expires Database column expires DBType(DATETIME), Default(None) */
  case class NotificationsRow(id: Int, `type`: Option[String] = None, body: Option[String] = None, subject: Option[String] = Some(""), senderId: Option[Int] = None, senderType: Option[String] = None, conversationId: Option[Int] = None, draft: Option[Boolean] = Some(false), updatedAt: java.sql.Timestamp, createdAt: java.sql.Timestamp, notifiedObjectId: Option[Int] = None, notifiedObjectType: Option[String] = None, notificationCode: Option[String] = None, attachment: Option[String] = None, global: Option[Boolean] = Some(false), expires: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching NotificationsRow objects using plain SQL queries */
  implicit def GetResultNotificationsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[Boolean]], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[NotificationsRow] = GR{
    prs => import prs._
    NotificationsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[Int], <<?[Boolean], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[Boolean], <<?[java.sql.Timestamp]))
  }
  /** Table description of table notifications. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class Notifications(_tableTag: Tag) extends Table[NotificationsRow](_tableTag, "notifications") {
    def * = (id, `type`, body, subject, senderId, senderType, conversationId, draft, updatedAt, createdAt, notifiedObjectId, notifiedObjectType, notificationCode, attachment, global, expires) <> (NotificationsRow.tupled, NotificationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, `type`, body, subject, senderId, senderType, conversationId, draft, updatedAt.?, createdAt.?, notifiedObjectId, notifiedObjectType, notificationCode, attachment, global, expires).shaped.<>({r=>import r._; _1.map(_=> NotificationsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9.get, _10.get, _11, _12, _13, _14, _15, _16)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column type DBType(VARCHAR), Length(255,true), Default(None)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Column[Option[String]] = column[Option[String]]("type", O.Length(255,varying=true), O.Default(None))
    /** Database column body DBType(TEXT), Length(65535,true), Default(None) */
    val body: Column[Option[String]] = column[Option[String]]("body", O.Length(65535,varying=true), O.Default(None))
    /** Database column subject DBType(VARCHAR), Length(255,true), Default(Some()) */
    val subject: Column[Option[String]] = column[Option[String]]("subject", O.Length(255,varying=true), O.Default(Some("")))
    /** Database column sender_id DBType(INT), Default(None) */
    val senderId: Column[Option[Int]] = column[Option[Int]]("sender_id", O.Default(None))
    /** Database column sender_type DBType(VARCHAR), Length(255,true), Default(None) */
    val senderType: Column[Option[String]] = column[Option[String]]("sender_type", O.Length(255,varying=true), O.Default(None))
    /** Database column conversation_id DBType(INT), Default(None) */
    val conversationId: Column[Option[Int]] = column[Option[Int]]("conversation_id", O.Default(None))
    /** Database column draft DBType(BIT), Default(Some(false)) */
    val draft: Column[Option[Boolean]] = column[Option[Boolean]]("draft", O.Default(Some(false)))
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column notified_object_id DBType(INT), Default(None) */
    val notifiedObjectId: Column[Option[Int]] = column[Option[Int]]("notified_object_id", O.Default(None))
    /** Database column notified_object_type DBType(VARCHAR), Length(255,true), Default(None) */
    val notifiedObjectType: Column[Option[String]] = column[Option[String]]("notified_object_type", O.Length(255,varying=true), O.Default(None))
    /** Database column notification_code DBType(VARCHAR), Length(255,true), Default(None) */
    val notificationCode: Column[Option[String]] = column[Option[String]]("notification_code", O.Length(255,varying=true), O.Default(None))
    /** Database column attachment DBType(VARCHAR), Length(255,true), Default(None) */
    val attachment: Column[Option[String]] = column[Option[String]]("attachment", O.Length(255,varying=true), O.Default(None))
    /** Database column global DBType(BIT), Default(Some(false)) */
    val global: Column[Option[Boolean]] = column[Option[Boolean]]("global", O.Default(Some(false)))
    /** Database column expires DBType(DATETIME), Default(None) */
    val expires: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("expires", O.Default(None))
    
    /** Foreign key referencing Conversations (database name notifications_on_conversation_id) */
    lazy val conversationsFk = foreignKey("notifications_on_conversation_id", conversationId, Conversations)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Notifications */
  lazy val Notifications = new TableQuery(tag => new Notifications(tag))
  
  /** Entity class storing rows of table Patches
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param num Database column num DBType(VARCHAR), Length(255,true), Default(None)
   *  @param changelog Database column changelog DBType(TEXT), Length(65535,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class PatchesRow(id: Int, num: Option[String] = None, changelog: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching PatchesRow objects using plain SQL queries */
  implicit def GetResultPatchesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[PatchesRow] = GR{
    prs => import prs._
    PatchesRow.tupled((<<[Int], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table patches. Objects of this class serve as prototypes for rows in queries. */
  class Patches(_tableTag: Tag) extends Table[PatchesRow](_tableTag, "patches") {
    def * = (id, num, changelog, createdAt, updatedAt) <> (PatchesRow.tupled, PatchesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, num, changelog, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> PatchesRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column num DBType(VARCHAR), Length(255,true), Default(None) */
    val num: Column[Option[String]] = column[Option[String]]("num", O.Length(255,varying=true), O.Default(None))
    /** Database column changelog DBType(TEXT), Length(65535,true), Default(None) */
    val changelog: Column[Option[String]] = column[Option[String]]("changelog", O.Length(65535,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Patches */
  lazy val Patches = new TableQuery(tag => new Patches(tag))
  
  /** Entity class storing rows of table Profiles
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param bnetid Database column bnetid DBType(VARCHAR), Length(255,true), Default(None)
   *  @param `private` Database column private DBType(BIT), Default(Some(false))
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param bnetnum Database column bnetnum DBType(INT), Default(Some(0))
   *  @param timeZone Database column time_zone DBType(VARCHAR), Length(255,true), Default(Some(EST))
   *  @param avatarFileName Database column avatar_file_name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param avatarContentType Database column avatar_content_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param avatarFileSize Database column avatar_file_size DBType(INT), Default(None)
   *  @param avatarUpdatedAt Database column avatar_updated_at DBType(DATETIME), Default(None)
   *  @param locale Database column locale DBType(VARCHAR), Length(255,true), Default(Some(en))
   *  @param sigPicFileName Database column sig_pic_file_name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param sigPicContentType Database column sig_pic_content_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param sigPicFileSize Database column sig_pic_file_size DBType(INT), Default(None)
   *  @param sigPicUpdatedAt Database column sig_pic_updated_at DBType(DATETIME), Default(None) */
  case class ProfilesRow(id: Int, name: Option[String] = None, bnetid: Option[String] = None, `private`: Option[Boolean] = Some(false), createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, userId: Option[Int] = None, bnetnum: Option[Int] = Some(0), timeZone: Option[String] = Some("EST"), avatarFileName: Option[String] = None, avatarContentType: Option[String] = None, avatarFileSize: Option[Int] = None, avatarUpdatedAt: Option[java.sql.Timestamp] = None, locale: Option[String] = Some("en"), sigPicFileName: Option[String] = None, sigPicContentType: Option[String] = None, sigPicFileSize: Option[Int] = None, sigPicUpdatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching ProfilesRow objects using plain SQL queries */
  implicit def GetResultProfilesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Boolean]], e3: GR[java.sql.Timestamp], e4: GR[Option[Int]], e5: GR[Option[java.sql.Timestamp]]): GR[ProfilesRow] = GR{
    prs => import prs._
    ProfilesRow.tupled((<<[Int], <<?[String], <<?[String], <<?[Boolean], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp]))
  }
  /** Table description of table profiles. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: private */
  class Profiles(_tableTag: Tag) extends Table[ProfilesRow](_tableTag, "profiles") {
    def * = (id, name, bnetid, `private`, createdAt, updatedAt, userId, bnetnum, timeZone, avatarFileName, avatarContentType, avatarFileSize, avatarUpdatedAt, locale, sigPicFileName, sigPicContentType, sigPicFileSize, sigPicUpdatedAt) <> (ProfilesRow.tupled, ProfilesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, bnetid, `private`, createdAt.?, updatedAt.?, userId, bnetnum, timeZone, avatarFileName, avatarContentType, avatarFileSize, avatarUpdatedAt, locale, sigPicFileName, sigPicContentType, sigPicFileSize, sigPicUpdatedAt).shaped.<>({r=>import r._; _1.map(_=> ProfilesRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column bnetid DBType(VARCHAR), Length(255,true), Default(None) */
    val bnetid: Column[Option[String]] = column[Option[String]]("bnetid", O.Length(255,varying=true), O.Default(None))
    /** Database column private DBType(BIT), Default(Some(false))
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `private`: Column[Option[Boolean]] = column[Option[Boolean]]("private", O.Default(Some(false)))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column bnetnum DBType(INT), Default(Some(0)) */
    val bnetnum: Column[Option[Int]] = column[Option[Int]]("bnetnum", O.Default(Some(0)))
    /** Database column time_zone DBType(VARCHAR), Length(255,true), Default(Some(EST)) */
    val timeZone: Column[Option[String]] = column[Option[String]]("time_zone", O.Length(255,varying=true), O.Default(Some("EST")))
    /** Database column avatar_file_name DBType(VARCHAR), Length(255,true), Default(None) */
    val avatarFileName: Column[Option[String]] = column[Option[String]]("avatar_file_name", O.Length(255,varying=true), O.Default(None))
    /** Database column avatar_content_type DBType(VARCHAR), Length(255,true), Default(None) */
    val avatarContentType: Column[Option[String]] = column[Option[String]]("avatar_content_type", O.Length(255,varying=true), O.Default(None))
    /** Database column avatar_file_size DBType(INT), Default(None) */
    val avatarFileSize: Column[Option[Int]] = column[Option[Int]]("avatar_file_size", O.Default(None))
    /** Database column avatar_updated_at DBType(DATETIME), Default(None) */
    val avatarUpdatedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("avatar_updated_at", O.Default(None))
    /** Database column locale DBType(VARCHAR), Length(255,true), Default(Some(en)) */
    val locale: Column[Option[String]] = column[Option[String]]("locale", O.Length(255,varying=true), O.Default(Some("en")))
    /** Database column sig_pic_file_name DBType(VARCHAR), Length(255,true), Default(None) */
    val sigPicFileName: Column[Option[String]] = column[Option[String]]("sig_pic_file_name", O.Length(255,varying=true), O.Default(None))
    /** Database column sig_pic_content_type DBType(VARCHAR), Length(255,true), Default(None) */
    val sigPicContentType: Column[Option[String]] = column[Option[String]]("sig_pic_content_type", O.Length(255,varying=true), O.Default(None))
    /** Database column sig_pic_file_size DBType(INT), Default(None) */
    val sigPicFileSize: Column[Option[Int]] = column[Option[Int]]("sig_pic_file_size", O.Default(None))
    /** Database column sig_pic_updated_at DBType(DATETIME), Default(None) */
    val sigPicUpdatedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("sig_pic_updated_at", O.Default(None))
    
    /** Index over (name) (database name index_profiles_on_name) */
    val index1 = index("index_profiles_on_name", name)
    /** Index over (userId) (database name index_profiles_on_user_id) */
    val index2 = index("index_profiles_on_user_id", userId)
  }
  /** Collection-like TableQuery object for table Profiles */
  lazy val Profiles = new TableQuery(tag => new Profiles(tag))
  
  /** Entity class storing rows of table Races
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class RacesRow(id: Int, name: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching RacesRow objects using plain SQL queries */
  implicit def GetResultRacesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[RacesRow] = GR{
    prs => import prs._
    RacesRow.tupled((<<[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table races. Objects of this class serve as prototypes for rows in queries. */
  class Races(_tableTag: Tag) extends Table[RacesRow](_tableTag, "races") {
    def * = (id, name, createdAt, updatedAt) <> (RacesRow.tupled, RacesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> RacesRow.tupled((_1.get, _2, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Races */
  lazy val Races = new TableQuery(tag => new Races(tag))
  
  /** Entity class storing rows of table Ranks
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param order Database column order DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class RanksRow(id: Int, name: Option[String] = None, order: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching RanksRow objects using plain SQL queries */
  implicit def GetResultRanksRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[java.sql.Timestamp]): GR[RanksRow] = GR{
    prs => import prs._
    RanksRow.tupled((<<[Int], <<?[String], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table ranks. Objects of this class serve as prototypes for rows in queries. */
  class Ranks(_tableTag: Tag) extends Table[RanksRow](_tableTag, "ranks") {
    def * = (id, name, order, createdAt, updatedAt) <> (RanksRow.tupled, RanksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, order, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> RanksRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column order DBType(INT), Default(None) */
    val order: Column[Option[Int]] = column[Option[Int]]("order", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Ranks */
  lazy val Ranks = new TableQuery(tag => new Ranks(tag))
  
  /** Entity class storing rows of table Rarities
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param cardId Database column card_id DBType(INT), Default(None)
   *  @param rarity Database column rarity DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class RaritiesRow(id: Int, cardId: Option[Int] = None, rarity: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching RaritiesRow objects using plain SQL queries */
  implicit def GetResultRaritiesRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[RaritiesRow] = GR{
    prs => import prs._
    RaritiesRow.tupled((<<[Int], <<?[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table rarities. Objects of this class serve as prototypes for rows in queries. */
  class Rarities(_tableTag: Tag) extends Table[RaritiesRow](_tableTag, "rarities") {
    def * = (id, cardId, rarity, createdAt, updatedAt) <> (RaritiesRow.tupled, RaritiesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, cardId, rarity, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> RaritiesRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column card_id DBType(INT), Default(None) */
    val cardId: Column[Option[Int]] = column[Option[Int]]("card_id", O.Default(None))
    /** Database column rarity DBType(VARCHAR), Length(255,true), Default(None) */
    val rarity: Column[Option[String]] = column[Option[String]]("rarity", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Rarities */
  lazy val Rarities = new TableQuery(tag => new Rarities(tag))
  
  /** Entity class storing rows of table Receipts
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param receiverId Database column receiver_id DBType(INT), Default(None)
   *  @param receiverType Database column receiver_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param notificationId Database column notification_id DBType(INT)
   *  @param isRead Database column is_read DBType(BIT), Default(Some(false))
   *  @param trashed Database column trashed DBType(BIT), Default(Some(false))
   *  @param deleted Database column deleted DBType(BIT), Default(Some(false))
   *  @param mailboxType Database column mailbox_type DBType(VARCHAR), Length(25,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class ReceiptsRow(id: Int, receiverId: Option[Int] = None, receiverType: Option[String] = None, notificationId: Int, isRead: Option[Boolean] = Some(false), trashed: Option[Boolean] = Some(false), deleted: Option[Boolean] = Some(false), mailboxType: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ReceiptsRow objects using plain SQL queries */
  implicit def GetResultReceiptsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[Option[Boolean]], e4: GR[java.sql.Timestamp]): GR[ReceiptsRow] = GR{
    prs => import prs._
    ReceiptsRow.tupled((<<[Int], <<?[Int], <<?[String], <<[Int], <<?[Boolean], <<?[Boolean], <<?[Boolean], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table receipts. Objects of this class serve as prototypes for rows in queries. */
  class Receipts(_tableTag: Tag) extends Table[ReceiptsRow](_tableTag, "receipts") {
    def * = (id, receiverId, receiverType, notificationId, isRead, trashed, deleted, mailboxType, createdAt, updatedAt) <> (ReceiptsRow.tupled, ReceiptsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, receiverId, receiverType, notificationId.?, isRead, trashed, deleted, mailboxType, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> ReceiptsRow.tupled((_1.get, _2, _3, _4.get, _5, _6, _7, _8, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column receiver_id DBType(INT), Default(None) */
    val receiverId: Column[Option[Int]] = column[Option[Int]]("receiver_id", O.Default(None))
    /** Database column receiver_type DBType(VARCHAR), Length(255,true), Default(None) */
    val receiverType: Column[Option[String]] = column[Option[String]]("receiver_type", O.Length(255,varying=true), O.Default(None))
    /** Database column notification_id DBType(INT) */
    val notificationId: Column[Int] = column[Int]("notification_id")
    /** Database column is_read DBType(BIT), Default(Some(false)) */
    val isRead: Column[Option[Boolean]] = column[Option[Boolean]]("is_read", O.Default(Some(false)))
    /** Database column trashed DBType(BIT), Default(Some(false)) */
    val trashed: Column[Option[Boolean]] = column[Option[Boolean]]("trashed", O.Default(Some(false)))
    /** Database column deleted DBType(BIT), Default(Some(false)) */
    val deleted: Column[Option[Boolean]] = column[Option[Boolean]]("deleted", O.Default(Some(false)))
    /** Database column mailbox_type DBType(VARCHAR), Length(25,true), Default(None) */
    val mailboxType: Column[Option[String]] = column[Option[String]]("mailbox_type", O.Length(25,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Foreign key referencing Notifications (database name receipts_on_notification_id) */
    lazy val notificationsFk = foreignKey("receipts_on_notification_id", notificationId, Notifications)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Receipts */
  lazy val Receipts = new TableQuery(tag => new Receipts(tag))
  
  /** Entity class storing rows of table Roles
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param resourceId Database column resource_id DBType(INT), Default(None)
   *  @param resourceType Database column resource_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class RolesRow(id: Int, name: Option[String] = None, resourceId: Option[Int] = None, resourceType: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching RolesRow objects using plain SQL queries */
  implicit def GetResultRolesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[java.sql.Timestamp]): GR[RolesRow] = GR{
    prs => import prs._
    RolesRow.tupled((<<[Int], <<?[String], <<?[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table roles. Objects of this class serve as prototypes for rows in queries. */
  class Roles(_tableTag: Tag) extends Table[RolesRow](_tableTag, "roles") {
    def * = (id, name, resourceId, resourceType, createdAt, updatedAt) <> (RolesRow.tupled, RolesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, resourceId, resourceType, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> RolesRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column resource_id DBType(INT), Default(None) */
    val resourceId: Column[Option[Int]] = column[Option[Int]]("resource_id", O.Default(None))
    /** Database column resource_type DBType(VARCHAR), Length(255,true), Default(None) */
    val resourceType: Column[Option[String]] = column[Option[String]]("resource_type", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (name) (database name index_roles_on_name) */
    val index1 = index("index_roles_on_name", name)
    /** Index over (name,resourceType,resourceId) (database name index_roles_on_name_and_resource_type_and_resource_id) */
    val index2 = index("index_roles_on_name_and_resource_type_and_resource_id", (name, resourceType, resourceId))
  }
  /** Collection-like TableQuery object for table Roles */
  lazy val Roles = new TableQuery(tag => new Roles(tag))
  
  /** Entity class storing rows of table SchemaMigrations
   *  @param version Database column version DBType(VARCHAR), Length(255,true) */
  case class SchemaMigrationsRow(version: String)
  /** GetResult implicit for fetching SchemaMigrationsRow objects using plain SQL queries */
  implicit def GetResultSchemaMigrationsRow(implicit e0: GR[String]): GR[SchemaMigrationsRow] = GR{
    prs => import prs._
    SchemaMigrationsRow(<<[String])
  }
  /** Table description of table schema_migrations. Objects of this class serve as prototypes for rows in queries. */
  class SchemaMigrations(_tableTag: Tag) extends Table[SchemaMigrationsRow](_tableTag, "schema_migrations") {
    def * = version <> (SchemaMigrationsRow, SchemaMigrationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = version.?.shaped.<>(r => r.map(_=> SchemaMigrationsRow(r.get)), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column version DBType(VARCHAR), Length(255,true) */
    val version: Column[String] = column[String]("version", O.Length(255,varying=true))
    
    /** Uniqueness Index over (version) (database name unique_schema_migrations) */
    val index1 = index("unique_schema_migrations", version, unique=true)
  }
  /** Collection-like TableQuery object for table SchemaMigrations */
  lazy val SchemaMigrations = new TableQuery(tag => new SchemaMigrations(tag))
  
  /** Entity class storing rows of table Seasons
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param num Database column num DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None) */
  case class SeasonsRow(id: Int, num: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, name: Option[String] = None)
  /** GetResult implicit for fetching SeasonsRow objects using plain SQL queries */
  implicit def GetResultSeasonsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[SeasonsRow] = GR{
    prs => import prs._
    SeasonsRow.tupled((<<[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table seasons. Objects of this class serve as prototypes for rows in queries. */
  class Seasons(_tableTag: Tag) extends Table[SeasonsRow](_tableTag, "seasons") {
    def * = (id, num, createdAt, updatedAt, name) <> (SeasonsRow.tupled, SeasonsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, num, createdAt.?, updatedAt.?, name).shaped.<>({r=>import r._; _1.map(_=> SeasonsRow.tupled((_1.get, _2, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column num DBType(INT), Default(None) */
    val num: Column[Option[Int]] = column[Option[Int]]("num", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Seasons */
  lazy val Seasons = new TableQuery(tag => new Seasons(tag))
  
  /** Entity class storing rows of table Sessions
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param sessionId Database column session_id DBType(VARCHAR), Length(255,true)
   *  @param data Database column data DBType(TEXT), Length(65535,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class SessionsRow(id: Int, sessionId: String, data: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching SessionsRow objects using plain SQL queries */
  implicit def GetResultSessionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[SessionsRow] = GR{
    prs => import prs._
    SessionsRow.tupled((<<[Int], <<[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table sessions. Objects of this class serve as prototypes for rows in queries. */
  class Sessions(_tableTag: Tag) extends Table[SessionsRow](_tableTag, "sessions") {
    def * = (id, sessionId, data, createdAt, updatedAt) <> (SessionsRow.tupled, SessionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, sessionId.?, data, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> SessionsRow.tupled((_1.get, _2.get, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column session_id DBType(VARCHAR), Length(255,true) */
    val sessionId: Column[String] = column[String]("session_id", O.Length(255,varying=true))
    /** Database column data DBType(TEXT), Length(65535,true), Default(None) */
    val data: Column[Option[String]] = column[Option[String]]("data", O.Length(65535,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (sessionId) (database name index_sessions_on_session_id) */
    val index1 = index("index_sessions_on_session_id", sessionId)
    /** Index over (updatedAt) (database name index_sessions_on_updated_at) */
    val index2 = index("index_sessions_on_updated_at", updatedAt)
  }
  /** Collection-like TableQuery object for table Sessions */
  lazy val Sessions = new TableQuery(tag => new Sessions(tag))
  
  /** Entity class storing rows of table ShortenedUrls
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param ownerId Database column owner_id DBType(INT), Default(None)
   *  @param ownerType Database column owner_type DBType(VARCHAR), Length(20,true), Default(None)
   *  @param url Database column url DBType(VARCHAR), Length(255,true)
   *  @param uniqueKey Database column unique_key DBType(VARCHAR), Length(10,true)
   *  @param useCount Database column use_count DBType(INT), Default(0)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class ShortenedUrlsRow(id: Int, ownerId: Option[Int] = None, ownerType: Option[String] = None, url: String, uniqueKey: String, useCount: Int = 0, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching ShortenedUrlsRow objects using plain SQL queries */
  implicit def GetResultShortenedUrlsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[String], e4: GR[java.sql.Timestamp]): GR[ShortenedUrlsRow] = GR{
    prs => import prs._
    ShortenedUrlsRow.tupled((<<[Int], <<?[Int], <<?[String], <<[String], <<[String], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table shortened_urls. Objects of this class serve as prototypes for rows in queries. */
  class ShortenedUrls(_tableTag: Tag) extends Table[ShortenedUrlsRow](_tableTag, "shortened_urls") {
    def * = (id, ownerId, ownerType, url, uniqueKey, useCount, createdAt, updatedAt) <> (ShortenedUrlsRow.tupled, ShortenedUrlsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, ownerId, ownerType, url.?, uniqueKey.?, useCount.?, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> ShortenedUrlsRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column owner_id DBType(INT), Default(None) */
    val ownerId: Column[Option[Int]] = column[Option[Int]]("owner_id", O.Default(None))
    /** Database column owner_type DBType(VARCHAR), Length(20,true), Default(None) */
    val ownerType: Column[Option[String]] = column[Option[String]]("owner_type", O.Length(20,varying=true), O.Default(None))
    /** Database column url DBType(VARCHAR), Length(255,true) */
    val url: Column[String] = column[String]("url", O.Length(255,varying=true))
    /** Database column unique_key DBType(VARCHAR), Length(10,true) */
    val uniqueKey: Column[String] = column[String]("unique_key", O.Length(10,varying=true))
    /** Database column use_count DBType(INT), Default(0) */
    val useCount: Column[Int] = column[Int]("use_count", O.Default(0))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (ownerId,ownerType) (database name index_shortened_urls_on_owner_id_and_owner_type) */
    val index1 = index("index_shortened_urls_on_owner_id_and_owner_type", (ownerId, ownerType))
    /** Uniqueness Index over (uniqueKey) (database name index_shortened_urls_on_unique_key) */
    val index2 = index("index_shortened_urls_on_unique_key", uniqueKey, unique=true)
    /** Index over (url) (database name index_shortened_urls_on_url) */
    val index3 = index("index_shortened_urls_on_url", url)
  }
  /** Collection-like TableQuery object for table ShortenedUrls */
  lazy val ShortenedUrls = new TableQuery(tag => new ShortenedUrls(tag))
  
  /** Entity class storing rows of table Subscriptions
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param cost Database column cost DBType(FLOAT), Default(None) */
  case class SubscriptionsRow(id: Int, name: Option[String] = None, cost: Option[Float] = None)
  /** GetResult implicit for fetching SubscriptionsRow objects using plain SQL queries */
  implicit def GetResultSubscriptionsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Float]]): GR[SubscriptionsRow] = GR{
    prs => import prs._
    SubscriptionsRow.tupled((<<[Int], <<?[String], <<?[Float]))
  }
  /** Table description of table subscriptions. Objects of this class serve as prototypes for rows in queries. */
  class Subscriptions(_tableTag: Tag) extends Table[SubscriptionsRow](_tableTag, "subscriptions") {
    def * = (id, name, cost) <> (SubscriptionsRow.tupled, SubscriptionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, cost).shaped.<>({r=>import r._; _1.map(_=> SubscriptionsRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column cost DBType(FLOAT), Default(None) */
    val cost: Column[Option[Float]] = column[Option[Float]]("cost", O.Default(None))
  }
  /** Collection-like TableQuery object for table Subscriptions */
  lazy val Subscriptions = new TableQuery(tag => new Subscriptions(tag))
  
  /** Entity class storing rows of table Taggings
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param tagId Database column tag_id DBType(INT), Default(None)
   *  @param taggableId Database column taggable_id DBType(INT), Default(None)
   *  @param taggableType Database column taggable_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param taggerId Database column tagger_id DBType(INT), Default(None)
   *  @param taggerType Database column tagger_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param context Database column context DBType(VARCHAR), Length(128,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME), Default(None) */
  case class TaggingsRow(id: Int, tagId: Option[Int] = None, taggableId: Option[Int] = None, taggableType: Option[String] = None, taggerId: Option[Int] = None, taggerType: Option[String] = None, context: Option[String] = None, createdAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching TaggingsRow objects using plain SQL queries */
  implicit def GetResultTaggingsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]]): GR[TaggingsRow] = GR{
    prs => import prs._
    TaggingsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[java.sql.Timestamp]))
  }
  /** Table description of table taggings. Objects of this class serve as prototypes for rows in queries. */
  class Taggings(_tableTag: Tag) extends Table[TaggingsRow](_tableTag, "taggings") {
    def * = (id, tagId, taggableId, taggableType, taggerId, taggerType, context, createdAt) <> (TaggingsRow.tupled, TaggingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, tagId, taggableId, taggableType, taggerId, taggerType, context, createdAt).shaped.<>({r=>import r._; _1.map(_=> TaggingsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column tag_id DBType(INT), Default(None) */
    val tagId: Column[Option[Int]] = column[Option[Int]]("tag_id", O.Default(None))
    /** Database column taggable_id DBType(INT), Default(None) */
    val taggableId: Column[Option[Int]] = column[Option[Int]]("taggable_id", O.Default(None))
    /** Database column taggable_type DBType(VARCHAR), Length(255,true), Default(None) */
    val taggableType: Column[Option[String]] = column[Option[String]]("taggable_type", O.Length(255,varying=true), O.Default(None))
    /** Database column tagger_id DBType(INT), Default(None) */
    val taggerId: Column[Option[Int]] = column[Option[Int]]("tagger_id", O.Default(None))
    /** Database column tagger_type DBType(VARCHAR), Length(255,true), Default(None) */
    val taggerType: Column[Option[String]] = column[Option[String]]("tagger_type", O.Length(255,varying=true), O.Default(None))
    /** Database column context DBType(VARCHAR), Length(128,true), Default(None) */
    val context: Column[Option[String]] = column[Option[String]]("context", O.Length(128,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME), Default(None) */
    val createdAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    
    /** Uniqueness Index over (tagId,taggableId,taggableType,context,taggerId,taggerType) (database name taggings_idx) */
    val index1 = index("taggings_idx", (tagId, taggableId, taggableType, context, taggerId, taggerType), unique=true)
  }
  /** Collection-like TableQuery object for table Taggings */
  lazy val Taggings = new TableQuery(tag => new Taggings(tag))
  
  /** Entity class storing rows of table Tags
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param taggingsCount Database column taggings_count DBType(INT), Default(Some(0)) */
  case class TagsRow(id: Int, name: Option[String] = None, taggingsCount: Option[Int] = Some(0))
  /** GetResult implicit for fetching TagsRow objects using plain SQL queries */
  implicit def GetResultTagsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[TagsRow] = GR{
    prs => import prs._
    TagsRow.tupled((<<[Int], <<?[String], <<?[Int]))
  }
  /** Table description of table tags. Objects of this class serve as prototypes for rows in queries. */
  class Tags(_tableTag: Tag) extends Table[TagsRow](_tableTag, "tags") {
    def * = (id, name, taggingsCount) <> (TagsRow.tupled, TagsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, taggingsCount).shaped.<>({r=>import r._; _1.map(_=> TagsRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column taggings_count DBType(INT), Default(Some(0)) */
    val taggingsCount: Column[Option[Int]] = column[Option[Int]]("taggings_count", O.Default(Some(0)))
    
    /** Uniqueness Index over (name) (database name index_tags_on_name) */
    val index1 = index("index_tags_on_name", name, unique=true)
  }
  /** Collection-like TableQuery object for table Tags */
  lazy val Tags = new TableQuery(tag => new Tags(tag))
  
  /** Entity class storing rows of table Teams
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param description Database column description DBType(TEXT), Length(65535,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class TeamsRow(id: Int, name: Option[String] = None, description: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching TeamsRow objects using plain SQL queries */
  implicit def GetResultTeamsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[java.sql.Timestamp]): GR[TeamsRow] = GR{
    prs => import prs._
    TeamsRow.tupled((<<[Int], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table teams. Objects of this class serve as prototypes for rows in queries. */
  class Teams(_tableTag: Tag) extends Table[TeamsRow](_tableTag, "teams") {
    def * = (id, name, description, createdAt, updatedAt) <> (TeamsRow.tupled, TeamsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, description, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> TeamsRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column description DBType(TEXT), Length(65535,true), Default(None) */
    val description: Column[Option[String]] = column[Option[String]]("description", O.Length(65535,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Teams */
  lazy val Teams = new TableQuery(tag => new Teams(tag))
  
  /** Entity class storing rows of table TeamUsers
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param teamId Database column team_id DBType(INT), Default(None) */
  case class TeamUsersRow(id: Int, userId: Option[Int] = None, teamId: Option[Int] = None)
  /** GetResult implicit for fetching TeamUsersRow objects using plain SQL queries */
  implicit def GetResultTeamUsersRow(implicit e0: GR[Int], e1: GR[Option[Int]]): GR[TeamUsersRow] = GR{
    prs => import prs._
    TeamUsersRow.tupled((<<[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table team_users. Objects of this class serve as prototypes for rows in queries. */
  class TeamUsers(_tableTag: Tag) extends Table[TeamUsersRow](_tableTag, "team_users") {
    def * = (id, userId, teamId) <> (TeamUsersRow.tupled, TeamUsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, userId, teamId).shaped.<>({r=>import r._; _1.map(_=> TeamUsersRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column team_id DBType(INT), Default(None) */
    val teamId: Column[Option[Int]] = column[Option[Int]]("team_id", O.Default(None))
  }
  /** Collection-like TableQuery object for table TeamUsers */
  lazy val TeamUsers = new TableQuery(tag => new TeamUsers(tag))
  
  /** Entity class storing rows of table Tournaments
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param startDate Database column start_date DBType(DATETIME), Default(None)
   *  @param creatorId Database column creator_id DBType(INT), Default(None)
   *  @param bracketFormat Database column bracket_format DBType(INT), Default(None)
   *  @param numPlayers Database column num_players DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param desc Database column desc DBType(VARCHAR), Length(255,true), Default(None)
   *  @param isPrivate Database column is_private DBType(BIT), Default(Some(false))
   *  @param numPods Database column num_pods DBType(INT), Default(None)
   *  @param started Database column started DBType(BIT), Default(Some(false))
   *  @param numDecks Database column num_decks DBType(INT), Default(Some(3))
   *  @param code Database column code DBType(VARCHAR), Length(255,true), Default(None)
   *  @param bestOf Database column best_of DBType(INT), Default(None) */
  case class TournamentsRow(id: Int, name: Option[String] = None, startDate: Option[java.sql.Timestamp] = None, creatorId: Option[Int] = None, bracketFormat: Option[Int] = None, numPlayers: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, desc: Option[String] = None, isPrivate: Option[Boolean] = Some(false), numPods: Option[Int] = None, started: Option[Boolean] = Some(false), numDecks: Option[Int] = Some(3), code: Option[String] = None, bestOf: Option[Int] = None)
  /** GetResult implicit for fetching TournamentsRow objects using plain SQL queries */
  implicit def GetResultTournamentsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]], e3: GR[Option[Int]], e4: GR[java.sql.Timestamp], e5: GR[Option[Boolean]]): GR[TournamentsRow] = GR{
    prs => import prs._
    TournamentsRow.tupled((<<[Int], <<?[String], <<?[java.sql.Timestamp], <<?[Int], <<?[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String], <<?[Boolean], <<?[Int], <<?[Boolean], <<?[Int], <<?[String], <<?[Int]))
  }
  /** Table description of table tournaments. Objects of this class serve as prototypes for rows in queries. */
  class Tournaments(_tableTag: Tag) extends Table[TournamentsRow](_tableTag, "tournaments") {
    def * = (id, name, startDate, creatorId, bracketFormat, numPlayers, createdAt, updatedAt, desc, isPrivate, numPods, started, numDecks, code, bestOf) <> (TournamentsRow.tupled, TournamentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, startDate, creatorId, bracketFormat, numPlayers, createdAt.?, updatedAt.?, desc, isPrivate, numPods, started, numDecks, code, bestOf).shaped.<>({r=>import r._; _1.map(_=> TournamentsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7.get, _8.get, _9, _10, _11, _12, _13, _14, _15)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
    /** Database column start_date DBType(DATETIME), Default(None) */
    val startDate: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("start_date", O.Default(None))
    /** Database column creator_id DBType(INT), Default(None) */
    val creatorId: Column[Option[Int]] = column[Option[Int]]("creator_id", O.Default(None))
    /** Database column bracket_format DBType(INT), Default(None) */
    val bracketFormat: Column[Option[Int]] = column[Option[Int]]("bracket_format", O.Default(None))
    /** Database column num_players DBType(INT), Default(None) */
    val numPlayers: Column[Option[Int]] = column[Option[Int]]("num_players", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column desc DBType(VARCHAR), Length(255,true), Default(None) */
    val desc: Column[Option[String]] = column[Option[String]]("desc", O.Length(255,varying=true), O.Default(None))
    /** Database column is_private DBType(BIT), Default(Some(false)) */
    val isPrivate: Column[Option[Boolean]] = column[Option[Boolean]]("is_private", O.Default(Some(false)))
    /** Database column num_pods DBType(INT), Default(None) */
    val numPods: Column[Option[Int]] = column[Option[Int]]("num_pods", O.Default(None))
    /** Database column started DBType(BIT), Default(Some(false)) */
    val started: Column[Option[Boolean]] = column[Option[Boolean]]("started", O.Default(Some(false)))
    /** Database column num_decks DBType(INT), Default(Some(3)) */
    val numDecks: Column[Option[Int]] = column[Option[Int]]("num_decks", O.Default(Some(3)))
    /** Database column code DBType(VARCHAR), Length(255,true), Default(None) */
    val code: Column[Option[String]] = column[Option[String]]("code", O.Length(255,varying=true), O.Default(None))
    /** Database column best_of DBType(INT), Default(None) */
    val bestOf: Column[Option[Int]] = column[Option[Int]]("best_of", O.Default(None))
  }
  /** Collection-like TableQuery object for table Tournaments */
  lazy val Tournaments = new TableQuery(tag => new Tournaments(tag))
  
  /** Entity class storing rows of table TournDecks
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param tournamentId Database column tournament_id DBType(INT), Default(None)
   *  @param tournUserId Database column tourn_user_id DBType(INT), Default(None)
   *  @param deckId Database column deck_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class TournDecksRow(id: Int, tournamentId: Option[Int] = None, tournUserId: Option[Int] = None, deckId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching TournDecksRow objects using plain SQL queries */
  implicit def GetResultTournDecksRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp]): GR[TournDecksRow] = GR{
    prs => import prs._
    TournDecksRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table tourn_decks. Objects of this class serve as prototypes for rows in queries. */
  class TournDecks(_tableTag: Tag) extends Table[TournDecksRow](_tableTag, "tourn_decks") {
    def * = (id, tournamentId, tournUserId, deckId, createdAt, updatedAt) <> (TournDecksRow.tupled, TournDecksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, tournamentId, tournUserId, deckId, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> TournDecksRow.tupled((_1.get, _2, _3, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column tournament_id DBType(INT), Default(None) */
    val tournamentId: Column[Option[Int]] = column[Option[Int]]("tournament_id", O.Default(None))
    /** Database column tourn_user_id DBType(INT), Default(None) */
    val tournUserId: Column[Option[Int]] = column[Option[Int]]("tourn_user_id", O.Default(None))
    /** Database column deck_id DBType(INT), Default(None) */
    val deckId: Column[Option[Int]] = column[Option[Int]]("deck_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table TournDecks */
  lazy val TournDecks = new TableQuery(tag => new TournDecks(tag))
  
  /** Entity class storing rows of table Tournies
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param challongeId Database column challonge_id DBType(INT), Default(None)
   *  @param status Database column status DBType(INT), Default(Some(0))
   *  @param winnerId Database column winner_id DBType(INT), Default(None)
   *  @param prize Database column prize DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param complete Database column complete DBType(BIT), Default(Some(false))
   *  @param userDecksId Database column user_decks_id DBType(INT), Default(None)
   *  @param title Database column title DBType(VARCHAR), Length(255,true), Default(None)
   *  @param desc Database column desc DBType(VARCHAR), Length(255,true), Default(None)
   *  @param date Database column date DBType(DATETIME), Default(None)
   *  @param picLink Database column pic_link DBType(VARCHAR), Length(255,true), Default(None) */
  case class TourniesRow(id: Int, challongeId: Option[Int] = None, status: Option[Int] = Some(0), winnerId: Option[Int] = None, prize: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, complete: Option[Boolean] = Some(false), userDecksId: Option[Int] = None, title: Option[String] = None, desc: Option[String] = None, date: Option[java.sql.Timestamp] = None, picLink: Option[String] = None)
  /** GetResult implicit for fetching TourniesRow objects using plain SQL queries */
  implicit def GetResultTourniesRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[Boolean]], e5: GR[Option[java.sql.Timestamp]]): GR[TourniesRow] = GR{
    prs => import prs._
    TourniesRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Int], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Boolean], <<?[Int], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[String]))
  }
  /** Table description of table tournies. Objects of this class serve as prototypes for rows in queries. */
  class Tournies(_tableTag: Tag) extends Table[TourniesRow](_tableTag, "tournies") {
    def * = (id, challongeId, status, winnerId, prize, createdAt, updatedAt, complete, userDecksId, title, desc, date, picLink) <> (TourniesRow.tupled, TourniesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, challongeId, status, winnerId, prize, createdAt.?, updatedAt.?, complete, userDecksId, title, desc, date, picLink).shaped.<>({r=>import r._; _1.map(_=> TourniesRow.tupled((_1.get, _2, _3, _4, _5, _6.get, _7.get, _8, _9, _10, _11, _12, _13)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column challonge_id DBType(INT), Default(None) */
    val challongeId: Column[Option[Int]] = column[Option[Int]]("challonge_id", O.Default(None))
    /** Database column status DBType(INT), Default(Some(0)) */
    val status: Column[Option[Int]] = column[Option[Int]]("status", O.Default(Some(0)))
    /** Database column winner_id DBType(INT), Default(None) */
    val winnerId: Column[Option[Int]] = column[Option[Int]]("winner_id", O.Default(None))
    /** Database column prize DBType(VARCHAR), Length(255,true), Default(None) */
    val prize: Column[Option[String]] = column[Option[String]]("prize", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column complete DBType(BIT), Default(Some(false)) */
    val complete: Column[Option[Boolean]] = column[Option[Boolean]]("complete", O.Default(Some(false)))
    /** Database column user_decks_id DBType(INT), Default(None) */
    val userDecksId: Column[Option[Int]] = column[Option[Int]]("user_decks_id", O.Default(None))
    /** Database column title DBType(VARCHAR), Length(255,true), Default(None) */
    val title: Column[Option[String]] = column[Option[String]]("title", O.Length(255,varying=true), O.Default(None))
    /** Database column desc DBType(VARCHAR), Length(255,true), Default(None) */
    val desc: Column[Option[String]] = column[Option[String]]("desc", O.Length(255,varying=true), O.Default(None))
    /** Database column date DBType(DATETIME), Default(None) */
    val date: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date", O.Default(None))
    /** Database column pic_link DBType(VARCHAR), Length(255,true), Default(None) */
    val picLink: Column[Option[String]] = column[Option[String]]("pic_link", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Tournies */
  lazy val Tournies = new TableQuery(tag => new Tournies(tag))
  
  /** Entity class storing rows of table TournMatches
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param tournPairId Database column tourn_pair_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param resultId Database column result_id DBType(INT), Default(None)
   *  @param tournUserId Database column tourn_user_id DBType(INT), Default(None)
   *  @param coin Database column coin DBType(BIT), Default(None)
   *  @param round Database column round DBType(INT), Default(None)
   *  @param tournDeckId Database column tourn_deck_id DBType(INT), Default(None) */
  case class TournMatchesRow(id: Int, tournPairId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, resultId: Option[Int] = None, tournUserId: Option[Int] = None, coin: Option[Boolean] = None, round: Option[Int] = None, tournDeckId: Option[Int] = None)
  /** GetResult implicit for fetching TournMatchesRow objects using plain SQL queries */
  implicit def GetResultTournMatchesRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp], e3: GR[Option[Boolean]]): GR[TournMatchesRow] = GR{
    prs => import prs._
    TournMatchesRow.tupled((<<[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[Int], <<?[Boolean], <<?[Int], <<?[Int]))
  }
  /** Table description of table tourn_matches. Objects of this class serve as prototypes for rows in queries. */
  class TournMatches(_tableTag: Tag) extends Table[TournMatchesRow](_tableTag, "tourn_matches") {
    def * = (id, tournPairId, createdAt, updatedAt, resultId, tournUserId, coin, round, tournDeckId) <> (TournMatchesRow.tupled, TournMatchesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, tournPairId, createdAt.?, updatedAt.?, resultId, tournUserId, coin, round, tournDeckId).shaped.<>({r=>import r._; _1.map(_=> TournMatchesRow.tupled((_1.get, _2, _3.get, _4.get, _5, _6, _7, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column tourn_pair_id DBType(INT), Default(None) */
    val tournPairId: Column[Option[Int]] = column[Option[Int]]("tourn_pair_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column result_id DBType(INT), Default(None) */
    val resultId: Column[Option[Int]] = column[Option[Int]]("result_id", O.Default(None))
    /** Database column tourn_user_id DBType(INT), Default(None) */
    val tournUserId: Column[Option[Int]] = column[Option[Int]]("tourn_user_id", O.Default(None))
    /** Database column coin DBType(BIT), Default(None) */
    val coin: Column[Option[Boolean]] = column[Option[Boolean]]("coin", O.Default(None))
    /** Database column round DBType(INT), Default(None) */
    val round: Column[Option[Int]] = column[Option[Int]]("round", O.Default(None))
    /** Database column tourn_deck_id DBType(INT), Default(None) */
    val tournDeckId: Column[Option[Int]] = column[Option[Int]]("tourn_deck_id", O.Default(None))
  }
  /** Collection-like TableQuery object for table TournMatches */
  lazy val TournMatches = new TableQuery(tag => new TournMatches(tag))
  
  /** Entity class storing rows of table TournPairs
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param tournamentId Database column tournament_id DBType(INT), Default(None)
   *  @param roundof Database column roundof DBType(INT), Default(None)
   *  @param pos Database column pos DBType(INT), Default(None)
   *  @param p1Id Database column p1_id DBType(INT), Default(None)
   *  @param p2Id Database column p2_id DBType(INT), Default(None)
   *  @param winners Database column winners DBType(BIT), Default(None)
   *  @param winnerId Database column winner_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param screenshotFileName Database column screenshot_file_name DBType(VARCHAR), Length(255,true), Default(None)
   *  @param screenshotContentType Database column screenshot_content_type DBType(VARCHAR), Length(255,true), Default(None)
   *  @param screenshotFileSize Database column screenshot_file_size DBType(INT), Default(None)
   *  @param screenshotUpdatedAt Database column screenshot_updated_at DBType(DATETIME), Default(None)
   *  @param undecided Database column undecided DBType(INT), Default(None) */
  case class TournPairsRow(id: Int, tournamentId: Option[Int] = None, roundof: Option[Int] = None, pos: Option[Int] = None, p1Id: Option[Int] = None, p2Id: Option[Int] = None, winners: Option[Boolean] = None, winnerId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, screenshotFileName: Option[String] = None, screenshotContentType: Option[String] = None, screenshotFileSize: Option[Int] = None, screenshotUpdatedAt: Option[java.sql.Timestamp] = None, undecided: Option[Int] = None)
  /** GetResult implicit for fetching TournPairsRow objects using plain SQL queries */
  implicit def GetResultTournPairsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[Option[Boolean]], e3: GR[java.sql.Timestamp], e4: GR[Option[String]], e5: GR[Option[java.sql.Timestamp]]): GR[TournPairsRow] = GR{
    prs => import prs._
    TournPairsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Boolean], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[Int], <<?[java.sql.Timestamp], <<?[Int]))
  }
  /** Table description of table tourn_pairs. Objects of this class serve as prototypes for rows in queries. */
  class TournPairs(_tableTag: Tag) extends Table[TournPairsRow](_tableTag, "tourn_pairs") {
    def * = (id, tournamentId, roundof, pos, p1Id, p2Id, winners, winnerId, createdAt, updatedAt, screenshotFileName, screenshotContentType, screenshotFileSize, screenshotUpdatedAt, undecided) <> (TournPairsRow.tupled, TournPairsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, tournamentId, roundof, pos, p1Id, p2Id, winners, winnerId, createdAt.?, updatedAt.?, screenshotFileName, screenshotContentType, screenshotFileSize, screenshotUpdatedAt, undecided).shaped.<>({r=>import r._; _1.map(_=> TournPairsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9.get, _10.get, _11, _12, _13, _14, _15)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column tournament_id DBType(INT), Default(None) */
    val tournamentId: Column[Option[Int]] = column[Option[Int]]("tournament_id", O.Default(None))
    /** Database column roundof DBType(INT), Default(None) */
    val roundof: Column[Option[Int]] = column[Option[Int]]("roundof", O.Default(None))
    /** Database column pos DBType(INT), Default(None) */
    val pos: Column[Option[Int]] = column[Option[Int]]("pos", O.Default(None))
    /** Database column p1_id DBType(INT), Default(None) */
    val p1Id: Column[Option[Int]] = column[Option[Int]]("p1_id", O.Default(None))
    /** Database column p2_id DBType(INT), Default(None) */
    val p2Id: Column[Option[Int]] = column[Option[Int]]("p2_id", O.Default(None))
    /** Database column winners DBType(BIT), Default(None) */
    val winners: Column[Option[Boolean]] = column[Option[Boolean]]("winners", O.Default(None))
    /** Database column winner_id DBType(INT), Default(None) */
    val winnerId: Column[Option[Int]] = column[Option[Int]]("winner_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column screenshot_file_name DBType(VARCHAR), Length(255,true), Default(None) */
    val screenshotFileName: Column[Option[String]] = column[Option[String]]("screenshot_file_name", O.Length(255,varying=true), O.Default(None))
    /** Database column screenshot_content_type DBType(VARCHAR), Length(255,true), Default(None) */
    val screenshotContentType: Column[Option[String]] = column[Option[String]]("screenshot_content_type", O.Length(255,varying=true), O.Default(None))
    /** Database column screenshot_file_size DBType(INT), Default(None) */
    val screenshotFileSize: Column[Option[Int]] = column[Option[Int]]("screenshot_file_size", O.Default(None))
    /** Database column screenshot_updated_at DBType(DATETIME), Default(None) */
    val screenshotUpdatedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("screenshot_updated_at", O.Default(None))
    /** Database column undecided DBType(INT), Default(None) */
    val undecided: Column[Option[Int]] = column[Option[Int]]("undecided", O.Default(None))
  }
  /** Collection-like TableQuery object for table TournPairs */
  lazy val TournPairs = new TableQuery(tag => new TournPairs(tag))
  
  /** Entity class storing rows of table TournUsers
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param tournamentId Database column tournament_id DBType(INT), Default(None)
   *  @param userId Database column user_id DBType(INT), Default(None) */
  case class TournUsersRow(id: Int, tournamentId: Option[Int] = None, userId: Option[Int] = None)
  /** GetResult implicit for fetching TournUsersRow objects using plain SQL queries */
  implicit def GetResultTournUsersRow(implicit e0: GR[Int], e1: GR[Option[Int]]): GR[TournUsersRow] = GR{
    prs => import prs._
    TournUsersRow.tupled((<<[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table tourn_users. Objects of this class serve as prototypes for rows in queries. */
  class TournUsers(_tableTag: Tag) extends Table[TournUsersRow](_tableTag, "tourn_users") {
    def * = (id, tournamentId, userId) <> (TournUsersRow.tupled, TournUsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, tournamentId, userId).shaped.<>({r=>import r._; _1.map(_=> TournUsersRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column tournament_id DBType(INT), Default(None) */
    val tournamentId: Column[Option[Int]] = column[Option[Int]]("tournament_id", O.Default(None))
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    
    /** Index over (userId) (database name index_tourn_users_on_user_id) */
    val index1 = index("index_tourn_users_on_user_id", userId)
  }
  /** Collection-like TableQuery object for table TournUsers */
  lazy val TournUsers = new TableQuery(tag => new TournUsers(tag))
  
  /** Entity class storing rows of table Types
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None) */
  case class TypesRow(id: Int, name: Option[String] = None)
  /** GetResult implicit for fetching TypesRow objects using plain SQL queries */
  implicit def GetResultTypesRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[TypesRow] = GR{
    prs => import prs._
    TypesRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table types. Objects of this class serve as prototypes for rows in queries. */
  class Types(_tableTag: Tag) extends Table[TypesRow](_tableTag, "types") {
    def * = (id, name) <> (TypesRow.tupled, TypesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name).shaped.<>({r=>import r._; _1.map(_=> TypesRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Types */
  lazy val Types = new TableQuery(tag => new Types(tag))
  
  /** Entity class storing rows of table UniqueDeckCards
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param uniqueDeckId Database column unique_deck_id DBType(INT), Default(None)
   *  @param cardId Database column card_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME) */
  case class UniqueDeckCardsRow(id: Int, uniqueDeckId: Option[Int] = None, cardId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching UniqueDeckCardsRow objects using plain SQL queries */
  implicit def GetResultUniqueDeckCardsRow(implicit e0: GR[Int], e1: GR[Option[Int]], e2: GR[java.sql.Timestamp]): GR[UniqueDeckCardsRow] = GR{
    prs => import prs._
    UniqueDeckCardsRow.tupled((<<[Int], <<?[Int], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table unique_deck_cards. Objects of this class serve as prototypes for rows in queries. */
  class UniqueDeckCards(_tableTag: Tag) extends Table[UniqueDeckCardsRow](_tableTag, "unique_deck_cards") {
    def * = (id, uniqueDeckId, cardId, createdAt, updatedAt) <> (UniqueDeckCardsRow.tupled, UniqueDeckCardsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, uniqueDeckId, cardId, createdAt.?, updatedAt.?).shaped.<>({r=>import r._; _1.map(_=> UniqueDeckCardsRow.tupled((_1.get, _2, _3, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column unique_deck_id DBType(INT), Default(None) */
    val uniqueDeckId: Column[Option[Int]] = column[Option[Int]]("unique_deck_id", O.Default(None))
    /** Database column card_id DBType(INT), Default(None) */
    val cardId: Column[Option[Int]] = column[Option[Int]]("card_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    
    /** Index over (uniqueDeckId) (database name index_unique_deck_cards_on_unique_deck_id) */
    val index1 = index("index_unique_deck_cards_on_unique_deck_id", uniqueDeckId)
  }
  /** Collection-like TableQuery object for table UniqueDeckCards */
  lazy val UniqueDeckCards = new TableQuery(tag => new UniqueDeckCards(tag))
  
  /** Entity class storing rows of table UniqueDecks
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param cardstring Database column cardstring DBType(VARCHAR), Length(255,true), Default(None)
   *  @param klassId Database column klass_id DBType(INT), Default(None)
   *  @param numMatches Database column num_matches DBType(INT), Default(None)
   *  @param numWins Database column num_wins DBType(INT), Default(None)
   *  @param numLosses Database column num_losses DBType(INT), Default(None)
   *  @param numMinions Database column num_minions DBType(INT), Default(None)
   *  @param numSpells Database column num_spells DBType(INT), Default(None)
   *  @param numWeapons Database column num_weapons DBType(INT), Default(None)
   *  @param lastPlayed Database column last_played DBType(DATETIME), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param winrate Database column winrate DBType(FLOAT), Default(None)
   *  @param numUsers Database column num_users DBType(INT), Default(None)
   *  @param manaCost Database column mana_cost DBType(INT), Default(None)
   *  @param uniqueDeckTypeId Database column unique_deck_type_id DBType(INT), Default(None) */
  case class UniqueDecksRow(id: Int, cardstring: Option[String] = None, klassId: Option[Int] = None, numMatches: Option[Int] = None, numWins: Option[Int] = None, numLosses: Option[Int] = None, numMinions: Option[Int] = None, numSpells: Option[Int] = None, numWeapons: Option[Int] = None, lastPlayed: Option[java.sql.Timestamp] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, winrate: Option[Float] = None, numUsers: Option[Int] = None, manaCost: Option[Int] = None, uniqueDeckTypeId: Option[Int] = None)
  /** GetResult implicit for fetching UniqueDecksRow objects using plain SQL queries */
  implicit def GetResultUniqueDecksRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[java.sql.Timestamp]], e4: GR[java.sql.Timestamp], e5: GR[Option[Float]]): GR[UniqueDecksRow] = GR{
    prs => import prs._
    UniqueDecksRow.tupled((<<[Int], <<?[String], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Float], <<?[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table unique_decks. Objects of this class serve as prototypes for rows in queries. */
  class UniqueDecks(_tableTag: Tag) extends Table[UniqueDecksRow](_tableTag, "unique_decks") {
    def * = (id, cardstring, klassId, numMatches, numWins, numLosses, numMinions, numSpells, numWeapons, lastPlayed, createdAt, updatedAt, winrate, numUsers, manaCost, uniqueDeckTypeId) <> (UniqueDecksRow.tupled, UniqueDecksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, cardstring, klassId, numMatches, numWins, numLosses, numMinions, numSpells, numWeapons, lastPlayed, createdAt.?, updatedAt.?, winrate, numUsers, manaCost, uniqueDeckTypeId).shaped.<>({r=>import r._; _1.map(_=> UniqueDecksRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11.get, _12.get, _13, _14, _15, _16)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column cardstring DBType(VARCHAR), Length(255,true), Default(None) */
    val cardstring: Column[Option[String]] = column[Option[String]]("cardstring", O.Length(255,varying=true), O.Default(None))
    /** Database column klass_id DBType(INT), Default(None) */
    val klassId: Column[Option[Int]] = column[Option[Int]]("klass_id", O.Default(None))
    /** Database column num_matches DBType(INT), Default(None) */
    val numMatches: Column[Option[Int]] = column[Option[Int]]("num_matches", O.Default(None))
    /** Database column num_wins DBType(INT), Default(None) */
    val numWins: Column[Option[Int]] = column[Option[Int]]("num_wins", O.Default(None))
    /** Database column num_losses DBType(INT), Default(None) */
    val numLosses: Column[Option[Int]] = column[Option[Int]]("num_losses", O.Default(None))
    /** Database column num_minions DBType(INT), Default(None) */
    val numMinions: Column[Option[Int]] = column[Option[Int]]("num_minions", O.Default(None))
    /** Database column num_spells DBType(INT), Default(None) */
    val numSpells: Column[Option[Int]] = column[Option[Int]]("num_spells", O.Default(None))
    /** Database column num_weapons DBType(INT), Default(None) */
    val numWeapons: Column[Option[Int]] = column[Option[Int]]("num_weapons", O.Default(None))
    /** Database column last_played DBType(DATETIME), Default(None) */
    val lastPlayed: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_played", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column winrate DBType(FLOAT), Default(None) */
    val winrate: Column[Option[Float]] = column[Option[Float]]("winrate", O.Default(None))
    /** Database column num_users DBType(INT), Default(None) */
    val numUsers: Column[Option[Int]] = column[Option[Int]]("num_users", O.Default(None))
    /** Database column mana_cost DBType(INT), Default(None) */
    val manaCost: Column[Option[Int]] = column[Option[Int]]("mana_cost", O.Default(None))
    /** Database column unique_deck_type_id DBType(INT), Default(None) */
    val uniqueDeckTypeId: Column[Option[Int]] = column[Option[Int]]("unique_deck_type_id", O.Default(None))
    
    /** Index over (cardstring) (database name index_unique_decks_on_cardstring) */
    val index1 = index("index_unique_decks_on_cardstring", cardstring)
  }
  /** Collection-like TableQuery object for table UniqueDecks */
  lazy val UniqueDecks = new TableQuery(tag => new UniqueDecks(tag))
  
  /** Entity class storing rows of table UniqueDeckTypes
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param matchString Database column match_string DBType(VARCHAR), Length(255,true), Default(None)
   *  @param archtypeId Database column archtype_id DBType(INT), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param klassId Database column klass_id DBType(INT), Default(None)
   *  @param name Database column name DBType(VARCHAR), Length(255,true), Default(None) */
  case class UniqueDeckTypesRow(id: Int, matchString: Option[String] = None, archtypeId: Option[Int] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, klassId: Option[Int] = None, name: Option[String] = None)
  /** GetResult implicit for fetching UniqueDeckTypesRow objects using plain SQL queries */
  implicit def GetResultUniqueDeckTypesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[java.sql.Timestamp]): GR[UniqueDeckTypesRow] = GR{
    prs => import prs._
    UniqueDeckTypesRow.tupled((<<[Int], <<?[String], <<?[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[String]))
  }
  /** Table description of table unique_deck_types. Objects of this class serve as prototypes for rows in queries. */
  class UniqueDeckTypes(_tableTag: Tag) extends Table[UniqueDeckTypesRow](_tableTag, "unique_deck_types") {
    def * = (id, matchString, archtypeId, createdAt, updatedAt, klassId, name) <> (UniqueDeckTypesRow.tupled, UniqueDeckTypesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, matchString, archtypeId, createdAt.?, updatedAt.?, klassId, name).shaped.<>({r=>import r._; _1.map(_=> UniqueDeckTypesRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column match_string DBType(VARCHAR), Length(255,true), Default(None) */
    val matchString: Column[Option[String]] = column[Option[String]]("match_string", O.Length(255,varying=true), O.Default(None))
    /** Database column archtype_id DBType(INT), Default(None) */
    val archtypeId: Column[Option[Int]] = column[Option[Int]]("archtype_id", O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column klass_id DBType(INT), Default(None) */
    val klassId: Column[Option[Int]] = column[Option[Int]]("klass_id", O.Default(None))
    /** Database column name DBType(VARCHAR), Length(255,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table UniqueDeckTypes */
  lazy val UniqueDeckTypes = new TableQuery(tag => new UniqueDeckTypes(tag))
  
  /** Entity class storing rows of table Users
   *  @param id Database column id DBType(INT), AutoInc, PrimaryKey
   *  @param email Database column email DBType(VARCHAR), Length(255,true), Default()
   *  @param encryptedPassword Database column encrypted_password DBType(VARCHAR), Length(255,true), Default()
   *  @param resetPasswordToken Database column reset_password_token DBType(VARCHAR), Length(255,true), Default(None)
   *  @param resetPasswordSentAt Database column reset_password_sent_at DBType(DATETIME), Default(None)
   *  @param rememberCreatedAt Database column remember_created_at DBType(DATETIME), Default(None)
   *  @param signInCount Database column sign_in_count DBType(INT), Default(0)
   *  @param currentSignInAt Database column current_sign_in_at DBType(DATETIME), Default(None)
   *  @param lastSignInAt Database column last_sign_in_at DBType(DATETIME), Default(None)
   *  @param currentSignInIp Database column current_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None)
   *  @param lastSignInIp Database column last_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param tournyId Database column tourny_id DBType(INT), Default(None)
   *  @param guest Database column guest DBType(BIT), Default(None)
   *  @param userkey Database column userkey DBType(VARCHAR), Length(255,true), Default(None)
   *  @param subscriptionId Database column subscription_id DBType(INT), Default(None)
   *  @param authenticationToken Database column authentication_token DBType(VARCHAR), Length(255,true), Default(None)
   *  @param customerId Database column customer_id DBType(VARCHAR), Length(255,true), Default(None)
   *  @param noEmail Database column no_email DBType(BIT), Default(Some(false)) */
  case class UsersRow(id: Int, email: String = "", encryptedPassword: String = "", resetPasswordToken: Option[String] = None, resetPasswordSentAt: Option[java.sql.Timestamp] = None, rememberCreatedAt: Option[java.sql.Timestamp] = None, signInCount: Int = 0, currentSignInAt: Option[java.sql.Timestamp] = None, lastSignInAt: Option[java.sql.Timestamp] = None, currentSignInIp: Option[String] = None, lastSignInIp: Option[String] = None, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, tournyId: Option[Int] = None, guest: Option[Boolean] = None, userkey: Option[String] = None, subscriptionId: Option[Int] = None, authenticationToken: Option[String] = None, customerId: Option[String] = None, noEmail: Option[Boolean] = Some(false))
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]], e4: GR[java.sql.Timestamp], e5: GR[Option[Int]], e6: GR[Option[Boolean]]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[Int], <<?[Boolean], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[Boolean]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends Table[UsersRow](_tableTag, "users") {
    def * = (id, email, encryptedPassword, resetPasswordToken, resetPasswordSentAt, rememberCreatedAt, signInCount, currentSignInAt, lastSignInAt, currentSignInIp, lastSignInIp, createdAt, updatedAt, tournyId, guest, userkey, subscriptionId, authenticationToken, customerId, noEmail) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, email.?, encryptedPassword.?, resetPasswordToken, resetPasswordSentAt, rememberCreatedAt, signInCount.?, currentSignInAt, lastSignInAt, currentSignInIp, lastSignInIp, createdAt.?, updatedAt.?, tournyId, guest, userkey, subscriptionId, authenticationToken, customerId, noEmail).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7.get, _8, _9, _10, _11, _12.get, _13.get, _14, _15, _16, _17, _18, _19, _20)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(INT), AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email DBType(VARCHAR), Length(255,true), Default() */
    val email: Column[String] = column[String]("email", O.Length(255,varying=true), O.Default(""))
    /** Database column encrypted_password DBType(VARCHAR), Length(255,true), Default() */
    val encryptedPassword: Column[String] = column[String]("encrypted_password", O.Length(255,varying=true), O.Default(""))
    /** Database column reset_password_token DBType(VARCHAR), Length(255,true), Default(None) */
    val resetPasswordToken: Column[Option[String]] = column[Option[String]]("reset_password_token", O.Length(255,varying=true), O.Default(None))
    /** Database column reset_password_sent_at DBType(DATETIME), Default(None) */
    val resetPasswordSentAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("reset_password_sent_at", O.Default(None))
    /** Database column remember_created_at DBType(DATETIME), Default(None) */
    val rememberCreatedAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("remember_created_at", O.Default(None))
    /** Database column sign_in_count DBType(INT), Default(0) */
    val signInCount: Column[Int] = column[Int]("sign_in_count", O.Default(0))
    /** Database column current_sign_in_at DBType(DATETIME), Default(None) */
    val currentSignInAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("current_sign_in_at", O.Default(None))
    /** Database column last_sign_in_at DBType(DATETIME), Default(None) */
    val lastSignInAt: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_sign_in_at", O.Default(None))
    /** Database column current_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None) */
    val currentSignInIp: Column[Option[String]] = column[Option[String]]("current_sign_in_ip", O.Length(255,varying=true), O.Default(None))
    /** Database column last_sign_in_ip DBType(VARCHAR), Length(255,true), Default(None) */
    val lastSignInIp: Column[Option[String]] = column[Option[String]]("last_sign_in_ip", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column tourny_id DBType(INT), Default(None) */
    val tournyId: Column[Option[Int]] = column[Option[Int]]("tourny_id", O.Default(None))
    /** Database column guest DBType(BIT), Default(None) */
    val guest: Column[Option[Boolean]] = column[Option[Boolean]]("guest", O.Default(None))
    /** Database column userkey DBType(VARCHAR), Length(255,true), Default(None) */
    val userkey: Column[Option[String]] = column[Option[String]]("userkey", O.Length(255,varying=true), O.Default(None))
    /** Database column subscription_id DBType(INT), Default(None) */
    val subscriptionId: Column[Option[Int]] = column[Option[Int]]("subscription_id", O.Default(None))
    /** Database column authentication_token DBType(VARCHAR), Length(255,true), Default(None) */
    val authenticationToken: Column[Option[String]] = column[Option[String]]("authentication_token", O.Length(255,varying=true), O.Default(None))
    /** Database column customer_id DBType(VARCHAR), Length(255,true), Default(None) */
    val customerId: Column[Option[String]] = column[Option[String]]("customer_id", O.Length(255,varying=true), O.Default(None))
    /** Database column no_email DBType(BIT), Default(Some(false)) */
    val noEmail: Column[Option[Boolean]] = column[Option[Boolean]]("no_email", O.Default(Some(false)))
    
    /** Uniqueness Index over (email) (database name index_users_on_email) */
    val index1 = index("index_users_on_email", email, unique=true)
    /** Uniqueness Index over (resetPasswordToken) (database name index_users_on_reset_password_token) */
    val index2 = index("index_users_on_reset_password_token", resetPasswordToken, unique=true)
    /** Index over (tournyId) (database name index_users_on_tourny_id) */
    val index3 = index("index_users_on_tourny_id", tournyId)
    /** Index over (userkey) (database name index_users_on_userkey) */
    val index4 = index("index_users_on_userkey", userkey)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
  
  /** Entity class storing rows of table UsersRoles
   *  @param userId Database column user_id DBType(INT), Default(None)
   *  @param roleId Database column role_id DBType(INT), Default(None) */
  case class UsersRolesRow(userId: Option[Int] = None, roleId: Option[Int] = None)
  /** GetResult implicit for fetching UsersRolesRow objects using plain SQL queries */
  implicit def GetResultUsersRolesRow(implicit e0: GR[Option[Int]]): GR[UsersRolesRow] = GR{
    prs => import prs._
    UsersRolesRow.tupled((<<?[Int], <<?[Int]))
  }
  /** Table description of table users_roles. Objects of this class serve as prototypes for rows in queries. */
  class UsersRoles(_tableTag: Tag) extends Table[UsersRolesRow](_tableTag, "users_roles") {
    def * = (userId, roleId) <> (UsersRolesRow.tupled, UsersRolesRow.unapply)
    
    /** Database column user_id DBType(INT), Default(None) */
    val userId: Column[Option[Int]] = column[Option[Int]]("user_id", O.Default(None))
    /** Database column role_id DBType(INT), Default(None) */
    val roleId: Column[Option[Int]] = column[Option[Int]]("role_id", O.Default(None))
    
    /** Index over (userId,roleId) (database name index_users_roles_on_user_id_and_role_id) */
    val index1 = index("index_users_roles_on_user_id_and_role_id", (userId, roleId))
  }
  /** Collection-like TableQuery object for table UsersRoles */
  lazy val UsersRoles = new TableQuery(tag => new UsersRoles(tag))
}