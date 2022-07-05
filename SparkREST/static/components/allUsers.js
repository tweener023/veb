Vue.component("allUsers", {
	data: function () {
		    return {
			  allUsers: {}
		    }
	},
	template: ` 
<div>
    <h3>Prikaz svih korisnika sistema</h3>
    <table border="1">
        <tr bgcolor="lightgrey">
            <th>Korisničko ime</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Pol</th>
            <th>Datum rođenja</th>
            <th>Uloga</th>
        </tr>
        <tr v-for="user in allUsers">
            <td>{{user.username}}</td>
            <td>{{user.name}}</td>
            <td>{{user.surname}}</td>
            <td>{{user.gender}}</td>
            <td>{{user.dateOfBirth}}</td>
            <td>{{user.role}}</td>
        </tr>
    </table>
</div>
`
	, 
	mounted () {
		this.getLoggedUser();
        this.getAllUsers();
    },
	methods: {
		getLoggedUser : function() {
			axios
			.get('rest/getLoggedUser')
			.then(response => {
				let user = response.data;
                if (user != null){
                    if (user.role != 'administrator'){
                        router.push('/');
                    }
                }
			})
            .catch(function(error){
                router.push('/');
            })
		},
        getAllUsers : function() {
            axios
            .get('rest/users')
            .then(response => {
                this.allUsers = response.data;
            })
        }
	},
	components: {
		//vuejsDatepicker
	}
}); 