package com.project.cardPaymentHandler.util;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@SuppressWarnings("deprecation")
//public class IdGenerator extends SequenceGenerator
//{
//    Random r = new Random();
//    private Logger log = LoggerFactory.getLogger(IdGenerator.class);
//    Session session;
//
//    int attempt = 0;
//
//    public int generate9DigitNumber()
//    {
//        int aNumber = (int) ((Math.random() * 900000000) + 100000000); 
//        return aNumber;
//    }
//
//    @Override
//    public Serializable generate(SessionImplementor sessionImplementor, Object obj)
//    {
//        session = (Session) sessionImplementor;
//        Integer id = generateRandomIndex();
//        return id;
//    }
//
//    public Integer generateRandomIndex()
//    {
//            for (int i = 0; i < 3; i++)
//			    {
//			        log.info("attempt: " + i);
//			        Integer a = generate9DigitNumber();
//			
//			        log.info("index: " + String.valueOf(a));
//			        if (session.get(DoiGenerator.class, a) == null)
//			        {
//			            log.info("not found this id");
//			            return a;
//			        } else
//			        {
//			            log.info("found this id");
//			        }
//			    }
//	
//		    for (int i = 100000000; i < 999999999; i++)
//			    {
//			        log.info("Is id free: " + i);
//			        if (session.get(DoiGenerator.class, i) == null)
//			        {
//			            log.info("id is free: " + i);
//			            return i;
//			        }
//			    }
//        return null;
//    }
//
//}

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
			        if ( session.get(Generator.class, a) == null)
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
			        if ( session.get(Generator.class, i) == null)
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
