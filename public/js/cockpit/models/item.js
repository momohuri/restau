define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.item = BaseModel.extend({

            url: '/item',

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            }
        });

        return  App.cockpit.models.item;
    });
