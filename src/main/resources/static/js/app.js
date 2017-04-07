'use strict';

var billingApp = angular.module( "billing", ["ngRoute","ngResource"] )
  .config(function ($routeProvider){
    $routeProvider
    .when("/",{
      templateUrl: "./partials/usage_list.html",
			controller: "usage_list_Controller"
    })
    .when("/client_list",{
      templateUrl: "./partials/client_list.html",
			controller: "client_list_Controller"
    })
    .when("/add_client_info",{
      templateUrl: "./partials/add_client_info.html",
			controller: "add_client_info_Controller"
    })
    .when("/edit_client_info/:id",{
      templateUrl: "./partials/edit_client_info.html",
			controller: "edit_client_info_Controller"
    })
    .when("/start_project/:id",{
      templateUrl: "./partials/start_project.html",
      controller: "start_project_Controller"
    })
    .when("/usage_list",{
      templateUrl: "./partials/usage_list.html",
			controller: "usage_list_Controller"
    })
    .when("/track_project/:id",{
      templateUrl: "./partials/track_project.html",
			controller: "track_project_Controller"
    })
    .when("/chamber_list",{
      templateUrl: "./partials/chamber_list.html",
			controller: "chamber_list_Controller"
    })
    .when("/add_chamber_info",{
      templateUrl: "./partials/add_chamber_info.html",
			controller: "add_chamber_info_Controller"
    })
    .when("/edit_chamber_info/:id",{
      templateUrl: "./partials/edit_chamber_info.html",
			controller: "edit_chamber_info_Controller"
    })
    .when("/department_list",{
      templateUrl: "./partials/department_list.html",
			controller: "department_list_Controller"
    })
    .when("/add_department_info",{
      templateUrl: "./partials/add_department_info.html",
			controller: "add_department_info_Controller"
    })
    .when("/edit_department_info/:id",{
      templateUrl: "./partials/edit_department_info.html",
			controller: "edit_department_info_Controller"
    })
    .when("/crop_list",{
      templateUrl: "./partials/crop_list.html",
			controller: "crop_list_Controller"
    })
    .when("/add_crop_info",{
      templateUrl: "./partials/add_crop_info.html",
			controller: "add_crop_info_Controller"
    })
    .when("/edit_crop_info/:id",{
      templateUrl: "./partials/edit_crop_info.html",
			controller: "edit_crop_info_Controller"
    });
  });
