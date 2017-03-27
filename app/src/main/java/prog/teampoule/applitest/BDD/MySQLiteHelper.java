package prog.teampoule.applitest.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

        // SQL statement to create Titre table
        String CREATE_TITRE_TABLE = "CREATE TABLE TitreConseils ( " +
                "id_titre INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom_titre TEXT );";
        ScriptDB += CREATE_TITRE_TABLE;

        // SQL statement to create Details table
        String CREATE_DETAILS_TABLE = "CREATE TABLE DetailsConseils ( " +
                "id_details INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_titre INTEGER,"+
                "nom_details TEXT,"+
                "contenu_details TEXT );";
        ScriptDB += " "+CREATE_DETAILS_TABLE;

        // create all the tables
        String ALTER_TABLE_FOREIGN_KEY_ID_DETAILS = "ALTER TABLE DetailsConseils " +
                "ADD CONSTRAINT fk_id_titre FOREIGN KEY (id_titre) REFERENCES TitreConseils(id_titre);";
        ScriptDB += " "+ALTER_TABLE_FOREIGN_KEY_ID_DETAILS;

        String INSERT_TITRE_TABLE = "INSERT INTO TitreConseils (nom_titre) VALUES (\"Patins\");"+
                "INSERT INTO TitreConseils (nom_titre) VALUES (\"Bâtons\""+
                "INSERT INTO TitreConseils (nom_titre) VALUES (\"Protections\")";
        ScriptDB += " "+INSERT_TITRE_TABLE;

        String INSERT_DETAILS_TABLE_PATINS_1 = "INSERT INTO DetailsConseils (id_titre, nom_details, contenu_details)"+
                "VALUES (1, \"Choisir correctement\", "+
                "\"\tPour la pointure appropriée, pensez à vous renseigner (sur internet ou auprès d'un vendeur), "+
                "ce n'est pas la même pointure que pour vos chaussures. "+
                "Selon votre pays, vous devrez choisir une pointure plus grande ou plus petite.\n"+
                "\tLorsque vous êtes debout, veillez à ce que vos doigts de pieds ne touchent pas le bout du patin.\n"+
                "\tVous pouvez acheter vos patins neufs ou usagers. "+
                "Pour un débutant, cela ne fait pas énormément de différence, tant qu'ils vous semblent confortables. "+
                "Vérifiez quand même que les patins d'occasions ne soient pas en trop mauvais état.\");";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PATINS_1;

        String INSERT_DETAILS_TABLE_PATINS_2 = "INSERT INTO DetailsConseils (id_titre, nom_details, contenu_details)"+
                "VALUES (1, \"Entretien des patins\", "+
                "\"\tAprès chaque utilisation, pensez à bien sécher vos patins (et lames) avec une serviette ou un chiffon.\n" +
                "\tLorsque vous n’êtes plus sur la glace, il est vivement conseillé de retirer ses patins. " +
                "Evitez de marcher avec sur une surface dur (autre que la glace).\n" +
                "\tIl est aussi préférable de vous procurer des protège-lames, si ceux-ci ne sont pas fournis avec l'achat des patins.\n" +
                "\tDe temps en temps, pensez à vérifier l'état de vos lacets et les vis de fixation des lames.\");";
        ScriptDB += " "+INSERT_DETAILS_TABLE_PATINS_2;

        db.execSQL(ScriptDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS DetailsConseils");
        db.execSQL("DROP TABLE IF EXISTS TitreConseils");
        // create fresh books table
        this.onCreate(db);
    }


}
