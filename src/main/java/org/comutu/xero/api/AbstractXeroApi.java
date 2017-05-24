package org.comutu.xero.api;

import com.xero.api.XeroClient;

/**
 * Abstract xero api class
 * All xero api call should inherit from this class
 */
public abstract class AbstractXeroApi {

   protected static XeroClient client;

   static {
      //TODO: change xero client to accept file input for configuration
//      client = new XeroClient( new JsonConfig( XeroCli.config ) );
      client = new XeroClient( );
   }
}
