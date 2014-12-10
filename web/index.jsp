
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<%@ page import="book.User" %>
<%@ page import="java.util.List"%>


<html>
<head>
    <title>Справочник</title>



    <script type="text/javascript" src="jquery-2.1.1.js"></script>

    <script type="text/javascript" >

        function edit(id, name, phone, address) {
            if (id != null) {
                var id = $('#id').val(id);
                var phone = $('#phone').val(phone);
                var address = $('#address').val(address);
                var name = $('#name').val(name);
            }
        }

        function del(id) {

            $.ajax({
                type: "POST",
                url: "/Book/editUser.do",
                data: {id: id},
                success: function(){
                    // we have the response
                    $('#table').load("users.jsp");
                },
                error: function(e){
                    alert('Error: ' + e);
                }
            })

        }

        function find() {
             var str = $('#sname').val();

            $.ajax({
                type: "POST",
                url: "/Book/viewAction.do",
                data: {str: str},
                success: function(){
                    // we have the response
                    $('#table').load("users.jsp");
                },
                error: function(e){
                    alert('Error: ' + e);
                }
            })


            $('#SearchForm').trigger('reset');

        }

        function postData()  {

            var id = $('#id').val();
            var phone = $('#phone').val();
            var address = $('#address').val();
            var name = $('#name').val();

            $.ajax({
                type: "POST",
                url: "/Book/ajaxAction.do",
                data: {name: name, phone: phone, id: id, address: address },
                success: function(){
                    // we have the response
                    $('#table').load("users.jsp");
                },
                error: function(e){
                    alert('Error: ' + e);
                }
            })


              $('#UserForm').trigger('reset');
        }



    </script>



</head>
<body>

<%--<%List<User> list = (List<User>)request.getAttribute("List"); %>--%>


<table align="center">   <tr>

    <table align="center">
        <html:form styleId="UserForm"  action="/ajaxAction">
        <html:hidden styleId="id" property="id"></html:hidden>
        <tr><td>   Enter user name </td><td> <html:text styleId="name" size="26" name="UserForm" property="name" />  </td></tr>
        <tr><td>    Enter phone number </td><td><html:text styleId="phone" size="26" name="UserForm" property="phone" />      </td></tr>
        <tr><td>   Enter user address </td><td> <html:text styleId="address" size="26" name="UserForm" property="address" />  </td></tr>
        <tr><th colspan="2">              <input type="button" onClick="postData()" style="width:100%" value="Submit" />

            </html:form>


            <html:form styleId="SearchForm" action="/viewAction">
        <tr><td>Enter user name</td><td><html:text size="26" name="SearchForm" styleId="sname"  property="str"/></td></tr>
        <tr><th colspan="2">  <input type="button" onClick="find()" style="width:100%" value="Search" /> </th></tr>

        </html:form>


    </table>  </tr>


    <tr>
        <div id="table">
        <%--<%@ include file="users.jsp"%>--%>
        <%--<div id="table">    </div>--%>

    <%--<table align="center" cellspacing="5">--%>
        <%--<tr>--%>
        <%--<td>User Name</td>--%>
        <%--<td>Phone</td>--%>
        <%--<td>Address</td>--%>
        <%--</tr>--%>
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

         </div>

        <%--</tr>--%>
        <%--</table>--%>
</table>

</body>
</html>