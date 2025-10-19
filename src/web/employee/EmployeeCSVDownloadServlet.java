package web.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.base.Employee;

/**
 * Employee一覧情報をCSVとして出力するサーブレット。
 * 一覧情報を文字列に変えてダウンロードする。
 */
@WebServlet("/ListDownload")
public class EmployeeCSVDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeCSVDownloadServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //一覧情報を取得
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> list = dao.employeeSelectAll();

        //listの中に何も入っていなかったらエラーメッセージ。
        if (list.isEmpty()) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            rd.forward(request, response);
            request.setAttribute("errorMessage", "ダウンロードできる情報がありません。");
            return;
        }
        //CVSヘッダー情報
        StringBuilder sbCSV = new StringBuilder("社員番号" + "," + "社員名" + "," + "年齢" + "," + "経験言語" + "," + "勤続年数" + "\r\n");

        //一覧情報をStringBuilderに追加していく。
        for (int i = 0; i < list.size(); i++) {
            int empno = list.get(i).getEmpno();
            String ename = list.get(i).getEname();
            int age = list.get(i).getAge();
            String languages = list.get(i).getLanguages();
            int years = list.get(i).getYears();
            String csvString = empno + "," + ename + "," + age + "," + languages + "," + years
                    + "\r\n";
            sbCSV.append(csvString);
        }
        // 文字コードと出力するcsvファイル名を設定
        response.setContentType("application/octet-stream; charset=\"Shift_JIS\"");
        response.setHeader("Content-Disposition", "attachment; filename=\"EmployeeList.csv\"");
        //PrintWriterにて出力。
        try (PrintWriter pw = response.getWriter()) {
            pw.print(sbCSV);
            pw.flush();
        }
    }

}
