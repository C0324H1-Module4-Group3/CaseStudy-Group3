//closet,find kiếm được đối tượng muốn tăng giảm
$('.btn-minus').click(function () {
    var cartID = $(this).data('id');
    var val = $(this).closest(".list-inline").find("#var-value").html();
    val = (val === '1') ? val : val - 1;
    $(this).closest(".list-inline").find("#var-value").html(val);
    $.ajax({
        type: "post",
        url: "http://localhost:8080/api/carts/minusQuantity/" + cartID,
        success: function () {

        }

    })
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

        }

    })
});





