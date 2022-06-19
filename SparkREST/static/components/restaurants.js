Vue.component("restaurants", {
	data: function () {
		    return {
		      polje: ". Primer polja"
		    }
	},
	template: ` 
<div>
	<p>Ovde će biti prikaz sportskih objekata {{polje}}.</p>
</div>		  
`
	, 
	mounted () {
		
    }
});