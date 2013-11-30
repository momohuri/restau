define(['namespace', './base-collection', '../models/order'],
    function (App, BaseCollection, Order, undefined) {
        App.cockpit.collections.orders = BaseCollection.extend({

            url: '/orders',

            model: Order,

            defaults: {

            },

            initialize: function () {


            }

        }, {
            isAllBillGenerated: function (next) {
                $.get("client/isAllBillGenerated", function (bool) {
                    next(bool);
                });
            }
        });

        return  App.cockpit.collections.orders;
    });
