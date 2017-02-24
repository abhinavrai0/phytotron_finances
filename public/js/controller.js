billingApp.controller("billing_info_Controller", function($scope,$http){
	$scope.message="asd";
	var form={
			name:"",
			phone:"",
			email:"",
			address:""
	}
	$scope.form=form;
	$scope.submit=function(){
		$http.post("/saveSampleForm",form)
		.success(function(form, status, headers, config) {
			$scope.message = form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
});
billingApp.controller("add_billing_info_Controller", function($scope,$http){
	$scope.message="asd";
	var form={
			name:"",
			phone:"",
			email:"",
			address:""
	}
	$scope.form=form;
	$scope.submit=function(){
		$http.post("/saveSampleForm",form)
		.success(function(form, status, headers, config) {
			$scope.message = form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
});
billingApp.controller("tracking_usage_Controller", function($scope,$http){
	$scope.message="asd";
	var form={
			name:"",
			phone:"",
			email:"",
			address:""
	}
	$scope.form=form;
	$scope.submit=function(){
		$http.post("/saveSampleForm",form)
		.success(function(form, status, headers, config) {
			$scope.message = form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
});
billingApp.controller("add_tracking_usage_Controller", function($scope,$http){
	$scope.message="asd";
	var form={
			name:"",
			phone:"",
			email:"",
			address:""
	}
	$scope.form=form;
	$scope.submit=function(){
		$http.post("/saveSampleForm",form)
		.success(function(form, status, headers, config) {
			$scope.message = form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
});
billingApp.controller("chamber_list_Controller", function($scope,$http){
	$scope.message="asd";
	var form={
			name:"",
			phone:"",
			email:"",
			address:""
	}
	$scope.form=form;
	$scope.submit=function(){
		$http.post("/saveSampleForm",form)
		.success(function(form, status, headers, config) {
			$scope.message = form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
});
