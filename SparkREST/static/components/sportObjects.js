Vue.component("sportObjects", {
	data: function () {
		    return {
				allSportObjects : {},
				sportObjectsToShow : {},
				name : '',
				type : '',
				adress : '',
				avgGrade : '',
				status : ''
		    }
	},
	template: `
	<div style="margin: 10px;">
		<form @submit='search'>
			<table bgcolor="lightblue">
				<tr>
					<th></th>
					<th></th>
					<th>Pretraga</th>
				</tr>
				<tr>
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
					<td>
						<select v-model="status">
							<option value="">Status</option>
							<option value="radi">Radi</option>
							<option value="ne_radi">Ne radi</option>
						</select>
					</td>
					
					<td><input type="submit" value="Pretraži"></td>
				</tr>
			</table>
		</form> 
		<table>
			<tr v-for="so in allSportObjects">
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
				this.getAllSportObjects = res.data;
			})
		},
		search : function () {
		}
	}
    
});