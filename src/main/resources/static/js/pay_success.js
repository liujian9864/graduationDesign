$(document).ready(function() {
    showAvatar();
    let orderId = $.cookie("orderId");
    let price = $.cookie("price");
    var html="订单号："+orderId+"，支付金额¥"+price+"，收款方冻梨小二"
    $("#show").html(html);

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