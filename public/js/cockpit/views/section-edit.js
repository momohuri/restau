define(['namespace', './base-view', '../../shared/collections/items', '../../shared/models/item', '../../shared/collections/sections'
], function (App, BaseView, Items, Item, Sections, undefined) {

    App.cockpit.views.sectionEdit = BaseView.extend({

        el: '#content',

        template: App.tmpl.cockpit.sectionEdit,

        sections: new Sections([
            {name: 'section1', id: 1},
            {name: 'section2', id: 2}
        ]),

        items: new Items([]),

        item: new Item(),

        events: {
            'submit #newItem': 'newItem',
            'click .edit': 'editItem',
            'click .deleteItem': 'deleteItem'
        },

        initialize: function () {
            _.bindAll(this, 'render');
            var that = this;
            this.items.bind('add', this.render, this);
            this.items.bind('change', this.render, this);
            this.item.bind('change', this.render);
            this.render();
        },

        render: function (e) {
            this.$el.html(this.template({sections: this.sections, items: this.items, item: this.item}));
        },

        newItem: function (e) {
            e.preventDefault();
            var that = this;
            var result = this.getFormData(e);

            if (result.id !== '') {
                this.items.remove(result.id)
            }  else{
                result.id = 1;  //todo delete when api
            }

            //  this.item.save(result,{success:function(){
            this.item.set(result);
            this.items.add(that.item.clone());
            //  }});

            this.item.clear();
            this.render();


        },

        editItem: function (e) {
            this.item = this.items.get(e.currentTarget.parentNode.parentNode.dataset.id);
            this.render();
        },

        deleteItem: function (e) {
            //destroy item
        }



    });
    return  App.cockpit.views.sectionEdit;
});