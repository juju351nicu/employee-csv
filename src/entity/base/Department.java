package entity.base;

import lombok.Data;

/**
 * DBのDepartmentのEntityクラス。JavaBean。 部署情報をフィールドに設定している。
 */
@Data
public class Department {
    private int deptno;
    private String dname;
}
