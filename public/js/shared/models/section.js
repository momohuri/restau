define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.section = BaseModel.extend({

            url: '/section',

            defaults: {
                created_at: new Date()

            },
            initialize: function () {

            }
        });

        return  App.cockpit.models.section;
    });
