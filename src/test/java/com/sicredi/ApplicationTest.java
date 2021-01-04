package com.sicredi;

import com.sicredi.model.Pauta;
import com.sicredi.model.Session;
import com.sicredi.model.SessionVoting;
import com.sicredi.model.User;
import com.sicredi.service.PautaService;
import com.sicredi.service.SessionService;
import com.sicredi.service.SessionVotingService;
import com.sicredi.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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

	@Before
	public void init() {
		MockitoAnnotations.openMocks ( this );
	}

	@Test
	void contextLoads() {
	}

//	@Test
	void testRepoPauta() {
		Pauta pauta = new Pauta ( null,"mammdamsmda3",0,0 );
		pauta=pautaService.save ( pauta );
		assertEquals ( "mammdamsmda3",pauta.getName () );
	}

//	@Test
	void testRepoUser() {
		User user = new User ( null, "3806905123" , true);
		user=userService.save ( user );
		assertEquals ( "3806905123",user.getCPF () );
	}

//	@Test
	void testRepoSession() throws ParseException, IllegalAccessException {

		Pauta pauta = new Pauta ( null,"pauta31231",0,0 );
		pauta=pautaService.save ( pauta );
		assertEquals ( "pauta31231",pauta.getName () );

		Session session = new Session (  null, new Date (  ), new Date (  ),pauta  ,false);
		session=sessionService.save ( session );
		assertEquals ( pauta.get_id (), session.getPauta ().get_id ());
	}

//	@Test
	void testRepoVote() throws ParseException, IllegalAccessException {

		User user = new User ( null, "3806905800" , true);
		user=userService.save ( user );
		assertEquals ( "3806905800",user.getCPF () );

		Pauta pauta = new Pauta ( null,"pauta2",0,0 );
		pauta=pautaService.save ( pauta );
		assertEquals ( "pauta2",pauta.getName () );

		Session session = new Session (  null, new Date (  ), new Date (  ),pauta , false);
		session=sessionService.save ( session );
		assertEquals ( pauta.get_id (), session.getPauta ().get_id ());

		SessionVoting sessionVoting = new SessionVoting ( null , user, session , true  );
		sessionVoting = sessionVotingService.save ( sessionVoting );
		assertEquals( sessionVoting.getUser ().get_id (),user.get_id ());
		assertEquals( sessionVoting.getSession ().get_id (),session.get_id ());

	}

	@Test
	void testListVotin() {
		sessionVotingService.all ( "5ff23a944fcd6e1b92958b08","5ff25170f0beb06df37c74be" );
	}
}

