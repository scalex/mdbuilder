package main.java.com.scalex;

import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

/**
 * Created by gingray on 10/7/15.
 */
public class MdbFileProcessor {
    private String path;
    private Database db;

    public MdbFileProcessor(String path) throws IOException, SQLException {
        this.path = path;
        db = new DatabaseBuilder(new File(this.path))
          .setFileFormat(Database.FileFormat.V2000)
          .setAutoSync(false)
          .create();
    }

    public Table createTable(String tableName, String[] fields) throws SQLException, IOException {
        TableBuilder tableBuilder = new TableBuilder(tableName);
        for(String field: fields){
            tableBuilder.addColumn(new ColumnBuilder(field).setSQLType(Types.VARCHAR));
        }
        return tableBuilder.toTable(db);
    }

    public void writeRow(Table table, Object[] inputs) throws IOException {
        table.addRow(inputs);
    }

    public void close() throws IOException {
        db.close();
    }

}
