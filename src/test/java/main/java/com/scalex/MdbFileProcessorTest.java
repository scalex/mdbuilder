package main.java.com.scalex;

import com.healthmarketscience.jackcess.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by gingray on 10/7/15.
 */

public class MdbFileProcessorTest {

    public Path path = Paths.get(getClass().getResource(".").getPath(), "test_mdb");
    public String[] fields = new String[] {"field1","field2"};
    public MdbFileProcessor mdb;

    @Before
    public void setUp() throws IOException, SQLException {
        mdb = new MdbFileProcessor(path.toString());
    }

    @Test
    public void testReadMdb() throws IOException, SQLException {
        mdb.createTable("test_table",this.fields);
        Database db = DatabaseBuilder.open(new File(path.toString()));
        Table table = db.getTable("test_table");
        assertTrue("table should exist", table != null);
        for(Column column : table.getColumns()){
            boolean contain = Arrays.asList(fields).contains(column.getName());
            assertTrue("Field contains", contain);
        }
    }

    @Test
    public void testNotExistingFields() throws IOException, SQLException {
        mdb.createTable("test_table",this.fields);
        Database db = DatabaseBuilder.open(new File(path.toString()));
        String[] fields = new String[] {"fail_field1", "fail_field2"};
        Table table = db.getTable("test_table");
        for(Column column : table.getColumns() ){
            boolean contain = Arrays.asList(fields).contains(column.getName());
            assertFalse("Field contains", contain);
        }
    }

    @Test
    public void testWriteRow() throws IOException, SQLException {
        Table table = mdb.createTable("test_table",this.fields);
        String[] inputs = new String[] {"1","2"};
        mdb.writeRow(table, inputs);

        Database db = DatabaseBuilder.open(new File(path.toString()));
        table = db.getTable("test_table");
        Row row = table.getNextRow();
        String field1 = row.getString("field1");
        String field2 = row.getString("field2");
        assertEquals(field1, "1");
        assertEquals(field2, "2");

    }

}