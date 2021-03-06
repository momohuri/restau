define(['namespace', './base-model'],
    function (App, BaseModel, undefined) {
        App.cockpit.models.item = BaseModel.extend({

            urlRoot: '/item',

            defaults: {


            },
            initialize: function () {


            },
            sync: function (method, model, options) {
                var url = 'section/' + model.get('sectionId') + '/item'
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
                    success: function (attrs) {
                        model.set(attrs);
                        options.success(model);

                    },
                    error: function (e) {
                        console.log('error : ', e);
                    }
                });
            },

            getAttrForOrder:function(){
                this.unset('displayRank', { silent: true });
                this.unset('createdAt', { silent: true });
                this.unset('created_at', { silent: true });
                this.set('pricePerUnit',this.get('price'));
                return this;
            }

        });

        return  App.cockpit.models.item;
    });
