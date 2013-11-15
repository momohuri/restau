require.config({
    baseUrl: 'js',
    paths: {
        'jquery': 'vendor/jquery/jquery.min',
        'underscore': 'vendor/underscore-amd/underscore',
        'Backbone': 'vendor/backbone',
        'namespace': './templates',
        'helpers': 'helpers/mainHelper',
        'backboneRouteFilter': 'vendor/backbone.routefilter',
        'bootstrap': 'vendor/bootstrap.min',
        'jqueryUI': 'vendor/jquery-ui-1.10.3.custom.min'
    },
    shim: {
        'Backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'bootstrap': {
            deps: ['jquery'],
            exports: 'bootstrap'
        },
        'backboneRouteFilter': {
            deps: ['Backbone'],
            exports: 'backboneRouteFilter'
        },
        'jqueryUI': {
            deps: ['jquery']
        }
    }
});

//require(['Backbone', './templates', 'jqueryUI']);

require(['Backbone', 'jqueryUI', 'namespace', 'cockpit/routers/router', 'client/routers/router'], function (Backbone, $UI, App, cockpitRouter, clientRouter, undefined) {
    $(function () {
        App.cockpit.router = new cockpitRouter();
        App.client.router = new clientRouter();
        Backbone.history.start();
    });
});