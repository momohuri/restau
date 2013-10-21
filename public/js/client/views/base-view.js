define(['namespace'], function (App, undefined) {
    App.client.views.BaseView = Backbone.View.extend({

        close: function () {
            this.remove();
            this.unbind();
        },

        getFormData:function(e){
            var data = $(e.currentTarget).serializeArray();
            var result = _(data).reduce(function (acc, field) {
                acc[field.name] = field.value;
                return acc;
            }, {});
            return result
        }

    });
    return App.client.views.BaseView;
});