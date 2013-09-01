$(document).ready(function () {


    var item = $("#Items tbody").children('tr').size() - 2;
    var item2 = item + 1;
    $("#addItem").click(function () {


        $("#item" + item).after(
            '<tr id="item' + item2 + '">                                                                                            \
                    <td>                                                                                             \
                    <input type="text" name="name" placeholder="Name" maxlength="25" minlength="4" required></td>   \
                    <td>                                                                                             \
                    <input type="text" name="description" placeholder="Description"></td>                               \
                   <td>                                                                                              \
                    <input type="text" name="calories" placeholder="Calories"></td>                              \
                    <td>                                                                                             \
                    <input type="number" name="price" placeholder="0" required></td>                                \
                    <td>                                                                                             \
                    <select name="spicy"> <option value="0">0</option><option value="1">1</option><option value="2">2</option> <option value="3">3</option></select>                   \
                    <td>                                                                                             \
                    <select name="vegetarian"> <option value="yes">yes</option><option value="no" selected>no</option></select>                             \
                    <td>                                                                                             \
                    <select name="enable"> <option value="yes">yes</option><option value="no">no</option></select>                               \
                    </tr>                                                                                            \
                    ');
        item++;
        item2++;
    });

    $("#deleteItem").click(function () {
        if (item != 0) {
            $('#item' + item).remove();
            item--;
            item2--;
        }
    });


    $('form').on('submit', function (e) {
        e.preventDefault();

        var form = $(this).serializeArray();
        var reduce = function (arr) {
            var x = 0;
            var y = -1;
            return _(arr).reduce(function (acc, field) {
                if (typeof acc.items === 'undefined') acc.items = [];
                if ((x - 1) % 7 === 0) {
                    y++;
                    acc.items[y] = {};
                }

                if (x === 0) {
                    acc[field.name] = field.value;
                    x++;
                }
                else {
                    acc.items[y][field.name] = field.value;
                    x++;
                }
                return acc;
            }, {});
        }

        form = reduce(form);

        $.ajax({
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            url: 'section',
            data: JSON.stringify(form),
            success: function (id) {
            },
            error: function (e) {
                console.log('error : ', e);
            }
        });

    });


});
;
