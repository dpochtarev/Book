<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="book.User" %>
<%@ page import="java.util.List" %>


<%--<%List<User> list = (List<User>)request.getAttribute("List"); %>--%>

<%List<User> list = (List<User>)session.getAttribute("List"); %>



<table align="center" cellspacing="5">
    <tr>
        <td>User Name</td>
        <td>Phone</td>
        <td>Address</td>
        <td></td>
    </tr>
    <%  if(list!=null) {

        for(User user:list) {   %>
    <tr>
        <td>

            <% String params = String.format("'%s', '%s', '%s', '%s'", user.getId(), user.getName(), user.getPhone(), user.getAddress());%>
            <a href="#" onClick="edit(<%=params%>)">
                <%=user.getName()%>
            </a></td>
        <td><%=user.getPhone()%></td>
        <td><%=user.getAddress()%></td>
        <td>
        <%--<%String linkdel = request.getContextPath() + "/editUser.do?id=" + user.getId() + "&edit=false";%>&ndash;%&gt;--%>
            <a href="#" onclick="del(<%=user.getId()%>)"> <img src="<%=request.getContextPath()%>/delete.png" width="15" height="15"> </a></td>
    </tr>
    <%}} %>



    </tr>
</table>
