define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.item = BaseModel.extend({

            url: '/item',

            defaults: {
                created_at: new Date()

            },
            initialize: function () {


            },
            sync: function (method, model, options) {
                var url = 'section/' + model.get('section') + '/item'
                var verb;
                switch (method) {
                    case 'create':
                        verb = 'POST';
                        break;
                    case 'delete':
                        verb = 'DELETE';
                        url += '/' + model.get('id');
                        break;
                    case 'update':
                        verb = 'PUT';
                        url += '/' + model.get('id');
                        break;

                }


                $.ajax({
                    type: verb,
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    url: url,
                    data: JSON.stringify(model.attributes),
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

        return  App.cockpit.models.item;
    });
