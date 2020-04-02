$(document).ready(function() {
    showAddressList();
});

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
                let html = '<tr>'
                    + '<td>#{tag}</td>'
                    + '<td>#{name}</td>'
                    + '<td>#{provinceName}#{cityName}#{areaName}#{address}</td>'
                    + '<td>#{phone}</td>'
                    + '<td><a class="btn btn-xs btn-info"><span class="fa fa-edit"></span> 修改</a></td>'
                    + '<td><a class="btn btn-xs add-del btn-info" onclick="deleteByAid(#{addressId})"><span class="fa fa-trash-o"></span> 删除</a></td>'
                    + '<td><a class="btn btn-xs add-def btn-default" onclick="setDefault(#{addressId})">设为默认</a></td>'
                    + '</tr>';

                html = html.replace(/#{addressId}/g, list[i].addressId);

                html = html.replace("#{tag}", list[i].tag);
                html = html.replace("#{name}", list[i].name);
                html = html.replace("#{provinceName}", list[i].provinceName);
                html = html.replace("#{cityName}", list[i].cityName);
                html = html.replace("#{areaName}", list[i].areaName);
                html = html.replace("#{address}", list[i].address);
                html = html.replace("#{phone}", list[i].phone);

                $("#address-list").append(html);
            }
            $(".add-def:eq(0)").hide();
        }
    });
}

function setDefault(addressId) {
    $.ajax({
        "url":"/addresses/" + addressId + "/set_default",
        "type":"post",
        "dataType":"json",
        "success":function(json) {
            if (json.state == 2000) {
                showAddressList();
            } else {
                alert("设置默认收货地址失败！" + json.message + "！");
            }
        },
        "error":function() {
            alert("您的登录信息已过期，请重新登录！");
        }
    });
}

function deleteByAid(aid) {
    $.ajax({
        "url":"/addresses/" + aid + "/delete",
        "type":"post",
        "dataType":"json",
        "success":function(json) {
            if (json.state == 2000) {
                showAddressList();
            } else {
                alert("删除收货地址失败！" + json.message + "！");
            }
        },
        "error":function() {
            alert("您的登录信息已过期，请重新登录！");
        }
    });
}