let defaultOption = '<option value="0">----- 请选择 -----</option>';

$(document).ready(function() {
    showProvinceList();

    $("#province-list").append(defaultOption);
    $("#city-list").append(defaultOption);
    $("#area-list").append(defaultOption);

    $("#province-list").change(function() {
        showCityList();

        $("#area-list").empty();
        $("#area-list").append(defaultOption);
    });

    $("#city-list").change(function() {
        showAreaList();
    });
});

function showProvinceList() {
    $.ajax({
        "url":"/districts/",
        "data":"parent=100000",
        "type":"get",
        "dataType":"json",
        "success":function(json) {
            let list = json.data;
            console.log("count=" + list.length);
            for (let i = 0; i < list.length; i++) {
                console.log("name=" + list[i].name);
                let op = '<option value="'
                    + list[i].code + '">'
                    + list[i].name + '</option>';
                $("#province-list").append(op);
            }
        }
    });
}

function showCityList() {
    let parentCode = $("#province-list").val();
    $("#city-list").empty();
    $("#city-list").append(defaultOption);

    if (parentCode == 0) {
        return;
    }

    $.ajax({
        "url":"/districts/",
        "data":"parent=" + parentCode,
        "type":"get",
        "dataType":"json",
        "success":function(json) {
            let list = json.data;
            console.log("count=" + list.length);
            for (let i = 0; i < list.length; i++) {
                console.log("name=" + list[i].name);
                let op = '<option value="'
                    + list[i].code + '">'
                    + list[i].name + '</option>';
                $("#city-list").append(op);
            }
        }
    });
}

function showAreaList() {
    let parentCode = $("#city-list").val();
    $("#area-list").empty();
    $("#area-list").append(defaultOption);

    if (parentCode == 0) {
        return;
    }

    $.ajax({
        "url":"/districts/",
        "data":"parent=" + parentCode,
        "type":"get",
        "dataType":"json",
        "success":function(json) {
            let list = json.data;
            console.log("count=" + list.length);
            for (let i = 0; i < list.length; i++) {
                console.log("name=" + list[i].name);
                let op = '<option value="'
                    + list[i].code + '">'
                    + list[i].name + '</option>';
                $("#area-list").append(op);
            }
        }
    });
}

$("#btn-addnew").click(function() {
    $.ajax({
        "url":"/addresses/addnew",
        "data":$("#form-addnew").serialize(),
        "type":"post",
        "dataType":"json",
        "success":function(json) {
            if (json.state == 2000) {
                alert("地址添加成功！");
                location.href = "address";
            } else {
                alert("增加失败！" + json.message + "！");
            }
        },
        "error":function() {
            alert("您的登录信息已经过期，请重新登录！\r\r请打开浏览器的Console或Network观察HTTP响应码，以判断错误的原因！");
            location.href = "login";
        }
    });
});