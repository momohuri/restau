define(['namespace', './base-view', '../../shared/collections/orders'
], function (App, BaseView, Orders, undefined) {

    App.cockpit.views.orders = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.orders,


        events: {
            'click .navigation': 'navigation',
            'click .editOrder': 'editOrder'
        },

        orders: new Orders(),

        initialize: function (param) {
            var that = this;
            _.bindAll(this, 'render');


            this.orders.fetch({
                data: {
                    q: param.action
                },
                success: function () {
                    //that.render();
                }
            });

            this.render();

        },

        render: function (e) {
            this.$el.html(this.template({orders: this.orders}));
            this.activeNav('.menuBasedOnName');
        },

        navigation: function (e) {
            Backbone.history.navigate('cockpit/orders/' + $(e.currentTarget)[0].id, true);
        },

        editOrder: function (e) {
            var id = e.currentTarget.dataset.id;
            Backbone.history.navigate('cockpit/orderEdit/'+id,{trigger:true})
        }

    });
    return  App.cockpit.views.orders;
});