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
<title>一括登録</title>
<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />
</head>
<body>
  <div class="container">
    アップロードするファイルを選択し、[アップロード]ボタンを押してください。
    <form name="upload" action="" method="POST" enctype="multipart/form-data">
      <input type="file" name="uploadfile"/>
      <br />
      <input type="submit" class="btn btn-primary" value="アップロード" />
    </form>
  </div>
</body>
</html>
