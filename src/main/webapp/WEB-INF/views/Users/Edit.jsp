<%-- 
    Document   : output
    Created on : Oct 1, 2016, 2:23:40 PM
    Author     : Administrator
--%>

<%@page import="com.sample.entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create User</title>
</head>
<body>
    <div id="wrapper">

        <jsp:include page="../Template/header_and_menu.jsp"></jsp:include>

            <div id="page-wrapper">

                <div class="row">
                    <div class="col-md-8 col-md-offset-1">
                        <br/>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Edit User</h3>
                            </div>
                            <div class="panel-body">
                            <%
                                if (request.getAttribute("user") != null) {
                                    Users user = (Users) request.getAttribute("user");

                            %>
                            <%                                if (request.getAttribute("errors") != null) {%>
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <%=request.getAttribute("errors").toString()%>
                            </div>  
                            <%}
                            %>
                            <form method="POST" action="<%=request.getContextPath()%>/Users/post-edit/<%=user.getId()%>">
                                <fieldset>
                                    <div class="form-group">
                                        <label>Name : </label>
                                        <input type="text" class="form-control" name="name" value="<%=user.getName()%>" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label>Phone : </label>
                                        <input type="text" class="form-control" name="phone" value="<%=user.getPhone()%>">
                                    </div>
                                    <div class="form-group">
                                        <label>Username : </label>
                                        <input required class="form-control" value="<%=user.getUsername()%>" placeholder="E-mail" name="email" type="email">
                                    </div>
                                    <div class="form-group">
                                        <label>Password : </label>
                                        <input required class="form-control" value="<%=user.getPassword()%>" placeholder="Password" name="password" type="password" value="">
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" class="btn btn-md btn-success" value="Update">
                                        <a class="btn btn-md btn-danger" href="<%=request.getContextPath()%>/Users/">Cancel</a>
                                    </div>
                                </fieldset>
                            </form>
                                    <%}else{%>
                                    No Data Found !
                                    <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
