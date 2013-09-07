define(['namespace', 'backbone', 'backboneRouteFilter', '../views/menu'],
    function (App, Backbone, Backbonefilter, Menu, undefined) {
        App.cockpit.routers.Router = Backbone.Router.extend({

            routes: {
                '': 'index',
                'section/:id':'section'

            },

            initialize: function () {

            },

            before: function () {
                $('*').off();
            },

            index: function () {
                new Menu();
            },

            section:function(id){
                  new Menu();
            }
        });
        return App.cockpit.routers.Router;
    });
