define(['namespace', './base-view', '../../shared/collections/menus'
], function (App, BaseView, Menus, undefined) {

    App.cockpit.views.navigation = BaseView.extend({

        el: 'body',

        template: App.tmpl.cockpit.navigation,

        menus: new Menus([
            {name: 'lol', id: 2}
        ]),

        events: {
            'click .toSection': 'toSection',
            'click .newSection': 'createNewSection',
            'click #currentOrders':'toOrders'

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
            Backbone.history.navigate('cockpit/section/' + e.currentTarget.dataset.id, true);
        },

        createNewSection: function () {
            Backbone.history.navigate('cockpit/section/new', true);
        } ,
        toOrders:function(){
            Backbone.history.navigate('cockpit/orders',true);
        }

    });
    return  App.cockpit.views.navigation;
});