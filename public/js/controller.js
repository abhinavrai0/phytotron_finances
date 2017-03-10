'use strict';
billingApp.controller("client_list_Controller", function($rootScope,$scope,$http,$log){
		$scope.message="Client List";
		$scope.savedClientInfo=$http.get('/client/')
			.then(function success(response) {
				$scope.ClientList = response.data;
				$scope.config = response.config;
				$scope.headers = response.headers;
				$scope.status = response.status;
				$scope.statusText = response.statusText;
			},function failure(response){
				$scope.ClientList = response.statusText;
				$scope.status = response.data;
				$log.info(response);
		});
});
billingApp.controller("edit_billing_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.message="Edit Billing Info";
	$scope.id = $routeParams.id;
	$scope.editBillInfo=$http.get('/bill-info/'+$scope.id)
		.then(function success(response) {
			$scope.billing_form = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
	$scope.submit=function(){

		$http.post("/bill-info/",$scope.billing_form)
		.success(function(billing_form, status, headers, config) {
			$scope.message = billing_form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
		$scope.billing_form='';
	}
});
billingApp.controller("add_client_info_Controller", function($scope,$http){
	$scope.message="Add Client Info";
	var client_form={
			project_client_name:"",
			project_client_email:"",
			dept_name:"",
			address:""
	};
	$scope.client_form = client_form;
	//$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		//$scope.list.push(this.client_form);
		//$scope.HoldList.pust(this.billing_form);
		if($scope.client_form){
				// $scope.client_form.dept_name = $scope.client_form.dept_name.departmentName;
				// console.log($scope.client_form.dept_name);
				$http.post("/client/",$scope.client_form)
				.success(function(response) {
					// remove this later
					// console.log("pass"+JSON.stringify(response));
				})
				.error(function(response) {
					console.log( "failure message: " + JSON.stringify(response));
				});
				$scope.client_form='';
		}
	}

	$scope.savedDepartments=$http.get('/department/')
		.then(function success(response) {
			$scope.departments = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.departments = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
});
billingApp.controller("tracking_usage_Controller", function($scope,$http){
	$scope.message="Ankit";
	// var form={
	// 		name:"",
	// 		phone:"",
	// 		email:"",
	// 		address:""
	// }
	// $scope.form=form;
	// $scope.submit=function(){
	// 	$http.post("/saveSampleForm",form)
	// 	.success(function(form, status, headers, config) {
	// 		$scope.message = form;
	// 	})
	// 	.error(function(data, status, headers, config) {
	// 		alert( "failure message: " + JSON.stringify({data: data}));
	// 	});
	// }
});
billingApp.controller("add_tracking_usage_Controller", function($scope,$http){
	$scope.message="Agrawal";
	// var form={
	// 		name:"",
	// 		phone:"",
	// 		email:"",
	// 		address:""
	// }
	// $scope.form=form;
	// $scope.submit=function(){
	// 	$http.post("/saveSampleForm",form)
	// 	.success(function(form, status, headers, config) {
	// 		$scope.message = form;
	// 	})
	// 	.error(function(data, status, headers, config) {
	// 		alert( "failure message: " + JSON.stringify({data: data}));
	// 	});
	// }
});
billingApp.controller("chamber_list_Controller", function($rootScope,$scope,$http,$log){
	$scope.message="Garg";
	$scope.savedChamberInfo=$http.get('/chamber/')
		.then(function success(response) {
			$scope.chamberList = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.chamberList = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
});
billingApp.controller("add_chamber_info_Controller", function($scope,$http){
	$scope.message="Add Chamber Info";
	var chamber_form={
			chamberType:"",
			chamberId:"",
			chamberCarts:""
	};
	$scope.chamber_form = chamber_form;
	//$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		//$scope.list.push(this.chamber_form);
		if($scope.chamber_form){
				$http.post("/chamber/",$scope.chamber_form)
				.success(function(response) {
					// remove this later
					// console.log("pass"+JSON.stringify(response));
				})
				.error(function(response) {
					console.log( "failure message: " + JSON.stringify(response));
				});
				$scope.chamber_form='';
		}
	}
});
billingApp.controller("edit_chamber_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.message="Edit Chamber Info";
	$scope.id = $routeParams.id;
	$scope.editChamberInfo=$http.get('/chamber/'+$scope.id)
		.then(function success(response) {
			$scope.chamber_form = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
	$scope.submit=function(){

		$http.post("/chamber/",$scope.chamber_form)
		.success(function(chamber_form, status, headers, config) {
			$scope.message = chamber_form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
		$scope.chamber_form='';
	}
});
billingApp.controller("department_list_Controller", function($rootScope,$scope,$http,$log){
	$scope.message="Department List";
	$scope.savedDepartmentInfo=$http.get('/department/')
		.then(function success(response) {
			$scope.departmentList = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.departmentList = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
});
billingApp.controller("add_department_info_Controller", function($scope,$http){
	$scope.message="Add Department Info";
	var department_form={
			departmentId:"",
			departmentName:""
	};
	$scope.department_form = department_form;
	$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		$scope.list.push(this.department_form);
		if($scope.department_form){
				$http.post("/department/",$scope.department_form)
				.success(function(response) {
					// remove this later
					// console.log("pass"+JSON.stringify(response));
				})
				.error(function(response) {
					console.log( "failure message: " + JSON.stringify(response));
				});
				$scope.department_form='';
		}
	}
});
billingApp.controller("edit_department_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.message="Edit Department Info";
	$scope.id = $routeParams.id;
	$scope.editDepartmentInfo=$http.get('/department/'+$scope.id)
		.then(function success(response) {
			$scope.department_form = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
	$scope.submit=function(){

		$http.post("/department/",$scope.department_form)
		.success(function(department_form, status, headers, config) {
			$scope.message = department_form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
		$scope.department_form='';
	}
});

billingApp.controller("crop_list_Controller", function($rootScope,$scope,$http,$log){
	$scope.message="Crop List";
	$scope.savedCropInfo=$http.get('/crop/')
		.then(function success(response) {
			$scope.cropList = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.cropList = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
});
billingApp.controller("add_crop_info_Controller", function($scope,$http){
	$scope.message="Add Crop Info";
	var crop_form={
			cropType:"",
			cropName:""
	};
	$scope.crop_form = crop_form;
	$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		$scope.list.push(this.crop_form);
		if($scope.crop_form){
				$http.post("/crop/",$scope.crop_form)
				.success(function(response) {
					// remove this later
					// console.log("pass"+JSON.stringify(response));
				})
				.error(function(response) {
					console.log( "failure message: " + JSON.stringify(response));
				});
				$scope.crop_form='';
		}
	}
});
billingApp.controller("edit_crop_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.message="Edit Crop Info";
	$scope.id = $routeParams.id;
	$scope.editCropInfo=$http.get('/crop/'+$scope.id)
		.then(function success(response) {
			$scope.crop_form = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
	$scope.submit=function(){

		$http.post("/crop/",$scope.crop_form)
		.success(function(crop_form, status, headers, config) {
			$scope.message = crop_form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
		$scope.crop_form='';
	}
});
