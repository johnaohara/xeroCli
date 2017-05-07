package org.comutu.xero.api;

import com.xero.model.Account;
import com.xero.model.Report;

import java.io.IOException;

/**
 * Created by johara on 07/05/17.
 */
public class BankAccountApi extends AccountsApi {

   public static Report getBankStatement(Account account) throws IOException, CliException {

      return getBankStatement( account.getAccountID() );

   }

   public static Report getBankStatement(String accountId) throws IOException, CliException {

      
      return client.getReportBankStatement( client.getAccount( accountId ).getAccountID(), null, null, null, null );

   }


}
