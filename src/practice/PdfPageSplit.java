package practice;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PdfPageSplit {

    /**
    * PDFページをPDFのまま分割できるか？
    *
    * @param args
    */
    public static void main(String[] args) {
        String filepath = "C:/Users/takah/Desktop/データソース.pdf";
        File pdfFile = new File(filepath);
        try {
            PDDocument pdDoc = PDDocument.load(pdfFile);
            int numberOfPages = pdDoc.getNumberOfPages();
            System.err.println("pdDoc.getNumberOfPages():" + numberOfPages);
            for (int n = 1; n <= numberOfPages; n++) {
                System.err.println("n:" + n);
                PDDocument doc = new PDDocument();
                PDPage page = (PDPage) pdDoc.getPage(n - 1);
//                if (n == 2) {
//                    doc.addPage(page);
//                }
                doc.addPage(page);
                File tempFile = File.createTempFile("test", "_" + n + ".pdf");
                System.err.println("tempFile:" + tempFile.getAbsolutePath());
                doc.save(tempFile);
                doc.close();
                System.err.println("saved!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
