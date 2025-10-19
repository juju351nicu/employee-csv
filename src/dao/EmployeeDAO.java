package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.base.Employee;
import utils.DBConnector;

/**
 * MySQLを利用したDAOクラス。CRUD処理を行う。
 *
 */
public class EmployeeDAO {
    /**
    * 社員情報のselect文、全件検索。
    *
    * @return List<Employee> 型
    * @throws SQLException
    */
    public List<Employee> employeeSelectAll() {
        List<Employee> resultList = new ArrayList<>();
        String sql = "SELECT empno,ename,age,languages,years,deptno FROM test2.employee";
        try (Connection conn = DBConnector.getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee entity = createEmployeeFromResultSet(rs);
                resultList.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
    * プライマリーキーによる個人を特定
    *
    * @param empno
    * @return Employee
    * @throws SQLException
    */
    public Employee employeeSelectByEmpno(int empno) {
        Employee entity = null;
        String sql = "SELECT empno,ename,age,languages,years,deptno FROM employee WHERE id = ?";
        try (Connection conn = DBConnector.getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                entity = createEmployeeFromResultSet(rs);
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
    * 取得したSQLを受け取るためのメソッド。
    * @param rs executeQuery()で発行したSQL
    * @return Employee
    * @throws SQLException
    */
    private Employee createEmployeeFromResultSet(ResultSet rs) {
        Employee entity = null;
        try {
            entity = new Employee();
            entity.setEmpno(rs.getInt("empno"));
            entity.setEname(rs.getString("ename"));
            entity.setAge(rs.getInt("age"));
            entity.setLanguages(rs.getString("languages"));
            entity.setYears(rs.getInt("years"));
            entity.setDeptno(rs.getInt("deptno"));
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
    * 社員情報の登録。
    *
    * @param entity
    * @return int 登録した行数が返ってくる。
    * @throws SQLException
    */
    public int employeeInsert(Employee entity) {
        String sql = "INSERT INTO employee (empno, ename, age, languages, years, deptno) VALUES (?,?,?,?,?,?)";
        int result = 0;
        try (Connection conn = DBConnector.getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, entity.getEmpno());
            pstmt.setString(2, entity.getEname());
            pstmt.setInt(3, entity.getAge());
            pstmt.setString(4, entity.getLanguages());
            pstmt.setInt(5, entity.getYears());
            pstmt.setInt(6, entity.getDeptno());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
    * プライマリーキーの重複判定
    * @param empno
    * @return 重複しなければcountは0のため
    * countが0だとtrue 0意外だとfalse
    * @throws SQLException
    */
    public int employeeCountByEmpno(int empno) {
        int count = 0;
        String sql = "SELECT COUNT(empno) FROM test2.employee WHERE empno = ?";
        try (Connection conn = DBConnector.getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empno);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("COUNT(empno)");
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
    * Listで受け取ったEmployeeを一括登録するメソッド。
    * @param List<Employee>
    * @return int
    */
    public int employeeBalkInsert(List<Employee> list) {
        String sql = "INSERT INTO employee (empno, ename, age, languages, years)"
                + " VALUES (?,?,?,?,?)";
        int result = 0;
        try (Connection conn = DBConnector.getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < list.size(); i++) {
                pstmt.setInt(1, list.get(i).getEmpno());
                pstmt.setString(2, list.get(i).getEname());
                pstmt.setInt(3, list.get(i).getAge());
                pstmt.setString(4, list.get(i).getLanguages());
                pstmt.setInt(5, list.get(i).getYears());
                result = pstmt.executeUpdate();
            }
            System.out.println(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
