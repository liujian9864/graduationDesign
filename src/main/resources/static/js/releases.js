$(document).ready(function() {
    showAllOrders();
});
function showAllOrders() {
    $.ajax({
        "url":"/products/findByUserId",
        "type":"get",
        "success":function(json) {
            if (json.state==2000){
                for(i=0;i<json.data.length;i++){
                    if(json.data[i].status=="1"){
                        json.data[i].status="上架中";
                    }else{
                        son.data[i].status="已售出"
                    }
                    let html = "<tr>"
                        + "<td><img src='"+json.data[i].image+"' class='img-responsive' style='height: 50px;width: 70px' /></td>"

                        + "<td><input type='hidden' id='productId' value='"+json.data[i].productId+"' />"+json.data[i].title+"</td>"
                        + "<td>"+json.data[i].productType+"</td>"
                        + "<td>¥<span>"+json.data[i].price+"</span></td>"
                        + "<td>"+json.data[i].status+"</td>"
                        + "<td>"+json.data[i].message+"</td>"
                        + "</tr>";
                    $("#releases").append(html);
                }
            }
        }
    });
}