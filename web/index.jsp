
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<%@ page import="book.User" %>
<%@ page import="java.util.*"%>


<html>
  <head>
    <title>Справочник</title>
  </head>
  <body>

    <%List<User> list = (List<User>)request.getAttribute("List");
    System.out.println("Список " + list);
      %>


       <table align="center">   <tr>

      <table align="center">
          <html:form action="/addUser">
              <html:hidden property="id"></html:hidden>
          <tr><td>   Enter user name </td><td> <html:text size="26" name="UserForm" property="name" />  </td></tr>
          <tr><td>    Enter phone number </td><td><html:text size="26" name="UserForm" property="phone" />      </td></tr>
          <tr><td>   Enter user address </td><td> <html:text size="26" name="UserForm" property="address" />  </td></tr>
          <tr><th colspan="2"> <html:submit style="width:100%" value="Submit" /> </th></tr>
          </html:form>
      </table>  </tr>


         <tr>
           <table align="center" cellspacing="5">
             <tr>
               <td>User Name</td>
               <td>Phone</td>
               <td>Address</td>
           </tr>
         <%  if(list!=null) {

             for(User user:list) {   %>
         <tr>
             <td>
                 <%String link = "http://localhost:8080/findUser.do?id=" + user.getId() + "&edit=true";%>
                 <a href=<%=link%>>
                 <%=user.getName()%>
                 </a></td>
             <td><%=user.getPhone()%></td>
             <td><%=user.getAddress()%></td>
             <td><%String linkdel = "http://localhost:8080/findUser.do?id=" + user.getId() + "&edit=false";%>
               <a href=<%=linkdel%>> remove </a></td>
           </tr>    
          <%}} %>
         </tr>
       </table>
       </table>

  </body>
</html>