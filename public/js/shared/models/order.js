define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.order = BaseModel.extend({

            url: '/',

            defaults: {
                price: 0,
                created_at: new Date()
            },
            initialize: function () {

            },
            setPrice: function () {
                var price = 0;
                //to get price we assume that theire is a collection of items
                this.get('items').forEach(function (item) {
                    price += item.get('price') * item.get('quantity');
                });
                this.set('price', price);
            }

        });

        return   App.cockpit.models.order;
    });
