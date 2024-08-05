
//closet,find kiếm được đối tượng muốn tăng giảm
$('.btn-minus').click(function () {
    var val = $(this).closest(".list-inline").find("#var-value").html();
    val = (val == '1') ? val : val - 1;
    $(this).closest(".list-inline").find("#var-value").html(val);
    $("#product-quanity").val(val);
    return false;
});

$('.btn-plus').click(function () {
    var val = $("#var-value").html();
    val++;
    $("#var-value").html(val);
    $("#product-quanity").val(val);
    return false;
});




