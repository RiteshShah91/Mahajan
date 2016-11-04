<%-- 
    Document   : output
    Created on : Oct 1, 2016, 2:23:40 PM
    Author     : Administrator
--%>

<%@page import="com.sample.utils.DateUtils"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.sample.utils.CheckInput"%>
<%@page import="java.util.List"%>
<%@page import="com.sample.entities.Donars"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Donors Diary - View</title>
</head>
<body>
    <div id="wrapper">

        <jsp:include page="../Template/header_and_menu.jsp"></jsp:include>

            <div id="page-wrapper">

                <div class="row">
                    <div class="col-md-10">
                        <br/>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">View Donor</h3>
                            </div>
                            <div class="panel-body">
                            <%
                                CheckInput checkInput = new CheckInput();
                                if (request.getAttribute("donar") != null) {
                                    Donars donar = (Donars) request.getAttribute("donar");

                            %>
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">First Name : </label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(donar.getName())%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">Address : </label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(donar.getAddress())%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">Mobile :</label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(donar.getMobile())%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">Phone :</label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(donar.getPhone())%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">First Donation Date :</label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(new DateUtils().dateWithFormat(donar.getDateOfFirstDonation(), "dd-MMMM-yyyy"))%>
                                            </p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">Created Date :</label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(new DateUtils().dateWithFormat(donar.getCreatedDate(), "dd-MMMM-yyyy"))%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">Created By :</label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(donar.getCreatedBy())%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">Last Changing Date :</label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(new DateUtils().dateWithFormat(donar.getModifiedDate(), "dd-MMMM-yyyy"))%>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">Last Changed By :</label>
                                        <div class="col-sm-6">
                                            <p>
                                                <%=checkInput.checkValue(donar.getModifiedBy())%>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <div class="form-group">
                                <a class="btn btn-md btn-default" href="<%=request.getContextPath()%>/Donors/">Back</a>
                            </div>
                            <%} else {%>
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
