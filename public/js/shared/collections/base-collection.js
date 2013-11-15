define(['namespace'], function (App, undefined) {

    App.cockpit.collections.BaseCollection = Backbone.Collection.extend({
        put: function (prop, value) {
            this.forEach(function (model) {
                model.set(prop, value);
            });
        },


        sendRankDisplay: function (order, next) {
            $.ajax({
                type: "PUT",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                url: this.url(),//it s function because ... (go see items collection)
                data: JSON.stringify(order),
                success: function () {

                },
                error: function (e) {
                    console.log('error : ', e);
                }
            });
        }
    });
    return App.cockpit.collections.BaseCollection;
});