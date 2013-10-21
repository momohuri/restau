define(['namespace', './base-view', '../../shared/collections/sections' , '../../shared/collections/items' , '../../shared/models/order'
], function (App, BaseView, Sections, Items, Order, undefined) {

    App.client.views.menu = BaseView.extend({

        el: 'body',

        template: App.tmpl.client.menu,

        sections: new Sections([
            {
                id: 0, name: 'sections1', items: new Items([
                {id: 0, name: 'lol', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 1, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 2, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 3, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 4, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 5, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 6, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 7, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 8, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 9, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 10, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 11, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 12, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 13, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 14, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 15, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 16, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 17, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 18, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20}
            ])  } ,
            {
                id: 1, name: 'section2', items: new Items([
                {id: 0, name: 'lol', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 1, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 2, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 3, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 4, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 5, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 6, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 7, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 8, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 9, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 10, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 11, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 12, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 13, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 14, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 15, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 16, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 17, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20},
                {id: 18, name: 'hello', spicy: '2', vegan: 'no', description: 'coucou', price: 20}
            ]) }
        ]),

        order: new Order({items: new Items()}),

        sentOrder: new Order({items: new Items()}),


        events: {
            'click .toSection': 'toSection',
            'change .orderItem': 'addToOrder',
            'click .animate': 'toggleReminder',
            'click #sendOrder': 'sendOrder'

        },

        initialize: function () {

            var that = this;

            _.bindAll(this, 'render');

            this.order.get('items').bind('all', this.render, this);

            this.render();

        },

        render: function (e) {
            this.$el.html(this.template({ sections: this.sections, order: this.order, sentOrder: this.sentOrder}));
        },

        toSection: function (e) {
            $(document.body).animate({
                'scrollTop': $('#' + e.currentTarget.dataset.id).offset().top
            }, 1000);
        },


        addToOrder: function (e) {
            var target = e.currentTarget;
            var item = this.sections.get(target.dataset.sectionid).get('items').get(target.dataset.itemid);
            if (target.value != 0) {
                item.set('quantity', target.value);
                this.order.get('items').add(item);
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

        toggle: 1,
        toggleReminder: function (e, next) {
            var that = this;
            var callback = true;//the callback happen twice, we just need it once
            if (this.toggle === 1) {
                var size = 600,
                    html = '>';
            } else {
                var size = -600,
                    html = '<';
            }

            $("#reminder,#animate").animate({
                right: "+=" + size
            }, 800, function (e) {
                if (callback) {
                    that.toggle *= -1;
                    callback = false;
                    $('#animate').html(html);
                    if (next !== undefined) {
                        next()
                    }
                    ;
                }
            });
        }




    });
    return  App.client.views.menu;
});