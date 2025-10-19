package practice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Pdf2Txt {
    public static final void main(String[] args) throws IOException {
        Pdf2Txt.class.getResourceAsStream(null);
        //PDFファイル名を指定する。（フルパス）
        String pdf = "C:/workspace/youkou_ver4_5.pdf";

        //PDFの内容をテキストに抽出する。
        String result = ConvertPdf2Text(pdf);

        //pdfファイル名と同名のtxtファイル名にする。
        String txt = pdf.replace(".pdf", ".txt");

        //テキストファイルへ書き出し。
        ToTxt(result, txt);
    }

    /**
    * PDFテキスト変換処理。
    *
    * 【処理概要】
    * 引数に指定したPDFからテキストを抽出して返却する。
    *
    * @param input PDF名を指定。
    * @return PDFから抽出したテキスト。
    */
    private static String ConvertPdf2Text(String input) {

        //パスを取得。
        Path p = Paths.get(input);

        //PDFからテキストを抽出。
        String result = "";
        try (PDDocument doc = PDDocument.load(p.toFile())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(doc.getNumberOfPages());
            result = stripper.getText(doc);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
    * テキスト書き込み処理。
    *
    * 【処理概要】
    * 第1引数に指定したテキスト内容を第2引数で指定したファイルへ書き込む。
    *
    * @param result 抽出したテキスト。
    * @param output 書き込み先のテキストファイル名。
    *
    */
    private static void ToTxt(String result, String output) throws IOException {

        //パスを取得。
        Path p = Paths.get(output);
        //空ファイルの生成。
        if (!Files.exists(p)) {
            Files.createFile(p);
        }
        //listへ変換。
        List<String> list = Arrays.asList(result);
        //ファイルに書き込み。
        Files.write(p.toAbsolutePath(),
                list,
                StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
