define(['namespace', './base-view', '../../shared/models/order', '../../shared/collections/items'], function (App, BaseView, Order, Items, undefined) {

    App.client.views.checkOrder = BaseView.extend({

        el: '#main',

        template: App.tmpl.client.checkOrder,


        events: {
            'click .sendOrder': 'sendOrder',
            'click .goToMenu': 'goToMenu',
            'change .quantityDropdown': 'addToOrder'
        },

        order: new Order(),

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            if (typeof order === "undefined") {
                Backbone.history.navigate('client/menu', {trigger: true});
            } else {
                this.order = order;
                this.render();
            }
            this.render();
        },

        render: function () {
            this.$el.html(this.template({order: this.order}));
        },

        sendOrder: function () {
            this.order.save({}, {
                success: function (e) {
                    order = new Order({orderItems: new Items()});
                    Backbone.history.navigate("client/generateBill", {trigger: true});
                }
            })
        }

    });
    return  App.client.views.checkOrder;
});