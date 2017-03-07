'use strict';
billingApp.controller("billing_info_Controller", function($rootScope,$scope,$http,$log){
		$scope.message="AKash";
		$scope.savedBillInfo=$http.get('/bill-info/')
			.then(function success(response) {
				$scope.BillList = response.data;
				$scope.config = response.config;
				$scope.headers = response.headers;
				$scope.status = response.status;
				$scope.statusText = response.statusText;
			},function failure(response){
				$scope.BillList = response.statusText;
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
billingApp.controller("add_billing_info_Controller", function($scope,$http){
	$scope.message="Add Billing Info";
	var billing_form={
			project_user_name:"",
			project_user_email:"",
			dept_name:"",
			address:"",
			project_name:"",
			acc_number:"",
			bill_rate:""
	};
	$scope.billing_form = billing_form;
	$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		$scope.list.push(this.billing_form);
		//$scope.HoldList.pust(this.billing_form);
		if($scope.billing_form){
				$http.post("/bill-info/",billing_form)
				.success(function(response) {
					// remove this later
					// console.log("pass"+JSON.stringify(response));
				})
				.error(function(response) {
					console.log( "failure message: " + JSON.stringify(response));
				});
				$scope.billing_form='';
		}
}});
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
	$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		$scope.list.push(this.chamber_form);
		if($scope.chamber_form){
				$http.post("/chamber/",chamber_form)
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
				$http.post("/department/",department_form)
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
	$scope.editChamberInfo=$http.get('/department/'+$scope.id)
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
