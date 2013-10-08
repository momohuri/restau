define(['namespace', './base-collection','../models/menu'],
    function (App, BaseCollection,Menu, undefined) {
        App.cockpit.collections.menus = BaseCollection.extend({

            url: '/menus',

            model:Menu,

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            }
        });

        return  App.cockpit.collections.menus;
    });
