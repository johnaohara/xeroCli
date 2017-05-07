package org.comutu.xero.api;

import com.xero.api.JsonConfig;
import com.xero.api.XeroClient;
import org.comutu.xero.XeroCli;

/**
 * Abstract xero api class
 * All xero api call should inherit from this class
 */
public abstract class AbstractXeroApi {

   protected static XeroClient client;

   static {
      client = new XeroClient( new JsonConfig( XeroCli.config ) );
   }
}
