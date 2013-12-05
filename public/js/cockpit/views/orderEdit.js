define(['namespace', './base-view', '../../shared/models/order', '../../shared/collections/items'
], function (App, BaseView, Order, Items, undefined) {

    App.cockpit.views.orderEdit = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.orderEdit,


        events: {
            'change .quantity': 'changeQuantity',
            'focusout #discount': 'discountChange',
            'click .save': 'saveOrder'
        },
        //todo type=cockpit
        order: new Order({type: 'cockpit'}),

        initialize: function (param) {
            var that = this;
            _.bindAll(this, 'render');
            this.order.id = param.orderId;
            this.order.fetch({
                success: function () {
                    if (that.order.get('discount') == undefined) that.order.set('discount', 0)
                    that.order.set('orderItems', new Items(that.order.get('orderItems')))
                    that.render()
                }
            });
        },

        render: function () {
            this.$el.html(this.template({order: this.order}));
        },

        changeQuantity: function (e) {
            var target = e.currentTarget;
            var item;
            //or in checkOrder
            item = this.order.get('orderItems').get(target.dataset.itemid).get('item');
            if (target.value != 0) {
                if (this.order.get('orderItems').get(target.dataset.itemid) !== undefined) {  //already existing
                    this.order.get('orderItems').get(target.dataset.itemid).set('quantity', target.value);
                } else {
                    var toInsert = new Item({id: item.id, quantity: target.value, item: item, name: item.get('name'), pricePerUnit: item.get('price'), sectionId: item.get('sectionId')});
                }
                this.order.get('orderItems').add(toInsert);
            } else if (target.value == 0) {
                this.order.get('orderItems').remove(target.dataset.itemid);
            }
            this.order.setPrice();
            $('.price').text(this.order.get('price') - this.order.get('discount'));
        },

        discountChange: function (e) {
            this.order.set('discount', e.currentTarget.value)
            $('.price').text(this.order.get('price') - this.order.get('discount'))
        },

        saveOrder:function(){
            this.order.save()
        }

    });
    return  App.cockpit.views.orderEdit;
});