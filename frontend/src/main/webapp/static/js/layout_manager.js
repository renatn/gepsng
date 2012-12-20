var app = app || {};

(function($) {

    // Layout Manager
    // --------------

    var LayoutManager = function() {

        this.show =function(view) {
            console.log('show');
            if (app.currentView && app.currentView['finalize']) {
                app.currentView.finalize();
            }
            app.currentView = view;
        }


    };

    app.LayoutManager = new LayoutManager();

})(jQuery);