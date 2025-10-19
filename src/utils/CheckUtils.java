package utils;

/**
 *
 * 独自クラスとして作った、Null等の判定を行うクラス。
 */
public class CheckUtils {

	/**
	 * Nullチェックを行う。
	 * @param value 検査文字列
	 * @return boolean Nullか空文字だった場合はtrue。違ったらfalse。
	 */
	public static boolean isNullOrEmpty(String value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 数値変換チェック。
	 * @param value 検査文字列
	 * @return boolean 変換できた場合はtrue。違ったらfalse。
	 */
	public static boolean isIntegerParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 負の値がないかチェック。
	 * @param value 検査文字列
	 * @return boolean 整数だったらtrue。違ったらfalse。
	 */
	public static boolean isMatcher(String value) {
		if (value != null && !value.isEmpty() && !value.matches("^[0-9]+$")) {
			return false;
		}
		return true;
	}
}
