package main.java.com.scalex;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by gingray on 10/8/15.
 */
public class CsvReader {
    private CSVParser parser;
    public CsvReader(String fileName) throws IOException {
        parser = new CSVParser(new FileReader(fileName), CSVFormat.DEFAULT.withHeader());
    }

    public String[] fetchHeaders(){
        Set<String> keys = parser.getHeaderMap().keySet();
        return keys.toArray(new String[keys.size()]);
    }

    public void fetchRows(IFetchRow fetchRowProcessor) throws IOException {
        for(CSVRecord row: parser){
            ArrayList<String> result = new ArrayList<String>();
            for(String item : row){
                result.add(item);
            }
            fetchRowProcessor.fetchRow(result.toArray(new String[result.size()]));
        }
    }

}
