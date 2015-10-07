package main.java.com.scalex;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by gingray on 10/7/15.
 */

public class MdbFileCreatorTest {

    public Path path = Paths.get(getClass().getResource(".").getPath(), "test_mdb");
    public String[] fields = new String[] {"field1","field2"};
    public MdbFileCreator mdb;

    @Before
    public void setUp() throws IOException, SQLException {
        mdb = new MdbFileCreator(path.toString());
    }

    @Test
    public void testReadMdb() throws IOException, SQLException {
        mdb.createTable("test_table",this.fields);
        Database db = DatabaseBuilder.open(new File(path.toString()));
        Table table = db.getTable("test_table");
        assertTrue("table should exist", table != null);
        for(Column column : table.getColumns() ){
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
    }

}