$(window).bind("load", function () {

    $(".numeric").numeric();

    $(document).on("click", ".button-delete", function() {
        let href = $(this).data("href");
        $("#link-delete").attr("href", href);

        $("#deleteModal").modal({
            show: true,
            keyboard: false
        });
    });

    $('form').on("submit", function() {
        $("button[type=submit]", $(this)).attr("disabled", "disabled");
    });

    // $(document).on("change", ".cikkID", function() {
    //     let id = $(this).val();
    //     let row = $(this).closest('.movement-item');
    //     let price = $("[name='ar[]']", row);
    //     let mul = parseFloat($("[name='ar[]']", row).attr("data-mul"));
    //
    //     if (id === "") {
    //         price.val("");
    //     }
    //     else {
    //         $.ajax({
    //             url: '/cikkek/get/' + id,
    //             type: 'GET',
    //             data: {},
    //             dataType: 'json',
    //             beforeSend: function () {
    //             },
    //             success: function (data) {
    //                 price.val(data.product.ar * mul);
    //             }
    //         });
    //     }
    // });
    //
    // $(document).on("click", "#newitem", function() {
    //     let lastItem = $("select[name='cikkID[]']:last");
    //     let type = $(this).data("type");
    //     let inventoryID = parseInt($("#inventory").val());
    //
    //     if (lastItem.val() !== "") {
    //         $.ajax({
    //             url: '/' + type + '/item',
    //             type: 'POST',
    //             headers: {
    //                 'x-csrf-token': $('meta[name="csrf-token"]').attr('content')
    //             },
    //             data: {
    //                 inventory_id: inventoryID
    //             },
    //             dataType: 'text',
    //             beforeSend: function () {
    //             },
    //             success: function (data) {
    //                 $("#newitems").append(data);
    //             }
    //         });
    //     }
    //     else {
    //         lastItem.focus();
    //     }
    // });

    $(document).on("click", ".button-delete-item", function() {
        $(this).closest('.movement-item').remove();
    });
    //
    // $(document).on("change", "#inventory", function() {
    //     let id = $(this).val();
    //
    //     if (id !== "") {
    //         $.ajax({
    //             url: '/cikkek/list/' + id,
    //             type: 'GET',
    //             data: {},
    //             dataType: 'text',
    //             beforeSend: function () {
    //             },
    //             success: function (data) {
    //                 $(".product-holder").html(data);
    //             }
    //         });
    //     }
    // });

});
