define(['namespace', 'backboneRouteFilter','../views/menu'],
    function (App, Backbonefilter,Menu, undefined) {
        App.client.routers.Router = Backbone.Router.extend({

            routes: {
                'client/*actions': 'index'
            },

            initialize: function () {

            },

            before: function () {
                $('*').off();
            },
            after: function () {
                //the function will put active the menu selected.
                // to use give add class menuBasedOnName or menuBasedOnId to the div with the menu
                // and then add a data-reference with the id or name to each li of the menu
                // the function will try to find the name/id in the url
                var activeNav = function (idOrName) {
                    if ($(idOrName).length !== 0) {
                        _.each($(idOrName + ' .nav li'), function (item) {
                            if (Backbone.history.getHash().split('/').indexOf(item.dataset.reference) !== -1) {
                                item.className = item.className + ' active';
                            }
                        });
                    }
                }
                activeNav('.menuBasedOnName');
                activeNav('.menuBasedOnId')
            },

            index: function () {
                //todo this is bad !!!

                $('head').append(' <link rel="stylesheet" type="text/css" media="screen" href="/css/client/theme.css"/>')

                new Menu();
            }
        });
        return App.client.routers.Router;
    });
