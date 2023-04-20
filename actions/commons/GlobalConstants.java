package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_DEV_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_DEV_URL = "https://admin-demo.nopcommerce.com/";

	public static final String PORTAL_TESTING_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_TESTING_URL = "https://admin-demo.nopcommerce.com/";

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getenv("os.name");
	public static final String JAVA_VERSION = System.getenv("java.version");

	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHtml5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNGSCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "Extent";

	public static final String DB_DEV_URL = "";
	public static final String DB_DEV_USER = "hltdz";
	public static final String DB_DEV_PASS = "!QAZ2wsx";

	public static final String DB_TEST_URL = "";
	public static final String DB_TEST_USER = "hltdz";
	public static final String DB_TEST_PASS = "!QAZ2wsx";

	public static final long SHOT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;
	public static final long RETRY_TEST_FAIL = 3;
}
