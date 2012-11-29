var app = app || {};

(function($) {

    // Message Edit View
    // --------------

    app.MessageEditView = Backbone.View.extend({

        template: _.template($('#message-edit-template').html()),

        events: {
            'click .save' : 'save',
            'click .back' : function() {app.GepsRouter.navigate('/', true)},
            'click .send' : 'send',
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

            if (this.model.get('messageId')) {
                this.$el.find('.send').removeClass('disabled');
            }

            return this;
        },

        save: function() {
            console.log("save message");

            var change = this.readForm();
            if (this.model.set(change)) {
                this.model.save();
            }
        },

        readForm: function() {
            return  {
                'recipient': $("#recipient").val(),
                'subject': $("#subject").val(),
                'text': $("#text").val()
            };
        },

        send: function() {
            console.log('send message');
            var change = this.readForm();
            _.extend(change, {action:'send'});
            if (this.model.set(change)) {
                this.model.save();
                app.GepsRouter.navigate('#', true);
            }
        },

        selectTo: function() {
            console.log('select to');
            this.selectOrganizationDialog.show(this.onOrganizationSelected);
        },

        onOrganizationSelected: function(organization) {
            console.log('organization selected: ' + organization);
            $('#recipient').val(organization);
        },

        onValidationError: function(model, errors) {
            console.log('validation error');
            _.each(errors, function(error){
                var field = $('#field-'+error.name);
                field.addClass("error");
                field.find(".help-inline").text(error.message);
            });
        },

        onMessageSaved: function(event, model) {
            console.log('message saved to server');
            this.hideErrors();
            app.GepsRouter.navigate('#messages/'+model.messageId+'/edit', false);
            this.$el.find('.send').removeClass('disabled');
        },

        hideErrors: function() {
            this.$('.control-group').removeClass('error');
            this.$('.help-inline').text('');
        }

    });
})(jQuery);