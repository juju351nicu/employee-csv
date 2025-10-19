<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>トップページへ</title>
<jsp:include page="nav.jsp" />
<jsp:include page="header.jsp" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
</head>
<body>
    <div class="alert alert-primary" role="alert">

    <c:forEach items="" var="errorMessage">

    </c:forEach>
  </div>
  <br>
  <div>
    <h1>所属長の詳細</h1>
    <table>
      <tr>
        <th>部署名</th>
        <td>${department.dname}</td>
      </tr>
      <tr>
        <th>部長名</th>
        <td>${employee.ename}</td>
      </tr>
      <tr>
        <th>年齢</th>
        <td>${employee.age}</td>
      </tr>
      <tr>
        <th>勤続年数</th>
        <td>${employee.years}</td>
      </tr>
    </table>
  </div>
  <br>
  <h2><font color="red">${message}</font></h2>
  <h1>メンバ一覧</h1>
  <div class="container">
    <table
      class="table table-clickable table-bordered table-hover table-striped">
      <tr>
        <th>社員番号</th>
        <th>社員名</th>
        <th>年齢</th>
        <th>勤続年数</th>
        <th>経験言語</th>
        <th>所属長の詳細</th>
      </tr>
      <c:forEach items="${user}" var="dto">
        <tr>
          <td>${dto.empno}</td>
          <td>${dto.ename}</td>
          <td>${dto.age}</td>
          <td>${dto.years}</td>
          <td>${dto.languages}</td>
          <td><form action="/Employee/DepartmentDetail" method="Get">
              <input name="deptno" type="hidden" value="${dto.deptno}" />
              <input type="submit" value="上司の情報を上に表示します。" class="btn-danger">
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <br>
    <form action="/Employee/Insert" method="Get">
      <input type="submit" value="メンバ追加" class="btn btn-danger">
    </form>

    <form action="/Employee/CsvUpload" method="Get">
      <input type="submit" value="メンバ一括登録" class="btn btn-primary">
    </form>

    <form action="/Employee/ListDownload" method="Get">
      <input type="submit" value="メンバ一括ダウンロード" class="btn btn-primary">
    </form>
  <br>
  <div class="alert alert-danger" role="alert">
    <c:forEach items="${errorMessages}" var="errorMessage">
    ${errorMessage}<br/>
    </c:forEach>
  </div>
</body>
</html>
