package WhitelistCompiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
        } catch (FileNotFoundException ex) { // File not found
            System.out.println("error: Alexa csv file not found at the specified path");
        }
        return whitelist;
    }
}
