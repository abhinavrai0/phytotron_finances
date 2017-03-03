'use strict';

var billingApp = angular.module( "billing", ["ngRoute","ngResource"] )
  .config(function ($routeProvider){
    $routeProvider
    .when("/",{
      templateUrl: "./partials/billing_info.html",
			controller: "billing_info_Controller"
    })
    .when("/billing_info",{
      templateUrl: "./partials/billing_info.html",
			controller: "billing_info_Controller"
    })
    .when("/add_billing_info",{
      templateUrl: "./partials/add_billing_info.html",
			controller: "add_billing_info_Controller"
    })
    .when("/edit_billing_info/:id",{
      templateUrl: "./partials/edit_billing_info.html",
			controller: "edit_billing_info_Controller"
    })
    .when("/tracking_usage",{
      templateUrl: "./partials/tracking_usage.html",
			controller: "tracking_usage_Controller"
    })
    .when("/add_tracking_usage",{
      templateUrl: "./partials/add_tracking_usage.html",
			controller: "add_tracking_usage_Controller"
    })
    .when("/chamber_list",{
      templateUrl: "./partials/chamber_list.html",
			controller: "chamber_list_Controller"
    });
  });
