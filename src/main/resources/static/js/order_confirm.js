$(document).ready(function() {
    showAvatar();
    showProduct();
    showAddressList();

    $("#btn-create").click(function() {
        var data=getParam();
        createOrder(data);
    });
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
function getParam() {
    var param={
        productId:$("#productId").val(),
        addressId:$("#address-list").val(),
        price:$("#price").text()
    }
    return param;
}
function createOrder(data) {

    $.ajax({
        type: 'post',
        url: '/orders/create',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success:function(json) {
            if (json.state == 2000) {
                alert("创建订单成功！订单号=" + json.data.orderId + "，订单金额：" + json.data.price);
                $.cookie("orderId", json.data.orderId, {"expires": new Date(new Date().getTime() + 60 * 1000 * 10)});
                $.cookie("price", json.data.price, {"expires": new Date(new Date().getTime() + 60 * 1000 * 10)});
                window.location.href = "/payment";
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
function showAddressList() {
    $("#address-list").empty();
    $.ajax({
        "url":"/addresses/",
        "type":"get",
        "dataType":"json",
        "success":function(json) {
            let list = json.data;
            console.log("count=" + list.length);
            for (let i = 0; i < list.length; i++) {
                console.log("name=" + list[i].name);
                let op = '<option value="#{aid}">#{tag}  /  #{name}  /  #{phone}  /  #{provinceName}#{cityName}#{areaName}  #{address}</option>';

                op = op.replace(/#{aid}/g, list[i].addressId);
                op = op.replace(/#{tag}/g, list[i].tag);
                op = op.replace(/#{name}/g, list[i].name);
                op = op.replace(/#{phone}/g, list[i].phone);
                op = op.replace(/#{provinceName}/g, list[i].provinceName);
                op = op.replace(/#{cityName}/g, list[i].cityName);
                op = op.replace(/#{areaName}/g, list[i].areaName);
                op = op.replace(/#{address}/g, list[i].address);

                $("#address-list").append(op);
            }
        }
    });
}
function showProduct() {
    $("#cart-list").empty();
    $.ajax({
        "url":"/products/findByProductId",
        "type":"get",
        "success":function(json) {
                let html = "<tr>"
                    + "<td><img src='"+json.data.image+"' class='img-responsive' /></td>"
                    + "<td><input type='hidden' id='productId' value='"+json.data.productId+"' />"+json.data.title+"</td>"
                    + "<td>¥<span>"+json.data.price+"</span></td>"
                    + "<td>1</td>"
                    + "<td>¥<span id='price'>"+json.data.price+"</span></td>"
                    + "</tr>";
            $("#selectTotal").html(json.data.price);
            $("#cart-list").html(html);
            }
    });
}

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
