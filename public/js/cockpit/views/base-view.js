define(['namespace'], function (App, undefined) {
    App.cockpit.views.BaseView = Backbone.View.extend({

        close: function () {
            this.remove();
            this.unbind();
        }

    });
    return App.cockpit.views.BaseView;
});