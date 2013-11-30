define(['namespace', '../../shared/models/item'], function (App, Item, undefined) {
    App.client.views.BaseView = Backbone.View.extend({

        close: function () {
            this.remove();
            this.unbind();
        },

        getFormData: function (e) {
            var data = $(e.currentTarget).serializeArray();
            var result = _(data).reduce(function (acc, field) {
                acc[field.name] = field.value;
                return acc;
            }, {});
            return result
        },


        addToOrder: function (e) {
            var target = e.currentTarget;
            var item;
            if (this.sections !== undefined) {         //if we are in menu
                item = this.sections.get(target.dataset.sectionid).get('items').get(target.dataset.itemid);
            } else {                        //or in checkOrder
                item = this.order.get('orderItems').get(target.dataset.itemid).get('item');
            }
            if (target.value != 0) {
                if (this.order.get('orderItems').get(target.dataset.itemid) !== undefined) {  //already existing
                    this.order.get('orderItems').get(target.dataset.itemid).set('quantity', target.value);
                } else {
                    var toInsert = new Item({id: item.id, quantity: target.value, item: item, name: item.get('name'), pricePerUnit: item.get('price'), sectionId: item.get('sectionId')});
                }
                this.order.get('orderItems').add(toInsert);
            } else if (target.value == 0) {
                this.order.get('orderItems').remove(target.dataset.itemid)
            }
            this.order.setPrice();
            $('#price').text(this.order.get('price'));
        },

        goToMenu: function () {
            Backbone.history.navigate('client/menu', {trigger: true});
        },

        goToCheckOrder: function () {
            Backbone.history.navigate('client/checkOrder', {trigger: true});
        }

    });
    return App.client.views.BaseView;
});