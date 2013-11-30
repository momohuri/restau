define(['namespace', './base-view', '../../shared/collections/sections' , '../../shared/collections/items' , '../../shared/models/order'
], function (App, BaseView, Sections, Items, Order,undefined) {

    App.client.views.menu = BaseView.extend({

        el: '#main',

        template: App.tmpl.client.menu,

        sections: new Sections(),

        order: new Order({orderItems: new Items()}),


        events: {
            'change .quantityDropdown': 'addToOrder',

            //smartphone
            'touchstart .sendOrder': 'goToCheckOrder',
            'touchstart .toSection': 'toSection',
            'touchstart .sectionTitle': 'showSection',

            //computer
            'click .sendOrder': 'goToCheckOrder',
            'click .toSection': 'toSection',
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
                    if (typeof order !== 'undefined') {
                        that.order = order;
                    } else{
                        order= that.order;
                    }
                    that.render();
                }
            });
        },

        render: function () {
            this.$el.html(this.template({ sections: this.sections, order: this.order, sentOrder: this.sentOrder}));
            $('.dishes').hide();
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