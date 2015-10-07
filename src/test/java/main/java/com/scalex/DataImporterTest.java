package main.java.com.scalex;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by gingray on 10/7/15.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataImporterTest {

    @Test
    public void mdbCreate() throws IOException {

        assertNotNull("Test file missing",
                getClass().getResource("/test_file.csv"));

    }

}