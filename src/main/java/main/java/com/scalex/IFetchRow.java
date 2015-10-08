package main.java.com.scalex;

import java.io.IOException;

/**
 * Created by gingray on 10/8/15.
 */
public interface IFetchRow {
    void fetchRow(String[] items) throws IOException;
    void finish() throws IOException;
}
