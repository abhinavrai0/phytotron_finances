billingApp.controller("billing_info_Controller", function($scope,$http,$log, billing_info_Service){
	$scope.message="AKash";
	$scope.savedBillInfo=$http.get('/bill-info/')
	.then(function success(response) {
		$scope.BillList = response.data;
		$scope.config = response.config;
		$scope.headers = response.headers;
		$scope.status = response.status;
		$scope.statusText = response.statusText;
		$log.info(response);
	},function failure(response){
		$scope.BillList = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.editBillingInfo = function(data){
			console.log(data);
			typeof(data);
			data = billing_info_Service;
	}

});
billingApp.controller("edit_billing_info_Controller", function($scope,$http, billing_info_Service){
	$scope.message="Edit Billing Info";
	var billing_form=billing_info_Service;
	$scope.billing_form=billing_form;
	$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		$scope.list.push(this.billing_form);
		$scope.HoldList.pust(this.billing_form);
		$http.post("/bill-info/",billing_form)
		.success(function(billing_form, status, headers, config) {
			$scope.message = billing_form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
		$scope.billing_form='';
	}
});
billingApp.service("billing_info_Service", function(){
		this.billing_form
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
	$scope.billing_form=billing_form;
	$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		$scope.list.push(this.billing_form);
		//$scope.HoldList.pust(this.billing_form);
		var asd="";
		if($scope.billing_form){
				$http.post("/bill-info/",billing_form)
				.success(function(response) {
					// remove this later
					console.log("pass"+JSON.stringify(response));
				})
				.error(function(response) {
					console.log( "failure message: " + JSON.stringify(response));
				});
				$scope.billing_form='';
		}
}
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
billingApp.controller("chamber_list_Controller", function($scope,$http){
	$scope.message="Garg";
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
