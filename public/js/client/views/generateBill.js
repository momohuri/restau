define(['namespace', './base-view', '../../shared/collections/orders'
], function (App, BaseView, Orders, undefined) {

    App.client.views.generateBill = BaseView.extend({

        el: '#main',

        template: App.tmpl.client.generateBill,


        events: {
            'click .goToMenu': 'goToMenu',
            'click .generateBill': 'generateBill'
        },

        isAllBillGenerated: false,

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            Orders.isAllBillGenerated(function (bool) {
                that.isAllBillGenerated = bool == "true";
                that.render();
            });
        },

        render: function (e) {
            this.$el.html(this.template({isAllBillGenerated: this.isAllBillGenerated}));
        },

        goToMenu: function () {
            Backbone.history.navigate('client/menu', {trigger: true});
        },

        generateBill: function () {
            var that = this;
            //todo change to put and add to model instead of direct ajax
            $.get('client/generateBill.do', function () {
                that.isAllBillGenerated = true;
                that.render()
            })
        }





    });
    return  App.client.views.generateBill;
});