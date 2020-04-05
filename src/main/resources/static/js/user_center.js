
$(function(){
    showAllInfo();
    function showAllInfo() {
        $.ajax({
            type: "get",
            url: "/users/getUserById",
            dataType: "json",
            success:function (result){
                if (result.state == 2000) {
                    $('#username').val(result.data.userName);
                    // $('#userId').val(result.userId);
                    $('#phone').val(result.data.phone);
                    $('#email').val(result.data.email);
                    if (result.data.sex==1){
                        $("input[name='sex'][value='1']").attr("checked",true);
                    }else if(result.data.sex==0){
                        $("input[name='sex'][value='0']").attr("checked",true);
                    }
                }else {
                    alert("获取用户信息失败！" + result.message + "！");
                    //应该跳转到服务器端用于退出登录的URL
                    location.href = "login";
                }
            },
            "error":function() {
                alert("您的登录信息已经过期，请重新登录！\r\r请打开浏览器的Console或Network观察HTTP响应码，以判断错误的原因！");
                location.href = "login";
            }
        });
    }

    function getParam(){
        var regParam={
            userName:$("#username").val(),
            phone:$("#phone").val(),
            email:$("#email").val(),
            sex:$('input:radio[name="sex"]:checked').val()
        }
        return regParam;
    };
    $(document).on("click", "#saveDetail", function () {
        var jsonData=getParam();
        $.ajax({
            type: 'post',
            url: '/users/change_info',
            data: JSON.stringify(jsonData),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (result.state == 2000) {
                    alert("修改成功！")
                } else {
                    alert("修改失败！" + result.message + "！");
                }
            },
            "error":function() {
                alert("您的登录信息已经过期，请重新登录！\r\r请打开浏览器的Console或Network观察HTTP响应码，以判断错误的原因！");
                location.href = "login";
            }
        });
    });
});
