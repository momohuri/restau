define(['namespace', './base-view', '../../shared/collections/sections' , '../../shared/collections/items' , '../../shared/models/order'
], function (App, BaseView, Sections, Items, Order, undefined) {
    App.client.views.menu = BaseView.extend({

        el: 'body',

        template: App.tmpl.client.menu,

        sections: new Sections(),

        order: new Order({items: new Items()}),

        sentOrder: new Order({items: new Items()}),

        events: {
            'change .quantityDropdown': 'addToOrder',
            'click .sendOrder': 'sendOrder',
            'touchstart .sectionTitle': 'showSection',
            'click .toSection': 'toSection',


            //computer
            'click .sectionTitle': 'showSection'
        },

        initialize: function () {

            var that = this;
            _.bindAll(this, 'render');
            this.sections.fetch({
                data: {items: true },
                success: function () {
                    that.sections.forEach(function (section, i) {
                        section.set('items', new Items(section.get('items')));
                    });
                    that.render();
                }
            });
        },

        render: function (e) {
            this.$el.html(this.template({ sections: this.sections, order: this.order, sentOrder: this.sentOrder}));
            $('.dishes').hide();
        },

        addToOrder: function (e) {
            var target = e.currentTarget;
            var item = this.sections.get(target.dataset.sectionid).get('items').get(target.dataset.itemid);
            if (target.value != 0) {
                item.set('quantity', target.value);
                this.order.get('items').add(item);
            } else if (target.value == 0) {
                this.order.get('items').remove(target.dataset.itemid)
            }
            this.order.setPrice();
            $('#price').text(this.order.get('price'));
        },

        sendOrder: function () {
            order = this.order;
            Backbone.route.navigate('client/cart', true)
        },

        toSection: function (e) {
            var el = $('#' + e.currentTarget.dataset.name);
            el.trigger("touchstart");
            $('html,body').animate({scrollTop: el.offset().top}, 'slow');

        },

        showSection: function (e) {
            $(e.currentTarget.nextElementSibling).toggle(900);
            $('.dishes').not(e.currentTarget.nextElementSibling).hide(900)
        }

    });
    return  App.client.views.menu;
});