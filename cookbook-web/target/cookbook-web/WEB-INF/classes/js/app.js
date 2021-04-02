$(window).bind("load", function () {

    $(".numeric").numeric();

    if ($(".editor").is(":visible")) {
        ClassicEditor.create(document.querySelector('.editor'), {
            toolbar: {
                items: [
                    'bold',
                    'italic',
                    'link',
                    'bulletedList',
                    'numberedList',
                    '|',
                    'indent',
                    'outdent',
                    '|',
                    'undo',
                    'redo'
                ]
            },
            language: 'hu',
            image: {
                toolbar: [
                    'imageTextAlternative',
                    'imageStyle:full',
                    'imageStyle:side'
                ]
            },
            table: {
                contentToolbar: [
                    'tableColumn',
                    'tableRow',
                    'mergeTableCells'
                ]
            },
            licenseKey: '',
        })
            .then(editor => {
                window.editor = editor;
            })
            .catch(error => {
            });
    }

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

    $(document).on("click", "#newItem", function() {
        let lastItem = $("select[name='materialId[]']:last");
        let type = $(this).data("url");

        if (lastItem.val() !== "") {
            $.ajax({
                url: type + '/item',
                type: 'GET',
                headers: {},
                data: {},
                dataType: 'text',
                beforeSend: function () {
                },
                success: function (data) {
                    $("#newItems").append(data);
                }
            });
        }
        else {
            lastItem.focus();
        }
    });

    $(document).on("click", ".button-delete-item", function() {
        $(this).closest('.row-item').remove();
    });

});
