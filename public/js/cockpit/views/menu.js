define(['namespace', './base-view', '../collections/sections'
], function (App, BaseView, Sections, undefined) {

    App.cockpit.views.menu = BaseView.extend({

        el: 'body',

        template: App.tmpl.cockpit.menu,

        sections: new Sections([
            {name: 'lol', id: 2}
        ]),

        events: {
            'click .toSection': 'toSection'
        },

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

//            this.sections.fetch({
//                success: function (e) {
            that.render();
//                }
//            });



        },

        render: function () {
            this.$el.html(this.template({sections: this.sections}));

        },

        toSection: function (e) {
            Backbone.history.navigate('section/' + e.currentTarget.dataset.id, true);
        }




    });
    return  App.cockpit.views.menu;
});