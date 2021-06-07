package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Igrac;
import rva.repository.IgracRepository;

@CrossOrigin
@RestController
@Api(tags = {"Igrač CRUD operacije"})
public class IgracRestController {
	
	@Autowired
	private IgracRepository igracRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("igrac")
	@ApiOperation(value = "Vraća kolekciju svih igrača iz baze podataka.")
	public Collection<Igrac> getIgraci() {
		return igracRepository.findAll();
	}

	@GetMapping("igrac/{id}")
	@ApiOperation(value = "Vraća igrača u odnosu na prosleđenu vrednost path varijable id.")
	public Igrac getIgrac(@PathVariable ("id") Integer id) {
		return igracRepository.getOne(id);
	}
	
	@GetMapping("igracIme/{ime}")
	@ApiOperation(value = "Vraća kolekciju igrača koji sadrže vrednost prosleđenu u okviru path varijable ime.")
	public Collection<Igrac> getIgracByIme(@PathVariable ("ime") String ime){
		return igracRepository.findByImeContainingIgnoreCase(ime);
	}
	//insert
	@PostMapping("igrac")
	@ApiOperation(value = "Dodaje novog igrača u bazu podataka.")
	public ResponseEntity<Igrac> insertIgrac(@RequestBody Igrac igrac){
		if(!igracRepository.existsById(igrac.getId())) {
			igracRepository.save(igrac);
			return new ResponseEntity<Igrac>(HttpStatus.OK);
		}
		return new ResponseEntity<Igrac>(HttpStatus.CONFLICT);
	}
	//update
	@PutMapping("igrac")
	@ApiOperation(value = "Update-uje postojećeg igrača.")
	public ResponseEntity<Igrac> updateIgrac(@RequestBody Igrac igrac) {
		if(!igracRepository.existsById(igrac.getId())) {
			return new ResponseEntity<Igrac>(HttpStatus.NO_CONTENT);
		}
		igracRepository.save(igrac);
		return new ResponseEntity<Igrac>(HttpStatus.OK);
	}
	//delete
	@DeleteMapping("igrac/{id}")
	@ApiOperation(value = "Briše igrača u odnosu na vrednost prosleđene path varijable id.")
	public ResponseEntity<Igrac> deleteIgrac(@PathVariable("id") Integer id){
		if(!igracRepository.existsById(id))
			return new ResponseEntity<Igrac>(HttpStatus.NO_CONTENT);
		igracRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute("INSERT INTO \"igrac\" (\"id\", \"ime\", \"prezime\", \"broj_reg\", \"datum_rodjenja\", \"nacionalnost\", \"tim\") "
					+ "VALUES(-100, 'Test', 'Test', 'TestReg', to_date('24.10.1925.' ,'dd,mm.yyyy.'), 2, 1)");
		return new ResponseEntity<Igrac>(HttpStatus.OK);
	}
	
}


