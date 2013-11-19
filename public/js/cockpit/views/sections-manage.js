define(['namespace', './base-view', '../../shared/collections/sections', '../../shared/models/section'
], function (App, BaseView, Sections, Section, undefined) {

    App.cockpit.views.sectionNew = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.sectionManage,


        sections: new Sections(),

        section: new Section(),

        events: {
            'submit #createNewSectionForm': 'newSection',
            'click .edit': 'editSection',
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
            });
        },

        render: function (e) {
            this.$el.html(this.template({sections: this.sections, section: this.section}));
            this.activeNav('.menuBasedOnId');
            $('.table tbody').sortable();
        },

        newSection: function (e) {
            e.preventDefault();
            var that = this;
            var result = this.getFormData(e);

            if (result.id !== '') {
                this.sections.remove(result.id)
            } else {
                delete result.id;
            }

            this.section.set(result);
            this.section.save({}, {
                    success: function () {
                        that.sections.add(that.section.clone());
                        that.section.clear();
                        that.render();
                    }}
            );
        },

        saveRankDisplay: function () {
            var order = _.map($('tbody tr'), function (item, index) {
                return {id: item.dataset.id, rankOrder: index}
            });
            this.sections.sendRankDisplay(order);
        },

        editSection: function (e) {
            this.section = this.sections.get(e.currentTarget.parentNode.parentNode.dataset.id);
            this.render();
        },

        deleteItem: function (e) {
            this.section = this.sections.get(e.currentTarget.parentNode.parentNode.dataset.id);
            this.section.destroy();
        }



    })
    ;
    return  App.cockpit.views.sectionNew;
})
;