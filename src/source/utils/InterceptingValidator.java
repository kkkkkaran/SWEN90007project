package source.utils;

public class InterceptingValidator {
	/**
	 * Validate uri, currently only detect the SQL injection
	 * if there are other problems need to validate, update this function
	 * @param uriString
	 * @return
	 */
	public static boolean validateURI(String uriString) {
		return checkSQLInjection(uriString);
		
	}
	
	/**
	 * detect SQL injection
	 * @param uri
	 * @return
	 */
	private static boolean checkSQLInjection(String uri) {
		String query = uri.toLowerCase();
		//detect SQL injection
		if (query.matches(
				"/\\w*((\\%27)|(\\'))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix")
				|| query.matches("/((\\%27)|(\\'))union/ix")){
			System.out.println("error");
			return false;
		}
		return true;
	}
}
