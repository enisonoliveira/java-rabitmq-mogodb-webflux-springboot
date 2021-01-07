package com.sicredi;

import com.sicredi.model.Pauta;
import com.sicredi.model.Session;
import com.sicredi.model.SessionVoting;
import com.sicredi.model.User;
import com.sicredi.request.PautaRequest;
import com.sicredi.request.SessionRequest;
import com.sicredi.request.SessionVotinRequest;
import com.sicredi.request.UserRequest;
import com.sicredi.service.PautaService;
import com.sicredi.service.SessionService;
import com.sicredi.service.SessionVotingService;
import com.sicredi.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest( includeFilters = {@ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = {SessionService.class, PautaService.class,UserService.class, SessionVotingService.class})})

//@RunWith( JUnit4.class)
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

	private int random;

	private  String namePauta;

	public ApplicationTest ( ) {
	}


	@Test
	void contextLoads() {
	}


	/*
	*
	* Metodo init
	* Iniciliza os objetos no banco de dados
	*
	* */
	@BeforeEach
	public void oneSetup() throws Exception {

		logger.info("startup");

		random=(int)  Instant.now().toEpochMilli() +(int)Instant.now().getEpochSecond ();;
		namePauta="startup"+random;

		PautaRequest pautaRequest = new PautaRequest ( null,namePauta,0,0 );
		Pauta pauta = pautaService.save ( pautaRequest );

		UserRequest userRequest = new UserRequest ( null, ""+random , true);
		User user=userService.save ( userRequest );

		SessionRequest sessionRequest = new SessionRequest (  null, new Date (  ), new Date (  ), pauta ,false,false);
		Session session=sessionService.save ( sessionRequest );

		logger.info ( "id pauta session"+session.getPauta ().getId () );

	}


	@Test
	void testRepoPautaFindName() {

		Optional < Pauta > pautaOptional = pautaService.findName ( namePauta);

		assertEquals ( namePauta, pautaOptional.get ().getName () );

		assertEquals ( true, pautaService.validateName ( namePauta ) );
	}

	@Test
	void testRepoUser() throws Exception {

		Optional< User> userOptional=userService.findByCPF (""+random  );

		assertEquals ( ""+random,userOptional.get ().getCPF () );

		assertEquals ( userService.noExistCPF ( "" ),false );

		 User user=userService.saveSearchUser ( "4"+random );

		assertEquals ( user.getCPF (  ),"4"+random );

	}


	@Test
	void testRepoSession() throws Exception {

		PautaRequest pautaRequest = new PautaRequest ( null,"repo"+namePauta,0,0 );
		Pauta pauta = pautaService.save ( pautaRequest );

	   SessionRequest sessionRequest = new SessionRequest (  null, new Date (  ), new Date (  ), pauta ,false,false);
	   Session session=sessionService.save ( sessionRequest );

		Optional < Pauta > pautaOptional = pautaService.findName ("repo"+namePauta);

		Optional < Session > sessionOptional = sessionService.findPautaId ( pautaOptional.get ().getId () );

		assertEquals ( pautaOptional.get ().getId (),  sessionOptional.get ().getPauta ().getId () );

		assertEquals ( session.getPauta ().getName (), pautaOptional.get ().getName () );



	}

	@Test
	void  testRepoSessionStart() throws Exception {

		PautaRequest pautaRequest = new PautaRequest ( null,"repo"+namePauta,0,0 );
		Pauta pauta = pautaService.save ( pautaRequest );

		SessionRequest sessionRequest = new SessionRequest (  null, new Date (  ), new Date (  ), pauta ,false,false);
		Session session=sessionService.save ( sessionRequest );

		assertEquals (true,sessionService.compareIntervalDate(session.getId ()));

		assertEquals ( sessionService.sessionHasInitialized ( session.getId () ),false);

		pauta=	sessionService.startSession ( session );

		assertEquals ( sessionService.finishSession ( session ).getFinish_session (),true);

	}

	@Test
	void testRepoVote() throws Exception {

		UserRequest userRequest = new UserRequest ( null, "1"+random , true);
		User user=userService.save ( userRequest );
		assertEquals ( "1"+random,user.getCPF () );

		PautaRequest pautaRequest = new PautaRequest ( null,"pauta 12412"+random,0,0 );
		Pauta pauta = pautaService.save ( pautaRequest );
		assertEquals ( "pauta 12412"+random, pauta.getName () );

		SessionRequest sessionRequest = new SessionRequest (  null, new Date (  ), new Date (  ), pauta , false,false);
		Session session=sessionService.save ( sessionRequest );

		SessionVotinRequest sessionVotingRequest = new SessionVotinRequest ( null, user ,session ,true );
		sessionVotingService .save ( sessionVotingRequest );

		userRequest = new UserRequest ( null, "2"+random , true);
		user=userService.save ( userRequest );

		sessionVotingRequest = new SessionVotinRequest ( null, user ,session ,false );
		sessionVotingService .save ( sessionVotingRequest );

		pauta =sessionService.startSession ( session );

		assertEquals ( pauta.getTotalVoteFavorable (),1);

		assertEquals ( pauta.getTotalVoteNotFavorable (),1);

		assertEquals ( pauta.getId (), session.getPauta ().getId ());

		assertEquals ( sessionVotingService.existsVotinSession ( user.getId () , session.getId () ) ,true );

		assertEquals ( sessionVotingService.getSessionVotin (  session.getId () ).size () ,2 );

		assertEquals ( sessionVotingService.validateTimeSession ( session.getId () ) ,true );

	}

}

