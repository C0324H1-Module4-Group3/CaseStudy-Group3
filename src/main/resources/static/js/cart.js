$('.btn-minus').click(function () {
    let cartID = $(this).data('id');
    let val = $(this).closest(".list-inline").find("#var-value").html();

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
        quantityCart++;
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
        url: "http://localhost:8080/api/carts/moneyTotal",
        success: function (data) {
            let html = (`
                    ${data}
            `)
            $(".moneyTotal").html(html);
        }
    })
}


$('.btn-size').click(function () {
    let size = $(this).html();
    let cartId = $(this).closest(".skuId").data('id');
    let _$this = $(this);

    $.ajax({
        type: "put",
        url: "http://localhost:8080/api/carts/update/" + cartId + "?size=" + size,
        success: function (data) {
            if(data===0){
                showAlert("This size is out of stock ", "message")
            }else {
                _$this.closest(".skuId").find(".btn-size").removeClass('btn-secondary').addClass('btn-success');
                _$this.removeClass('btn-success').addClass('btn-secondary')
            }
        }

    })
});

function elementBilll() {
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/carts/cart",
        success: function (data) {
            console.log(data)
            let html = ``;
            $.each(data, function (index, el) {
                html += (`
            <div class="d-flex justify-content-between ">
                    <p>${el.name}</p>
                    <p>:</p>
                    <div class="justify-content-end d-flex">
                    <p  class=" text-end me-1">${el.price * el.quantity}</p> ($)
                    </div>
                </div>
                `)
            })
            $(".elementBill").html(html);
        }
    })

}



function confirmDelete(event, element) {
    event.preventDefault();
    let confirmation = confirm("Xóa hả chắc chưa")
    if (confirmation) {
        setTimeout(() => {
            window.location.href = element.getAttribute('href')
        }, 3000);
        showAlert("Xóa thành công", "message")

    }

}

const showAlert = (message, type, text) => {
    Swal.fire({
        icon: type,
        title: message,
        text: text
    });
}



