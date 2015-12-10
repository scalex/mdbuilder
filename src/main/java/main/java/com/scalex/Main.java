package main.java.com.scalex;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        if (args.length < 2) {
            System.out.println("first param is path to csv file with headers, second is in which table data should be written");
            return;
        }
        CsvToMdbConverter processor = new CsvToMdbConverter(args[0]);
        processor.processToTable(args[1]);
        processor.finish();
    }
}
