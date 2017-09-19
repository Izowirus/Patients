app.controller('viewController', viewController);

function viewController($scope, $http, $mdDialog) {	
	$scope.orderBy = 'surname';
	$scope.currentPatient = null;
	$scope.buttonDisabled = true;
	$scope.currentPage = 1;
	$scope.order = function(value) {
		$scope.orderBy = value;
	}

	$scope.getPatients = function() {
		$http.get('/app/patients?size=10&page='+$scope.currentPage).then(function(response) {
			$scope.patients = response.data;
		}, null);
	}
	$scope.setPatient = function(id) {
		if(id==null || id==undefined){
			$scope.buttonDisabled=true;
			$scope.tests = null;
			$scope.currentPatient=null;
			return;
		}
		$http.get('/app/patients/' + id).then(function(response) {
			$scope.currentPatient = response.data;
			if ($scope.currentPatient==null ){
				$scope.buttonDisabled=true;
				$scope.test = null;
			}
			else {
				$scope.buttonDisabled=false
				var patientId = $scope.currentPatient.id;
				$http.get('/app/tests/patient/'+id).then(function(response){
					$scope.tests = response.data;
				});
			}
			
		}, null);
	}
	$scope.deletePatient = function(id) {
		$http.delete('/app/patients/' + id).then(function(response) {
			$scope.setPatient(null);
			$scope.getPatients();
		}, null);
	}
	$scope.nextPage = function(){
		$scope.currentPage +=1;
		$scope.getPatients();
	}
	$scope.previousPage = function(){
		$scope.currentPage -=1;
		if($scope.currentPage < 1) $scope.currentPage=0;
		$scope.getPatients();
	}
	$scope.goToFirstPage = function(){
		$scope.currentPage =1;
		$scope.getPatients();
	}	
	$scope.deleteTest = function(id) {
		$http.delete('/app/tests/' + id).then(function(response) {
			var patientId = $scope.currentPatient.id;
			$scope.setPatient(patientId);
		});

	}
	$scope.getPatients();
	
	// addPateintDialog
	$scope.addPatientDialog = function($event, editedPatient) {
		var parent = angular.element(document.body);
		var url = '/addPatientDialog';
		$mdDialog.show({
			parent : parent,
			targetEvent : $event,
			templateUrl : url,
			controller : addPatientsController,
			fullscreen : true,
			locals:{
				editedPatient : editedPatient
			}
		}).then(function(patient){
			if(patient==undefined || patient==null)var id = null;
			else var id = patient.id;
			$scope.setPatient(id);
			$scope.getPatients();			
		},null);		
	}
	// addTestDialog
	$scope.addTestDialog = function($event,id){
		var parent = angular.element(document.body);
		var url = '/addTestDialog';
		$mdDialog.show({
			parent : parent,
			targetEvent : $event,
			templateUrl : url,
			controller : addTestController,
			fullscreen : true,
			locals:{
				patientId:id
			}
		}).then(function(){
			$scope.setPatient($scope.currentPatient.id);
			$scope.getPatients();	
		});	
	}
		// editTestDialog
	$scope.editTestDialog = function($event,id){
		var parent = angular.element(document.body);
		var url = '/editTestDialog';
		$mdDialog.show({
			parent : parent,
			targetEvent : $event,
			templateUrl : url,
			controller : editTestController,
			fullscreen : true,
			locals:{
				testId:id
			}
		}).then(function(){
			$scope.setPatient($scope.currentPatient.id);
			$scope.getPatients();	
		});		
	}
	// testDialog
	$scope.testDialog = function($event, id){
		var parent = angular.element(document.body);
		var url = '/testDialog';
		$mdDialog.show({
			parent : parent,
			targetEvent : $event,
			templateUrl : url,
			controller : testController,
			fullscreen : true,
			locals:{
				testId:id
			}
		});
	}
	
}

function testController($scope,$mdDialog,$http,testId){
	$scope.closeDialog = function() {
		$mdDialog.hide();
	}
	$scope.test=null;
	function init(){
		$http.get('/app/tests/'+testId).then(function(response){
			$scope.test = response.data; 
			console.log($scope.test);
			patient($scope.test.patientId);
			var url = 'files/'+$scope.test.pathToImg;
			var numberOfImage = $scope.test.numberOfFiles;
			startDwv(url, numberOfImage);		
	})};
	function patient(id){
		$http.get('app/patients/'+id).then(function (response){
			$scope.patient = response.data;
	})};
	init();
	
}

function startDwv(url, numberOfImage) {
    /// / main application
  var myapp = new dwv.App();
    console.log(numberOfImage);
    /// / initialise the application
  var urls =[];
    for(i=0;i<parseInt(numberOfImage);i++){
    	urls.push(url+'-'+i);
    }
    console.log(urls)
	myapp.loadURLs(urls,[])
    myapp.init({
        "containerDivId": "dwv",
        "fitToWindow": true,
        "gui": ["tool"],
        "tools": ["Scroll", "ZoomAndPan", "WindowLevel"],
        "isMobile": false
    });

    dwv.gui.appendResetHtml(myapp);
}		

function addPatientsController($scope, $mdDialog, $http, editedPatient) {
	$scope.patient=editedPatient;
	$scope.closeDialog = function() {
		$mdDialog.hide();
	}
	$scope.addPatient = function() {
		$http.post('/app/patients',$scope.patient).then(function(response){
			$mdDialog.hide(response.data);
		});
	}
}

function addTestController($scope,$mdDialog,$http,Upload,patientId){
	$scope.date = null;
	$scope.test = {
		id:null,
		patientId: patientId,
		testDate: null,
		shortDescription: null,
		description: null,	
		numberOfFiles:null,
		pathToImg:null
	}
	$scope.files = null;
	$scope.uploadFiles = function($files){
		$scope.files=$files;
	}
	$scope.closeDialog = function() {
		$mdDialog.hide();
	}
	$scope.addTest = function(){
		$scope.test.testDate = $scope.date.toISOString().split("T")[0];
		$scope.test.numberOfFiles = $scope.files.length;
		var url = '/app/tests';
		$http.post(url,$scope.test).then(function(response){
			$scope.files.forEach(function(item, index){
				var url = '/files/'+response.data.pathToImg+'-'+index;
				Upload.upload({
					url:url,
					data:{
						files:item
					}
				});
			});
			$mdDialog.hide();
		});		
	}
}

function editTestController($scope,$mdDialog,$http,testId){
	$http.get('/app/tests/'+testId).then(function(response){
		$scope.test=response.data;
	});
	$scope.closeDialog = function(){
		$mdDialog.hide();
	}
	$scope.editTest = function(){
		var url = '/app/tests';
		$http.put(url,$scope.test).then(function(response){
			$mdDialog.hide();
		});		
	}

}


dwv.browser.check();
dwv.i18nInitialise();

