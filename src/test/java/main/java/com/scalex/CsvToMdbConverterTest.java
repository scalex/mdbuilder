package main.java.com.scalex;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by gingray on 10/7/15.
 */
public class CsvToMdbConverterTest {

    private URL pathCsv = getClass().getResource("/test_file.csv");
    private URL pathMdb = getClass().getResource("/test_file.mdb");

    @Before
    public void removeFileBefore(){
        File file = new File(pathMdb.getPath());
        if(file.exists()){
            file.delete();
        }
    }

    public void createTable(String tableName) throws IOException, SQLException {
        CsvToMdbConverter processor = new CsvToMdbConverter(pathCsv.getPath());
        processor.processToTable(tableName);
        processor.finish();
    }

    @Test
    public void mdbFileCreate() throws IOException, SQLException {
        createTable("test_table");
        File file = new File(pathMdb.getPath());
        assertTrue(file.exists());
    }

    @Test
    public void mdbCheckWrotenRows() throws IOException, SQLException {
        createTable("test_table");
        Database db = DatabaseBuilder.open(new File(pathMdb.getPath()));
        Table table = db.getTable("test_table");
        for(Row row : table){
            assertEquals(10, row.size());
        }
    }
}