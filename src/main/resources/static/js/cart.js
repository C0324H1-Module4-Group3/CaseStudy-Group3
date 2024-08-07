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
            elementBilll()
        }
    })


});

$('.btn-plus').click(function () {
    let cartID = $(this).data('id');

    let val = $(this).closest(".list-inline").find("#var-value").html();
    let quantityCart = Number(val)

    let quantitySku = $(this).closest(".list-inline").find("#var-value").data('quantity');

    if (quantitySku >= quantityCart + 1) {
        quantityCart ++;
        $(this).closest(".list-inline").find("#var-value").html(quantityCart);
        $.ajax({
            type: "post",
            url: "http://localhost:8080/api/carts/addQuantity/" + cartID,
            success: function () {
                moneyTotal()
                elementBilll()
            }
        })
    }
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

function elementBilll() {
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/carts/cart/1",
        success: function (data) {
            console.log(data)
            let html = ``;
            $.each(data, function (index, el) {
                html += (`
            <div class="d-flex justify-content-between ">
                    <p>${el.name}</p>
                    <p>:</p>
                    <div class="justify-content-end d-flex">
                    <p  class="zz text-end me-1">${el.price*el.quantity}</p> ($)
                    </div>
                </div>
                `)
            })
            $(".elementBill").html(html);
        }
    })

}






