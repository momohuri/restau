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
                var toInsert = new Item({id: item.id, quantity: target.value, item: item, name: item.get('name'), pricePerUnit: item.get('price'), sectionId: item.get('sectionId')});
                this.order.get('orderItems').set(toInsert);
            } else if (target.value == 0) {
                this.order.get('orderItems').remove(target.dataset.itemid)
            }
            this.order.setPrice();
            $('#price').text(this.order.get('price'));
        }

    });
    return App.client.views.BaseView;
});