
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ page import="book.User" %>
<%@ page import="java.util.List"%>


<html>
<head>
    <title>Справочник</title>



    <script type="text/javascript" src="jquery-2.1.1.js"></script>

    <script type="text/javascript" >

        function postData() {
            var id = $("#id").val;
            var name = $("#name").val;
            var phone = $("#phone").val;
            var address = $("#address").val;



            $.ajax({
                type: "POST",
                url: "/Book/ajaxAction.do",
                data: $("#userForm").val ,
                success: function(){
                    // we have the response
                    alert("done");
                }
            });
        };



    </script>



</head>
<body>

<%List<User> list = (List<User>)request.getAttribute("List"); %>


<table align="center">   <tr>

    <table align="center">
        <html:form styleId="userform"  action="/ajaxAction">
        <html:hidden styleId="id" property="id"></html:hidden>
        <tr><td>   Enter user name </td><td> <html:text styleId="name" size="26" name="UserForm" property="name" />  </td></tr>
        <tr><td>    Enter phone number </td><td><html:text styleId="phone" size="26" name="UserForm" property="phone" />      </td></tr>
        <tr><td>   Enter user address </td><td> <html:text styleId="address" size="26" name="UserForm" property="address" />  </td></tr>
        <tr><th colspan="2">
            </html:form>
                <input type="button"style="width:100%" onclick="postData()" value="Submit" />

            <input type="button" onClick="postData()"
        <html:form action="/viewAction">
            <tr><td>Enter user name</td><td><html:text size="26" name="SearchForm"  property="str"/></td></tr>
            <tr><th colspan="2"> <html:submit style="width:100%" value="Search" /> </th></tr>

        </html:form>


    </table>  </tr>


    <tr>
        <table align="center" cellspacing="5">
            <tr>
                <td>User Name</td>
                <td>Phone</td>
                <td>Address</td>
            </tr>
            <%--<%  if(list!=null) {--%>

                <%--for(User user:list) {   %>--%>
            <%--<tr>--%>
                <%--<td>--%>

                    <%--<% String link = request.getContextPath() + "/editUser.do?id=" + user.getId() + "&edit=true";%>--%>
                    <%--<a href=<%=link%>>--%>
                        <%--<%=user.getName()%>--%>
                    <%--</a></td>--%>
                <%--<td><%=user.getPhone()%></td>--%>
                <%--<td><%=user.getAddress()%></td>--%>
                <%--<td><%String linkdel = request.getContextPath() + "/editUser.do?id=" + user.getId() + "&edit=false";%>--%>
                    <%--<a href=<%=linkdel%>> <img src="<%=request.getContextPath()%>/delete.png" width="15" height="15"> </a></td>--%>
            <%--</tr>--%>
            <%--<%}} %>--%>
            <div id="info" style="color: green;"></div>


            </tr>
        </table>
</table>

</body>
</html>