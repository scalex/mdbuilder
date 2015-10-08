package main.java.com.scalex;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by gingray on 10/8/15.
 */
public class CsvReaderTest {
    public URL path = getClass().getResource("/test_file.csv");

    @Test
    public void testCsvReadingHeaders() throws IOException {
        CsvReader reader = new CsvReader(path.getFile());
        String[] headers = reader.fetchHeaders();
        assertEquals(10, headers.length);

    }

    @Test
    public void testCsvReadingRows() throws IOException {
        CsvReader reader = new CsvReader(path.getFile());
        reader.fetchRows(new IFetchRow() {
            public void fetchRow(String[] items) throws IOException {
                assertEquals(10,items.length);
            }

            public void finish() throws IOException {

            }
        });
    }

}