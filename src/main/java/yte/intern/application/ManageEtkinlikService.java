package yte.intern.application;

import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service

public class ManageEtkinlikService {
  
	private final EtkinlikRepository etkinlikRepository ;

	public EtkinlikRepository getEtkinlikRepository() {
		return etkinlikRepository;
	}
  

	public List<Etkinlik> listAllEtkinliks() {
		return etkinlikRepository.findAll();
	}

	public Etkinlik getEtkinlikByName(String name) {
		return etkinlikRepository.findByName(name);
	}

	
  
	public ManageEtkinlikService(EtkinlikRepository etkinlikRepository) {
	
		this.etkinlikRepository = etkinlikRepository;
	}


	public Etkinlik addEtkinlik(Etkinlik etkinlik) {
	
		return etkinlikRepository.save(etkinlik);
	}

	/*
		Derste update ile ilgili bir problem olmuştu. Bunun sebebi, version kullandığımız için ID'sini setlediğimiz
		controller'dan gelen entity'i yeni bir entity olarak algılıyordu. Onu düzelttikten sonra da book'lar olmadığı için
		hata hattı. Bu sebeple bir entity'i güncellemenin en iyi yolunun doğrudan veri tabanından getirdiğimiz entity'nin
		field'larını controller'dan gelen entity ile güncellemek olduğunu fark ettim. Orjinal çözüm kadar temiz değil, ama
		çalışıyor. Öbür türlü tüm relation'ları tek tek zaten map'lememiz gerekiyordu. Onun yerine doğrudan veri tabanından
		gelen entity'i güncellemenin daha mantıklı olduğuna karar verdim.
	 */
	@Transactional
	public Etkinlik updateEtkinlik(String name,Etkinlik etkinlik) {
		Etkinlik etkinlikOptional = etkinlikRepository.findByName(name);
	
			updateEtkinlikFromDB(etkinlik, etkinlikOptional);
			return etkinlikRepository.save(etkinlik);
		

	}

	private void updateEtkinlikFromDB(Etkinlik etkinlik, Etkinlik etkinlikFromDB) {
	
		etkinlikFromDB.setName(etkinlik.getName());
		etkinlikFromDB.setStart(etkinlik.getStart());
		etkinlikFromDB.setEnd(etkinlik.getEnd());
		etkinlikFromDB.setId(etkinlik.getId());
	}

	public void deleteEtkinlik(String name,String start,String end) {
		etkinlikRepository.deleteByNameAndStartAndEnd(name,start,end);
	}

//
//	public Optional<Etkinlik> getEtkinlikById(Long id) {
//		// TODO Auto-generated method stub
//		return etkinlikRepository.findById(id);
//	}

	/**
	 * Burada bussiness rule'larımızı işletiyoruz. Eğer öğrencinin 5 kitabı varsa veya eklenmeye çalışılan kitap
	 * zaten öğrencinin elinde varsa bir exception fırlatıyoruz(genellikle exception fırlatmak yerine özel bir error nesnesi
	 * dönmek daha mantıklı, fakat aşırı karmaşık olacağı için şimdilik sadece exception fırlatmayı tercih ettim. Fakat
	 * bazıları bussiness rule'ların oluşan hatalar için exception fırmatlamanın doğru olmadığını söylüyorlar, detaylı bir konu)
	 * Burda service layer'ında bussiness rule'ların kontrolünü yapmak yerine, bu kontrolleri doğrudan entity'nin üzerine attım.
	 * Bu da biraz daha advanced seviyede büyük uygulamalarda görebileceğiniz bir pratik. Mümkün olduğunca bussiness rule execution'larını
	 * entity'lerin üzerine atmanın iyi olduğunu söylüyorlar. O yüzden burda if(student.getBooks().size == 5) gibi bir kod yerine doğrudan
	 * student'a 5 kitabı olup olmadığını soruyorum.
	 */
//	public Book addBookToStudent(String studentNumber, Book book) {
//		Optional<Student> studentOptional = studentRepository.findByStudentNumber(studentNumber);
//		if (studentOptional.isPresent()) {
//			Student student = studentOptional.get();
//			Set<Book> books = student.getBooks();
//
//			if (student.hasFiveBooks()) {
//				throw new IllegalStateException();
//			} else if (student.hasBook(book)) {
//				throw new IllegalStateException();
//			}
//
//			books.add(book);
//			Student savedStudent = studentRepository.save(student);
//			return savedStudent
//					.getBooks()
//					.stream()
//					.filter(it -> it.getTitle().equals(book.getTitle()))
//					.collect(toList())
//					.get(0);
//		} else {
//			throw new EntityNotFoundException();
//		}
//	}

	/*
		Burada tuhaf şeyler oluyor arkadaşlar. Hibernate, bir sete, doğrudan assigning yapmayı sevmiyor. Orjinal olarak
		burda student.setBooks(filteredBooks) yapıyordum, fakat set referansı değiştiği anda hibernate hata atıyor. Çözüm
		olarak seti temizleyip, filtre edilmiş seti student'ımızın book'larına ekleyip kaydediyoruz. Bu sayede hibernate hata
		atmıyor, bizde istediğimiz elemanı setten çıkarmış oluyoruz.
//	 */
//	public void deleteBook(String studentNumber, String bookTitle) {
//		Optional<Student> studentOptional = studentRepository.findByStudentNumber(studentNumber);
//		if(studentOptional.isPresent()) {
//			Student student = studentOptional.get();
//			removeBookFromStudent(bookTitle, student);
//			studentRepository.save(student);
//		}
//	}

//	private void removeBookFromStudent(String bookTitle, Student student) {
//		Set<Book> filteredBooks = student.getBooks()
//				.stream()
//				.filter(it -> !it.getTitle().equals(bookTitle))
//				.collect(toSet());
//
//		student.getBooks().clear();
//		student.getBooks().addAll(filteredBooks);
//	}
}
