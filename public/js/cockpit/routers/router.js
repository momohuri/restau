define(['namespace', 'backbone', 'backboneRouteFilter', '../views/navigation', '../views/section-new', '../views/section-edit','../views/orders'],
    function (App, Backbone, Backbonefilter, Menu, SectionNew, SectionEdit,Orders, undefined) {
        App.cockpit.routers.Router = Backbone.Router.extend({

            routes: {
                'cockpit/section/new': 'createNewSection',
                'cockpit/section/:id': 'section',
                'cockpit/orders':'orders',
                'cockpit/*actions': 'index'
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
                new Menu();
                new SectionNew();
            },

            section: function (id) {
                new Menu();
                new SectionEdit();
            },

            createNewSection: function () {
                new Menu();
                new SectionNew();
            },

            orders: function(){
                new Menu();
                new Orders();
            }

        });
        return App.cockpit.routers.Router;
    });
