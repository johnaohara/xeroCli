package org.comutu.xero.api;

/**
 * Created by johara on 07/05/17.
 */
public enum AccountType {
   BANK ("Bank account"),
   CURRENT	("Current Asset account");

   private String type;
   AccountType(String s) {
      this.type = s;
   }

   public String getType() {
      return type;
   }
}

//   CURRLIAB	Current Liability account	DEPRECIATN	Depreciation account	DIRECTCOSTS	Direct Costs account	EQUITY	Equity account	EXPENSE	Expense account	FIXED	Fixed Asset account	INVENTORY	Inventory Asset account	LIABILITY	Liability account	NONCURRENT	Non-current Asset account	OTHERINCOME	Other Income account	OVERHEADS	Overhead account	PREPAYMENT	Prepayment account	REVENUE	Revenue account	SALES	Sale account	TERMLIAB	Non-current Liability account	PAYGLIABILITY	PAYG Liability account	SUPERANNUATIONEXPENSE	Superannuation Expense account	SUPERANNUATIONLIABILITY	Superannuation Liability account	WAGESEXPENSE	Wages Expense account	WAGESPAYABLELIABILITY	Wages Payable Liability account
