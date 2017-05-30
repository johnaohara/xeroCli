package org.comutu.xero.api;

import com.xero.model.Account;
import com.xero.model.Payment;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by johara on 07/05/17.
 */
public class AccountsApi extends AbstractXeroApi {

   public static List<Account> getAllAccountsByType() throws IOException {
      List<Account> accounts = null;
      accounts = client.getAccounts();
      return accounts;
   }

   public static List<Account> getAllAccountsByType(String type) throws IOException {
      return client.getAccounts( null, "TYPE==\"" + type + "\"", null );
   }

   public static Account getAccountDetails(String number) throws IOException, CliException {

      //TODO : change to make correct call through client API
      List<Account> accounts = client.getAccounts( null, "Code==\"" + number + "\"", null );

      if ( accounts.size() == 0 )
         throw new CliException( "No accounts found with ID: " + number );

      if ( accounts.size() > 1 )
         throw new CliException( "More than one account found with ID: " + number );

      return accounts.get( 0 );

   }

   public static List<Payment> getPayments() throws IOException {
      return client.getPayments();
   }

   public static List<Payment> getPayments(String accountNumnber, String fromDate, String toDate) throws IOException {

      String accountID = client.getAccount( accountNumnber ).getAccountID();
      String where = "Account.AccountID = " + accountID;

      return client.getPayments(null, where, null);
//      public List<Payment> getPayments(Date modifiedAfter, String where, String order) throws IOException {

      }
}
