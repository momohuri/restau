define(['namespace', './base-view', '../../shared/collections/items', '../../shared/models/item', '../../shared/collections/sections'
], function (App, BaseView, Items, Item, Sections, undefined) {

    App.cockpit.views.sectionEdit = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.sectionEdit,

        sections: new Sections(),

        sectionId: null,

        items: new Items(),

        item: new Item(),

        events: {
            'submit #newItem': 'newItem',
            'click .edit': 'editItem',
            'click .deleteItem': 'deleteItem',
            'click #saveRankDisplay': 'saveRankDisplay'
        },

        initialize: function (params) {

            this.sectionId = params.sectionId;

            _.bindAll(this, 'render');
            var that = this;
            this.items.bind('add', this.render, this);
            this.items.bind('change', this.render, this);
            this.item.bind('change', this.render);

            this.items.add({sectionId: this.sectionId}, {at: 0});

            $.when(this.items.fetch(), this.sections.fetch()).then(function () {
                that.render();
            });

        },

        render: function (e) {
            this.$el.html(this.template({sections: this.sections, sectionId: this.sectionId, items: this.items, item: this.item}));
            this.activeNav('.menuBasedOnId');
            $('.table tbody').sortable();
        },

        newItem: function (e) {
            e.preventDefault();
            var that = this;
            var result = this.getFormData(e);

            if (result.id !== '') {
                this.items.remove(result.id)
            } else {
                delete result.id;
            }

            this.item.set(result);
            this.item.save({}, {
                    success: function () {
                        that.items.add(that.item.clone());
                        that.item.clear();
                        that.render();
                    }}
            );


        },

        saveRankDisplay: function () {
            var order = _.map($('tbody tr'), function (item, index) {
                return {id: item.dataset.id, rankOrder: index}
            });
            this.items.sendRankDisplay(order);
        },

        editItem: function (e) {
            this.item = this.items.get(e.currentTarget.parentNode.parentNode.dataset.id);
            this.render();
        },

        deleteItem: function (e) {
            var id = $(e.currentTarget).closest('tr')[0].dataset.id;
            this.item = this.items.get(id);
            this.item.destroy(function () {

            });
        }



    });
    return  App.cockpit.views.sectionEdit;
});