<%-- 
    Document   : output
    Created on : Oct 1, 2016, 2:23:40 PM
    Author     : Administrator
--%>

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
                                <h3 class="panel-title">Create User</h3>
                            </div>
                            <div class="panel-body">
                            <%
                                if (request.getAttribute("errors") != null) {%>
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <%=request.getAttribute("errors").toString()%>
                            </div>  
                            <%}
                            %>
                            <form role="form" method="POST" action="<%=request.getContextPath()%>/Users/postcreate">
                                <fieldset>
                                    <div class="form-group">
                                        <label>Name : </label>
                                        <input required class="form-control" placeholder="Full Name" name="name" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label>Phone : </label>
                                        <input required class="form-control" placeholder="Phone" name="phone" type="text">
                                    </div>
                                    <div class="form-group">
                                        <label>Username : </label>
                                        <input required class="form-control" placeholder="E-mail" name="email" type="text">
                                    </div>
                                    <div class="form-group">
                                        <label>Password : </label>
                                        <input required class="form-control" placeholder="Password" name="password" type="password" value="">
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" class="btn btn-md btn-success" value="Save">
                                        <a class="btn btn-md btn-danger" href="<%=request.getContextPath()%>/Users/">Cancel</a>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
