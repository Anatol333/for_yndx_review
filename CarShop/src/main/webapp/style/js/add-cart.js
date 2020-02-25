function add_to_cart(productId){
    $.ajax({
        url: "cart",
        type: 'POST',
        dataType: 'json',
        data: {
            id: productId,
            type: "add"
        },
        success : function(data) {
            var value = JSON.parse(JSON.stringify(data));
            $("#cart_numbers").text(value.header_val);
            $("#cart_price").text("$" + value.cart_price)
        }
    });
}

function minus_in_cart(productId){
    var className = ".cart-" + productId;
    var edit = $(className).parent().find('[type="text"]').get(0)
        if(edit.value > 0 && edit.value > 1) {
            $.ajax({
                url: "cart",
                type: 'POST',
                dataType: 'json',
                data: {
                    id: productId,
                    type: "minus"
                },
                success : function(data) {
                    var value = JSON.parse(JSON.stringify(data));

                    $(className).val(value.json_out);
                    $("#cart_numbers").text(value.header_val);
                    $("#cart_price").text("$" + value.cart_price)
                }
            });
    }
}

function plus_in_cart(productId){
    $.ajax({
        url: "cart",
        type: 'POST',
        dataType: 'json',
        data: {
                id: productId,
                type: "plus"
        },
        success : function(data) {
            var value = JSON.parse(JSON.stringify(data));

            var className = ".cart-" + productId;
            $(className).val(value.json_out);
            $("#cart_numbers").text(value.header_val);
            $("#cart_price").text("$" + value.cart_price)
        }
    });
}

function remove_in_cart(productId){
    $.ajax({
        url: "cart",
        type: 'POST',
        dataType: 'json',
        data: {
                id: productId,
                type: "remove"        
        },
        success : function(data) {
            var value = JSON.parse(JSON.stringify(data));
            $("#cart_numbers").text(value.header_val);
            $("#cart_price").text("$" + value.cart_price)
            window.location = "/cart";
        }
    });
}