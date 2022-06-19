Vue.component("sportObjects", {
	data: function () {
		    return {
				allSportObjects : {},
				sportObjectsToShow: {},
				name : '',
				type : '',
				adress : '',
				avgGrade : ''
		    }
	},
	template: `
	<div>
	<div style="margin: 10px;">
	<div>
		<form @submit='search'>
			<table bgcolor="lightblue">
				<tr>
					<td><b>Pretraga: </b></td>
					<td><input type="text" placeholder="Naziv" v-model="name"></td>
					<td>
						<select v-model="type">
							<option value="">Tip</option>
							<option value="teretana">Teretana</option>
							<option value="bazen">Bazen</option>
							<option value="sportski_centar">Sportski centar</option>
							<option value="plesni_studio">Plesni studio</option>
						</select>
					</td>
					<td><input type="text" placeholder="Lokacija(grad)" v-model="adress"></td>
					<td>
						<select v-model="avgGrade">
							<option value="">Prosečna ocena</option>
							<option value="1.00">0-1</option>
							<option value="2.00">1-2</option>
							<option value="3.00">2-3</option>
							<option value="4.00">3-4</option>
							<option value="5.00">4-5</option>
						</select>
					</td>
					<td><input type="submit" value="Pretrazi"></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<table>
			<tr v-for="so in sportObjectsToShow">
				<td style="width: 2500px">
					<div style="border-style: dotted; max-width: 50%; margin: 10px;">
						<table>
							<tr>
								<td style="width: 200px;">
									<p>Ovde ce se nalaziti slika</p>
								</td>
									<td>
									<table>
										<tr>
											<td>Ime sportskog objekta: </td>
											<td>{{so.name}}</td>
										</tr>
										<tr>
											<td>Adresa: </td>
											<td>{{so.location.adress}}</td>
										</tr>
										<tr>
											<td>Prosecna ocena: </td>
											<td>{{so.avgGrade}}</td>
										</tr>
										<tr>
											<td>Tip: </td>
											<td>{{so.type}}</td>
										</tr>
										<tr>
											<td>Status: </td>
											<td>{{so.status}}</td>
										</tr>
									</table>
									</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</div>
`
	, 
	mounted () {
		this.getAllSportObjects();
    },
    
    methods:{
		getAllSportObjects : function() {
			axios
			.get('rest/sportObjects')
			.then(res => {
				this.allSportObjects = res.data;
				this.sportObjectsToShow = res.data;
			})
		},
		search : function (event) {
			alert("dugme je kliknuto");
			event.preventDefault();
			let filteredSportObjects = [];
			for(sportObject of this.allSportObjects) {
				if (sportObject.name.toLowerCase().includes(this.name.toLowerCase()) &&
					(sportObject.type == this.type || this.type == '') &&
					sportObject.location.adress.city.toLowerCase().includes(this.adress.toLowerCase()) &&
					(((this.avgGrade - 1) <= sportObject.avgGrade && this.avgGrade >= sportObject.avgGrade) || this.avgGrade == '')){
						filteredSportObjects.push(sportObject);
					}
			}
			this.sportObjectsToShow = filteredSportObjects;
		}
	}
    
});