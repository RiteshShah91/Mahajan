<%@page import="com.gonenotgone.utilities.DateUtils"%>
<%@page import="java.util.Date"%>
<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12 copyright">
                <p>&copy; <%=new DateUtils().dateWithFormat(new Date(), "yyyy")%> GoneNotGone - All rights reserved            
                    <a href="<%=request.getContextPath()%>/Legal/">Legal</a>  
                    <a href="https://www.paypal.com/webapps/mpp/paypal-popup" title="How PayPal Works" onclick="javascript:window.open('https://www.paypal.com/webapps/mpp/paypal-popup', 'WIPaypal', 'toolbar = no, location = no,directories = no, status = no, menubar = no,scrollbars = yes, resizable = yes, width = 1060,height = 700');
                            return false;">
                            <img src="<%=request.getContextPath()%>/assets/gngTheme/images/paypal_logo.jpg" width="200" height="70" border="0" alt="PayPal Acceptance Mark">
                    </a>
                    <a href="#" onclick="window.open('https://www.sitelock.com/verify.php?site=gonenotgone.com', 'SiteLock', 'width=600,height=600,left=160,top=170');">
                        <img style="width: 110px; height: 65px;" width="200" alt="SiteLock" title="SiteLock" src="//shield.sitelock.com/shield/gonenotgone.com" />
                    </a>
                    <span id="siteseal"><script async type="text/javascript" src="https://seal.godaddy.com/getSeal?sealID=QkG0P0CyQCGOSHiIOb79XRC6x5qgXapcAYf4kwgygoq3PL6VPjuyhzJCbjal"></script></span>
                    <span style="padding-left: 0.75%;color: black;font-size: 85%;">Website Developed By K Business Solutions Inc.:<a href="http://www.kbizzsolutions.com/" target="_blank"><img src="<%=request.getContextPath()%>/assets/gngTheme/images/kbizz-icon.png" alt=""></a></span>
                </p>

            </div>

        </div>
    </div>
</footer>

<!-- jQuery -->
<script src="<%=request.getContextPath()%>/assets/gngTheme/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=request.getContextPath()%>/assets/gngTheme/js/bootstrap.min.js"></script>
<script>
                        $(function () {
                            var d = new Date();
                            var n = d.getFullYear();
                            $("#year").html(n);
                        });

</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-83362003-1', 'auto');
  ga('send', 'pageview');

</script>