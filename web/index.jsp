
<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>


<%@ page import="book.User" %>
<%@ page import="java.util.List"%>


<html>
  <head>
    <title>Справочник</title>
      <script type="text/javascript" src="prototype.js" />

          <script type="text/javascript" >

              try {
                  function postData() {
                      var url = '${contextPath}/ajaxAction.do?method=postData';
                      var params = { text: $F['id'], text: $F['name'], text: $F['address'], text: $F['phone']};
                      new Ajax.Updater('usr', url, {method: 'post', parameters: params});

                  }
              }
              catch(error) {alert(error)}


      </script>



  </head>
  <body>

    <%List<User> list = (List<User>)request.getAttribute("List"); %>


       <table align="center">   <tr>

      <table align="center">
          <html:form action="/addUser">
              <html:hidden styleId="id" property="id"></html:hidden>
          <tr><td>   Enter user name </td><td> <html:text styleId="name" size="26" name="UserForm" property="name" />  </td></tr>
          <tr><td>    Enter phone number </td><td><html:text styleId="phone" size="26" name="UserForm" property="phone" />      </td></tr>
          <tr><td>   Enter user address </td><td> <html:text styleId="address" size="26" name="UserForm" property="address" />  </td></tr>
          <tr><th colspan="2">
                  <%--<html:submit style="width:100%" value="Submit" />--%>

                      </html:form>
                      <button onclick="postData()">Submit</button>
          <html:form styleId="2"  action="/viewAction">
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
         <%  if(list!=null) {

             for(User user:list) {   %>
         <tr>
             <td>

                 <% String link = request.getContextPath() + "/editUser.do?id=" + user.getId() + "&edit=true";%>
                 <a href=<%=link%>>
                 <%=user.getName()%>
                 </a></td>
             <td><%=user.getPhone()%></td>
             <td><%=user.getAddress()%></td>
             <td><%String linkdel = request.getContextPath() + "/editUser.do?id=" + user.getId() + "&edit=false";%>
               <a href=<%=linkdel%>> <img src="<%=request.getContextPath()%>/delete.png" width="15" height="15"> </a></td>
           </tr>    
          <%}} %>
         </tr>
       </table>
       </table>

  </body>
</html>