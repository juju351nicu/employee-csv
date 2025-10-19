package web.employee;

import java.io.IOException;

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
 * 入力された登録情報を確認、実行するサーブレット。
 * employeeInsertConfirm.jspに対応。
 *
 */
@WebServlet("/EmployeeInsertConfirm")
public class EmployeeInsertConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeInsertConfirmServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("dto");
        session.removeAttribute("dto");
        request.setAttribute("dto", user);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/employeeInsertConfirm.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        // 入力値を受け取る。
        String type = request.getParameter("button");
        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String age = request.getParameter("age");
        String languages = request.getParameter("languages");
        String years = request.getParameter("years");
        String deptno = request.getParameter("deptno");

        Employee entity = new Employee();
        entity.setEmpno(Integer.parseInt(empno));
        entity.setEname(ename);
        entity.setAge(Integer.parseInt(age));
        entity.setLanguages(languages);
        entity.setYears(Integer.parseInt(years));
        entity.setDeptno(Integer.parseInt(deptno));

        EmployeeDAO dao = new EmployeeDAO();
        //修正の場合
        if (type.equals("修正")) {
            request.getSession().setAttribute("dto", entity);
            response.sendRedirect("./Insert");
        }
        //登録の場合
        else {
            dao.employeeInsert(entity);
            String message = "登録が完了しました。";
            request.setAttribute("message", message);
            response.sendRedirect("./TopServlet");
        }
    }

}
