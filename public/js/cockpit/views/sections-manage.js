define(['namespace', './base-view', '../../shared/collections/sections', '../../shared/models/section'
], function (App, BaseView, Sections, Section, undefined) {

    App.cockpit.views.sectionNew = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.sectionManage,


        sections: new Sections(),

        section: new Section(),

        events: {
            'submit #createNewSectionForm': 'newSection',
            'click .edit': 'editItem',
            'click .deleteItem': 'deleteItem',
            'click #saveRankDisplay': 'saveRankDisplay'
        },

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');

            this.sections.fetch({
                success: function () {
                    that.render();
                }
            })

        },

        render: function (e) {
            this.$el.html(this.template({sections: this.sections}));
            this.activeNav('.menuBasedOnId');
            $('.table tbody').sortable();
        },

        newSection: function (e) {
            e.preventDefault();
            var data = this.getFormData(e);
            var that = this;
            this.section.save(data, {success: function () {
                Backbone.history.navigate('cockpit/section/' + that.section.id, true);
            }});
        },

        saveRankDisplay: function () {
            var order = _.map($('tbody tr'), function (item, index) {
                return {id: item.dataset.id, rankOrder: index}
            });
            this.sections.sendRankDisplay(order);
        },

        editItem: function (e) {
            this.section = this.sections.get(e.currentTarget.parentNode.parentNode.dataset.id);
            this.render();
        },

        deleteItem: function (e) {
            this.section = this.sections.get(e.currentTarget.parentNode.parentNode.dataset.id);
            this.section.destroy(function () {

            });
            //destroy item
        }



    })
    ;
    return  App.cockpit.views.sectionNew;
})
;