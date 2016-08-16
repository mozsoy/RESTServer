package WhitelistCompiler;

import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GiantDomainCompiler {
	
	private static ArrayList<String> giantDomainList = new ArrayList<>(Arrays.asList("google","facebook","amazon","twitter"));
	private static ArrayList easyList = new ArrayList<>(Arrays.asList("ad"));
	
	/**
	 * For giant domaoins Google, Facebook, Amazon, Twitter 
	 * whitelist everything except there is 'ad' in the url.
	 * However this class is under development. 
	 * 
	 * @param urlMap
	 * @return
	 */
	public void filterGiantDomains(HashMap<String,Long> urlMap) {
 
	}
}
