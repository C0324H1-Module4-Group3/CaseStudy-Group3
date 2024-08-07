$('.btn-minus').click(function () {
    var cartID = $(this).data('id');
    var val = $(this).closest(".list-inline").find("#var-value").html();

    val = (val === '1') ? val : val - 1;
    $(this).closest(".list-inline").find("#var-value").html(val);
    $.ajax({
        type: "post",
        url: "http://localhost:8080/api/carts/minusQuantity/" + cartID,
        success: function () {
            moneyTotal()
            elementBill()
        }
    })

    alert("nhu cc")
});

$('.btn-plus').click(function () {
    var cartID = $(this).data('id');
    var val = $(this).closest(".list-inline").find("#var-value").html();

    // var val = $("#var-value").html();
    val++;
    $(this).closest(".list-inline").find("#var-value").html(val);
    // $("#var-value").html(val);
    $.ajax({
        type: "post",
        url: "http://localhost:8080/api/carts/addQuantity/" + cartID,
        success: function () {
            moneyTotal()
            elementBill()
        }

    })
    alert("nhu lol")
});

function moneyTotal() {
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/carts/moneyTotal/1",
        success: function (data) {
            let html = (`
                    ${data}
            `)
            $(".moneyTotal").html(html);
        }
    })
}


$('.btn-size').click(function () {
    var size = $(this).html();
    alert(size);
    var skuId = $(this).closest(".skuId").data('id');
    alert(skuId);

    $(".btn-size").removeClass('btn-secondary').addClass('btn-success');
    $(this).removeClass('btn-success').addClass('btn-secondary')
    $.ajax({
        type: "put",
        url: "http://localhost:8080/api/carts/update/" + skuId + "?size=" + size,
        success: function () {

        }

    })
});
function elementBill(){
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/carts/1",
        success: function (data) {
            console.log(data)
            let html = ``;
            $.each(data.content,function (index,el){
                console.log(el)
        html+= (`
            <div class="d-flex justify-content-between elementBill">
                    <p>a6s5d65as4d65</p>
                    <p>:</p>
                    <div class="justify-content-end d-flex">
                    <p  class="zz text-end me-1">3a2sd321a3s21</p> ($)
                    </div>
                </div>
`)
            })
            $(".elementBill").html(html);
        }
    })

}






