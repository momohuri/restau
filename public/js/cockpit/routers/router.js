define(['namespace', 'backbone', 'backboneRouteFilter', '../views/menu', '../views/section-new','../views/section-edit'],
    function (App, Backbone, Backbonefilter, Menu, SectionNew,SectionEdit, undefined) {
        App.cockpit.routers.Router = Backbone.Router.extend({

            routes: {
                '': 'index',
                'section/:id': 'section',
                'createnewsection': 'createNewSection'

            },

            initialize: function () {

            },

            before: function () {
                $('*').off();
            },

            index: function () {
                new Menu();
            },

            section: function (id) {
                new Menu();
                new SectionEdit();
            },

            createNewSection: function () {
                new Menu();
                new SectionNew();
            }

        });
        return App.cockpit.routers.Router;
    });
