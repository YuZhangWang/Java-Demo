/**
 * Created by LENOVO on 2018/9/29.
 */


function addOrder() {
    var goodsTable = document.getElementById('goodsTable');
    var rows = goodsTable.children[1].rows;
    alert(rows[0].getElementsByTagName('td')[3].getElementsByTagName('span')[0].innerHTML);
    var ids = new Array();
    var nums = new Array();
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].getElementsByTagName('td')[0].innerHTML);
        nums.push(rows[i].getElementsByTagName('td')[3].getElementsByTagName('span')[0].innerHTML);
    }
    var pro_id = JSON.stringify(ids);
    var num = JSON.stringify(nums);
    var consigneeName = $("#consigneeName").val();
    var consigneePhone = $("#consigneePhone").val();
    var province = $("#province").find("option:selected").text();
    var consigneeAddress = province + $("#address").val();
    var userName = $("#userName").val();
    var userPhone = $("#userPhone").val();

    alert(ids.length + "," + nums.length);
    $.ajax({
        type: "GET",
        url: "http://localhost:8088/flower/order/insert.action",
        contentType: "application/json;charset=UTF-8",

        data: {
            "pro_id": ids, "num": nums, "consigneeName": consigneeName, "consigneePhone": consigneePhone,
            "consigneeAddress": consigneeAddress, "userName": userName, "userPhone": userPhone
        },
        dataType: "text",
        traditional: true,//这里设置为true
        success: function (data) {
            alert("data=" + data);
            if (data == "OK") {
                alert("ok");
                //window.location.href ="index.jsp";

            } else {
                alert("else");
            }


        },
        error: function (error) {
            alert("error" + error);
        }

    });


}

