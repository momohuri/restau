define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.menu = BaseModel.extend({

            url: '/',

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            }
        });

        return  App.cockpit.models.menu;
    });
