<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>books</title>
    <script>
        // function downLoad(name) {
        //     $.ajax({
        //         url:"/file/downLoad",
        //         method:"post",
        //         data:{"name":name},
        //         success:function(data) {
        //             alert(data)
        //         }
        //     })
        // }
    </script>
</head>
<body>
<table border="1">
    <h>欢迎 !&nbsp${userName}</h><br>
    <h><a href="/logout">退出</a></h>
    <tr>
        <th>资料名称</th>
        <th>操作</th>
    </tr>
        <c:forEach items="${files}" var="file">
     <tr>
        <td><input type="text" value="${file.name}"></td>
        <td><a href="/file/downLoad?name=${file.name}">点击下载</a></td>
    </tr>
        </c:forEach>

</table>
<form action="/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
