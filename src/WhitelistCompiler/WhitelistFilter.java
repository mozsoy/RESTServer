package WhitelistCompiler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;

public class WhitelistFilter {

	 // Whitelist providers flags
    private static boolean top1000flag = true;
    /* Keep alexa500flag off. 
     * Alexa uses a zip file. 
     * Heroku filesystem is read-only and does not allow downloading zip file.
     * 
     * Instead get the unzipped csv file using alexa1mflag from:
     * https://github.com/mozsoy/RESTServer/blob/master/alexaWhitelist.csv
     * */  
    private static boolean alexa500flag = false;
    private static boolean alexa1mflag	 = false;
    
    // Return url as a string without the http and www parts
    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
    
    public static HashMap<String,Long> filterUrlMap(HashMap<String,Long> urlMap) {
    	Set<String> urlSet = urlMap.keySet();
    	Iterator<String> itr = urlSet.iterator();
    	if(top1000flag) {
    		Top1000Compiler top1000compiler = new Top1000Compiler();
            ArrayList<String> top1000whitelist
                    = top1000compiler.downloadTOP1000Whitelist();            
            while (itr.hasNext())
            {
                String s = itr.next();
                if (top1000whitelist.contains(s)) {
                    urlMap.remove(s);
                }
            }
    	}
    	itr = urlSet.iterator(); // Reset iterator
    	if(alexa500flag) {
            Alexa500Compiler alexa500compiler = new Alexa500Compiler();
            String zipFile = alexa500compiler.downloadAlexaZipFile();
            ArrayList<String> csvFiles
                    = ZipUtil.unZipIt(zipFile, alexa500compiler.OUTPUT_FOLDER);
            System.out.println(csvFiles.get(0));
            ArrayList<String> alexaWhitelist
                    = alexa500compiler.readAlexaWhitelistFromCsvFile(
                            alexa500compiler.OUTPUT_FOLDER + csvFiles.get(0));
            while (itr.hasNext())
            {
                String s = itr.next();
                if (alexaWhitelist.contains(s)) {
                    urlMap.remove(s);
                }
            }
    		
    	}
    	itr = urlSet.iterator(); // Reset iterator
    	if(alexa1mflag) {
    		Alexa500Compiler alexa500Compiler = new Alexa500Compiler();
            ArrayList<String> alexawhitelist
                    = alexa500Compiler.downloadAlexa1mWhitelist();            
            while (itr.hasNext())
            {
                String s = itr.next();
                if (alexawhitelist.contains(s)) {
                    urlMap.remove(s);
                }
            }   		  		
    	}
    	return urlMap;
    }
}
