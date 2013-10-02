define(['namespace', './base-view', '../models/section'
], function (App, BaseView, Section, undefined) {

    App.cockpit.views.sectionNew = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.sectionNew,


        section: new Section(),

        events: {
            'submit #createNewSectionForm': 'newSection'
        },

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            this.render();

        },

        render: function (e) {
            this.$el.html(this.template({section:this.section}));
        },

        newSection: function (e) {
            e.preventDefault();

            var data = this.getFormData(e);

            this.section.save(result,{success:function(){
                Backbone.history.navigate('section/'+this.section.id,true);
            }});


        }


    })
    ;
    return  App.cockpit.views.sectionNew;
})
;