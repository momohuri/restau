define(['namespace', './base-view', '../../shared/collections/sections'
], function (App, BaseView, Sections, undefined) {

    App.cockpit.views.sectionNew = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.sectionNew,


        sections: new Sections([
            {name: 'section1', id: 1},
            {name: 'section2', id: 2}
        ]),
        events: {
            'submit #createNewSectionForm': 'newSection'
        },

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            this.sections.fetch({
                success:function(){
                    that.render();
                }
            })

        },

        render: function (e) {
            this.$el.html(this.template({sections:this.sections}));
        },

        newSection: function (e) {
            e.preventDefault();

            var data = this.getFormData(e);

            var that = this;
            this.section.save(data,{success:function(){
                Backbone.history.navigate('cockpit/section/'+this.section.id,true);
            }});


        }


    })
    ;
    return  App.cockpit.views.sectionNew;
})
;