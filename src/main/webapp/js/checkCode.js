
  //  <%-- 增加时间戳来更换验证码图片 --%>
    window.onload=function(){
    document.getElementById("img").onclick=function(){
        this.src = "verify?time=" + new Date().getTime();
    }
}
