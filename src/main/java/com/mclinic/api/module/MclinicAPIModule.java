package com.mclinic.api.module;

import com.burkeware.search.api.logger.LogLevel;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.mclinic.api.registry.ServerConfigRegistry;
import com.mclinic.util.Constants;

public class MclinicAPIModule extends AbstractModule {
	
	private String luceneDir;
	private String luceneDocumentKey;
	private String server;
	private String username;
	private String password;

    public MclinicAPIModule(final String luceneDir, final String luceneDocumentKey) {
    	super();
		this.luceneDir=luceneDir;
		this.luceneDocumentKey=luceneDocumentKey;
	}
    
	/**
	 * @param webserver the webserver to set
	 */
	public void setServer(final  String server) {
		this.server = server;
	}

	/**
	 * @param username the user to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final  String password) {
		this.password = password;
	}

	@Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named(Constants.LUCENE_DIR_NAME)).toInstance(luceneDir);
        bind(String.class).annotatedWith(Names.named(Constants.LUCENE_DOCUMENT_KEY)).toInstance(luceneDocumentKey);
        bind(LogLevel.class).toInstance(LogLevel.DEBUG);
        
        // if instance needs a server then register the server username and password
       if (server != null) {
        	ServerConfigRegistry registry = new ServerConfigRegistry();
        	registry.putEntry(Constants.SERVER, server);
        	registry.putEntry(Constants.CONNECTION_USERNAME, username);
        	registry.putEntry(Constants.CONNECTION_PASSWORD, password);
        	bind(ServerConfigRegistry.class).toInstance(registry);
       }
      
    }
}