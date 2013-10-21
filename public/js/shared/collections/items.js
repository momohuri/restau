define(['namespace', './base-collection','../models/item'],
    function (App, BaseCollection,Item, undefined) {
        App.cockpit.collections.items = BaseCollection.extend({

            url: '/items',

            model:Item,

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            }
        });

        return  App.cockpit.collections.items;
    });
