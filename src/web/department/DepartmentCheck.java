package web.department;

import java.util.List;

import utils.CheckUtils;

/**
 * 登録時など入力チェックを行うためのクラス
 *
 */
public class DepartmentCheck {
    /**
    * 所属部署番号のチェックメソッド
    * @param deptno 部署番号の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void deptnoAddMessages(String deptno, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(deptno)) {
            messages.add("部署番号が未入力です");
        }
        if (deptno.length() > 3) {
            messages.add("部署番号の文字数が制限をオーバーしています。");
        }
        if (!CheckUtils.isMatcher(deptno)) {
            messages.add("部署番号は整数で入力してください。");
        }
        if (!CheckUtils.isIntegerParseInt(deptno)) {
            messages.add("部署番号は数字で入力してください。");
        }
    }

    /**
    * 社員名のチェックメソッド
    * @param dname 社員名の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void dnameAddMessages(String dname, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(dname)) {
            messages.add("部署名が未入力です。");
        }
        if (dname.length() > 20) {
            messages.add("部署名の文字数が制限をオーバーしています。");
        }
    }

    /**
    * 上司社員番号のチェックメソッド
    * @param mgrNo 上司社員番号の文字列
    * @param messages エラーメッセージのオブジェクト
    */

    public static void mgrNoAddMessages(String mgrNo, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(mgrNo)) {
            messages.add("上司の社員番号が未入力です");
        }
        if (mgrNo.length() > 5) {
            messages.add("上司の社員番号の文字数が制限をオーバーしています。");
        }
        if (!CheckUtils.isMatcher(mgrNo)) {
            messages.add("上司の社員番号は整数で入力してください。");
        }
        if (!CheckUtils.isIntegerParseInt(mgrNo)) {
            messages.add("上司の社員番号は数字で入力してください。");
        }
    }
}
