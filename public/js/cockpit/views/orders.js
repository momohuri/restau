define(['namespace', './base-view','../../shared/collections/orders'
], function (App, BaseView, Orders, undefined) {

    App.cockpit.views.orders = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.orders,


        events: {

        },

        orders : new Orders(),

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            this.render();

        },

        render: function (e) {
            this.$el.html(this.template({orders:this.orders}));
        }



    });
    return  App.cockpit.views.orders;
});