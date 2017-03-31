'use strict';
billingApp.controller("client_list_Controller", function($rootScope,$scope,$http,$log){
		$scope.message="Client List";
		$scope.savedClientInfo=$http.get('/client')
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
		$scope.selectedClient="";
});
billingApp.controller("edit_client_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.message="Edit Client";
	$scope.id = $routeParams.id;
	$scope.editClientInfo=$http.get('/client/'+$scope.id)
		.then(function success(response) {
			$scope.client_form = response.data;
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

		$http.put("/client/"+$scope.id,$scope.client_form)
		.success(function(client_form, status, headers, config) {
			$scope.message = client_form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
		$scope.client_form='';
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

	$scope.options = ["Active", "Inactive"];
	$scope.client_form.client_status = $scope.options[0];
});
billingApp.controller("add_client_info_Controller", function($scope,$http){
	$scope.message="Add Client Info";
	var client_form={
			client_first_name:"",
			client_last_name:"",
			client_email:"",
			client_phone:"",
			dept_name:"",
			client_status:"",
			client_address:""
	};
	$scope.options = ["Active", "Inactive"];
	$scope.client_form = client_form;
	$scope.client_form.client_status = $scope.options[0];

	//$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		//$scope.list.push(this.client_form);
		//$scope.HoldList.pust(this.billing_form);
		if($scope.client_form){
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
	$scope.savedDepartmentInfo=$http.get('/department')
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
				$http.post("/department",$scope.department_form)
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

		$http.put("/department/"+$scope.id,$scope.department_form)
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
			cropCommonName:"",
			cropScientificName:""
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
billingApp.controller("start_project_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.message="Start Project";
	/*get request returns a promise.
	 http://stackoverflow.com/questions/31714821/access-http-get-json-property-in-angular-controller
	*/
  var uneditableEntry={content: {}};;
	$scope.id = $routeParams.id;
	uneditableEntry.$promise=$http.get('/client/'+$scope.id)
		.then(function success(response) {
			angular.copy(response.data, uneditableEntry.content);
			$scope.client_unedit_form = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
	var project_form={
			client : uneditableEntry.content,
			project_name :"",
			rate:"",
			acc_number:"",
			chambers:"",
			carts:"",
			startDate:"",
			endDate:"",
			lastBillDate:"",
			currentBill:"",
			billPaidTotal:"",
			billPay:"",
			accountStatus:"NEW PROJECT"
	};
	$scope.project_form = project_form;
	$scope.submit=function(){
		if($scope.project_form){
				$http.post("/project/",$scope.project_form)
				.success(function(response) {
					// remove this later
					// console.log("pass"+JSON.stringify(response));
				})
				.error(function(response) {
					console.log( "failure message: " + JSON.stringify(response));
				});
				$scope.project_form='';
		}
	}
});
billingApp.controller("usage_list_Controller", function($scope,$http){
	$scope.message="Tracking usage list";
	$scope.savedTrackingUsage=$http.get('/project')
		.then(function success(response) {
			$scope.projectList = response.data;
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.projectList = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
});
billingApp.controller("track_project_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.message="Tracking usage";
	$scope.id = $routeParams.id;
	$scope.editUsageForm=$http.get('/project/'+$scope.id)
		.then(function success(response) {
			$scope.usage_form = response.data;
			var start = $scope.usage_form.startDate.split('-');
			var end = $scope.usage_form.endDate.split('-');
			var lastBill = $scope.usage_form.lastBillDate.split('-');
			//var lastBillPaid = $scope.usage_form.lastBillPaidDate.split('-');
			$scope.usage_form.startDate = new Date(start[1]+"/"+start[2]+"/"+start[0]);
			$scope.usage_form.endDate = new Date(end[1]+"/"+end[2]+"/"+end[0]);
			$scope.usage_form.lastBillDate = new Date(lastBill[1]+"/"+lastBill[2]+"/"+lastBill[0]);
			//$scope.usage_form.lastBillPaidDate = new Date(lastBillPaid[1]+"/"+lastBillPaid[2]+"/"+lastBillPaid[0]);
			$scope.config = response.config;
			$scope.headers = response.headers;
			$scope.status = response.status;
			$scope.statusText = response.statusText;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
	});
	$scope.save=function(){
		$http.put("/project/"+$scope.id, $scope.usage_form)
		.success(function(usage_form, status, headers, config) {
			$scope.message = usage_form;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	}

	var generateBillDate = {
		date: ""
	};

	$scope.generateBillDate = generateBillDate;
	$scope.generate = function(){
		$http.post("/project/"+$scope.id+"/generatebill/",$scope.generateBillDate)
		.success(function(lastBill, status, headers, config) {
			$scope.usage_form.lastBillDate = $scope.generateBillDate.date;
			$scope.usage_form.currentBill = lastBill;
		})
		.error(function(data, status, headers, config) {
			console.log( "failure message: " + JSON.stringify({data: data}));
		});
	}


  // $scope.usage_form.billPaidTotal
	var pay_amount = 0;
	$scope.pay_amount = pay_amount;
	$scope.pay = function(){

		$http.post("/project/"+$scope.id+"/paybill", $scope.pay_amount)
		.success(function(pay_bill, status, headers, config) {
			$scope.usage_form.currentBill = pay_bill.remainingCurrentBill;
			$scope.usage_form.billPaidTotal = pay_bill.billPaidTotal;
			$scope.usage_form.lastBillPaidDate = pay_bill.lastBillPaidDate;
		})
		.error(function(response) {
			console.log( "failure message: " + response);
		});
	}
});
