const SportObjects = { template: '<sportObjects></sportObjects>'}
const LoginAndRegistration = { template: '<loginAndRegistration></loginAndRegistration>'}
const Profile = { template: '<profile></profile>'}
const AllUsers = {template: '<allUsers></allUsers>'}
const SportObjectPage = {template: '<sportObjectPage></sportObjectPage>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'Početna', component: SportObjects},
		{ path: '/login', name:'login', component:LoginAndRegistration},
		{ path: '/profile', name:'profil', component: Profile},
		{ path: '/allUsers', name:'sviKorisnici', component: AllUsers},
		{ path: '/sportObjectPage', name:'stranicaSportskogObjekta', component: SportObjectPage}
	  ]
});

var app = new Vue({
	router,
	el: '#mainView',
	data: {
        loggedUser: {},
        userRole: "Neulogovan",
        	selectedSportObject: {}
    },
	mounted() {
        
    },
    methods: {
		getLoggedUser: function() {
			axios
			.get('rest/getLoggedUser')
			.then(response => {
				this.loggedUser = response.data;
				this.userRole = this.loggedUser.role;
				router.push('/');
			})
		},
        logout: function() {
            this.userRole = "Neulogovan";
            axios
            .get('rest/logout')
            .then(response => (router.push('/')));
        },
        setSelectedSportObject : function(sportObject) {
			this.setSelectedSportObject = sportObject;
		}
    }
});

function fixDate(users) {
	for (var u of users) {
		u.dateOfBirth = new Date(parseInt(u.dateOfBirth));
	}
	return users;
} 