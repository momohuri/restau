define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.order = BaseModel.extend({

            urlRoot:  '/order',

            defaults: {
                price: 0,
                type: "client"
            },
            initialize: function () {
                this.urlRoot = this.get('type')+this.urlRoot;
            },
            setPrice: function () {
                var price = 0;
                //to get price we assume that theire is a collection of items
                this.get('orderItems').forEach(function (item) {
                    price += item.get('pricePerUnit') * item.get('quantity');
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
                  if(item.get('quantity')===0){
                      this.get('orderItems').remove(item)
                  }
              });
            }

        });

        return   App.cockpit.models.order;
    });
