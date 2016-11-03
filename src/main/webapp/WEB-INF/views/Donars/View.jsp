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
                    <div class="col-md-8 col-md-offset-1">
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
                                <div class="form-group">
                                    <label>Donar Name : </label>
                                    <span><%=donar.getName()%> </span>
                                </div>
                                <div class="form-group">
                                    <label>Address : </label>
                                    <span><%=checkInput.checkValue(donar.getAddress())%> </span>
                                </div>
                                <div class="form-group">
                                    <label>Mobile : </label>
                                    <span><%=checkInput.checkValue(donar.getMobile())%> </span>
                                </div>
                                <div class="form-group">
                                    <label>Phone : </label>
                                    <span><%=checkInput.checkValue(donar.getPhone())%> </span>
                                </div>
                                <div class="form-group">
                                    <label>First Donation Date : </label>
                                    <span><%=checkInput.checkValue(new DateUtils().dateWithFormat(donar.getDateOfFirstDonation(), "dd-MMMM-yyyy"))%> </span>
                                </div>

                                <div class="form-group">
                                    <label>Created Date : </label>
                                    <span><%=checkInput.checkValue(donar.getCreatedDate())%> </span>
                                </div>
                                <div class="form-group">
                                    <label>Last Modified Date : </label>
                                    <span><%=checkInput.checkValue(donar.getModifiedDate())%> </span>
                                </div>
                                <div class="form-group">
                                    <a class="btn btn-md btn-default" href="<%=request.getContextPath()%>/Donars/">Back</a>
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
