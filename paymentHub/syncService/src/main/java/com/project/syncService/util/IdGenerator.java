package com.project.syncService.util;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.syncService.model.WebshopClient;


public class IdGenerator implements  IdentifierGenerator {

	Random r = new Random();
  private Logger log = LoggerFactory.getLogger(IdGenerator.class);
  Session session;

  int attempt = 0;

  public long generate10DigitNumber()
  {
	  long aNumber = (long) ((Math.random() * 9000000000l) + 1000000000); 
      return aNumber;
  }


  public Long generateRandomIndex()
  {
          for (int i = 0; i < 3; i++)
			    {
			        log.info("attempt: " + i);
			        Long a = generate10DigitNumber();
			
			        log.info("index: " + String.valueOf(a));
			        if ( session.get(WebshopClient.class, a) == null)
			        {
			            log.info("not found this id");
			            return a;
			        } else
			        {
			            log.info("found this id");
			        }
			    }
	
		    for (long i = 1000000000; i < 9999999999l; i++)
			    {
			        log.info("Is id free: " + i);
			        if ( session.get(WebshopClient.class, i) == null)
			        {
			            log.info("id is free: " + i);
			            return i;
			        }
			    }
      return null;
  }


	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		this.session = (Session) session;
	    Long id = generateRandomIndex();
	    return id;

	}    
}
