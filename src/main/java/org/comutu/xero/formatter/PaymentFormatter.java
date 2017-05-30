package org.comutu.xero.formatter;

import com.xero.model.Payment;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Created by johara on 27/05/17.
 */
public class PaymentFormatter implements Formater<Payment> {

   @Override
   public String format(Payment payment) {
      StringBuilder stringBuilder = new StringBuilder();
      SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

      stringBuilder.append( Objects.toString( format1.format(payment.getDate().getTime()), "") );
      stringBuilder.append( " | " );
      stringBuilder.append( Objects.toString( payment.getReference(), "" ) );
      stringBuilder.append( " | " );
      stringBuilder.append( Objects.toString( payment.getAmount(), "") );
      stringBuilder.append( " | " );
      stringBuilder.append( Objects.toString( payment.getInvoice().getType().value(), "") );
      stringBuilder.append( " | " );
      stringBuilder.append( Objects.toString( payment.getInvoice().getAmountCredited(), "") );
      stringBuilder.append( " | " );
      stringBuilder.append( Objects.toString( payment.getInvoice().getAmountPaid(), "") );

      return stringBuilder.toString();
   }
}
