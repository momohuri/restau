define(['namespace', './base-view', '../../shared/models/order'
], function (App, BaseView, Order, undefined) {

    App.cockpit.views.orderEdit = BaseView.extend({

        el: 'body',

        template: App.tmpl.cockpit.orderEdit,


        events: {

        },

        order: new Order({type: 'cockpit'}),

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            this.order.fetch({
                success: that.render()
            });
        },

        render: function (e) {
            this.$el.html(this.template());
        }

    });
    return  App.cockpit.views.orderEdit;
});