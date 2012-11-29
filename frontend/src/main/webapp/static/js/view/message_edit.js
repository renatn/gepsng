var app = app || {};

(function($) {

    // Message Edit View
    // --------------

    app.MessageEditView = Backbone.View.extend({

        template: _.template($('#message-edit-template').html()),

        events: {
            'click .save' : 'save',
            'click .back' : function() {app.GepsRouter.navigate('/', true)}
        },

        initialize: function() {
            console.log('init message edit view');
            this.render();
        },

        render: function() {
            console.log('render message edit');
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        save: function() {
            console.log("save message");

            var text = $("#text").val();
            var subject = $("#subject").val();

            var change = {
                "subject": subject,
                "text": text
            };
            this.model.set(change);
            this.model.save();
            console.log("Save: " + this.model.get("messageId"));
        }

    });
})($);