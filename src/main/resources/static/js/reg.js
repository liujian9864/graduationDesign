$.ajaxSetup({contentType: "application/json; charset=utf-8"});

function getParam(){
    var regParam={
        userName:$("#username").val(),
        userPw:$("#password").val()
    }
    return regParam;
};
$(document).on("click", "#regBtn", function () {
    var jsonData=getParam();
    var rePaw=$("#rePaw").val();
    // alert(jsonData.userName);
    if (jsonData.userName==null || jsonData.userName == ''){
        alert("用户名不能为空！");
        return;
    }
    if (jsonData.userPw==null || jsonData.userPw == ''){
        alert("密码不能为空！");
        return;
    }
    if (rePaw==null || rePaw == ''){
        alert("请确认密码！");
        return;
    }
    if(jsonData.userPw!=rePaw){
        alert("密码不一致");
        return;;
    }
    $.ajax({
        type: 'post',
        url: '/users/reg',
        data: JSON.stringify(jsonData),
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result.state == 2000) {
                alert("注册成功！");
                window.location.href = "/login"
            }else if (result.state == 4000) {
                alert("注册失败！" + result.message + "！");
            } else {
                alert("注册失败！" + result.message + "！");
            }
        }
    });
});