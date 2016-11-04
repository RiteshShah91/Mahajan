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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css">

    <title>Donors Diary - Edit</title>
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
                                <h3 class="panel-title">Update Donor</h3>
                            </div>
                            <div class="panel-body">
                            <%
                                CheckInput checkInput = new CheckInput();
                                if (request.getAttribute("donar") != null) {
                                    Donars donar = (Donars) request.getAttribute("donar");

                            %>
                            <%                               if (request.getAttribute("errors") != null) {
                                    List<String> errors = (List<String>) request.getAttribute("errors");
                                    if (!errors.isEmpty()) {
                            %>
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <%
                                    Iterator<String> iterator = errors.iterator();
                                    while (iterator.hasNext()) {
                                        out.println("<i class='fa fa-arrow-circle-right'></i> " + iterator.next() + "<br/>");
                                    }
                                %>
                                <%
                                    }
                                %>
                            </div>
                            <%
                                }

                            %>
                            <!--                            <div class="alert alert-danger alert-dismissable" style="display: none;">
                                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                                            <div id="msg"></div>
                                                        </div>-->
                            <form role="form" method="POST" action="<%=request.getContextPath()%>/Donors/postedit/<%=donar.getId()%>">
                                <fieldset>
                                    <div class="form-group">
                                        <label>Donor Name* : </label>
                                        <input required class="form-control" id="donarname" value="<%=checkInput.checkValueEdit(donar.getName(), request.getParameter("donarname"))%>" placeholder="Donor's Full Name" name="donarname" type="text" autofocus>
                                    </div>

                                    <div class="form-group">
                                        <label>Mobile No. : </label>
                                        <input class="form-control" id="mobile" value="<%=checkInput.checkValueEdit(donar.getMobile(), request.getParameter("mobile"))%>" placeholder="Mobile Number" name="mobile" type="text">
                                    </div>

                                    <div class="form-group">
                                        <label>Phone No. : </label>
                                        <input class="form-control" id="phone" value="<%=checkInput.checkValueEdit(donar.getPhone(), request.getParameter("phone"))%>" placeholder="Phone/Land Line Number" name="phone" type="text">
                                    </div>

                                    <div class="form-group">
                                        <label>Address : </label>
                                        <textarea class="ckeditor" cols="80" id="editor" name="address" rows="10"><%=checkInput.checkValueEdit(donar.getAddress(), request.getParameter("address"))%></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label>First Donation date : </label>
                                        <input type="text" id="donation_date" name="donation_date" value="<%=checkInput.checkValueEdit(new DateUtils().dateWithFormat(donar.getDateOfFirstDonation(), "dd-MMMM-yyyy"), request.getParameter("donation_date"))%>" class="form-control" placeholder="Donation Date">
                                    </div>


                                    <div class="form-group">
                                        <input id="submit" type="submit" class="btn btn-md btn-success" value="Save">
                                        <a class="btn btn-md btn-danger" href="<%=request.getContextPath()%>/Donors/">Cancel</a>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/vendor/ckeditor/ckeditor.js"></script>
    <script src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap-datepicker.js"></script>
    <script>
        var editor = CKEDITOR.replace('editor', {
            toolbar: [
                {name: 'document', groups: ['mode', 'document', 'doctools'], items: ['Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates']},
                {name: 'clipboard', groups: ['clipboard', 'undo'], items: ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo']},
                {name: 'editing', groups: ['find', 'selection', 'spellchecker'], items: ['Find', 'Replace', '-', 'SelectAll', '-', 'Scayt']},
                {name: 'forms', items: ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField']},
                //                                '/',
                {name: 'basicstyles', groups: ['basicstyles', 'cleanup'], items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat']},
                {name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align', 'bidi'], items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language']},
                //                                {name: 'links', items: ['Link', 'Unlink', 'Anchor']},
                {name: 'insert', items: ['Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe']},
                //                                '/',
                {name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize']},
                {name: 'colors', items: ['TextColor', 'BGColor']},
                {name: 'tools', items: ['Maximize', 'ShowBlocks']},
                {name: 'others', items: ['-']},
                {name: 'about', items: ['About']}
            ]
        });


        $('#donation_date').datepicker({
            maxDate: '0d',
            format: "dd-MM-yyyy",
            autoclose: true
        });
    </script>
</body>

</html>
