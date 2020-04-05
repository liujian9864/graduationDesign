$(function() {
    showAvatar();
    showAllByPage(1,15)
    function showAllByPage(currentPage,currentRows) {
        $.ajax({
            type: "get",
            url: "/products/findByPage",
            dataType: "json",
            data:{productType:'other',page:currentPage,rows:currentRows},
            success:function (result){
                $("#f1Content").html("");
                $("#f2Content").html("");
                $("#f3Content").html("");
                $("#f4Content").html("");
                $("#f5Content").html("");
                $("#f2Content").css("display","none");
                $("#f3Content").css("display","none");
                $("#f4Content").css("display","none");
                $("#f5Content").css("display","none");
                var totalPage = result.data.totalPage;
                var currentPage = result.data.currentPage;
                var body="";
                for(i=0;i<result.data.products.length;i++){
                    var tr="";
                    tr+="<div class='goods'><img src='"+result.data.products[i].image+"' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goods_details?productId="+result.data.products[i].productId+"'>"+result.data.products[i].title+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+result.data.products[i].price+"</span>";
                    tr+="<p>" +result.data.products[i].message+ "</p></div>";
                    body+=tr;
                    if(result.data.products.length<3 && i==result.data.products.length-1 || i==2){
                        $("#f1Content").html(body);
                        $(".second_content_left").css("height","430");
                        tr="";
                        body="";
                    }
                    else if(result.data.products.length<6 && i==result.data.products.length-1 || i==5){
                        $(".second_content_left").css("height","820");
                        $("#f2Content").css("display","block");
                        $("#f2Content").html(body);
                        tr="";
                        body="";
                    }
                    else if(result.data.products.length<9 && i==result.data.products.length-1 || i==8){
                        $(".second_content_left").css("height","1110");
                        $("#f3Content").css("display","block");
                        $("#f3Content").html(body);
                        tr="";
                        body="";
                    }
                    else if(result.data.products.length<12 && i==result.data.products.length-1 || i==11){
                        $(".second_content_left").css("height","1400");
                        $("#f4Content").css("display","block");
                        $("#f4Content").html(body);
                        tr="";
                        body="";
                    }
                    else if(result.data.products.length<15 && i==result.data.products.length-1 || i==14){
                        $(".second_content_left").css("height","1690");
                        $("#f5Content").css("display","block");
                        $("#f5Content").html(body);
                    }
                }
                console.log(body);

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
                            showAllByPage(currentPage-1,15);
                        }
                    }else if(id == "nextPage1"){
                        if(currentPage == totalPage){
                            alert("已经是最后一页了");
                        }else{
                            showAllByPage(currentPage+1,15);
                        }
                    }else if(id == "numberPage1"){
                        showAllByPage($(this).text(),15);
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
})