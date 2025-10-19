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
<title>新規登録確認画面</title>
<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />
</head>
<body>
  <h3>登録確認画面</h3>
  <div class="container">
  <table>
    <tr>
      <th>社員番号</th>
      <td>${dto.empno}</td>
    </tr>
    <tr>
      <th>社員名</th>
      <td>${dto.ename}</td>
    </tr>
    <tr>
      <th>年齢</th>
      <td>${dto.age}</td>
    </tr>
    <tr>
      <th>勤続年数</th>
      <td>${dto.years}</td>
    </tr>
    <tr>
      <th>経験言語</th>
      <td>${dto.languages}</td>
    </tr>
    <tr>
      <th>所属部署番号</th>
      <td>${dto.deptno}</td>
    </tr>
  </table>
  </div>
  <form action="" method="Post">
    <input type="hidden" name="empno" value="${dto.empno}">
    <input type="hidden" name="ename" value="${dto.ename}">
    <input type="hidden" name="age" value="${dto.age}">
    <input type="hidden" name="years" value="${dto.years}">
    <input type="hidden" name="languages" value="${dto.languages}">
    <input type="hidden" name="deptno" value="${dto.deptno}">
    <input class="btn-success" type="submit" name="button" value="登録">
    <input class="btn-success" type="submit" name="button" value="修正">
  </form>
  <br>
  <div>
    <a class="btn btn-primary" href="javascript:history.back()">戻る</a>
  </div>
  <br>
  <form id="main" method="post" name="main" action="" onsubmit="redirect(this);">
    <input type="hidden" name="submit" />
  </form>
</body>
</html>