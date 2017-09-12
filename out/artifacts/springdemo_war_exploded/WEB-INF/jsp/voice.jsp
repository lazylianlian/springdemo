<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: llsam
  Date: 2017/9/9
  Time: 下午8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<audio controls="controls" loop="loop">

<c:forEach items="${txtList}" varStatus="status" var="item">
      <source src="http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&spd=4&text=${item}"  type="audio/mpeg" />
    </c:forEach>
    </audio>
</body>


</html>
