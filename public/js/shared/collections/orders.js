define(['namespace', './base-collection','../models/order'],
    function (App, BaseCollection,Order, undefined) {
        App.cockpit.collections.orders = BaseCollection.extend({

            url: '/orders',

            model : Order,

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            }
        });

        return  App.cockpit.collections.orders;
    });
