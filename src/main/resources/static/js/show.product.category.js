let items = document.querySelectorAll('.carousel .carousel-item')

items.forEach((el) => {
    const minPerSlide = 4
    let next = el.nextElementSibling
    for (var i=1; i<minPerSlide; i++) {
        if (!next) {

            next = items[0]
        }
        let cloneChild = next.cloneNode(true)
        el.appendChild(cloneChild.children[0])
        next = next.nextElementSibling
    }
})

$(document).ready(function() {

    $('.go-carousel').on('click', function(e) {
        e.preventDefault();


        $('html, body').animate({
            scrollTop: $("#featured-products").offset().top
        }, 100);


    });
});
$(document).ready(function () {

    function loadCarouselData(categoryId) {
        $.ajax({
            url: 'http://localhost:8080/api/products?categoryId=' + categoryId,
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                var carousel = $('#carouselCategory.carousel-inner');
                carousel.empty();

                console.log(response);

                if (response && response.length > 0) {
                    response.forEach(function (product, index) {
                        if (product && product.category) {
                            var categoryName = product.category.name || 'Default Category Name';
                            $('#featured-products h1').text(categoryName);
                        } else {
                            console.error("Không tìm thấy danh mục trong dữ liệu trả về.");
                            $('#featured-products h1').text('Default Category Name');
                        }

                        var isActive = index === 0 ? 'active' : '';
                        var carouselItem = `
                            <div class="carousel-item ${isActive}">
                                <div class="col-3">
                                    <div class="card mb-4 product-wap rounded-0">
                                        <div class="card rounded-0">
                                            <img class="card-img rounded-0 img-fluid" src="${product.imagePath}" alt="${product.name}">
                                            <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                                                <ul class="list-unstyled">
                                                    <li><a class="btn btn-success text-white mt-2" data-href="/home/shop-single/${product.id}"><i class="far fa-eye"></i></a></li>
                                                 
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <a href="shop-single.html" class="h3 text-decoration-none">${product.name}</a>
                                            <ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
                                                <li class="pt-2">
                                                    <span class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
                                                    <span class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
                                                    <span class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
                                                    <span class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
                                                    <span class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
                                                </li>
                                            </ul>
                                            <ul class="list-unstyled d-flex justify-content-center mb-1">
                                                <li>
                                                    <i class="text-warning fa fa-star"></i>
                                                    <i class="text-warning fa fa-star"></i>
                                                    <i class="text-warning fa fa-star"></i>
                                                    <i class="text-warning fa fa-star"></i>
                                                    <i class="text-warning fa fa-star"></i>
                                                </li>
                                            </ul>
                                            <p class="text-center mb-0"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                        carousel.append(carouselItem);
                    });


                    $('#carouselCategory').carousel('dispose').carousel({
                        interval: 2000
                    });
                    $('.carousel-item a').each(function() {
                        var href = $(this).data('href');
                        $(this).attr('href', href);
                    });

                    let items = document.querySelectorAll('.carousel .carousel-item');
                    items.forEach((el) => {
                        const minPerSlide = response.length;
                        let next = el.nextElementSibling;
                        for (var i = 1; i < minPerSlide; i++) {
                            if (!next) {
                                next = items[0];
                            }
                            let cloneChild = next.cloneNode(true);
                            el.appendChild(cloneChild.children[0]);
                            next = next.nextElementSibling;
                        }
                    });
                }
            },
            error: function (xhr, status, error) {
                console.error("AJAX error:", status, error);
            }
        });
    }

    loadCarouselData(1);

    $('.go-carousel').on('click', function (event) {
        event.preventDefault();
        var categoryId = $(this).data('category-id') || 1;
        loadCarouselData(categoryId);
    });
});
