package yte.intern.application;
 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;


@Getter
@Builder
public class EtkinlikDTO {

	@Size(max=255,min=1,message="Etkinlik adı 255 karakterden fazla ya da boşluk olamaz!")
	@NotBlank
	public final String name;

    
	public final String start;

	public final String end;
	@AssertTrue
	public boolean isTarihlerValid() {
	//	yil1> yil2||(yil1===yil2 && ay1>ay2 )|| (yil1===yil2 && ay1===ay2 && gun1>gun2) || (name===""||name===" "|| name.length<=1 || name.length>=255
		return ((Integer.parseInt(this.start.substring(6,10))<Integer.parseInt(this.end.substring(6,10)))|| (Integer.parseInt(this.start.substring(6,10))==Integer.parseInt(this.end.substring(6,10))&& Integer.parseInt(this.start.substring(3,5))<(Integer.parseInt(this.end.substring(3,5))))
				
				|| (Integer.parseInt(this.start.substring(6,10))==Integer.parseInt(this.end.substring(6,10))&& Integer.parseInt(this.start.substring(3,5))==(Integer.parseInt(this.end.substring(3,5)))&&Integer.parseInt(this.start.substring(0,2))<=Integer.parseInt(this.end.substring(0,2)))
				
				);
	}
	

	/** StudentDTO ve BookDTO derste yaptığımızdan biraz farklı. Bunu sebebi DTO objelerinde immutability'yi sağlamaya
	 * çalışmamdan kaynaklı. Farkettiyseniz tüm field'lar public ve final, ve sadece @Getter var. Bunun sebebi bir
	 * class eğer immutable olursa, içeriğindeki state değişiminden kaynaklı pek çok hatanın önüne geçmiş oluruz.
	 * Immutability ile ilgili pek çok kaynak var, giriş olarak bu makaleyi okuyabilirsiniz:
	 * <l>https://www.leadingagile.com/2018/03/immutability-in-java/</l>
	 * @JsonProperty ise gelen JSON objesinin, bizim DTO objemize field ile değil, constructor ile initialize etmesini
	 * sağlar. Normalde bildiriğiniz gibi jackson, JSON'u setter methodları ile map'liyor, fakat burda şu anda setter
	 * olmadığı için doğrudan constructor ile yapmamız gerekiyor. Bunun için de aşağıdaki @JsonCreator annotation'ını
	 * consturctor üzerine koyarak jackson'a mapping işlemini constructor ile yapmasını söylüyoruz. JSON'daki hangi field'ın
	 * hangi constructor parametresine karşılık geldiğini de @JsonProperty ile söylüyoruz.
	 * @Builder ise yine lombok tarafında sağlanan bir annotation ve builder pattern'ının kullanımını sağlıyor.
	 * Builder pattern'ı için daha detaylı bilgi için bkn: <l>https://dzone.com/articles/design-patterns-the-builder-pattern</l>
	 * Bu annotation'ını da MapStruct için kullanıyoruz. Normalde MapStruct da bildiğiniz gibi setter'lar kullanıyor, fakat
	 * setter olmayınca builder pattern'ını kullanarak iki yer arasında mapping yapıyor.
	 *
	 */
	@JsonCreator
	public EtkinlikDTO(@JsonProperty("name") String name,
					  @JsonProperty("start") String start,
	                   @JsonProperty("end") String end) {
	
	
		
		this.name = name;
		this.start = start;
		this.end = end;
		
	}


	public String getName() {
		return name;
	}


	public String getStart() {
		return start;
	}


	public String getEnd() {
		return end;
	}
}
