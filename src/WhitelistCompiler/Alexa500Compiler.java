package WhitelistCompiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Metehan
 */
public class Alexa500Compiler {

    final String OUTPUT_FOLDER = "/";

    private URL prepareUrl() {
        try {
            String url = "http://s3.amazonaws.com/alexa-static/top-1m.csv.zip";
            return new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getOutputFilename() {
        Calendar today = Calendar.getInstance();
        return OUTPUT_FOLDER + "Alexa_"
                + today.get(Calendar.MONTH)
                + today.get(Calendar.DAY_OF_MONTH)
                + today.get(Calendar.YEAR)
                + ".zip";

    }

    public String downloadAlexaZipFile() {
        return ZipUtil.downloadZipFile(prepareUrl(), getOutputFilename());
    }

    public ArrayList<String> readAlexaWhitelistFromCsvFile(String path) {
        ArrayList<String> whitelist = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) { //read next line in the txt file
                String line = scanner.nextLine();
                String[] fieldsInThisLine = line.split(",");
                whitelist.add(fieldsInThisLine[1]);
            }
            scanner.close();
        } catch (FileNotFoundException ex) { // File not found
            System.out.println("error: Alexa csv file not found at the specified path");
        }
        return whitelist;
    }
    
    /**
     * Use this method to download Alexa 1m Whitelist as a csv file from 
     * https://github.com/mozsoy/RESTServer/blob/master/alexaWhitelist.csv
     * 
     * @return ArrayList<String> whitelist
     */
    public ArrayList<String> downloadAlexa1mWhitelist() {
        // The security report to be returned by this method
        ArrayList<String> whitelist = new ArrayList<>();
        try {
            URL url = new URL("https://github.com/mozsoy/RESTServer/blob/master/alexaWhitelist.csv");
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            Scanner scanner = new Scanner(in);

            while (scanner.hasNextLine()) { //read next line in the txt file
                String line = scanner.nextLine();
                String[] fieldsInThisLine = line.split(",");
                whitelist.add(fieldsInThisLine[1]);
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
