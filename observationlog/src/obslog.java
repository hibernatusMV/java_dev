import de.util.database.DBController;

public class obslog {
    public static void main(String[] args) {
        DBController dbStmt = new DBController();
        dbStmt.dbGetData("SELECT * FROM observations;");
    }
}