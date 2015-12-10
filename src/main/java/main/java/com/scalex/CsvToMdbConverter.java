package main.java.com.scalex;

import com.healthmarketscience.jackcess.*;
import com.healthmarketscience.jackcess.util.*;

import java.io.IOException;
import java.sql.SQLException;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CsvToMdbConverter {
    private Database db;
    private BufferedReader reader;

    public CsvToMdbConverter(String csvFileName) throws IOException, SQLException {
        File mdbFile = new File(changeFileNameToMdb(csvFileName));
        this.db = new DatabaseBuilder(mdbFile).setFileFormat(Database.FileFormat.V2000).create();
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFileName), "UTF8"));
    }

    public void processToTable(String tableName) throws IOException, SQLException {
        new ImportUtil.Builder(db, tableName).setDelimiter(",").importReader(reader);
    }

    public void finish() throws IOException {
        db.close();
    }

    private String changeFileNameToMdb(String path){
        String resultPath = path.substring(0, path.lastIndexOf('.'));
        return resultPath + ".mdb";
    }
}
