$(function() {
    showAvatar();
    showAllByPage(1,3);
    function showAllF1ByPage(currentPage,currentRows) {
        var tabId='book';
        $.ajax({
            type: "post",
            url: "/product/findByPage",
            data:tabId,
            contentType: "application/json; charset=utf-8",
            success:function (result){
                var body="";
                for(i=0;i<result.products.length;i++){
                    var tr="";
                    if(i!=0 && i%3==0){
                        tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    }
                    tr+="<div class='goods'><img src='imgs/book/"+1+".jpg' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goos_details'>"+特别的猫+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+25+"</span>";
                    tr+="<p>" +"2007年诺贝尔文学奖得主多丽丝·莱辛一部非虚构类代表作《特别的猫》中文版, 多丽丝·莱辛是个爱猫成痴的作家，她在《特别的猫》里讲述了人与猫之间的动人故事, 细数曾经让她欢欣也让她忧愁的猫。在她笔下，猫的世界精彩纷呈。故事从莱辛在非洲的童年开始。娇美的公主灰咪咪和低调的黑猫咪因为争宠上演了一出出情景剧；同为猫妈妈，育儿之道却大相径庭，令观者莞尔；"+ "</p></div>";
                    body+=tr;
                }
                $("#f1Content").html(body);

                if (result.products.length>=3){
                    $("#showAllBookFenyeDiv").style.display='block';
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
                                showAllByPage(currentPage-1);
                            }
                        }else if(id == "nextPage1"){
                            if(currentPage == totalPage){
                                alert("已经是最后一页了");
                            }else{
                                showAllByPage(currentPage+1);
                            }
                        }else if(id == "numberPage1"){
                            showAllByPage($(this).text());
                        }
                    })
                    var number = currentPage-1;
                    $(".fenli1:eq("+number+")").attr("class","am-active");
                }
            }
        })
    }
    function showAllByPage(currentPage,currentRows) {
        $.ajax({
            type: "get",
            url: "/products/findByPage",
            dataType: "json",
            data:{productType:'book',page:currentPage,rows:currentRows},
            success:function (result){
                var f1body = "";
                for(i=0;i<result.data.products.length;i++){
                    // $.cookie("productId", result.data[i].productId, {"expires": 7});
                    // let avatar = $.cookie("avatar");
                    var tr="";
                    // if(i!=0 && i%3==0){
                    //     tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    // }
                    tr+="<div class='goods'><img src='"+result.data.products[i].image+"' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goods_details?productId="+result.data.products[i].productId+"'>"+result.data.products[i].title+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+result.data.products[i].price+"</span>";
                    tr+="<p>" +result.data.products[i].message+ "</p></div>";
                    f1body+=tr;
                }
                $("#f1Content").html(f1body);

            }
        })

        $.ajax({
            type: "get",
            url: "/products/findByPage",
            dataType: "json",
            data:{productType:'numerical',page:currentPage,rows:currentRows},
            success:function (result){
                var f2body = "";
                for(i=0;i<result.data.products.length;i++){
                    var tr="";
                    // if(i!=0 && i%3==0){
                    //     tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    // }
                    tr+="<div class='goods'><img src='"+result.data.products[i].image+"' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goods_details?productId="+result.data.products[i].productId+"'>"+result.data.products[i].title+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+result.data.products[i].price+"</span>";
                    tr+="<p>" +result.data.products[i].message+ "</p></div>";
                    f2body+=tr;
                }
                $("#f2Content").html(f2body);

            }
        })

        $.ajax({
            type: "get",
            url: "/products/findByPage",
            dataType: "json",
            data:{productType:'clothes',page:currentPage,rows:currentRows},
            success:function (result){
                var f3body = "";
                for(i=0;i<result.data.products.length;i++){
                    var tr="";
                    if(i!=0 && i%3==0){
                        tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    }
                    tr+="<div class='goods'><img src='"+result.data.products[i].image+"' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goods_details?productId="+result.data.products[i].productId+"'>"+result.data.products[i].title+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+result.data.products[i].price+"</span>";
                    tr+="<p>" +result.data.products[i].message+ "</p></div>";
                    f3body+=tr;
                }
                $("#f3Content").html(f3body);

            }
        })

        $.ajax({
            type: "get",
            url: "/products/findByPage",
            dataType: "json",
            data:{productType:'daily',page:currentPage,rows:currentRows},
            success:function (result){
                var f4body = "";
                for(i=0;i<result.data.products.length;i++){
                    var tr="";
                    if(i!=0 && i%3==0){
                        tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    }
                    tr+="<div class='goods'><img src='"+result.data.products[i].image+"' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goods_details?productId="+result.data.products[i].productId+"'>"+result.data.products[i].title+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+result.data.products[i].price+"</span>";
                    tr+="<p>" +result.data.products[i].message+ "</p></div>";
                    f4body+=tr;
                }
                $("#f4Content").html(f4body);

            }
        })

        $.ajax({
            type: "get",
            url: "/products/findByPage",
            dataType: "json",
            data:{productType:'vehicle',page:currentPage,rows:currentRows},
            success:function (result){
                var f5body = "";
                for(i=0;i<result.data.products.length;i++){
                    var tr="";
                    if(i!=0 && i%3==0){
                        tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    }
                    tr+="<div class='goods'><img src='"+result.data.products[i].image+"' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goods_details?productId="+result.data.products[i].productId+"'>"+result.data.products[i].title+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+result.data.products[i].price+"</span>";
                    tr+="<p>" +result.data.products[i].message+ "</p></div>";
                    f5body+=tr;
                }
                $("#f5Content").html(f5body);

            }
        })

        $.ajax({
            type: "get",
            url: "/products/findByPage",
            dataType: "json",
            data:{productType:'other',page:currentPage,rows:currentRows},
            success:function (result){
                var f6body = "";
                for(i=0;i<result.data.products.length;i++){
                    var tr="";
                    if(i!=0 && i%3==0){
                        tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    }
                    tr+="<div class='goods'><img src='"+result.data.products[i].image+"' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goods_details?productId="+result.data.products[i].productId+"'>"+result.data.products[i].title+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+result.data.products[i].price+"</span>";
                    tr+="</div>";
                    f6body+=tr;
                }
                $("#f6Content").html(f6body);

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
});
