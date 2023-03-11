import de.util.database.*;
import de.util.helper.*;

public class obslog {
    public static void main(String[] args) {
        DBController dbStmt = new DBController();
        math mth = new math();
        //dbStmt.dbGetData("SELECT * FROM observations;");

        //System.out.println(mth.deg2RA(251.108));
        //System.out.println(mth.ra2Deg("15h 30m 33.1s"));
        System.out.println(mth.deg2dms(251.108));
        System.out.println(mth.dms2deg("15° 30\' 33.1\""));
    }
}