package web.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import entity.base.Employee;

/**
 * Employee登録情報を入力するサーブレット。
 * employeeInsertForm.jspに対応。
 */
@WebServlet("/Insert")
public class EmployeeInsertFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeInsertFormServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //確認画面から戻ってきた場合、sessionから受け取る。
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("dto");
        session.removeAttribute("dto");
        request.setAttribute("dto", user);
        // メンバ追加画面に飛ぶ
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/employeeInsertForm.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        // 入力値を受け取る。
        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String age = request.getParameter("age");
        String languages = request.getParameter("languages");
        String years = request.getParameter("years");
        String deptno = request.getParameter("deptno");

        // 入力チェック。
        List<String> messages = new ArrayList<>();
        EmployeeCheck.empnoAddMessages(empno, messages);
        EmployeeCheck.enameAddMessages(ename, messages);
        EmployeeCheck.ageAddMessages(age, messages);
        EmployeeCheck.languageAddMessages(languages, messages);
        EmployeeCheck.yearsAddMessages(years, messages);
        EmployeeCheck.deptnoAddMessages(deptno, messages);
        //empnoの重複チェック
        EmployeeDAO dao = new EmployeeDAO();
        int result = dao.employeeCountByEmpno(Integer.parseInt(empno));
        if (result != 0) {
            messages.add("こちらの社員番号は既に登録されています。");
        }
        // 入力チェックNGの場合はエラーメッセージを出す。
        if (!messages.isEmpty()) {
            request.setAttribute("errorMessages", messages);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
            rd.forward(request, response);
            return;
        }

        Employee entity = new Employee();
        entity.setEmpno(Integer.parseInt(empno));
        entity.setEname(ename);
        entity.setAge(Integer.parseInt(age));
        entity.setLanguages(languages);
        entity.setYears(Integer.parseInt(years));
        entity.setDeptno(Integer.parseInt(deptno));
        //確認画面へ飛ぶ。
        request.getSession().setAttribute("dto", entity);
        response.sendRedirect("./EmployeeInsertConfirm");

    }

}
