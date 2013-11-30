define(['namespace', './base-view', '../../shared/models/order'], function (App, BaseView, Order, undefined) {

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
            this.order.save({},{
                success: function (e) {
                    delete order;
                    Backbone.history.navigate("client/generateBill",{trigger:true});
                }
            })
        }



    });
    return  App.client.views.checkOrder;
});