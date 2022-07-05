Vue.component("sportObjectPage", {
    data: function() {
        return {
            sportObject : {},
                loggedUser : {},
                mode : 'SEARCH',
                workoutForChange : {},
                newWorkout : {
                    "name": "",
                    "type": "",
                    "sportObject": {},
                    "length": "",
                    "trainer": {},
                    "description":  "" 
                }
        }
    },
    template: `
    <div style="margin: 10px;">
        <div v-if="mode === 'SEARCH'">
            <table>
                <tr>
                    
                </tr>
            </table>

            <div>
                <div style="max-width: 50%; margin: 10px;">
                    <table border="1px;">
                        <th>Naziv treninga</th><th>Tip</th><th>Duzina trajanja(min)</th><th>Opis</th>
                        <tr v-for="workout in sportObject.content">
                            <td>{{workout.name}}</td>
                            <td>{{workout.type}}</td>
                            <td>{{workout.length}}</td>
                            <td>{{workout.description}}</td>
							<td v-if="loggedUser.role === 'menadzer'"><button @click="changeWorkout(workout)">Izmeni trening</button></td>
							<td v-if="loggedUser.role === 'menadzer'"><button @click="deleteWorkout(workout)">Obrisi trening</button></td>
                        </tr>        
                    </table>
                </div>
            </div>
        </div>

        <div v-if="mode === 'UPDATESPORTOBJECT'">
            <table>
                <tr>
                    <td><button @click="changeModeFromSportObjectUpdate()">Prikaz treninga</button></td>
                </tr>
            </table>

            <form @submit="updateSportObject">
                <table>
                    <tr><td>Ime sportskog objekta</td><td><input type="text" v-model="sportObject.name"></td></tr>
                    <tr>
                        <td>Tip sportskog objekta</td>
                        <td><select v-model="sportObject.type">
                                <option value="teretana">Teretana</option>
                                <option value="bazen">Bazen</option>
                                <option value="sportski_centar">Sportski centar</option>
                                <option value="plesni_studio">Plesni studio</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                    <td>Status sportskog objekta</td>
                    <td><select model="sportObject.status">
                            <option value="radi">Radi</option>
                            <option value="ne_radi">Ne radi</option>
                        </select>
                    </td>
                    </tr>
                    <tr><td>Geografska sirina</td><td><input type="number" v-model="sportObject.location.longitude"></td></tr>
                    <tr><td>Geografska duzina</td><td><input type="number" v-model="sportObject.location.latitude"></td></tr>
                    <tr><td>Adresa</td><td><input type="text" v-model="sportObject.location.adress"></td></tr>
                    <tr><td><input type="submit" value="Azuriraj podatke"></td></tr>
                </table>
            </form>
        </div>

        <div v-if="mode === 'ADDINGWORKOUT'">
        	<table>
        		<tr>
        			<td><button @click="changeModeFromAddingWorkout()">Prikaz treninga</button></td>
        		</tr>
        	</table>
        	
        	<form @submit="addWorkout">
        		<table>
        			<tr><td>Ime treninga</td><td><input type="text" v-model="newWorkout.name"></td></tr>
        			<tr><td>Duzina treninga(min)</td><td><input type="number" v-model="newWorkout.length"></td></tr>
        			<tr><td>Opis treninga</td><td><input type="text" v-model="newWorkout.description"></td></tr>
        			<tr>
        			<td>Status sportskog objekta</td>
        				<td>
        					<select v-model="newWorkout.type">
        						<option value="grupni">Grupni</option>
        						<option value="personalni">Personalni</option>
        						<option value="teretana">Teretana</option>
        					</select>
        				</td>
        			</tr>
        			<tr><td><input type="submit" value="Kreiraj trening"></td></tr>
        		</table>
        	</form>
        </div>
        
        
        <div v-if="mode === 'UPDATEWORKOUT'">
        	<table>
        		<tr>
        			<td><button @click="changeModeFromAddItem()">Prikaz treninga</button></td>
        		</tr>
        	</table>
        	
        	<form @submit="updateWorkout">
        		<table>
        			<tr><td>Ime treninga</td><input type="text" v-model="workoutForChange.name"></td></tr>
        			<tr><td>Duzina treninga(min)</td><input type="text" v-model="workoutForChange.length"></td></tr>
        			<tr><td>Opis treninga</td><input type="text" v-model="workoutForChange.description"></td></tr>
        			<tr>
        			<td>Status sportskog objekta</td>
        				<td>
        					<select v-model="workoutForChange.type">
        						<option value="grupni">Grupni</option>
        						<option value="personalni">Personalni</option>
        						<option value="teretana">Teretana</option>
        					</select>
        				</td>
        			</tr>
        			<tr><td><input type="submit" value="Izmeni trening"></td></tr>
        		</table>
        	</form>
        </div>
    </div>
    `
    ,
    mounted() {
		this.sportObject = app.selectedSportObject;
		this.loggedUser = app.loggedUser;
},
	methods: {
		getSelectedSportObject : function() {
			axios
			.get('rest/sportObjects' + app.selectedSportObject.id)
			.then(res => {
				app.setSelectedSportObject(res.data)
			})
		},
		changeModeFromSportObjectUpdate : function() {
			if(this.mode === "SEARCH") {
				this.mode = "UPDATESPORTOBJECT";
			} else {
				this.mode = "SEARCH";
			}
		},
		changeModeFromWorkoutUpdate : function() {
			if(this.mode === "SEARCH") {
				this.mode = "UPDATEWORKOUT";
			} else {
				this.mode = "SEARCH";
			}
		},
		changeModeFromAddWorkout : function() {
			if(this.mode === "SEARCH") {
				this.mode = "ADDINGWORKOUT";
			} else {
				this.getSelectedSportObject();
				this.mode = "SEARCH";
			}
		},
		updateSportObject : function(event) {
			event.preventDefault();
			
			var sportObjectForChange = JSON.stringify(this.sportObject);
			axios
			.put('rest/sportObjects', sportObjectForChange)
			.then(response => {
				this.sportObject = response.data;
				alert('Uspesno azurirani podaci');
			})
			.catch(function(error) {
				alert('Neuspesno azuriranje podataka');
			})
		},
		changeWorkout : function(workout){
			this.workoutForChange =
			{
				"id": workout.id,
				"deleted": workout.deleted,
				"name": workout.name,
				"type": workout.type,
				"sportObject": workout.sportObject,
				"length": workout.length,
				"trainer": workout.trainer,
				"description": workout.description
			}
			this.changeModeFromWorkoutUpdate();
		},
		addWorkout : function(event){
			event.preventDefault();
			
			this.newWorkout.sportObject = this.sportObject;
			
			var addedWorkout = JSON.stringify(this.newWorkout);
			
			axios.post('rest/workouts', addedWorkout)
			.then(res => {
				this.sportObject = res.data;
				
				this.newWorkout.name = "";
				this.newWorkout.length = 0;
				this.newWorkout.type = "";
				this.newWorkout.description = "";
				
				alert("Trening uspesno kreiran");
			})
			.catch(err => {
				alert("Greska prilikom kreiranja treninga");
			})
		},
		updateWorkout : function(workout) {
			event.preventDefault();
			
			var workout = JSON.stringify(this.workoutForChange);
			
			axios.put('rest/workouts', workout)
			.then(res => {
				this.sportObject = res.data;
				alert("Trening je uspesno izmenjen");
			})
			.catch(err => {
				alert("Doslo je do greske prilikom izmene treninga");
			})
		},
		deleteWorkout : function(workout) {
			axios.delete('rest/workouts/' + workut.id)
			.then(res => {
				this.sportObject = res.data;
			})
			.catch(err => {
				
			})
		}
		
	}
});