package yte.intern.application;


import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;







@CrossOrigin(origins="http://localhost:3000")
@RestController

public class IndexController {
  
	private final  EtkinlikRepository etkinlikRepository;

    private  final ManageEtkinlikService manageEtkinlikService;
    
	private final EtkinlikMapper etkinlikMapper;
	


public IndexController(EtkinlikRepository etkinlikRepository, ManageEtkinlikService manageEtkinlikService,EtkinlikMapper etkinlikMapper
			) {
		this.etkinlikRepository = etkinlikRepository;
		this.manageEtkinlikService = manageEtkinlikService;
		this.etkinlikMapper=etkinlikMapper;
	
	}
	//
//
//public IndexController() {
//		super();
//	}
//public EtkinlikRepository getEtkinlikRepository() {
//		return etkinlikRepository;
//	}
//	public void setEtkinlikRepository(EtkinlikRepository etkinlikRepository) {
//		this.etkinlikRepository = etkinlikRepository;
//	}
//	public ManageEtkinlikService getManageEtkinlikService() {
//		return manageEtkinlikService;
//	}
//	public void setManageEtkinlikService(ManageEtkinlikService manageEtkinlikService) {
//		this.manageEtkinlikService = manageEtkinlikService;
//	}
//	public EtkinlikMapper getEtkinlikMapper() {
//		return etkinlikMapper;
//	}
//	public void setEtkinlikMapper(EtkinlikMapper etkinlikMapper) {
//		this.etkinlikMapper = etkinlikMapper;
//	}
//public IndexController(EtkinlikRepository etkinlikRepository, ManageEtkinlikService manageEtkinlikService,
//			EtkinlikMapper etkinlikMapper) {
//		super();
//		this.etkinlikRepository = etkinlikRepository;
//		this.manageEtkinlikService = manageEtkinlikService;
//		this.etkinlikMapper = etkinlikMapper;
//	}
	//@DeleteMapping ("/delete/{name}") //bunu alttaki gibi yapmaya çalışınca patlıyor
//	public void index(@RequestBody Etkinlik person) {
//	
//	  etkinlikRepository.delete(person);
//	}
//	 @GetMapping ("/etkinlik/{id}")
//	public Etkinlik console(@PathVariable Long id,@RequestParam String name,@RequestParam String start,@RequestParam String end) {
//	System.out.println("Name:"+ name+" Start:"+start+" end:"+end);
//		return etkinlikRepository.findByName(name);	
//		
//		}
//	@CrossOrigin(origins="http://localhost:3000")
//	@GetMapping("/login")
//	public String index() {
//		
//		return "/users";
//	}
	@CrossOrigin(origins="https://localhost:3000")
	@GetMapping("/users")
	public List<EtkinlikDTO> listAllEtkinliks() {
		
		List<Etkinlik> etkinlik = manageEtkinlikService.listAllEtkinliks();
		return etkinlikMapper.mapToDto(etkinlik);
	}@CrossOrigin(origins="http://localhost:3000")
	 @GetMapping("/update/{name}")
		public EtkinlikDTO getEtkinlikById(@PathVariable  String name ) {
			Etkinlik etkinlik = manageEtkinlikService.getEtkinlikByName(name);
			return etkinlikMapper.mapToDto(etkinlik);
		}
//	 @GetMapping("/student")
//	 @ResponseBody
//	 public Etkinlik getEtkinlik() {
//		 
//		 return new Etkinlik("simge",LocalDateTime.parse(),LocalDateTime.parse());
//		 
//	 }
    @Transactional
	@CrossOrigin(origins="http://localhost:3000")
		@PostMapping("/update/etkinlik")
	public  EtkinlikDTO updateEtkinlik(@Valid @RequestBody EtkinlikDTO etkinlikDTO) {
		Etkinlik etkinlik = etkinlikMapper.mapToEntity(etkinlikDTO);
		Etkinlik addedEtkinlik = manageEtkinlikService.addEtkinlik(etkinlik);
		return etkinlikMapper.mapToDto(addedEtkinlik);
		}

	
//	 @PostMapping ("/addEtkinlik")
//	 @ResponseBody
//	 public Etkinlik addPeople(@RequestBody Etkinlik people) {
//		 etkinlikRepository.save(people);
//
//		 return people;
	//	 }
@CrossOrigin(origins="http://localhost:3000")
	 @PostMapping("/addEtkinlik")
		public  EtkinlikDTO addEtkinlik(@Valid @RequestBody EtkinlikDTO etkinlikDTO) {
			Etkinlik etkinlik = etkinlikMapper.mapToEntity(etkinlikDTO);
			Etkinlik addedEtkinlik = manageEtkinlikService.addEtkinlik(etkinlik);
			return etkinlikMapper.mapToDto(addedEtkinlik);
			
		}
@Transactional
	@CrossOrigin(origins="http://localhost:3000")
		@DeleteMapping("/delete/{name}")
		public void deleteEtkinlik(@PathVariable String name,@RequestParam String start,@RequestParam String end) {
			manageEtkinlikService.deleteEtkinlik(name,start,end);
			//System.out.println(name+start+end);
			
		}
//	 @PostMapping ("/addWebPeople")
//	 @ResponseBody
//	 @CrossOrigin(origins="http://localhost:3000")
//	 public WebPerson[] addWebPerson(@RequestBody WebPerson[] people) {
//		 
//	 for(WebPerson e : people) {
//			 e.yasArtir();
//			 System.out.println(e.getYas());
//		 }
//		 return people;
//	 }
}
