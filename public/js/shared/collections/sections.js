define(['namespace', './base-collection', '../models/section'],
    function (App, BaseCollection, Section, undefined) {
        App.cockpit.collections.sections = BaseCollection.extend({

            url: function () {
                return '/sections';
            },

            model: Section,


            initialize: function () {


            }
        });
        return  App.cockpit.collections.sections;
    });
