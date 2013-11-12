define(['namespace', './base-collection','../models/section'],
    function (App, BaseCollection,Section, undefined) {
        App.cockpit.collections.sections = BaseCollection.extend({

            url:'/sections',

            model:Section,


            initialize: function () {


            }
        },
            {
                sendOrder:function(order,next){
                    $.ajax({
                        type: verb,
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        url: url,
                        data: order,
                        success: function (id) {
                            if (model.get('id') === undefined) model.set('id', id);
                            options.success(model, model.attributes, options);

                        },
                        error: function (e) {
                            console.log('error : ', e);
                        }
                    });
                }
            });

         return  App.cockpit.collections.sections;
    });
