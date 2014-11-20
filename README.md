DareBoost API Client
====================

The DareBoost API Client allows to manipulate the DareBoost API with a Java client.

Some samples of use are available in the test folder:

	src/test/java/dareboost/api/client/DareBoostAPIClientTest.java

You can also see the full API documentation :

	https://www.dareboost.com/vassets/DareBoostAPIUserDocumentation.pdf

Configuration
-------------

Configure your token in the config.properties file :

	$ cp config.properties.sample config.properties

You can retrieve this token from your profile : https://www.dareboost.com/profile/api


Installation
------------

You'll need maven to install the library:

    $ mvn clean
	$ mvn install

You have to configure your config.properties to run the tests successfully. So you need a token, that you can retrieve from your profile : https://www.dareboost.com/profile/api

Don't want to use some of your credits? Skip the tests :
    
	$ mvn clean
	$ mvn install -Dmaven.test.skip=true
