package bos.sshproject.utils;

import java.io.IOException;
import java.net.URLEncoder;

import sun.misc.BASE64Encoder;

public class FileUtils {
		/**
		 * 涓嬭浇鏂囦欢鏃讹紝閽堝涓嶅悓娴忚鍣紝杩涜闄勪欢鍚嶇殑缂栫爜
		 * 
		 * @param filename
		 *            涓嬭浇鏂囦欢鍚�
		 * @param agent
		 *            瀹㈡埛绔祻瑙堝櫒
		 * @return 缂栫爜鍚庣殑涓嬭浇闄勪欢鍚�
		 * @throws IOException
		 */
		public static String encodeDownloadFilename(String filename, String agent)
				throws IOException {
			if (agent.contains("Firefox")) { // 鐏嫄娴忚鍣�
				filename = "=?UTF-8?B?"
						+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
						+ "?=";
				filename = filename.replaceAll("\r\n", "");
			} else { // IE鍙婂叾浠栨祻瑙堝櫒
				filename = URLEncoder.encode(filename, "utf-8");
				filename = filename.replace("+"," ");
			}
			return filename;
		}
}
