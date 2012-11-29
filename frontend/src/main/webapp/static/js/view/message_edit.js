var app = app || {};

(function($) {

    // Message Edit View
    // --------------

    app.MessageEditView = Backbone.View.extend({

        template: _.template($('#message-edit-template').html()),

        events: {
            'click .save' : 'save',
            'click .back' : function() {app.GepsRouter.navigate('/', true)},
            'click #recipient' : 'selectTo'
        },

        initialize: function() {
            console.log('init message edit view');
            this.selectOrganizationDialog = new app.OrganizationsView();
            this.model.on('error', this.onValidationError);
            this.model.on('sync', this.onMessageSaved, this);
        },

        render: function() {
            console.log('render message edit');
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        save: function() {
            console.log("save message");

            var change = {
                'recipient': $("#recipient").val(),
                'subject': $("#subject").val(),
                'text': $("#text").val()
            };
            this.model.save(change);
        },

        selectTo: function() {
            console.log('select to');
            this.selectOrganizationDialog.show(this.onOrganizationSelected);
        },

        onOrganizationSelected: function(organization) {
            console.log('organization selected: ' + organization);
            $('#recipient').val(organization);
        },

        onValidationError: function(model, error) {
            console.log('validation error');
            var field = $('#field-'+error.field);
            field.addClass("error");
            field.find(".help-inline").text(error.text);
        },

        onMessageSaved: function(event, model) {
            console.log('message saved to server');
            this.model.set({'messageId':model.messageId});
        }

    });
})($);