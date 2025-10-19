package web.department;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import entity.EmployeeJoinDepartment;
import entity.base.Department;

/**
 *	所属部署の、上司情報を表示させるためのサーブレットクラス。
 *
 */
@WebServlet("/DepartmentDetail")
public class DepartmentDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DepartmentDetailServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");

        // 入力チェック。
        List<String> messages = new ArrayList<>();
        DepartmentCheck.deptnoAddMessages(deptno, messages);
        // 入力チェックNGの場合はエラーメッセージを出す。
        if (!messages.isEmpty()) {
            request.setAttribute("errorMessages", messages);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
            rd.forward(request, response);
            return;
        }

        DepartmentDAO dao = new DepartmentDAO();
        EmployeeJoinDepartment entity = dao.departmentPrimaryKey(Integer.parseInt(deptno));
        //上司がいない場合は、上司がいないメッセージを表示。
        if (entity == null) {
            request.setAttribute("errorMessages", "上司はおりません。");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
            rd.forward(request, response);
            return;
        }

        Department base = new Department();
        base.setDname(entity.getDepartment().getDname());

        request.setAttribute("employee", entity);
        request.setAttribute("department", base);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
        rd.forward(request, response);
    }
}
