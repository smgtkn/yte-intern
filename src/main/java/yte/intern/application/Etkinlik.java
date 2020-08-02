package yte.intern.application;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(name="etkinlik")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Etkinlik{

	


	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="start")
	private String start;
	@Column(name="_end")
	private String end;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}


	public String getEnd() {
		return end;
	}


	public void setEnd(String end) {
		this.end = end;
	}


	public Etkinlik() {
		super();
	}

	public Etkinlik(Long id, String name, String start, String end) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
	}


	@Override
	public String toString() {
		return "Etkinlik [id=" + id + ", name=" + name + ", surname=" + start + ", number=" + end 
				+ "]";
	}


}
