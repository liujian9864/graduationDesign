$(function() {
    showAllByPage(1,15)
    function showAllByPage(currentPage,currentRows) {
        var tabId='book';
        $.ajax({
            type: "post",
            url: "/product/findByPage",
            data:tabId,
            contentType: "application/json; charset=utf-8",
            success:function (result){
                var totalPage = result.data.totalPage;
                var currentPage = result.data.currentPage;
                var body="";
                for(i=0;i<result.data.length;i++){
                    var tr="";
                    if(i!=0 && i%3==0){
                        tr+="</div><div style='height: 10px'></div><div class='third_content'>";
                    }
                    tr+="<div class='goods'><img src='imgs/book/"+1+".jpg' ></div>";
                    tr+="<div class='describe'><img src='imgs/product/箭头.png' ><a href='goos_details.html'>"+特别的猫+"</a>";
                    tr+="<img src='imgs/product/金钱2.png' class='rmb'><span>"+25+"</span>";
                    tr+="<p>" +"2007年诺贝尔文学奖得主多丽丝·莱辛一部非虚构类代表作《特别的猫》中文版, 多丽丝·莱辛是个爱猫成痴的作家，她在《特别的猫》里讲述了人与猫之间的动人故事, 细数曾经让她欢欣也让她忧愁的猫。在她笔下，猫的世界精彩纷呈。故事从莱辛在非洲的童年开始。娇美的公主灰咪咪和低调的黑猫咪因为争宠上演了一出出情景剧；同为猫妈妈，育儿之道却大相径庭，令观者莞尔；"+ "</p></div>";
                    body+=tr;
                }
                $("#f1Content").html(body);

                if (result.data.length>=3){
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
})