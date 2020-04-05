$.ajaxSetup({contentType: "application/json; charset=utf-8"});

function getParam(){
    var loginParam={
        userName:$("#username").val(),
        userPw:$("#password").val()
    }
    return loginParam;
};
$(document).on("click", "#loginBtn", function () {
    var jsonData=getParam();
    // alert(jsonData.userName);
    if (jsonData.userName==null || jsonData.userName == ''){
        alert("用户名不能为空！");
        return;
    }
    if (jsonData.userPw==null || jsonData.userPw == ''){
        alert("密码不能为空！");
        return;
    }
    $.ajax({
        type: 'post',
        url: '/users/login',
        data: JSON.stringify(jsonData),
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result.state == 2000) {
                $.cookie("avatar", result.data.avatar, {"expires": new Date(new Date().getTime() + 60 * 1000 * 5)});
                $.cookie("userName", result.data.userName, {"expires": new Date(new Date().getTime() + 60 * 1000 * 5)});
                window.location.href = "/index"
                // let avatar = $.cookie("avatar");
                // console.log(avatar);
            }else if (result.state == 4001) {
                alert("登录失败！" + result.message + "！");
            } else if (result.state == 4002) {
                alert("登录失败！" + result.message + "！");
            }
        }
    });
});