/**
 * 
 */
app.factory('NotificationService',function($http){
	console.log('In notification service');
	var notificationService={}
	
	notificationService.getNotification=function(id){
		
		 var url="http://localhost:8086/middleware/notification/"+id 
		return $http.get(url,id)
	}
	
	notificationService.updateNotification=function(id){
		var url="http://localhost:8086/middleware/updatenotification/"+id
	     return $http.put(url,id)
	}
	
	notificationService.getNotificationNotViewed=function()
	{
		var url="http://localhost:8086/middleware/notifications"
		return $http.get(url)
	}
	
	return notificationService;
	
	
})