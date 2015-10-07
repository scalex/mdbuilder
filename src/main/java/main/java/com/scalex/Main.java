package main.java.com.scalex;
import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
public class Main {


    public static void startDatabaseProcess() throws IOException, SQLException {
        Database db = DatabaseBuilder.create(Database.FileFormat.V2000, new File("new.mdb"));
        Table newTable = new TableBuilder("NewTable")
                .addColumn(new ColumnBuilder("a")
                        .setSQLType(Types.INTEGER))
                .addColumn(new ColumnBuilder("b")
                        .setSQLType(Types.VARCHAR))
                .toTable(db);
        newTable.addRow(1, "foo");
    }

    public static void main(String[] args) throws IOException, SQLException {
        startDatabaseProcess();
    }
}
