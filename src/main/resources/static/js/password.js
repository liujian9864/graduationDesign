$.ajaxSetup({contentType: "application/json; charset=utf-8"});

function getParam(){
    var regParam={
        newPwd:$("#newPwd").val(),
        userPw:$("#oldPwd").val()
    }
    return regParam;
};
$(document).on("click", "#rePwdBtn", function () {
    var jsonData=getParam();
    var rePwd=$("#rePwd").val();
    // alert(jsonData.userName);
    if(jsonData.newPwd!=rePwd){
        alert("密码不一致");
        return;;
    }
    $.ajax({
        type: 'post',
        url: '/users/change_password',
        data: JSON.stringify(jsonData),
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result.state == 2000) {
                alert("修改密码成功！");
                location.href = "login";
            } else {
                alert("修改密码失败！" + result.message + "！");
            }
        },
        "error":function() {
            alert("您的登录信息已经过期，请重新登录！\r\r请打开浏览器的Console或Network观察HTTP响应码，以判断错误的原因！");
            location.href = "login";
        }
    });
});