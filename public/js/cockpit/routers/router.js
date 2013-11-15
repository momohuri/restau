define(['namespace', 'backboneRouteFilter', '../views/navigation', '../views/sections-manage', '../views/section-edit','../views/orders'],
    function (App, Backbonefilter, Menu, manageSections, SectionEdit,Orders, undefined) {
        App.cockpit.routers.Router = Backbone.Router.extend({

            routes: {
                'cockpit/section/manageSections': 'manageSections',
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
                activeNav('.menuBasedOnId');
            },

            index: function () {
                Backbone.history.navigate('cockpit/section/manageSections')
            },

            section: function (id) {
                new Menu();
                new SectionEdit();
            },

            manageSections: function () {
                new Menu();
                new manageSections();
            },

            orders: function(){
                new Menu();
                new Orders();
            }

        });
        return App.cockpit.routers.Router;
    });
