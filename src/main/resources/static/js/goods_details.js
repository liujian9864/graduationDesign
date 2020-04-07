$(function() {
    showAvatar();
    showMessage(1,5);
    showAllProduct();
    function showAllProduct() {
        $.ajax({
            type: "get",
            url: "/products/findByProductId",
            dataType: "json",
            success:function (result) {
                $("#productId").val(result.data.productId);
                $("#title").html(result.data.title);
                $("#img_product").attr("src",result.data.image);
                $("#phone").attr("data-content",result.data.phone);
                $("#price").html(result.data.price);
                $("#productType").html(result.data.productType);
                $("#message").html(result.data.message);
            }
        })
    }
    $(document).on("click", "#addMessage", function () {
        var message=$('#messages').val();
        $.ajax({
            type: "post",
            url: "/products/addMessage",
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({message:message}),
            success:function (result) {
                if (result.state==2000){
                    alert("留言成功");
                    showMessage(1,5);
                    $('#messages').val("")
                }else if(result.state==4001){
                    alert("请先登录后再留言");
                    location.href = "login";
                }
            }
        })
    })
    function showMessage(currentPage,currentRows){
        $.ajax({
            type: "get",
            url: "/products/showMessage",
            dataType: "json",
            data:{page:currentPage,rows:currentRows},
            success:function (result){
                var totalPage = result.data.totalPage;
                var currentPage = result.data.currentPage;
                var body="";
                if (result.data.messages.length==0){
                    $("#fContent").css("height","110");
                    $(".second_content_center").css("height","1300");
                }
                if (result.data.messages.length==1){
                    $("#fContent").css("height","220");
                    $(".second_content_center").css("height","1410");
                }
                if (result.data.messages.length==2){
                    $("#fContent").css("height","330");
                    $(".second_content_center").css("height","1520");
                }
                if (result.data.messages.length==3){
                    $("#fContent").css("height","440");
                    $(".second_content_center").css("height","1630");
                }
                if (result.data.messages.length==4){
                    $("#fContent").css("height","550");
                    $(".second_content_center").css("height","1740");
                }
                if (result.data.messages.length>4){
                    $("#fContent").css("height","660");
                    $(".second_content_center").css("height","1850");
                }
                for(i=0;i<result.data.messages.length;i++){
                    var tr="";
                    tr+="<div class='attr'> <label class='label1'>用户：</label>";
                    tr+="<span class='s1'>"+result.data.messages[i].userName+"</span>";
                    tr+="<span class='s2'>"+result.data.messages[i].date+"</span><br>";
                    tr+="<span class='s3'>"+result.data.messages[i].message+"</span></div>";
                    body+=tr;
                }
                body+="<div><div id='showAllBookFenyeDiv' style='margin-right:20px;'><ul id='showAllBookFenye' class='am-pagination am-fr'></ul></div></div>"

                $("#fContent").html(body);
                var ul = "";
                for(i=1;i<=totalPage;i++){
                    var li = "";
                    li += "<li class='fenli fenli1'><a class='fenye fenye1' id='numberPage1'>"+i+"</a></li>";
                    ul += li;
                }
                var uli =""
                uli += "<li><a class='fenye fenye1' id='previousPage1'>«</a></li>";
                uli += ul;
                uli += "<li><a class='fenye fenye1' id='nextPage1'>»</a></li>";
                $("#showAllBookFenye").html(uli);

                $(".fenye1").unbind("click");
                $(".fenye1").click(function(){
                    var id = $(this).attr("id");
                    if(id == "previousPage1"){
                        if(currentPage == 1){
                            alert("已经第一页了");
                        }else{
                            showAllByPage(currentPage-1,5);
                        }
                    }else if(id == "nextPage1"){
                        if(currentPage == totalPage){
                            alert("已经是最后一页了");
                        }else{
                            showAllByPage(currentPage+1,5);
                        }
                    }else if(id == "numberPage1"){
                        showAllByPage($(this).text(),5);
                    }
                })
                var number = currentPage-1;
                $(".fenli1:eq("+number+")").attr("class","am-active");

            }
        })
    }
    $(document).on("click", "#search-button", function () {
        var searchKey=$('#search').val();
        $.cookie("searchKey", searchKey, {"expires": 7});
        window.location.href = "/after_search";
    })
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
    $(document).on("click", "#exit", function () {
        $.cookie("avatar",null);
        $.cookie("userName",null);
        window.location.href = "/login";
    })
    $(document).on("click", "#buy", function () {
        var productId=$("#productId").val();
        window.location.href = "/order_confirm?productId="+productId;
    })
})