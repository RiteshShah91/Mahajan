<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sample.entities.Users"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Users</title>
    </head>

    <body>

        <div id="wrapper">

            <jsp:include page="../Template/header_and_menu.jsp"></jsp:include>

                <div id="page-wrapper">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Users</h1>

                        <%if (request.getParameter("m") != null) {
                                if (request.getParameter("m").equals("c")) {
                        %>

                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            User Created Successfully..!
                        </div>
                        <%}
                            if (request.getParameter("m").equals("e")) {
                        %>

                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            User Updated Successfully..!
                        </div>
                        <%} else if (request.getParameter("m").equals("d")) {
                        %>

                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            User Deleted Successfully..!
                        </div>
                        <%}%>
                    </div>
                    <%}%>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <a href="<%=request.getContextPath()%>/Users/precreate" class="btn btn-md btn-success" style="text-align: left;">Create New User </a>
                        <br/><br/>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                User List
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Phone</th>
                                            <th>Email</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%if (request.getAttribute("userList") != null) {
                                                List<Users> userlist = (List<Users>) request.getAttribute("userList");
                                                for (Users user : userlist) {%>
                                        <tr>
                                            <td><%=user.getName()%></td>
                                            <td><%=user.getPhone()%></td>
                                            <td><%=user.getUsername()%></td>
                                            <td><%=user.getStatus()%></td>
                                            <td>
                                                <a class="btn btn-md btn-info" href="<%=request.getContextPath()%>/Users/edit/<%=user.getId()%>">Edit</a>
                                                <%--                                                <a class="btn btn-md btn-danger" onclick="return confirm('Are you sure to remove this user ?')" href="<%=request.getContextPath()%>/Users/delete/<%=user.getId()%>">Delete</a>--%>
                                            </td>
                                        </tr>
                                        <%}
                                        } else {%>
                                        No Data Available ..!!
                                        <%}%>
                                    </tbody>
                                </table>
                                <!-- /.table-responsive -->

                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="<%=request.getContextPath()%>/assets/vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="<%=request.getContextPath()%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="<%=request.getContextPath()%>/assets/vendor/metisMenu/metisMenu.min.js"></script>

        <!-- DataTables JavaScript -->
        <script src="<%=request.getContextPath()%>/assets/vendor/datatables/js/jquery.dataTables.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/vendor/datatables-responsive/dataTables.responsive.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../dist/js/sb-admin-2.js"></script>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').DataTable({
                    responsive: true
                });
            });
        </script>

    </body>

</html>

