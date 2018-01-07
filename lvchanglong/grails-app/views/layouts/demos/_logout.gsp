<a href="#" id="logoutModal">退出</a>

<script>
    jQuery("#logoutModal").click(function () {
        jQuery.post("${createLink(controller: "vip", action: "logout")}", function (data) {
            window.location.reload();
        });
    });
</script>