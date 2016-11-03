<%@page import="com.sample.utils.DateUtils"%>
<%@page import="com.sample.utils.CheckInput"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sample.entities.Donars"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Donars</title>
    </head>

    <body>

        <div id="wrapper">

            <jsp:include page="../Template/header_and_menu.jsp"></jsp:include>

                <div id="page-wrapper">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Donars</h1>

                        <%
                            CheckInput checkinput = new CheckInput();
                            if (request.getParameter("m") != null) {
                                if (request.getParameter("m").equals("c")) {
                        %>

                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Donar Added Successfully..!
                        </div>
                        <%}
                            if (request.getParameter("m").equals("e")) {
                        %>

                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Donar Updated Successfully..!
                        </div>
                        <%} else if (request.getParameter("m").equals("d")) {
                        %>

                        <div class="alert alert-success alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            Donar Deleted Successfully..!
                        </div>
                        <%}%>
                    </div>
                    <%}%>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <a href="<%=request.getContextPath()%>/Donars/create" class="btn btn-md btn-success" style="text-align: left;">Add New Donar </a>
                        <br/><br/>

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Donar List
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Mobile</th>
                                            <th>Phone</th>
                                            <th>First Donation Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%if (request.getAttribute("donarsList") != null) {
                                                List<Donars> donarslist = (List<Donars>) request.getAttribute("donarsList");
                                                for (Donars donar : donarslist) {%>
                                        <tr>
                                            <td><%=checkinput.checkValue(donar.getName())%></td>
                                            <td>
                                                <%=checkinput.checkValue(donar.getMobile())%>
                                            </td>
                                            <td>
                                                <%=checkinput.checkValue(donar.getPhone())%>
                                            </td>
                                            <td><%=checkinput.checkValue(new DateUtils().dateWithFormat(donar.getDateOfFirstDonation(), "dd-MMMM-yyyy"))%></td>
                                            <td>
                                                <a class="btn btn-md btn-info" href="<%=request.getContextPath()%>/Donars/edit/<%=donar.getId()%>">Edit</a>
                                                <a class="btn btn-md btn-warning" href="<%=request.getContextPath()%>/Donars/view/<%=donar.getId()%>">View</a>
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
                    responsive: true,
                    aaSorting: [],
                    autoWidth: false
                });
            });
        </script>

    </body>

</html>

