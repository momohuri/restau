define(['namespace', './base-collection','../models/section'],
    function (App, BaseCollection,Section, undefined) {
        App.cockpit.collections.sections = BaseCollection.extend({

            url:'/sections',

            model:Section,


            initialize: function () {


            },

            sendRankDisplay:function(order,next){
                debugger
                $.ajax({
                    type: "PUT",
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    url: 'section/'+ +'items',
                    data: order,
                    success: function (id) {


                    },
                    error: function (e) {
                        console.log('error : ', e);
                    }
                });
            }
        });
         return  App.cockpit.collections.sections;
    });
