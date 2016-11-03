<%-- 
    Document   : output
    Created on : Oct 1, 2016, 2:23:40 PM
    Author     : Administrator
--%>

<%@page import="com.sample.utils.DateUtils"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.sample.utils.CheckInput"%>
<%@page import="java.util.List"%>
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

    <title>Edit Task</title>
</head>
<body>
    <div id="wrapper">

        <jsp:include page="../Template/header_and_menu.jsp"></jsp:include>

            <div id="page-wrapper">

                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <br/>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">View Task</h3>
                            </div>
                            <div class="panel-body">
                            <%
                                CheckInput checkInput = new CheckInput();
                                if (request.getAttribute("donar") != null) {
                                    Donars donar = (Donars) request.getAttribute("donar");

                            %>

                            <fieldset>
                                <div class="col-md-10">
                                    <div class="form-group">
                                        <label class="col-md-4 ">Donar Name : </label>
                                        <div class="col-md-4">
                                            <span><%=donar.getName()%> </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4">Address : </label>
                                        <div class="col-md-4">
                                            <span><%=checkInput.checkValue(donar.getAddress())%> </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4">Mobile : </label>
                                        <div class="col-md-4">
                                            <span><%=checkInput.checkValue(donar.getMobile())%> </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4">Phone : </label>
                                        <div class="col-sm-4">
                                            <span><%=checkInput.checkValue(donar.getPhone())%> </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4">First Donation Date : </label>
                                        <div class="col-sm-4">
                                            <span><%=checkInput.checkValue(new DateUtils().dateWithFormat(donar.getDateOfFirstDonation(), "dd-MMMM-yyyy"))%> </span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-4">Created Date : </label>
                                        <div class="col-sm-4">
                                            <span><%=checkInput.checkValue(new DateUtils().dateWithFormat(donar.getCreatedDate(), "dd-MMMM-yyyy, hh:mm a"))%> </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4">Last Modified Date : </label>
                                        <div class="col-sm-4">
                                            <span><%=checkInput.checkValue(new DateUtils().dateWithFormat(donar.getModifiedDate(), "dd-MMMM-yyyy, hh:mm a"))%> </span>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <a class="btn btn-md btn-default" href="<%=request.getContextPath()%>/Donars/">Back</a>
                                    </div>
                                </div>
                            </fieldset>
                            </form>
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
