package WhitelistCompiler;

import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;

public class GiantDomainCompiler {
	
	/**
	 * For giant domaoins Google, Facebook, Amazon, Twitter 
	 * whitelist everything except there is 'ad' in the url.
	 * 
	 * @param urlMap
	 * @return
	 */
	public void filterGiantDomains(HashMap<String,Long> urlMap) {
		Set<String> urlSet = urlMap.keySet();
    	Iterator<String> itr = urlSet.iterator();
    	itr = urlSet.iterator(); // Reset iterator
        while (itr.hasNext())
        {
            String s = itr.next();
            String sLowerCase = s.toLowerCase();
            if ((sLowerCase.contains("facebook") 
            		|| sLowerCase.contains("google") 
            		|| sLowerCase.contains("amazon") 
            		|| sLowerCase.contains("twitter"))
            		&& !sLowerCase.contains("ad")) {
                urlMap.remove(s);
            }
        } 
	}
}
