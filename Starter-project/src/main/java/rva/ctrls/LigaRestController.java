package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

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
import rva.jpa.Liga;
import rva.repository.LigaRepository;

@CrossOrigin
@RestController
@Api(tags = {"Liga CRUD operacije"})
public class LigaRestController {
	
	@Autowired
	private LigaRepository ligaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("liga")
	@ApiOperation(value = "Vraća kolekciju svih liga iz baze podataka.")
	public Collection<Liga> getLige() {
		return ligaRepository.findAll();
	}

	@GetMapping("liga/{id}")
	@ApiOperation(value = "Vraća ligu u odnosu na prosleđenu vrednost path varijable id.")
	public Liga getLiga(@PathVariable ("id") Integer id) {
		return ligaRepository.getOne(id);
	}
	
	@GetMapping("ligaNaziv/{naziv}")
	@ApiOperation(value = "Vraća kolekciju liga koje sadrže vrednost prosleđenu u okviru path varijable naziv.")
	public Collection<Liga> getLigaByNaziv(@PathVariable ("naziv") String naziv){
		return ligaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	//insert
	@PostMapping("liga")
	@ApiOperation(value = "Dodaje novu ligu u bazu podataka.")
	public ResponseEntity<Liga> insertLiga(@RequestBody Liga liga){
		if(!ligaRepository.existsById(liga.getId())) {
			ligaRepository.save(liga);
			return new ResponseEntity<Liga>(HttpStatus.OK);
		}
		return new ResponseEntity<Liga>(HttpStatus.CONFLICT);
	}
	//update
	@PutMapping("liga")
	@ApiOperation(value = "Update-uje postojeću ligu.")
	public ResponseEntity<Liga> updateLiga(@RequestBody Liga liga) {
		if(!ligaRepository.existsById(liga.getId())) {
			return new ResponseEntity<Liga>(HttpStatus.NO_CONTENT);
		}
		ligaRepository.save(liga);
		return new ResponseEntity<Liga>(HttpStatus.OK);
	}
	//delete
	
//	@Transactional
	@DeleteMapping("liga/{id}")
	@ApiOperation(value = "Briše ligu u odnosu na vrednost prosleđene path varijable id.")
	public ResponseEntity<Liga> deleteLiga(@PathVariable("id") Integer id){
		if(!ligaRepository.existsById(id))
			return new ResponseEntity<Liga>(HttpStatus.NO_CONTENT);
		
		
		
		ligaRepository.deleteById(id);
		jdbcTemplate.execute("DELETE FROM tim WHERE liga=" + id);
		if(id == -100)
			jdbcTemplate.execute("INSERT INTO \"liga\" (\"id\", \"naziv\", \"oznaka\") "
				+ "VALUES (-100, 'TestLigga', 'TST')");
		return new ResponseEntity<Liga>(HttpStatus.OK);
	}
}
