package practice;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Main {
    public static void main(String args[]) {
        // 空のドキュメントオブジェクトを作成します
        try (PDDocument document = new PDDocument()) {
            // 新しいページのオブジェクトを作成します
            PDPage page = new PDPage();
            document.addPage(page);
            System.out.println("ここを通りました。");
            // ドキュメントを保存します
            document.save("sample.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
