<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="../../../css/login.css">

    <script type="text/javascript">
        function changeImg() {
            var img = document.getElementById("img");
            img.src = "/verificationCode.do?date=" + new Date();
        }

        function checkVerificationCode() {
            var verificationCode = document.getElementById('verificationCode').value;
            var flag = (getCookie('v_c_v') == verificationCode);
            if (!flag) {
                alert('验证码输入错误');
            }
            return flag;
        }

        function getCookie(cookie_name) {
            var allCookies = document.cookie;
            var cookie_pos = allCookies.indexOf(cookie_name);   //如果找到了索引，就代表cookie存在
            if (cookie_pos != -1) {
                cookie_pos += cookie_name.length + 1;
                var cookie_end = allCookies.indexOf(";", cookie_pos);
                if (cookie_end == -1) {
                    cookie_end = allCookies.length;
                }
                return unescape(allCookies.substring(cookie_pos, cookie_end));
            }
            return null;
        }
    </script>
</head>
<body>
<div class="login">
    <div class="header">
        <h1>
            <a href="/login.do">登录</a>
            <a href="/regPrompt.do">注册</a>
        </h1>
        <button></button>
    </div>
    <form action="/regedit.do" method="post">
        <div class="name">
            <input type="text" id="name" name="username" placeholder="请输入用户名">
            <p></p>
        </div>
        <div class="pwd">
            <input type="password" id="pwd" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
            <p></p>
        </div>
        <div class="idcode">
            <input type="text" id="verificationCode" placeholder="请输入验证码">
            <a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;换一张</a>
            <span><img id="img" src="/verificationCode.do"/></span>
            <div class="clear"></div>
        </div>
        <div class="btn-red">
            <input onclick="return checkVerificationCode();" type="submit" value="注册" id="login-btn">
        </div>
    </form>
</div>
</body>
</html>