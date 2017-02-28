# draughts
[![Build Status](https://travis-ci.org/tdouillard/draughts.svg?branch=master)](https://travis-ci.org/tdouillard/draughts)

This is a simple JEE draughts game using:

 * Junit
 * AssertJ
 * Servlet 
 * CDI
 * JPA
 * JAX-RS
 * Maven
 

## How to launch

	$ mvn clean install
	$ cd  draught-web
	$ mvn glassfish:run

It launches a Glassfish server on port 9090, so your application can be browsed at : 

	http://localhost:9090/draughts-web/
