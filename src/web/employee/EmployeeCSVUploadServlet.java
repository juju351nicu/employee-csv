package web.employee;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import dao.EmployeeDAO;
import entity.base.Employee;

/**
 * Employee情報のCSVファイルをアップロードし、それを一括登録するサーブレット。
 * employeeCsvUpload.jspに対応。
 */
@WebServlet("/CsvUpload")
@MultipartConfig(location = "C:/upload/")
public class EmployeeCSVUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeCSVUploadServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // メンバ一括登録画面に飛ぶ
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/employeeCsvUpload.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Partオブジェクトの取得
        Part part = request.getPart("uploadfile");

        //csvファイルかどうかのチェック
        String fileName = part.getSubmittedFileName();
        if (!fileName.endsWith(".csv")) {
            request.setAttribute("errorMessages", "csvファイルでないので読み込めません。");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            rd.forward(request, response);
            return;
        }

        //csvファイルを読み込み。
        List<String[]> csvLine = loadCsv(part);

        //Csv情報を取り出す。
        List<String> messages = new ArrayList<>();
        List<Employee> csvEntity = new ArrayList<>();
        //各行から要素を取り出し、チェックする。
        for (String[] element : csvLine) {
            //csvファイルが空の時、break。
            if (csvLine.isEmpty()) {
                break;
            }
            String empno = element[0];
            String ename = element[1];
            String age = element[2];
            String years = element[3];
            String languages = element[4];
            System.out.println("行の中身は" + String.join(" ", element));
            System.out.println("項目数は" + element.length + "です。");

            //項目数が5つ出なかった場合
            if (element.length != 5) {
                System.out.println("項目数が違うのでスキップしました。");
                continue;
            }
            //もしヘッダーがあった場合はその行はスキップする。
            if (empno.contains("社員番号")) {
                System.out.println("ヘッダー行をスキップしました。");
                continue;
            }
            //登録時のフォーマットチェック
            EmployeeCheck.empnoAddMessages(empno, messages);
            EmployeeCheck.enameAddMessages(ename, messages);
            EmployeeCheck.ageAddMessages(age, messages);
            EmployeeCheck.languageAddMessages(languages, messages);
            EmployeeCheck.yearsAddMessages(years, messages);
            //empnoの重複チェック
            EmployeeDAO dao = new EmployeeDAO();
            int result = dao.employeeCountByEmpno(Integer.parseInt(empno));
            if (result != 0) {
                messages.add("社員番号" + empno + "番は既に登録されています。");
            }
            //チェック項目にエラーがなければ、登録情報をcsvEntityに追加。
            Employee entity = new Employee();
            entity.setEmpno(Integer.parseInt(empno));
            entity.setEname(ename);
            entity.setAge(Integer.parseInt(age));
            entity.setLanguages(languages);
            entity.setYears(Integer.parseInt(years));
            csvEntity.add(entity);
        }
        // 入力チェックNGの場合はエラーメッセージを出す。
        if (!messages.isEmpty()) {
            request.setAttribute("errorMessages", messages);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
            rd.forward(request, response);
            return;
        }

        //取り出したcsv情報をDBに登録する。
        EmployeeDAO dao = new EmployeeDAO();
        dao.employeeBalkInsert(csvEntity);
        response.sendRedirect("/Employee/TopServlet");
//        Part filePart = request.getPart("file");
//        InputStream fileContent = filePart.getInputStream();
//
//        Reader in = new InputStreamReader(fileContent);
//
//        CSVReader reader;
//        Iterator<String[]> iterator;
//
//        try {
//
//            reader = new CSVReader(new InputStreamReader(fileContent));
//            iterator = reader.iterator();
//
//            String[] row = iterator.next();
//
//            Map<Object, String> map = new HashMap<>();
//            for(int i = 0; i < row.length; i++){
//                map.put(i, row[i]);
//
//                //Do rest of the code
//
//            }
//
//        }catch(Exception e) {}
//
//        in.close();

    }

    /**
    * csvファイルの読み込みメソッド
    * @param part
    * @return List<String[]>
    * @exception IOException
    * @exception CsvException
    */
    private List<String[]> loadCsv(Part part) {
        List<String[]> csvLine = null;
        try (InputStream in = part.getInputStream();
                InputStreamReader inReader = new InputStreamReader(in);
                CSVReader readerCSV = new CSVReader(inReader)) {
            csvLine = readerCSV.readAll();
            return csvLine;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return csvLine;
    }
}
