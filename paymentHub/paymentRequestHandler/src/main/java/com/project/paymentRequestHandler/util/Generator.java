package com.project.paymentRequestHandler.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Generator {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	@GenericGenerator(name = "idGenerator",
	    strategy = "com.project.paymentRequestHandler.util.IdGenerator",
	    parameters = {
	        @Parameter(name = "sequence", value = "generator_generator_id_sequence")
	    })
	private Long generatedId;
	
	

	public Long getGeneratedId() {
		return generatedId;
	}

	public void setGeneratedId(Long generatedId) {
		this.generatedId = generatedId;
	}

	public Generator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Generator(Long generatedId) {
		super();
		this.generatedId = generatedId;
	}
	
	
	
	
	
}


