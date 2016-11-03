
<div class="side-banner" id="img1">
    <%--    <img alt="" src="<%=request.getContextPath()%>/assets/gngTheme/images/life-insurance.jpg" class="img-responsive img-brdr" />--%>
</div>

<div class="side-banner" id="img2">
    <%--    <img alt="" src="<%=request.getContextPath()%>/assets/gngTheme/images/morris-law.jpg" class="img-responsive img-brdr" />--%>
</div>

<div class="side-banner" id="img3">
    <%--    <img alt="" src="<%=request.getContextPath()%>/assets/gngTheme/images/claim-care.jpg" class="img-responsive img-brdr" />--%>
</div>

<div class="side-banner" id="img4">
    <%--    <img alt="" src="<%=request.getContextPath()%>/assets/gngTheme/images/lic.jpg" class="img-responsive img-brdr" />--%>
</div>
<script type="text/javascript">
    window.onload = function WindowLoad(event) {
        var a = Math.floor(Math.random() * 10) + 1;
        //document.getElementById("msg").innerHTML = "Hello World : " + a;
        //$("#msg").html("Hello World : " + a);

        $.ajax({
            type: "GET",
            url: '<%=request.getContextPath()%>/HeaderAdd/getHorizontalAdList/',
            dataType: 'json',
            success: function (data) {
                if (data === "") {
                    console.log(data);
                } else {

                    var cnt = 1;
                    $.each(data.adList, function ()
                    {
                        var file = "";
                        var fileUrl = "<%=request.getContextPath()%>/ImageAccess/" + this.imgUrl + "/" + this.acountId + "";
                        var redirectionUrl = '<%=request.getContextPath()%>/AdvertisementHistory/click-ad/'+this.adId;
                        var advertisementName = this.advertisementName;

                        file = "<a href='" + redirectionUrl + "' target='_blank'><img id='headerAdd' src='" + fileUrl + "' style='height :128px;' width='100%' title='" + advertisementName + "' class='img-responsive img-brdr' ></a>";
                        $("#img" + cnt).html(file);
                        
                        cnt++;
                        //$('#img'+cnt).append($('<option/>').val(this.value).text(this.name));
                    });

                }
            }
        });
    }

</script>