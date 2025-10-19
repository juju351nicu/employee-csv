<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Chache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>新規登録画面</title>
<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />
</head>
<body>
<div class="container">
  <form id="sender" action="" method="POST">
    <table border="1">
      <tr>
        <th>社員番号</th>
        <td><input type="text" name="empno" size="10" value="${dto.empno}" /></td>
      </tr>
      <tr>
        <th>社員名</th>
        <td><input type="text" name="ename" size="10" value="${dto.ename}"/></td>
      </tr>
      <tr>
        <th>年齢</th>
        <td><input type="text" name="age" size="10" value="${dto.age}"/></td>
      </tr>
      <tr>
        <th>勤続年数</th>
        <td><input type="text" name="years" size="10" value="${dto.years}"/></td>
      </tr>
      <tr>
        <th>経験言語</th>
        <td><input type="text" name="languages" size="10" value="${dto.languages}"/></td>
      </tr>
      <tr>
        <th>所属部署番号</th>
        <td><input type="text" name="deptno" size="10" value="${dto.deptno}"/></td>
      </tr>
    </table>
    <input type="submit" value="確認" class="btn btn-primary">
  </form>
  </div>
</body>
</html>