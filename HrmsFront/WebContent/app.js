/**
 * Angular js module
 */
//declare a variable
//variable refers to an angular js module
//parameter 1.name of the module
//parameter 2. array of dependent modules
var app=angular.module("app",['ngRoute','ngCookies'])

app.config(function($routeProvider,$locationProvider)
		{
	 
		$routeProvider
		.when('/' ,{templateUrl : 'views/home.html',controller : 'NotificationCtrl'})
		.when('/registration',{controller:'UserCtrl',templateUrl:'views/registration.html'})
		.when('/login',{controller:'UserCtrl',templateUrl:'views/login.html'})
		.when('/updateprofile',{controller:'UserCtrl',templateUrl:'views/updateprofile.html'})
		.when('/home',{templateUrl:'views/home.html',controller : 'NotificationCtrl'})
		/*.when('/addjob',{controller:'JobCtrl',templateUrl:'views/jobform.html'})
	.when('/getalljobs',{controller:'JobCtrl',templateUrl:'views/listofjobs.html'})*/
	.when('/addblogpost',{controller:'LeavesCtrl',templateUrl:'views/blogpostform.html'})
	.when('/getblogs',{controller:'LeavesCtrl',templateUrl:'views/listofblogsapproved.html'})
	.when('/getblog/:id',{controller:'LeavesInDetailCtrl',templateUrl:'views/blogindetail.html'})
	.when('/leaveswaitingforapproval',{controller:'LeavesCtrl',templateUrl:'views/listofblogswaitingforapproval.html'})
	.when('/getblogwaitingforapproval/:id',{controller:'LeavesInDetailCtrl',templateUrl:'views/blogapprovalform.html'})
	.when('/getnotification',{controller:'NotificationCtrl',templateUrl:'views/notificationdetails.html'})
	
	.when('/viewEvents', {
		templateUrl : 'views/viewEvents.html',
		controller : 'EventController'
	})
	
	.when('/manageEvents', {
		templateUrl : 'views/manageEvents.html',
		controller : 'EventController'
	}).when('/addEvents', {
		templateUrl : 'views/manageEvents.html',
		controller : 'EventController'
	})
	.when('/ered', {
		templateUrl : 'views/ered.html',
	})

	.when('/viewEvents', {
		templateUrl : 'views/viewEvents.html',
		controller : 'EventController'
	})
	
	.when('/getnotification/:id', {
		templateUrl : 'views/notificationdetails.html',
		controller : 'NotificationCtrl'
	}).when('/updatenotification/:id', {
		templateUrl : 'views/notificationdetails.html',
		controller : 'NotificationCtrl'
})
	/*.when('/suggestedusers',{controller:'FriendCtrl',templateUrl:'views/suggesteduserslist.html'})
	.when('/pendingrequests',{controller:'FriendCtrl',templateUrl:'views/pendingrequests.html'})
	.when('/listoffriends',{controller:'FriendCtrl',templateUrl:'views/friendslist.html'})
	.when('/uploadprofilepicture',{templateUrl : 'views/uploadprofilepicture.html'})
	.when('/chat',{
		templateUrl:'views/chat.html',
		controller:'ChatCtrl'
	})*/
.when('/uploadprofilepicture',{templateUrl : 'views/uploadprofilepicture.html'})

   .otherwise({templateUrl:'views/home.html',controller : 'NotificationCtrl'})
		})

		app.run(function($rootScope,$cookieStore,UserService,$location)
				{
					if($rootScope.user==undefined)
						$rootScope.user=$cookieStore.get('userDetails')
						
						$rootScope.logout=function(){
						alert('Entering the function logout')
						UserService.logout().then(function(response){
							delete $rootScope.user
							$cookieStore.remove('userDetails')
							$location.path('/login')
						},function(response){
							if($rootScope.user!=undefined)
								delete $rootScope.user
								$cookieStore.remove('userDetails')
								$location.path('/login')
						})
					}
				})
		