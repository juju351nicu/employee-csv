package web.employee;

import java.util.List;

import utils.CheckUtils;

/**
 * 登録時など入力チェックを行うためのクラス
 *
 */
public class EmployeeCheck {
    /**
    * 社員番号のチェックメソッド
    * @param empno 社員番号の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void empnoAddMessages(String empno, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(empno)) {
            messages.add("社員番号の項目が未入力です");
        }
        if (empno.length() > 5) {
            messages.add("社員番号の文字数が制限をオーバーしています。");
        }
        if (!CheckUtils.isMatcher(empno)) {
            messages.add("社員番号は整数で入力してください。");
        }
        if (!CheckUtils.isIntegerParseInt(empno)) {
            messages.add("社員番号は数字で入力してください。");
        }
    }

    /**
    * 社員名のチェックメソッド
    * @param ename 社員名の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void enameAddMessages(String ename, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(ename)) {
            messages.add("社員名が未入力です。");
        }
        if (ename.length() > 20) {
            messages.add("社員名の文字数が制限をオーバーしています。");
        }
    }

    /**
    * 年齢のチェックメソッド
    * @param age 年齢の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void ageAddMessages(String age, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(age)) {
            messages.add("年齢が未入力です");
        }
        if (age.length() > 3) {
            messages.add("年齢の文字数が制限をオーバーしています。");
        }
        if (!CheckUtils.isMatcher(age)) {
            messages.add("年齢は整数で入力してください。");
        }
        if (!CheckUtils.isIntegerParseInt(age)) {
            messages.add("年齢は数字で入力してください。");
        }
    }

    /**
    * 経験言語のチェックメソッド
    * @param languages 経験言語の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void languageAddMessages(String languages, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(languages)) {
            messages.add("経験言語が未入力です。");
        }
        if (languages.length() > 100) {
            messages.add("経験言語の文字数が制限をオーバーしています。");
        }
    }

    /**
    * 経験年数のチェックメソッド
    * @param years 経験年数の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void yearsAddMessages(String years, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(years)) {
            messages.add("経験年数が未入力です");
        }
        if (years.length() > 3) {
            messages.add("経験年数の文字数が制限をオーバーしています。");
        }
        if (!CheckUtils.isMatcher(years)) {
            messages.add("経験年数は整数で入力してください。");
        }
        if (!CheckUtils.isIntegerParseInt(years)) {
            messages.add("経験年数は数字で入力してください。");
        }
    }

    /**
    * 所属部署番号のチェックメソッド
    * @param deptno 所属部署番号の文字列
    * @param messages エラーメッセージのオブジェクト
    */
    public static void deptnoAddMessages(String deptno, List<String> messages) {
        if (CheckUtils.isNullOrEmpty(deptno)) {
            messages.add("所属部署番号が未入力です");
        }
        if (deptno.length() > 3) {
            messages.add("所属部署番号の文字数が制限をオーバーしています。");
        }
        if (!CheckUtils.isMatcher(deptno)) {
            messages.add("所属部署番号は整数で入力してください。");
        }
        if (!CheckUtils.isIntegerParseInt(deptno)) {
            messages.add("所属部署番号は数字で入力してください。");
        }
    }
}
