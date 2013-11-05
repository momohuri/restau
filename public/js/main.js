require.config({
    paths: {
        'jquery': 'vendor/jquery/jquery.min',
        'underscore': 'vendor/underscore-amd/underscore',
        'backbone': 'vendor/backbone',
        'namespace': 'namespace',
        'helpers': 'helpers/mainHelper',
        'backboneRouteFilter': 'vendor/backbone.routefilter',
        'bootstrap': 'vendor/bootstrap.min'
    },
    shim: {
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'bootstrap': {
            deps: ['jquery'],
            exports: 'bootstrap'
        },
        'backboneRouteFilter': {
            deps: ['backbone'],
            exports: 'backboneRouteFilter'
        }
    }
});

//require(['jquery', './templates', 'bootstrap','helpers']);

require(['namespace', 'backbone', 'cockpit/routers/router','client/routers/router','./templates'], function (App, Backbone, cockpitRouter,clientRouter, undefined) {
    $(function () {
        App.cockpit.router = new cockpitRouter();
        App.client.router = new clientRouter();
        Backbone.history.start();
    });
});