package web.employee;

import java.io.IOException;
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
 * Employee一覧情報をDBから取得し、出力するサーブレット。
 * top.jspに対応。
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TopServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> list = dao.employeeSelectAll();
        session.setAttribute("user", list);
        // 検索一覧を表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
        rd.forward(request, response);
    }
}
