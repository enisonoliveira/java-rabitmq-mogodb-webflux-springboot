package com.sicredi;

import com.sicredi.model.Pauta;
import com.sicredi.model.Session;
import com.sicredi.model.User;
import com.sicredi.service.PautaService;
import com.sicredi.service.SessionService;
import com.sicredi.service.SessionVotingService;
import com.sicredi.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest( includeFilters = {@ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = {SessionService.class, PautaService.class,UserService.class, SessionVotingService.class})})
public class ApplicationTest {

	Logger logger = LoggerFactory.getLogger( ApplicationTest.class);

	@Autowired
	private PautaService pautaService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private SessionVotingService sessionVotingService;

	@Autowired
	private UserService userService;

	private int random=(int) new Date().getTime();

	public ApplicationTest ( ) {
	}

	@Before
	public void init() {
		 random=(int) new Date().getTime();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testRepoPauta() {
		Pauta pauta = new Pauta ( null,"md5mfkdourtumsfarpskam2rnnit"+random,0,0 );
		pauta = pautaService.save ( pauta );
		assertEquals ( "md5mfkdourtumsfarpskam2rnnit"+random, pauta.getName () );
	}

	@Test
	void testRepoUser() throws Exception {
		User user = new User ( null, ""+random , true);
		user=userService.save ( user );
		assertEquals ( ""+random,user.getCPF () );
	}

	@Test
	void testRepoSession() throws ParseException, IllegalAccessException {

		Pauta pauta = new Pauta ( null,"pauta de numero 2050"+random,0,0 );
		pauta = pautaService.save ( pauta );
		assertEquals ( "pauta de numero 2050"+random, pauta.getName () );

		Session session = new Session (  null, new Date (  ), new Date (  ), pauta ,false);
		session=sessionService.save ( session );
		assertEquals ( pauta.getId (), session.getPauta ().getId ());
	}

	@Test
	void testRepoVote() throws Exception {

		User user = new User ( null, "1"+random , true);
		user=userService.save ( user );
		assertEquals ( "1"+random,user.getCPF () );

		Pauta pauta = new Pauta ( null,"pauta 12412"+random,0,0 );
		pauta = pautaService.save ( pauta );
		assertEquals ( "pauta 12412"+random, pauta.getName () );

		Session session = new Session (  null, new Date (  ), new Date (  ), pauta , false);
		session=sessionService.save ( session );

		pauta =sessionService.startSession ( session );

		assertEquals ( pauta.getId (), session.getPauta ().getId ());

	}

}

