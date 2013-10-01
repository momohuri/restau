require.config({
	paths: {
		'jquery': 'vendor/jquery/jquery.min',
		'underscore': 'vendor/underscore-amd/underscore',
		'backbone': 'vendor/backbone-amd/backbone',
		'namespace': 'namespace',

        'backboneRouteFilter': 'vendor/backbone.routefilter',
        'bootstrap': 'vendor/bootstrap.min'
	},
	shim: {
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'bootstrap':{
            deps:['jquery'],
            exports : 'bootstrap'
        },
        'backboneRouteFilter' :{
            deps:['backbone'],
            exports : 'backboneRouteFilter'
        }
  }
});

require(['jquery','./templates']);

define(['namespace', 'backbone', 'underscore', 'cockpit/routers/router'], function (App, Backbone, _,  cockpitRouter, undefined) {
    $(function () {
        App.cockpit.router = new cockpitRouter();
        Backbone.history.start();
    });
});