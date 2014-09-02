
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.Impl.UserDAOImpl" %>

<%@ page import="main.resources.User" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<html>
  <head>
    <title>Справочник</title>
  </head>
  <body>

    <%List<User> list =(List<User>)request.getAttribute("List");
    System.out.println("Список " + list);%>


       <table align="center">   <tr><td>
  <form method="post" action="<%=request.getContextPath()%>/Handler">
      <table>
          <tr><td>   Enter user name </td><td> <input type="text" name="name" size="26">   </td></tr>
          <tr><td>    Enter phone number </td><td> <input type="text" name="phone" size="26">      </td></tr>
          <tr><td>   Enter user address </td><td> <input type="text" name="address" size="26">   </td></tr>

          <tr><th colspan="2">   <input type="submit" style="width:100%"value="Add new user"> </th></tr>
      </table>
  </form>
  </tr>  <tr>  <td>
       <form method="post" action="<%=request.getContextPath()%>/Editor">
           <table>
                 <tr><td> Enter user name </td><td> <input type="text" name="search" size="26">   </td></tr>
               <tr><th colspan="2">   <input type="submit" style="width:100%"value="Search user"> </th></tr>
           </table>

       </form>

       </td> </tr>
         <tr> <td>
  <table >
      <font SIZE="12">   <tr><td>Name</td><td>Phone</td><td>Address</td></tr>     </font>


      <%--<%--%>
          <%--for(User user:list) {--%>
      <%--%>--%>
         <%--<tr><td><%=user.getName()%></td><td><%=user.getPhone()%></td><td><%=user.getAddress()%></td></tr>--%>

       <%--<%--%>
           <%--}--%>
       <%--%>--%>



  </table>
         </td></tr>
       </table>
  </body>
</html>


