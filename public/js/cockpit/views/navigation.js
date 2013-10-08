define(['namespace', './base-view', '../collections/menus'
], function (App, BaseView, Menus, undefined) {

    App.cockpit.views.navigation = BaseView.extend({

        el: 'body',

        template: App.tmpl.cockpit.navigation,

        menus: new Menus([
            {name: 'lol', id: 2}
        ]),

        events: {
            'click .toSection': 'toSection',
            'click .newSection': 'createNewSection'

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
            this.$el.html(this.template({menus: this.menus}));
        },

        toSection: function (e) {
            Backbone.history.navigate('section/' + e.currentTarget.dataset.id, true);
        },

        createNewSection: function () {
            Backbone.history.navigate('section/new', true);
        }

    });
    return  App.cockpit.views.navigation;
});