'use strict';
/**
 * This controller shows all the available clients.
 */
billingApp.controller("client_list_Controller", function($rootScope,$scope,$http,$log){
	// This is the selectedClient from the Client list page. The selectedClient is then bound to a radio button which is kept disabled in the html till a client is selected.
	$scope.selectedClient="";
	/**
	 * This get request fetches all the clients.
	 */
	$scope.savedClientInfo=$http.get('/client')
	.then(function success(response) {
		$scope.ClientList = response.data;
	},function failure(response){
		$scope.ClientList = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
});
/**
 * This controller allows for editing the existing client information.
 */
billingApp.controller("edit_client_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.id = $routeParams.id;
	$scope.options = ["Active", "Inactive"];
	/**
	 * Gets the client information for the selected client.
	 */
	$scope.editClientInfo=$http.get('/client/'+$scope.id)
	.then(function success(response) {
		$scope.client_form = response.data;
	},function failure(response){
		$log.info(response);
	});
	/**
	 * ['Puts' the edited client form.]
	 * @return {[type]} [the submitted form plus status fields]
	 */

	$scope.submit=function(){
		$http.put("/client/"+$scope.id,$scope.client_form)
		.success(function(client_form, status, headers, config) {
			$scope.message = "Client "+ client_form.client_first_name + " Updated Successfully";
			$scope.savedSuccessfully = true; // show success message
			$scope.showAlertMessage = true;
		})
		.error(function(data, status, headers, config) {
			$scope.message = "Couldn't edit the client. Please verify edited information.";
			$scope.savedSuccessfully = false; // show failure message
			$scope.showAlertMessage = true;
		});
	}
	/**
	 * Fetches all the departments to display in the drop down.
	 * @type {[type]}
	 */
	$scope.savedDepartments=$http.get('/department/')
	.then(function success(response) {
		$scope.departments = response.data;
	},function failure(response){
		$scope.departments = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
});
/**
 * Adds a new client
 * @param  {[type]} $scope
 * @param  {[type]} $http
 * @return {[type]}
 */

billingApp.controller("add_client_info_Controller", function($scope,$http){
	var initializeClient = function(){
		$scope.options = ["Active", "Inactive"];
		return {
				client_first_name:"", client_last_name:"", client_email:"", client_phone:"", dept_name:"", client_status: $scope.options[0], client_address:""
		};
	}
	$scope.client_form= initializeClient();

	$scope.submit=function(){
		if($scope.client_form){
			$http.post("/client/",$scope.client_form)
			.success(function(response) {
				console.log(response);
				$scope.message = "Client " +response.client_first_name +" Added Successfully";
				$scope.savedSuccessfully = true; // show success message
				$scope.showAlertMessage = true;
				$scope.client_form=initializeClient();
			})
			.error(function(response) {
				$scope.message = "Client adding unsuccessful. Please verify information.";
				$scope.savedSuccessfully = false; // show success message
				$scope.showAlertMessage = true;
				//console.log( "failure message: " + JSON.stringify(response));
			});
		}
	}

	$scope.savedDepartments=$http.get('/department/')
	.then(function success(response) {
		$scope.departments = response.data;
	},function failure(response){
		$scope.departments = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
});
billingApp.controller("chamber_list_Controller", function($rootScope,$scope,$http,$log){
	$scope.savedChamberInfo=$http.get('/chamber/')
	.then(function success(response) {
		$scope.chamberList = response.data;
	},function failure(response){
		$scope.chamberList = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
});
billingApp.controller("add_chamber_info_Controller", function($scope,$http){
	var initializeChamber = function(){
			return{
				chamberName:"", chamberCarts:""
			}
	};
	$scope.chamber_form = initializeChamber();
	//$scope.list=[];		// EMpty list to show data on page. TEsting purposes
	$scope.submit=function(){
		//$scope.list.push(this.chamber_form);
		if($scope.chamber_form){
			$http.post("/chamber/",$scope.chamber_form)
			.success(function(response) {
				$scope.message = "Chamber "+ response.chamberName + " Added Successfully";
				$scope.savedSuccessfully = true; // show success message
				$scope.showAlertMessage = true;
				$scope.chamber_form = initializeChamber();
				// remove this later
				// console.log("pass"+JSON.stringify(response));
			})
			.error(function(response) {
				$scope.message = "Couldn't add this chamber.";
				$scope.savedSuccessfully = false; // show failure message
				$scope.showAlertMessage = true;
			});
		}
	}
});
billingApp.controller("edit_chamber_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.id = $routeParams.id;
	$scope.editChamberInfo=$http.get('/chamber/'+$scope.id)
	.then(function success(response) {
		$scope.chamber_form = response.data;
	},function failure(response){
		$scope.selectedInfo = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.submit=function(){

		$http.post("/chamber/",$scope.chamber_form)
		.success(function(chamber_form, status, headers, config) {
			$scope.message = "Chamber Edited Successfully";
			$scope.savedSuccessfully = true; // show success message
			$scope.showAlertMessage = true;
		})
		.error(function(data, status, headers, config) {
			$scope.message = "Couldn't edit this chamber";
			$scope.savedSuccessfully = false; // show failure message
			$scope.showAlertMessage = true;
		});
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
	//sort function
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
});
billingApp.controller("add_department_info_Controller", function($scope,$http){
	$scope.message="Add Department Info";
	var initializeDepartment = function(){
		return {
			departmentId:"", departmentName:""
		}
	}
	$scope.department_form = initializeDepartment();
	$scope.submit=function(){
		if($scope.department_form){
			$http.post("/department",$scope.department_form)
			.success(function(response) {
				$scope.message = "Department "+ response.departmentName + " Added Successfully";
				$scope.savedSuccessfully = true; // show success message
				$scope.showAlertMessage = true;
				$scope.department_form = initializeDepartment();
				// remove this later
				// console.log("pass"+JSON.stringify(response));
			})
			.error(function(response) {
				$scope.message = "Department Failed to add";
				$scope.savedSuccessfully = false; // show failure message
				$scope.showAlertMessage = true;
			});
		}
	}
});
billingApp.controller("edit_department_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
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
			$scope.message = "Department "+ department_form.departmentName + " edited Successfully";
			$scope.savedSuccessfully = true; // show success message
			$scope.showAlertMessage = true;
		})
		.error(function(data, status, headers, config) {
			$scope.message = "Department "+ department_form.departmentName + " failed to edit";
			$scope.savedSuccessfully = false; // show success message
			$scope.showAlertMessage = true;
			//alert( "failure message: " + JSON.stringify({data: data}));
		});
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
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
});
billingApp.controller("add_crop_info_Controller", function($scope,$http){
	var initializeCrop = function(){
		return {
			cropCommonName:"", cropScientificName:""
		}
	}
	$scope.crop_form = initializeCrop();
	$scope.submit=function(){
		if($scope.crop_form){
			$http.post("/crop/",$scope.crop_form)
			.success(function(response) {
				$scope.message = "Crop "+ response.cropCommonName + " Added Successfully";
				$scope.savedSuccessfully = true; // show success message
				$scope.showAlertMessage = true;
				$scope.crop_form = initializeCrop();
			})
			.error(function(response) {
				$scope.message = "Crop Failed to add";
				$scope.savedSuccessfully = false; // show failure message
				$scope.showAlertMessage = true;
			});
		}
	}
});
billingApp.controller("edit_crop_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
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

		$http.put("/crop/"+$scope.id,$scope.crop_form)
		.success(function(crop_form, status, headers, config) {
			$scope.message = "Crop "+ crop_form.cropCommonName + " Edited Successfully";
			$scope.savedSuccessfully = true; // show success message
			$scope.showAlertMessage = true;
		})
		.error(function(crop_form, status, headers, config) {
			$scope.message = "Crop "+ crop_form.cropCommonName + " failed to edit";
			$scope.savedSuccessfully = failure; // show failure message
			$scope.showAlertMessage = true;
			//alert( "failure message: " + JSON.stringify({data: data}));
		});
	}
});

/**
 * This controller starts a new project for the user selected client.
 */
billingApp.controller("start_project_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	var currentClient = null;
	var initializeProject = function() {
		return {
			client : null, project_id : "", project_Title :"", rateValue:"", acc_number:"", chambers: [], carts:0, startDate:"", endDate:"", lastBillDate:"", currentBill:"", billPaidTotal:"", accountStatus:""
		};
	}
	// Saves all information regarding a new project for a selected client.
	$scope.project_form = initializeProject();
	// Get the selected client from the routeParams.
	$scope.id = $routeParams.id;
	// To remove the selected chamber from the list and display it in the table.
	$scope.selectedChamber = undefined;

	/**
	 * 'Get' the client details for the selected client by id.
	 */
	$http.get('/client/'+$scope.id)
	.then(function success(response) {
		if(response.status == 200) {
			$scope.project_form.client = response.data;
			currentClient = response.data;
		} else {
			$log.error(response.statusText);
		}
	},function failure(response){
		$log.error(response.statusText);
	});

	/**
	 * Submit Functionality.
	 */
	$scope.submit=function(){
		if($scope.project_form){
			console.log("before s: ",$scope.project_form.startDate);
			console.log("before e: ",$scope.project_form.endDate);
			console.log("before e: ",$scope.startDate);
			console.log("before e: ",$scope.endDate);
    //			var start=new Date($scope.project_form.startDate);
    //			var end=new Date($scope.project_form.endDate);
			$scope.project_form.startDate=new Date($scope.startDate);;
			$scope.project_form.endDate=new Date($scope.endDate);;
			console.log($scope.project_form.startDate);
			console.log($scope.project_form.endDate);
			$http.post("/project/",$scope.project_form)
			.success(function(response) {
				$scope.message = "Project "+ response.project_Title + " Saved Successfully";
				$scope.savedSuccessfully = true; // show success message
				$scope.showAlertMessage = true;
				$scope.project_form = initializeProject(); // Empty the form to be able to start a new project.
				$scope.project_form.client = currentClient; // Show the uneditableEntry fields.
			})
			.error(function(response) {
				$scope.message = "Couldn't start new project";
				$scope.savedSuccessfully = false; // show failure message
				$scope.showAlertMessage = true;
			});
		} else {
			// TODO
		}
	}
	$(document).ready(function(){
		var date_input=$('input[name="date"]'); //our date input has the name "date"
		var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
		var options={
				format: 'D M dd yyyy',
				/*  format: {

				 * Say our UI should display a week ahead,
				 * but textbox should store the actual date.
				 * This is useful if we need UI to select local dates,
				 * but store in UTC
	    		        toDisplay: function (date, format, language) {
	    		            var d = new Date(date);
      //	    		            d.setDate(d.getDate() - 7);
      //	    		            return d.toISOString();
	    		            return d.toISOString();
      //	    		            return d.toDateString();
	    		        },
	    		        toValue: function (date, format, language) {
	    		            var d = new Date(date);
      //	    		            d.setDate(d.getDate() - 7);
	    		            return d.toDateString();
      //	    		            return new Date(d);
	    		        }
	    		    }	, */
	        container: container,
	        todayHighlight: true,
	        autoclose: true,
	      };
	      date_input.datepicker(options);
	    })
	/**
	 * Get All chambers from the chambers page and display in the drop down
	 */
	$scope.savedChambers=$http.get('/chamber/')
	.then(function success(response) {
		$scope.chambers = response.data;
	},function failure(response){
		$scope.chambers = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.savedRates=$http.get('/rate')
	.then(function success(response) {
		$scope.rates = response.data;
		if($scope.rates!=null){
			$scope.project_form.rateValue = $scope.rates[0];
		}
	},function failure(response){
		$scope.chambers = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});

	/**
	 * Adds rows to the selected chamber table, removing chamber from the drop down and adding it to the table.
	 */
	$scope.addChamberRow = function(){
		for(var i = 0; i < $scope.chambers.length; i++){
			if($scope.selectedChamber === $scope.chambers[i]){
				$scope.chambers.splice(i,1);
			}
		}
		// Display the total sum of the carts in each chamber in the finalised chambers.
		$scope.project_form.carts = parseInt($scope.project_form.carts) + parseInt($scope.selectedChamber.chamberCarts);
		// Save the selected chamber id in the chamberId variable to send to back-end
		$scope.project_form.chambers.push($scope.selectedChamber);
	};

	/**
	 * Removes rows from the table, and adds them back to the drop down.
	 */
	$scope.removeRow = function ($index) {
		$scope.project_form.carts = parseInt($scope.project_form.carts) - parseInt($scope.project_form.chambers[$index].chamberCarts);
		// adding the removed chamber back to the list.
		$scope.chambers.push($scope.project_form.chambers[$index]);
		// Removing the chamber from the final chamber ids to be sent.
		$scope.project_form.chambers.splice($index, 1);
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
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
});
billingApp.controller("track_project_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.selectedChamber = undefined;
	$scope.id = $routeParams.id;
	// Create a string value for the existing rate. created a dictionary with string, rateObject pair. In the list of rates, we are showing
	// the strings. Whenever we select a new string from the select rate option, we call ng-change, which will look for the key value pair and
	// update $scope.usage_form.rateValue with the value of the selected(changed) rate string.
	$scope.currentRateValue = "";
	// http.get().then(function success(response){}, function failure(response){});

	// To make a part of the track project page uneditable when the user submits a end project request.
	$scope.projectEnded = false;

	$scope.paymentPending = true;
	function check(callback){
		$http.get('/project/'+$scope.id)
			.then(function success(response) {
				callback();
				$scope.usage_form = response.data;
				if($scope.usage_form.projectStatus === "Payment Pending"){
					$scope.projectEnded = true;
				}
				if($scope.usage_form.projectStatus === "Completed"){
					$scope.projectEnded = true;
					$scope.paymentPending = false;
				}
				// console.log("start date",$scope.usage_form.startDate);
				// console.log("end date",$scope.usage_form.endDate);
				// console.log("lastBillDate",$scope.usage_form.lastBillDate);
				// setting the input ratetype into currentRateValue. Will use this as ng-model to show the current selected.
				$scope.currentRateValue = $scope.usage_form.rateValue.rateType;
				// console.log("start date",$scope.usage_form.startDate);
				var tempStartDate=$scope.usage_form.startDate+"T00:00:00";
				var tempStart=new Date(tempStartDate);
				$scope.startDate = tempStart.toDateString();
				$scope.endDate = (new Date($scope.usage_form.endDate+"T00:00:00")).toDateString();
		/*		console.log(typeof $scope.usage_form.startDate);
				console.log(tempStart);
				console.log(tempStart.toDateString())*/;

				/*if($scope.usage_form.startDate != null){
						var start = $scope.usage_form.startDate.split('-');
						$scope.usage_form.startDate = new Date(start[1]+"/"+start[2]+"/"+start[0]);
					}
				if($scope.usage_form.endDate != null){
					var end = $scope.usage_form.endDate.split('-');
					$scope.usage_form.endDate = new Date(end[1]+"/"+end[2]+"/"+end[0]);
				}
		*/

				/*if($scope.usage_form.lastBillDate != null){
					var lastBill = $scope.usage_form.lastBillDate.split('-');
					$scope.usage_form.lastBillDate = new Date(lastBill[1]+"/"+lastBill[2]+"/"+lastBill[0]);
				}*/
				//var lastBillPaid = $scope.usage_form.lastBillPaidDate.split('-');
				//$scope.usage_form.lastBillPaidDate = new Date(lastBillPaid[1]+"/"+lastBillPaid[2]+"/"+lastBillPaid[0]);
			},function failure(response){
				$scope.selectedInfo = response.statusText;
				$scope.status = response.data;
				$log.info(response);
		});
	}

	/**
	 * This is to provide a custom date picker
	 */
	$(document).ready(function(){
		var date_input=$('input[name="date"]'); //our date input has the name "date"
		var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
		var options={
				format: 'D M dd yyyy',
				container: container,
				todayHighlight: true,
				autoclose: true,
		};
		date_input.datepicker(options);
	})
	/**
	 * This get request will fetch all rates, and create a dictionary of it. (rateType as String, the object associated with it)
	 */
	$scope.savedRates=$http.get('/rate')
	.then(function success(response) {
		var rateObjArray = response.data;
		$scope.rates = {};
		for( var i in rateObjArray) {
			$scope.rates[rateObjArray[i]['rateType']] = rateObjArray[i];
		}
	},function failure(response){
		$scope.rates = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	/**
	 * This is the ng-change method. This binds the usage_form.rateValue with the select string. We find the associated object in the dictionary and
	 * bind it to usage_form.rateValue.
	 */
	$scope.changeRateValue = function() {
		$scope.usage_form.rateValue = $scope.rates[$scope.currentRateValue];
	};
	check(function(){
		$http.get('/chamber/')
			.then(function success(response) {
				var chamberObjArray = response.data;

				//
				// // Return chambers that are in $scope.chambers but not in usage_form
				// if($scope.chambers!=null && $scope.usage_form.chambers!= null){
				// 	$scope.existingChambers = $scope.chambers.filter(function(obj) {
				// 	    return !$scope.usage_form.chambers.some(function(obj2) {
				// 	        return obj.chamberName == obj2.chamberName;
				// 	    });
				// 	});
				// }

				var existingChams = [];
				$scope.existingChambers = {};
				// Return chambers that are in $scope.chambers but not in usage_form
				if(chamberObjArray!=null && $scope.usage_form != null && $scope.usage_form.chambers!= null){
					existingChams = chamberObjArray.filter(function(obj) {
					    return !$scope.usage_form.chambers.some(function(obj2) {
					        return obj.chamberName == obj2.chamberName;
					    });
					});
				}
				for(var i in existingChams){
					$scope.existingChambers[existingChams[i]['chamberName']] = existingChams[i];
				}
			},function failure(response){
				$scope.chambers = response.statusText;
				$scope.status = response.data;
				$log.info(response);
		});
	});
	// $scope.savedChambers=$http.get('/chamber/')
	// 	.then(function success(response) {
	// 		var chamberObjArray = response.data;
	//
	// 		//
	// 		// // Return chambers that are in $scope.chambers but not in usage_form
	// 		// if($scope.chambers!=null && $scope.usage_form.chambers!= null){
	// 		// 	$scope.existingChambers = $scope.chambers.filter(function(obj) {
	// 		// 	    return !$scope.usage_form.chambers.some(function(obj2) {
	// 		// 	        return obj.chamberName == obj2.chamberName;
	// 		// 	    });
	// 		// 	});
	// 		// }
	//
	// 		var existingChams = [];
	// 		$scope.existingChambers = {};
	// 		// Return chambers that are in $scope.chambers but not in usage_form
	// 		if(chamberObjArray!=null && $scope.usage_form != null && $scope.usage_form.chambers!= null){
	// 			existingChams = chamberObjArray.filter(function(obj) {
	// 			    return !$scope.usage_form.chambers.some(function(obj2) {
	// 			        return obj.chamberName == obj2.chamberName;
	// 			    });
	// 			});
	// 			console.log(existingChams);
	// 		}
	// 		for( var i in existingChams){
	// 			$scope.existingChambers[existingChams[i]['chamberName']] = existingChams[i];
	// 		}
	// 	},function failure(response){
	// 		$scope.chambers = response.statusText;
	// 		$scope.status = response.data;
	// 		$log.info(response);
	// });
	$scope.addChamberRow = function(){
		// for(var i = 0; i < $scope.existingChambers.length; i++){
		// 	if($scope.selectedChamber === $scope.existingChambers[i]){
		// 		$scope.existingChambers.splice(i,1);
		// 	}
		// }
		for(var key in $scope.existingChambers){
			if($scope.selectedChamber === $scope.existingChambers[key]['chamberName']){
				// Display the total sum of the carts in each chamber in the finalised chambers.
				$scope.usage_form.carts = parseInt($scope.usage_form.carts) + parseInt($scope.existingChambers[key].chamberCarts);
				// Save the selected chamber id in the chamberId variable to send to back-end
				$scope.usage_form.chambers.push($scope.existingChambers[key]);

				delete $scope.existingChambers[key];
			}
		}
	};

	/**
	 * Removes rows from the table, and adds them back to the drop down.
	 */
	$scope.removeRow = function ($index) {
		$scope.usage_form.carts = parseInt($scope.usage_form.carts) - parseInt($scope.usage_form.chambers[$index].chamberCarts);
		// adding the removed chamber back to the list.
		//$scope.existingChambers.push($scope.usage_form.chambers[$index]);
		$scope.existingChambers[$scope.usage_form.chambers[$index].chamberName] = $scope.usage_form.chambers[$index];
		// Removing the chamber from the final chamber ids to be sent.
		$scope.usage_form.chambers.splice($index, 1);
	}

	$scope.save=function(){
		//		var start=new Date($scope.project_form.startDate);
		//		var end=new Date($scope.project_form.endDate);
		$scope.usage_form.startDate=new Date($scope.startDate);;
		$scope.usage_form.endDate=new Date($scope.endDate);;
		$http.put("/project/"+$scope.id, $scope.usage_form)
		.success(function(usage_form, status, headers, config) {
			$scope.message = "Project Updated Successfully";
			$scope.savedSuccessfully = true; // show success message
			$scope.showAlertMessage = true;
			$scope.message = usage_form;
		})
		.error(function(data, status, headers, config) {
			$scope.message = "Error Updating Project";
			$scope.savedSuccessfully = false; // show failure message
			$scope.showAlertMessage = true;
			//alert( "failure message: " + JSON.stringify({data: data}));
		});
	}

	/*var generateBillDate = {
			date: ""
	};

	$scope.generateBillDate = generateBillDate;*/
	$scope.generate = function(){
		console.log("$scope.generateBillDate.date",$scope.generateBillDate);
		console.log(typeof $scope.generateBillDate);
		//		var generateDate = $scope.generateBillDate.date; // 2013-07-30 17:11:00
		var generateDate = new Date($scope.generateBillDate);
		console.log("generateDate :",generateDate)
		//		generateDate=new Date($scope.generateDate);;
		var genrateEndOfDayDate = 	new Date(generateDate.getFullYear()
                ,generateDate.getMonth()
                ,generateDate.getDate()
                ,10,0,0);
		console.log(genrateEndOfDayDate)
		//		generateBillDate.date=genrateEndOfDayDate;
		$http.post("/project/"+$scope.id+"/generatebill/",genrateEndOfDayDate)
		.success(function(lastBill, status, headers, config) {
			$scope.usage_form.lastBillDate = genrateEndOfDayDate.toDateString();
			$scope.usage_form.currentBill = lastBill;
		})
		.error(function(data, status, headers, config) {
			console.log( "failure message: " + JSON.stringify({data: data}));
		});
	}
	/**
	 * Sets the current project id to a variable. This variable is used to track the project payment history.
	 */
	$scope.currentProject = function(){
		$scope.currentProjectID = $scope.id;
		//$scope.usage_form = project_service.project_info;
	}

	// $scope.usage_form.billPaidTotal
	var pay_amount = 0;
	$scope.pay_amount = pay_amount;
	$scope.pay = function(){

		$http.post("/payment/"+$scope.id+"/paybill", $scope.pay_amount)
		.success(function(pay_bill, status, headers, config) {
			$scope.usage_form.currentBill = pay_bill.remainingCurrentBill;
			console.log("outside: "+$scope.usage_form.currentBill);
			console.log($scope.usage_form.currentBill === 0);
			if($scope.usage_form.currentBill === 0){
				console.log($scope.usage_form.currentBill);

				$scope.paymentPending = false;
			}
			$scope.usage_form.billPaidTotal = pay_bill.billPaidTotal;
			$scope.usage_form.lastBillPaidDate = pay_bill.lastBillPaidDate;
		})
		.error(function(response) {
			console.log( "failure message: " + response);
		});
	}

	$scope.endProject = function(){
		console.log('/project/'+$scope.id+'/endProject')
		$http.get('/project/'+$scope.id+'/endProject')
		.then(function success(response) {
			console.log(response.data)
			$scope.usage_form = response.data;
			$scope.projectEnded = true;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
		});
	}

	$scope.finishProject = function(){
		$http.get('/project/'+$scope.id+'/finishProject')
		.then(function success(response) {
			console.log(response.data)
			$scope.usage_form = response.data;
			$scope.projectEnded = true;
		},function failure(response){
			$scope.selectedInfo = response.statusText;
			$scope.status = response.data;
			$log.info(response);
		});
	}
});
billingApp.controller("payment_list_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.id = $routeParams.id;
	$scope.paymentList=$http.get('/payment/'+$scope.id)
	.then(function success(response) {
		$scope.paymentHistory = response.data;
		// var payDate = $scope.paymentHistory.paidDate.split('-');
		// $scope.paymentHistory.paidDate = new Date(payDate[1]+"/"+payDate[2]+"/"+payDate[0]);
	},function failure(response){
		$scope.selectedInfo = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});

	$scope.projectDetails=$http.get('/project/'+$scope.id)
	.then(function success(response) {
		$scope.project_details = response.data;
		$scope.config = response.config;
		$scope.headers = response.headers;
		$scope.status = response.status;
		$scope.statusText = response.statusText;
	},function failure(response){
		$scope.selectedInfo = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	$scope.printPage = function () {
	//  var printContents = document.getElementById("print").innerHTML;
	//  var mywindow = window.open('', 'PRINT', 'height=400,width=600');
	//  mywindow.document.open();
	//  mywindow.document.write('<html><head><link rel="stylesheet" href="css/main.css" media="all" type="text/css" /><link rel="stylesheet" href="css/bootstrap_cdn.css" media="all" type="text/css" /><title>' + document.title  + '</title>');
	//  mywindow.document.write('</head><body >');
	//  mywindow.document.write(printContents);
	//  mywindow.document.write('</body></html>');
	 //
	//  mywindow.document.close(); // necessary for IE >= 10
	//  mywindow.focus(); // necessary for IE >= 10*/
	 //
	//  mywindow.print();
	//  mywindow.close();

		var printContents = document.getElementById("print").innerHTML;
	  var popupWin = window.open('', '_blank');
	  popupWin.document.open();
	  popupWin.document.write('<html><head><link rel="stylesheet" href="css/main.css" media="all" type="text/css" /><link rel="stylesheet" href="css/bootstrap_cdn.css" media="all" type="text/css" /></head><body onload="window.print()">' + printContents + '</body></html>');
	  popupWin.document.close();
	}
});


billingApp.controller("rate_list_Controller", function($rootScope,$scope,$http,$log){
	$scope.savedRateInfo=$http.get('/rate/')
	.then(function success(response) {
		$scope.rateList = response.data;
	},function failure(response){
		$scope.rateList = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
});
billingApp.controller("add_rate_info_Controller", function($scope,$http){
	var initializeRate = function(){
		return{
			rateType:"", rate:0
		}
	}
	$scope.rate_form = initializeRate();
	$scope.submit=function(){
		if($scope.rate_form){
			$http.post("/rate/",$scope.rate_form)
			.success(function(response) {
				$scope.message = "Rate "+ response.rateType + " Added Successfully";
				$scope.savedSuccessfully = true; // show success message
				$scope.showAlertMessage = true;
				$scope.rate_form = initializeRate();
				// remove this later
				// console.log("pass"+JSON.stringify(response));
			})
			.error(function(response) {
				$scope.message = "Rate Failed to Add";
				$scope.savedSuccessfully = false; // show failure message
				$scope.showAlertMessage = true;
			});
		}
	}
});
billingApp.controller("edit_rate_info_Controller", function($rootScope,$scope,$http, $log, $routeParams){
	$scope.id = $routeParams.id;
	$scope.editRateInfo=$http.get('/rate/'+$scope.id)
	.then(function success(response) {
		$scope.rate_form = response.data;
	},function failure(response){
		$scope.selectedInfo = response.statusText;
		$scope.status = response.data;
		$log.info(response);
	});
	$scope.submit=function(){

		$http.put("/rate/"+$scope.id,$scope.rate_form)
		.success(function(rate_form, status, headers, config) {
			$scope.message = "Rate "+ rate_form.rateType + " Edited Successfully";
			$scope.savedSuccessfully = true; // show success message
			$scope.showAlertMessage = true;
		})
		.error(function(data, status, headers, config) {
			$scope.message = "Rate Failed to edit";
			$scope.savedSuccessfully = false; // show failure message
			$scope.showAlertMessage = true;
		});
	}
});
