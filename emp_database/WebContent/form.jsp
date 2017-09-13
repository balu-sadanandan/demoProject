<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Operations</title>
</head>
<body>
    <center>
        <h1>Employee Database</h1>
        <h2>
            <a href="new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Employees</a>
            
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${emp != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${emp == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${emp != null}">
                        Edit Employee
                    </c:if>
                    <c:if test="${emp == null}">
                        Add New Employee
                    </c:if>
                </h2>
            </caption>
                <c:if test="${emp != null}">
                    <input type="hidden" name="id" value="<c:out value='${emp.emp_id}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="40"
                            value="<c:out value='${emp.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Contact No. </th>
                <td>
                    <input type="text" name="cno" size="40"
                            value="<c:out value='${emp.cno}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>emailid: </th>
                <td>
                    <input type="text" name="mid" size="50"
                            value="<c:out value='${emp.mailid}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
        ${cnocheck=='fal' ? "Check the Contact number entered " : ""}
        
    </div>   
</body>
</html>