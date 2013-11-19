define(['namespace', './base-view'
], function (App, BaseView, undefined) {

    App.cockpit.views.navigation = BaseView.extend({

        el: 'body',

        template: App.tmpl.cockpit.navigation,


        events: {
            'click .toSection': 'toSection',
            'click .manageSections': 'manageSections',
            'click #currentOrders':'toOrders'

        },

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            that.render();

        },

        render: function () {
            this.$el.html(this.template());
        },

        toSection: function (e) {
            Backbone.history.navigate('cockpit/section/' + e.currentTarget.dataset.id, true);
        },

        manageSections: function () {
            Backbone.history.navigate('cockpit/section/manageSections', true);
        } ,
        toOrders:function(){
            Backbone.history.navigate('cockpit/orders/currentOrders',true);
        }

    });
    return  App.cockpit.views.navigation;
});