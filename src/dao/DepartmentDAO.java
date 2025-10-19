package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EmployeeJoinDepartment;
import entity.base.Department;
import utils.DBConnector;

/**
 * MySQLを利用したDAOクラス。CRUD処理を行う。
 *
 */
public class DepartmentDAO {
    /**
    * プライマリキーによる部署の上司情報の検索。
    * @param id
    * @return entity
    * @throws SQLException
    */
    public EmployeeJoinDepartment departmentPrimaryKey(int id)  {
        String sql = "SELECT e.ename, e.age, e.years, d.dname " +
                "FROM test2.department d " +
                "JOIN test2.employee e on d.mgr_no = e.empno " +
                "WHERE d.deptno = ?";
        EmployeeJoinDepartment entity = null;
        try (Connection conn = DBConnector.getConnect(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                entity = new  EmployeeJoinDepartment();
                entity.setEname(rs.getString("e.ename"));
                entity.setAge(rs.getInt("e.age"));
                entity.setYears(rs.getInt("e.years"));
                Department base = new Department();
                base.setDname(rs.getString("d.dname"));
                entity.setDepartment(base);
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
