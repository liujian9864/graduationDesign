function getParam(){
    var param={
        title:$("#title").val(),
        productType:$("#productType").val(),
        price:$("#price").val(),
        message:$("#message").val()
    }
    return param;
}

$("#btn-addnew").click(function() {
    var param=getParam();
    $.ajax({
        "url":"/products/addnew",
        "data": JSON.stringify(param),
        "type":"post",
        "contentType": "application/json; charset=utf-8",
        "success":function(json) {
            if (json.state == 2000) {
                $.ajax({
                    "url":"/products/addnewPic",
                    "data":new FormData($("#form-change-avatar")[0]),
                    "processData":false,
                    "contentType":false,
                    "type":"post",
                    "dataType":"json",
                    "success":function(json1) {
                        if (json1.state == 2000) {
                            alert("商品发布成功！" + json1.data);
                            location.href = "index";
                        } else {
                            alert("商品发布失败！" + json1.message + "！");
                        }
                    }
                });
            } else {
                alert("发布失败！" + json.message + "！");
            }
        },
        "error":function() {
            alert("您的登录信息已经过期，请重新登录！\r\r请打开浏览器的Console或Network观察HTTP响应码，以判断错误的原因！");
            location.href = "login";
        }
    });
});