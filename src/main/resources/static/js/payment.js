$(document).ready(function() {
    showAvatar();
    let orderId = $.cookie("orderId");
    let price = $.cookie("price");
    var html="订单号："+orderId+"，支付金额¥"+price+"，收款方达冻梨小二"
    $("#show").html(html);
    $("#price").html(price);

    $(document).on("click", "#confirmPay", function () {
        pay(orderId);
    })

    $(document).on("click", "#search-button", function () {
        var searchKey=$('#search').val();
        $.cookie("searchKey", searchKey, {"expires": 7});
        window.location.href = "/after_search";
    })
    $(document).on("click", "#exit", function () {
        $.cookie("avatar",null);
        $.cookie("userName",null);
        window.location.href = "/login";
    })
});
function showAvatar(){
    let avatar = $.cookie("avatar");
    let userName = $.cookie("userName");
    if(userName != null){
        $("#userName").css("display","inline-block");
        $(".avatar").css("display","inline-block");
        $("#exit").css("display","inline-block");
        $("#login").css("display","none");
        $("#reg").css("display","none");
        $("#exit").css("display","inline-block");

        $("#userName").text(userName);
        $("#avatar").attr("src", avatar);
    }
}
function pay(id) {
    $.ajax({
        type: 'post',
        url: '/orders/update',
        data: JSON.stringify({orderId:id}),
        contentType: "application/json; charset=utf-8",
        success:function(json) {
            if (json.state == 2000) {
                $.cookie("orderId", json.data.orderId, {"expires": new Date(new Date().getTime() + 60 * 1000 * 10)});
                $.cookie("price", json.data.price, {"expires": new Date(new Date().getTime() + 60 * 1000 * 10)});
                window.location.href = "/pay_success";
            } else {
                alert("创建订单失败！" + json.message + "！");
            }
        },
        error:function() {
            alert("您的登录信息已过期！请重新登录！");
            window.location.href = "/login";
        }
    });
}