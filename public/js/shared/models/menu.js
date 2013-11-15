define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.menu = BaseModel.extend({

            urlRoot: '/',

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            }
        });

        return  App.cockpit.models.menu;
    });
