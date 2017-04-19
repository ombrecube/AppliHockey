package prog.teampoule.applitest.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.ExifInterface;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Noemie on 27/03/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ConseilsBDD";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("OncreateDB","Je suis la");
        String ScriptDB=" ";

        db.execSQL("DROP TABLE IF EXISTS detailsconseils");
        db.execSQL("DROP TABLE IF EXISTS titreconseils");
        db.execSQL("DROP TABLE IF EXISTS evenements");

        // SQL statement to create Titre table
        String CREATE_TITRE_TABLE = "CREATE TABLE titreconseils ( " +
                "id_titre INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom_titre TEXT );";
        //ScriptDB += CREATE_TITRE_TABLE;

        // SQL statement to create Details table
        String CREATE_DETAILS_TABLE = "CREATE TABLE detailsconseils ( " +
                "id_details INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_titre INTEGER,"+
                "nom_details TEXT,"+
                "contenu_details TEXT ) ";
        ScriptDB += CREATE_DETAILS_TABLE;

        String CREATE_EVENEMENTS_TABLE = "CREATE TABLE evenements ( " +
                "id_evenement INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom_evenement TEXT ," +
                "date_evenement TEXT );";
        ScriptDB += CREATE_EVENEMENTS_TABLE;

        // create all the tables

        String ALTER_TABLE_FOREIGN_KEY_ID_DETAILS = "ALTER TABLE detailsconseils " +
                "ADD CONSTRAINT fk_id_titre FOREIGN KEY (id_titre) REFERENCES titreconseils(id_titre)";
        ScriptDB += ALTER_TABLE_FOREIGN_KEY_ID_DETAILS;

        String INSERT_TITRE_TABLE = "INSERT INTO titreconseils (nom_titre) VALUES (\"Patins\");"+
                "INSERT INTO titreconseils (nom_titre) VALUES (\"Bâtons\");"+
                "INSERT INTO titreconseils (nom_titre) VALUES (\"Protections\");";
        ScriptDB += INSERT_TITRE_TABLE;


        String INSERT_EVENT = "INSERT INTO evenements (nom_evenement, date_evenement) VALUES (\"Test event bdd\", \"1492315200000\");";

        String INSERT_EVENT2 = "INSERT INTO evenements (nom_evenement, date_evenement) VALUES (\"Go Sags Go !!\", \"1492488000000\");";


        String INSERT_DETAILS_TABLE_PATINS_1 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (1, \"Choisir correctement\", "+
                "\"\t\tPour la pointure appropriée, pensez à vous renseigner (sur internet ou auprès d'un vendeur), "+
                "ce n'est pas la même pointure que pour vos chaussures. "+
                "Selon votre pays, vous devrez choisir une pointure plus grande ou plus petite.\n"+
                "\t\tLorsque vous êtes debout, veillez à ce que vos doigts de pieds ne touchent pas le bout du patin.\n"+
                "\t\tVous pouvez acheter vos patins neufs ou usagers. "+
                "Pour un débutant, cela ne fait pas énormément de différence, tant qu'ils vous semblent confortables. "+
                "Vérifiez quand même que les patins d'occasions ne soient pas en trop mauvais état.\")";
        ScriptDB += INSERT_DETAILS_TABLE_PATINS_1;


        String INSERT_DETAILS_TABLE_PATINS_2 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (1, \"Entretien des patins\", "+
                "\"\t\tAprès chaque utilisation, pensez à bien sécher vos patins (et lames) avec une serviette ou un chiffon.\n" +
                "\t\tLorsque vous n’êtes plus sur la glace, il est vivement conseillé de retirer ses patins. " +
                "Evitez de marcher avec sur une surface dur (autre que la glace).\n" +
                "\t\tIl est aussi préférable de vous procurer des protège-lames, si ceux-ci ne sont pas fournis avec l'achat des patins.\n" +
                "\t\tDe temps en temps, pensez à vérifier l'état de vos lacets et les vis de fixation des lames.\")";
        ScriptDB += INSERT_DETAILS_TABLE_PATINS_2;

        Log.d("test",ScriptDB);

        String INSERT_DETAILS_TABLE_PATINS_3 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (1, \"Affûtage des lames\", "+
                "\"\t\tSi vos patins sont neufs, vous devez faire affuter  aaaaaaa les lames avant la première utilisation.\n"+
                "\t\tIl est assez difficile de déterminer la durée de vie d’un affûtage. "+
                "Si vous sentez qu’une lame et/ou les deux lames ne mordent plus dans la glace lorsque vous poussez pour avancer "+
                "c’est le signe qu’un affûtage s’impose.\")";
        ScriptDB += INSERT_DETAILS_TABLE_PATINS_3;

        String INSERT_DETAILS_TABLE_BATONS_1 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (2, \"Choisir correctement\", "+
                "\"\t\tDans un premier temps, vous devez déterminer s'il vous faut un bâton de gaucher ou droitier. "+
                "Lorsque vous empoignez le bâton, si vous êtes plus à l'aise avec votre main droite en haut du bâton, "+
                "il vous faut un bâton de gaucher. Si vous êtes plus à l'aise avec votre main gauche, il vous faut un bâton de droitier.\n"+
                "\t\tPour choisir la bonne longueur du bâton, placez-le sur le plancher devant vous. " +
                "Si vous portez des chaussures, le bâton devrait vous arriver entre le nez et le menton, " +
                "de sorte que lorque vous porterez vos patins, il vous arrivera légèrement en-dessous du menton.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_BATONS_1;

        String INSERT_DETAILS_TABLE_BATONS_2 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (2, \"Appliquer du tape (ruban) sur son bâton\", "+
                "\"\t\tAppliquer du tape sur votre bâton de hockey vous donnera une meilleure prise, "+
                "plus de contrôle sur la rondelle et vous permettra de prolonger sa durée de vie.\n"+
                "\t\tSi vous le souhaitez, vous pouvez créer un pommeau avec le tape au bout de votre bâton pour empêcher votre main de glisser. "+
                "Ensuite, pour avoir une meilleure prise, enroulez le tape sur une distance de quatre à six pouces (10-15cm) à partir de l'extrémité du bâton.\n" +
                "\t\tAppliquez le tape sur la lame, du talon au bout, ou du bout au talon, selon votre préférence. " +
                "Recouvrez le tape déjà appliqué soigneusement et de façon constante.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_BATONS_2;

        String INSERT_DETAILS_TABLE_PROTECTIONS_1 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Le casque\", "+
                "\"\t\tLe port d'un casque au hockey est très important pour éviter des blessures à la tête ou aux yeux. "+
                "Certains casques offrent divers degrés de protection suivant votre niveau de jeu (avec ou sans mise en échec). "+
                "Il vous faut trouver le casque qui satisfait aux normes actuelles de sécurité, mais aussi qui vous va bien.\n"+
                "\t\tLe casque doit être approuvé par différentes instances, soit le HECC (The Hockey Equipment Certification Council) ou le CSA (The Canadian Standards Association).\n" +
                "\t\tLes casques ont également une date d'expiration recommandée. " +
                "Selon le pays dans lequel vous vous trouvez, il vous faudra peut-être changer votre casque avant cette date d'expiration. "+
                "Un casque endommagé doit être remplacé sans faute.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_1;

        String INSERT_DETAILS_TABLE_PROTECTIONS_2 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Les gants\", "+
                "\"\t\tLes gants doivent être suffisamment amples pour ne pas nuire aux mouvements de vos poignets. "+
                "Penser à essayer plusieurs modèles de gants jusqu’à ce que vous en trouviez un qui soit confortable.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_2;

        String INSERT_DETAILS_TABLE_PROTECTIONS_3 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Les protège-coudes\", "+
                "\"\t\tLe haut du protège-coude doit joindre le bas de l’épaulière et le haut du gant. "+
                "Il ne doit pas gêner votre mobilité.\n"+
                "\t\tIl existe différentes grandeurs de protèges-coudes (petit, moyen, grand).\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_3;

        String INSERT_DETAILS_TABLE_PROTECTIONS_4 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Les épaulières\", "+
                "\"\t\tLes épaulières doivent permettre une grande liberté de mouvement au joueur tout en offrant un maximum de protection. "+
                "Elles ne doivent pas se soulever ou gêner votre mobilité.\n"+
                "\t\tIl existe différents types d'épaulières suivant votre niveau de jeu.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_4;

        String INSERT_DETAILS_TABLE_PROTECTIONS_5 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Les culottes (ou pantalon)\", "+
                "\"\t\tVous pouvez choisir vos culottes en fonction de votre tour de taille, mais il faut aussi s'assurer qu'elles ne soient pas trop longues (ou trop courtes). "+
                "Elles doivent tomber environ à mi-chemin de votre genou et recouvrir partiellement vos jambières.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_5;

        String INSERT_DETAILS_TABLE_PROTECTIONS_6 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Les jambières\", "+
                "\"\t\tVos jambières devraient vous couvrir des genoux aux chevilles. "+
                "Elles doivent s’arrêter à plus ou moins deux doigts au-dessus de l’os de la cheville. "+
                "Le genou doit se trouver au milieu de la coquille.\n"+
                "\t\tDes jambières fissurées ou craquées doivent être remplacées immédiatement.\n"+
                "\t\tMettez vos bas de hockey par-dessus vos jambières.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_6;

        String INSERT_DETAILS_TABLE_PROTECTIONS_7 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Le protège-cou\", "+
                "\"\t\tVous pouvez choisir un protège-cou traditionel (sans bavette) mais certaines parties du cou pourraient ne pas être protégées. "+
                "Vous pouvez également choisir une protège-cou avec bavette qui est un peu plus sécuritaire.\n"+
                "\t\tLe protège-cou doit avant tout être très confortable.\")";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_7;

        String INSERT_DETAILS_TABLE_PROTECTIONS_8 = "INSERT INTO detailsconseils (id_titre, nom_details, contenu_details) "+
                "VALUES (3, \"Autres conseils\", "+
                "\"\t\tLorsque vous essayez votre équipement, essayez plusieurs pièces à la fois. "+
                "Par exemple : essayez des gants et des épaulières avec des protège-coudes. "+
                "L’ensemble des éléments doit assurer une protection complète.\n"+
                "\t\tFaites sécher l’équipement à l’air libre après chaque usage. "+
                "Ne faites jamais sécher l’équipement sur ou près d’une source directe de chaleur, car cela pourrait l’endommager."+
                "\t\tUtilisez un support conçu à cet effet qui peut être rangé et installé facilement.\n"+
                "\t\tPour le nettoyage, utilisez une eau légèrement savonneuse et une brosse douce.\")";

        ScriptDB += " "+INSERT_DETAILS_TABLE_PROTECTIONS_8;

        db.execSQL(CREATE_TITRE_TABLE);
        db.execSQL(CREATE_DETAILS_TABLE);
        db.execSQL(CREATE_EVENEMENTS_TABLE);
        db.execSQL(INSERT_TITRE_TABLE);
        db.execSQL(INSERT_DETAILS_TABLE_PATINS_1);
        db.execSQL(INSERT_DETAILS_TABLE_PATINS_2);
        db.execSQL(INSERT_DETAILS_TABLE_PATINS_3);
        db.execSQL(INSERT_DETAILS_TABLE_BATONS_1);
        db.execSQL(INSERT_DETAILS_TABLE_BATONS_2);
        db.execSQL(INSERT_EVENT);
        db.execSQL(INSERT_EVENT2);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_1);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_2);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_3);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_4);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_5);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_6);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_7);
        db.execSQL(INSERT_DETAILS_TABLE_PROTECTIONS_8);

        Log.d("test",ScriptDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        Log.d("Upgrade","Lol");
        db.execSQL("DROP TABLE IF EXISTS detailsconseils");
        db.execSQL("DROP TABLE IF EXISTS titreconseils");

        // create fresh books table
        this.onCreate(db);
    }


}
