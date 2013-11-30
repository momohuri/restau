define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.order = BaseModel.extend({

            urlRoot:  '/order',

            defaults: {
                price: 0,
                created_at: new Date(),
                type: "client"
            },
            initialize: function () {
                this.urlRoot = this.get('type')+this.urlRoot;
            },
            setPrice: function () {
                var price = 0;
                //to get price we assume that theire is a collection of items
                this.get('orderItems').forEach(function (item) {
                    price += item.get('item').get('price') * item.get('quantity');
                });
                this.set('price', price);
            },

            save: function (key, val, options) {
                this.beforeSave(key, val, options);
                return Backbone.Model.prototype.save.call(this, key, val, options);
            },

            beforeSave: function (key, val, options) {
              this.get('orderItems').forEach(function(item){
                 item.unset('item');
              });
            }

        });

        return   App.cockpit.models.order;
    });
