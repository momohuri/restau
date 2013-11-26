define(['namespace', './base-view', '../../shared/collections/sections' , '../../shared/collections/items' , '../../shared/models/order'
], function (App, BaseView, Sections, Items, Order, undefined) {

    App.client.views.menu = BaseView.extend({

        el: 'body',

        template: App.tmpl.client.menu,

        sections: new Sections(),

        order: new Order({items: new Items()}),

        sentOrder: new Order({items: new Items()}),

        events: {
            'change .orderItem': 'addToOrder',
            'click #sendOrder': 'sendOrder',
            'touchstart .sectionTitle' : 'showSection',


            //computer
            'click .sectionTitle' : 'showSection'
        },

        initialize: function () {

            var that = this;

            _.bindAll(this, 'render');

            this.order.get('items').bind('all', this.render, this);

            this.sections.fetch({
                success: function () {
                    that.render();
                }
            });


        },

        render: function (e) {
            this.$el.html(this.template({ sections: this.sections, order: this.order, sentOrder: this.sentOrder}));
            $('.dishes').hide();
        },

        addToOrder: function (e) {
            debugger
            var target = e.currentTarget;
            var item = this.sections.get(target.dataset.sectionid).get('items').get(target.dataset.itemid);
            if (target.value != 0) {
                item.set('quantity', target.value);
                this.order.get('items').add(item);
                this.order.setPrice();
            } else if (target.value == 0) {
                this.order.get('items').remove(target.dataset.itemid)
            } else {
                debugger
            }
        },

        sendOrder: function () {
            var that = this;
            this.toggleReminder('', function () {

                that.sentOrder.get('items').add(that.order.get('items').toJSON());
                //this ugly find another way of doing it :
                // we need to reset quantity of all items from collections to go to 0 again
                that.sections.forEach(function (item) {
                    item.get('items').put("quantity", 0);
                });
                that.order.get('items').reset();
                that.toggle = 1;// we render so the reminder will have the default place
            });
        },

        showSection: function(e){
            $(e.currentTarget.nextElementSibling).toggle(900);
            $('.dishes').not(e.currentTarget.nextElementSibling).hide(900)
        }

    });
    return  App.client.views.menu;
});