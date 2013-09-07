define(['namespace', './base-collection'],
    function (App, BaseCollection, undefined) {
        App.cockpit.collections.items = BaseCollection.extend({

            url: '/items',

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            }
        });

        return  App.cockpit.collections.items;
    });
