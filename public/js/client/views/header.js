define(['namespace', './base-view'],
    function (App, BaseView, undefined) {

        App.client.views.header = BaseView.extend({

            el: '#header',

            template: App.tmpl.client.header,


            events: {
                'click .goToMenu': 'goToMenu',
                'click .goToCheckOrder':'goToCheckOrder'
            },


            initialize: function () {
                var that = this;

                _.bindAll(this, 'render');

                this.render()
            },

            render: function (e) {
                this.$el.html(this.template());
            }


        });
        return  App.client.views.header;
    }
);
