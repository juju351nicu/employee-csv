package entity;

import entity.base.Department;
import entity.base.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * Employeeを拡張し、Departmentを集約したクラス
 *
 */
@Getter
@Setter
public class EmployeeJoinDepartment extends Employee {
    private Department department;
}
