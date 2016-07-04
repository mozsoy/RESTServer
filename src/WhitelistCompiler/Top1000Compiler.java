package WhitelistCompiler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Metehan
 */
public class Top1000Compiler {

    private String prepareUrl() {
        String url = "http://top1000.anthologeek.net/top1000.current.txt";
        return url;
    }

    public ArrayList<String> downloadTOP1000Whitelist() {
        // The security report to be returned by this method
        ArrayList<String> whitelist = new ArrayList<>();
        try {
            URL url = new URL(prepareUrl());
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            Scanner scanner = new Scanner(in);

            scanner.nextLine(); // read header line
            scanner.nextLine(); // read header line

            while (scanner.hasNext()) { //read next line in the txt file
                String line = scanner.nextLine();
                String[] fieldsInThisLine = line.split("\\s+");                  
                whitelist.add(fieldsInThisLine[3]);                     
            }
            scanner.close();
        } catch (MalformedURLException ex) {
            System.out.println("TOP1000 Whitelist: Malformed URL Error");
        } catch (IOException ex) {
            System.out.println("TOP1000 Whitelist: Txt File not found error");
        }
        return whitelist;
    }
}
