define(['namespace'], function (App, undefined) {
    App.cockpit.views.BaseView = Backbone.View.extend({

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
        },


        //the function will put active the menu selected.
        // to use give add class menuBasedOnName or menuBasedOnId to the div with the menu
        // and then add a data-reference with the id or name to each li of the menu
        // the function will try to find the name/id in the url
        activeNav : function (idOrName) {
            if ($(idOrName).length !== 0) {
                _.each($(idOrName + ' .nav li'), function (item) {
                    if (Backbone.history.getHash().split('/').indexOf(item.dataset.reference) !== -1) {
                        item.className = item.className + ' active';
                    }
                });
            }
        }

    });
    return App.cockpit.views.BaseView;
});