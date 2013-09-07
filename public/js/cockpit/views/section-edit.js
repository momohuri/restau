define(['namespace', './base-view', '../collections/items', '../models/item'
], function (App, BaseView, Items, Item, undefined) {

    App.cockpit.views.sectionEdit = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.sectionEdit,

        items: new Items([]),

        item: new Item(),

        events: {
            'submit #newItem': 'newItem',
            'click .edit': 'editItem'
        },

        initialize: function () {
            var that = this;

            _.bindAll(this, 'render');
            this.items.bind('add', this.render, this);
            this.item.bind('change',this.render);

            this.render();

        },

        render: function (e) {
            this.$el.html(this.template({items: this.items, item: this.item}));
        },

        newItem: function (e) {
            e.preventDefault();
            var that = this;
            var data = $(e.currentTarget).serializeArray();
            var result = _(data).reduce(function (acc, field) {
                acc[field.name] = field.value;
                return acc;
            }, {});


            result.id=0;
            //  this.item.save(result,{success:function(){
            this.item.set(result);
            this.items.add(that.item.clone());
            //  }});

            this.item.clear();
        },

        editItem: function (e) {
            this.item.set(this.items.get(e.currentTarget.parentNode.parentNode.dataset.id).attributes);
        }



    });
    return  App.cockpit.views.sectionEdit;
});