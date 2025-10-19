package entity.base;

import lombok.Data;

/**
 * DBのEmployeeのEntityクラス。JavaBean。 従業員のidや年齢、プログラミング言語などをフィールドに設定している。
 *
 */
@Data
public class Employee {
    private int empno;
    private String ename;
    private int age;
    private String languages;
    private int years;
    private int deptno;

    public Employee() {
    }

    public Employee(int empno, String ename, int age, String languages, int years, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.age = age;
        this.languages = languages;
        this.years = years;
        this.deptno = deptno;
    }
}
