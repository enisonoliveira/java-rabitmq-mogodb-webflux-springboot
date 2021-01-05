package com.sicredi;

import com.sicredi.model.Ruling;
import com.sicredi.model.Session;
import com.sicredi.model.User;
import com.sicredi.service.RulingService;
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
		classes = {SessionService.class, RulingService.class,UserService.class, SessionVotingService.class})})
public class ApplicationTest {

	Logger logger = LoggerFactory.getLogger( ApplicationTest.class);

	@Autowired
	private RulingService rulingService;

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
		Ruling ruling = new Ruling ( null,"md5mfkdourtumsfarpskam2rnnit"+random,0,0 );
		ruling = rulingService.save ( ruling );
		assertEquals ( "md5mfkdourtumsfarpskam2rnnit"+random, ruling.getName () );
	}

	@Test
	void testRepoUser() {
		User user = new User ( null, ""+random , true);
		user=userService.save ( user );
		assertEquals ( ""+random,user.getCPF () );
	}

	@Test
	void testRepoSession() throws ParseException, IllegalAccessException {

		Ruling ruling = new Ruling ( null,"pauta de numero 2050"+random,0,0 );
		ruling = rulingService.save ( ruling );
		assertEquals ( "pauta de numero 2050"+random, ruling.getName () );

		Session session = new Session (  null, new Date (  ), new Date (  ), ruling ,false);
		session=sessionService.save ( session );
		assertEquals ( ruling.get_id (), session.getPauta ().get_id ());
	}

	@Test
	void testRepoVote() throws ParseException, IllegalAccessException {

		User user = new User ( null, "1"+random , true);
		user=userService.save ( user );
		assertEquals ( "1"+random,user.getCPF () );

		Ruling ruling = new Ruling ( null,"pauta 12412"+random,0,0 );
		ruling = rulingService.save ( ruling );
		assertEquals ( "pauta 12412"+random, ruling.getName () );

		Session session = new Session (  null, new Date (  ), new Date (  ), ruling , false);
		session=sessionService.save ( session );

		ruling =sessionService.startSession ( session );

		assertEquals ( ruling.get_id (), session.getPauta ().get_id ());

	}

}

