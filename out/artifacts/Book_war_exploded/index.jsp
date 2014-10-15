
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<%@ page import="book.User" %>
<%@ page import="java.util.*"%>


<html>
  <head>
    <title>Справочник</title>
  </head>
  <body>

    <%String list =(String)request.getAttribute("List");
    System.out.println("Список " + list);%>


       <table align="center">   <tr><td>

           <html:form method="POST" action="/addUser">

      <table>
          <tr><td>   Enter user name </td><td> <html:text size="26" name="UserForm" property="name" />  </td></tr>
          <tr><td>    Enter phone number </td><td><html:text size="26" name="UserForm" property="phone" />      </td></tr>
          <tr><td>   Enter user address </td><td> <html:text size="26" name="UserForm" property="address" />  </td></tr>

          <tr><th colspan="2">   <html:submit style="width:100%" value="Add new user" /> </th></tr>
      </table>

  </tr>  <tr>  <td>
            </html:form>


       </td> </tr>
         <tr> <td>
         <%=list%>

         </td></tr>
       </table>
  </body>
</html>


