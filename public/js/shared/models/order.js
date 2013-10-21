define(['namespace', './base-model','../collections/items'],
    function (App, BaseModel,Items, undefined) {
        App.cockpit.models.order = BaseModel.extend({

            url: '/',

            defaults: {
                created_at: new Date()
            },
            initialize: function () {


            }
        });

        return   App.cockpit.models.order;
    });
