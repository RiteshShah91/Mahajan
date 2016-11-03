<%-- 
    Document   : output
    Created on : Oct 1, 2016, 2:23:40 PM
    Author     : Administrator
--%>

<%@page import="java.util.Iterator"%>
<%@page import="com.apps.utils.CheckInput"%>
<%@page import="com.apps.entities.MA_Tasks"%>
<%@page import="com.apps.entities.MA_Projects"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.apps.entities.MA_Users"%>
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
                                <h3 class="panel-title">Edit Task</h3>
                            </div>
                            <div class="panel-body">
                            <%
                                CheckInput checkInput = new CheckInput();
                                if (request.getAttribute("task") != null) {
                                    MA_Tasks task = (MA_Tasks) request.getAttribute("task");

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
                            <div class="alert alert-danger alert-dismissable" style="display: none;">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <div id="msg"></div>
                            </div>
                            <form role="form" method="POST" action="<%=request.getContextPath()%>/Task/postedit/<%=task.getId()%>">
                                <fieldset>
                                    <input type="hidden" value="<%=task.getId()%>" id="taskid" name="taskid">
                                    <div class="form-group">
                                        <label>Subject : </label>
                                        <input required class="form-control" placeholder="Subject" value="<%=task.getSubject()%>" name="subject" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label>Description : </label>
                                        <textarea class="ckeditor" cols="80" id="editor" name="detail" rows="10"><%=task.getDescrition()%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Projects : </label>
                                        <select name="project" class="form-control">
                                            <option value="">Select Project</option>
                                            <%
                                                if (request.getAttribute("projectList") != null) {
                                                    List<MA_Projects> projects = (List<MA_Projects>) request.getAttribute("projectList");
                                                    for (MA_Projects project : projects) {
                                            %>
                                            <option <%=checkInput.checkValueEdit(task.getProject().getId().toString(), request.getParameter("project")).equals(project.getId()) ? "selected" : ""%>  value="<%=project.getId()%>"><%=project.getProject_name()%></option>

                                            <%}
                                                }%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Users : </label>
                                        <select name="user" class="form-control">
                                            <option value="">Select User</option>
                                            <%
                                                if (request.getAttribute("userList") != null) {
                                                    List<MA_Users> users = (List<MA_Users>) request.getAttribute("userList");
                                                    for (MA_Users user : users) {
                                            %>
                                            <option <%=checkInput.checkValueEdit(task.getUser().getId().toString(), request.getParameter("user")).equals(user.getId()) ? "selected" : ""%>   value="<%=user.getId()%>"><%=user.getName()%></option>
                                            <%--<option value="<%=country.getCountryid()%>"><%=country.getName()%></option>--%>
                                            <%}
                                                }%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Task Type: </label>
                                        <select name="type" class="form-control">
                                            <option value="">Select Type</option>
                                            <option  <%=checkInput.checkValueEdit(task.getTaskType(), request.getParameter("type")).equals("Task") ? "selected" : ""%> value="Task">Task</option>
                                            <option <%=checkInput.checkValueEdit(task.getTaskType(), request.getParameter("type")).equals("Bug") ? "selected" : ""%>  value="Bug">Bug</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Task Priority </label>
                                        <select name="priority" class="form-control">
                                            <option value="">Select Priority</option>
                                            <option <%=checkInput.checkValueEdit(task.getPriority(), request.getParameter("priority")).equals("Low") ? "selected" : ""%> value="Low">Low</option>
                                            <option <%=checkInput.checkValueEdit(task.getPriority(), request.getParameter("priority")).equals("Normal") ? "selected" : ""%> value="Normal">Normal</option>
                                            <option <%=checkInput.checkValueEdit(task.getPriority(), request.getParameter("priority")).equals("High") ? "selected" : ""%>  value="High">High</option>
                                            <option <%=checkInput.checkValueEdit(task.getPriority(), request.getParameter("priority")).equals("Critical") ? "selected" : ""%> value="Critical">Critical</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <input id="submit" type="submit" class="btn btn-md btn-success" value="Save">
                                        <a class="btn btn-md btn-danger" href="<%=request.getContextPath()%>/Task/">Cancel</a>
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

//        $(function () {
//            $("#submit").click(function (event) {
//                event.preventDefault();
//                $('#submit').attr('disabled', 'disabled');
//                $.ajax({
//                    type: "POST",
//                    contentType: "html",
//                    dataType: 'html',
//                    url: "<%=request.getContextPath()%>/Task/postedit",
//                    data: {taskid $("#taskid").val(), subject: $("#subject").val(), detail: editor.getData(), project: $("#project").val(),
//                                user: $("#user").val(), type: $("#type").val(), priority: $("#priority").val()},
//                    async: false,
//                    success: function (response) {
//                        var data = response.split(":");
//                        if (data[1] === 'err') {
//                            $("#msg").html(data[0]);
//                            $('.alert-danger').show();
//                            $('html, body').animate({
//                                scrollTop: $("body").offset().top
//                            }, 500);
//                            $("#submit").removeAttr("disabled");
//                        } else {
//                            console.log(data[1]);
//                            window.location.href = data[0];
//
//                        }
//                    }
//                });
//            });
//        });
    </script>
</body>

</html>
