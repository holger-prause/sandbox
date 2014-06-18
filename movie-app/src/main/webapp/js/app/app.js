// declare a module
var movieAppModule = angular.module('movieApp', []);
var listUrl = "http://localhost:8080/movie-app/rest/movies/list";
var createUrl = "http://localhost:8080/movie-app/rest/movies/create";


movieAppModule.controller("MovieController", function($scope, $http) {
	$scope.refresh = function() {
		$http.get(listUrl).success(function(data) {
			$scope.movieList = data;
		});
	};
	
    $scope.reset = function() {
        // clear input fields
    	$scope.newMovie = {};
    };
	
    $scope.init = function() {
        $scope.reset();
    	$scope.refresh();
    }; 
    
    //init
    $scope.init();
    
    
	$scope.submit = function(item, event) {
		var dataObject = $scope.newMovie;
		var responsePromise = $http.post(createUrl, dataObject, {});
		responsePromise.success(function(dataFromServer, status, headers, config) 
		{
			$scope.init();
		});
		
		responsePromise.error(function(data, status, headers, config) {
			alert("Submitting form failed!");
		});
	};
});





