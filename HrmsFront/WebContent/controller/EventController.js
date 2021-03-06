app.controller('EventController', function($scope, $location, EventService)
{
	console.log("Entering Event Controller")
	$scope.events;
	$scope.event;
	$scope.message;
	
	
	listEvents = function()
	{
		console.log("Entering List all events")
		EventService.listEvents()
		.then
		(
				function(response)
				{
					console.log("List Blog Success")
					$scope.events = response.data;
				}
		)
	}
	listEvents();
	
	
	getEvents = function()
	{
		console.log("Entering Get Events")
		EventService.listEvents()
		.then
		(
			function(response)
			{
				console.log("Events Recieved")
				$scope.events=response.data
			}
		)
	}
	getEvents();
	
	$scope.removeEvent = function(id)
	{
		console.log("Removing Event")
		AdminService.removeEvent(id)
		.then(
			function(response)
			{
				console.log("Event Removed")
				$location.path("/ered")
			}
		)
	}
	
	
	
	$scope.addEvents=function()
	{
		console.log("Add Event Started")
		EventService.addEvent($scope.event)
		.then
		(
				function(response)
				{
					console.log("Add Event Success "+response.status)
					alert("Event has been added")
					$location.path("/home")
				}
		);
	}
})