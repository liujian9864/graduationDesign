$(document).ready(function() {
    let avatar = $.cookie("avatar");
    // avatar="E:\\workspace\\myevaluate\\src\\main\\resources\\static\\"+avatar;
    // alert(avatar);
    $("#img-avatar").attr("src", avatar);
});
$(document).on("click", "#btn-change-avatar", function () {
    // alert("准备上传头像！");
    $.ajax({
        "url":"/users/change_avatar",
        "data":new FormData($("#form-change-avatar")[0]),
        "processData":false,
        "contentType":false,
        "type":"post",
        "dataType":"json",
        "success":function(json) {
            if (json.state == 2000) {
                alert("上传头像成功！" + json.data);
                // 显示新头像
                $("#img-avatar").attr("src", json.data);
                // 将新头像的路径存入到Cookie中
                $.cookie("avatar", json.data, {"expires": 7});
                // location.href = "login.html";
            } else {
                alert("上传头像失败！" + json.message + "！");
            }
        },
        "error":function() {
            alert("您的登录信息已经过期，请重新登录！\r\r请打开浏览器的Console或Network观察HTTP响应码，以判断错误的原因！");
            // location.href = "login.html";
        }
    });
})