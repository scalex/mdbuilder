package main.java.com.scalex;

import com.healthmarketscience.jackcess.Table;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by gingray on 10/7/15.
 */
public class CsvToMdbConverter implements IFetchRow {
    private final CsvReader reader;
    private final MdbFileProcessor importer;
    private Table table;

    public CsvToMdbConverter(String csvFileName) throws IOException, SQLException {
        reader = new CsvReader(csvFileName);
        importer = new MdbFileProcessor(changeFileNameToMdb(csvFileName));
    }

    public void processToTable(String tableName) throws IOException, SQLException {
        String[] headers = reader.fetchHeaders();
        table = importer.createTable(tableName,headers);
        reader.fetchRows(this);
    }

    public void finish() throws IOException {
        importer.close();

    }

    public void fetchRow(String[] items) throws IOException {
        importer.writeRow(table, items);
    }

    private String changeFileNameToMdb(String path){
        String resultPath = path.substring(0, path.lastIndexOf('.'));
        return resultPath + ".mdb";
    }
}
