define(['namespace'],function (App, undefined) {

    App.cockpit.collections.BaseCollection = Backbone.Collection.extend({
        put: function(prop, value) {
            this.forEach(function(model) {
                model.set(prop, value);
            });
        }
    });
    return App.cockpit.collections.BaseCollection;
});