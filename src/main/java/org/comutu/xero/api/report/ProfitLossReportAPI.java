package org.comutu.xero.api.report;

import com.xero.model.Report;
import org.comutu.xero.api.AbstractXeroApi;

import java.io.IOException;

/**
 * Created by johara on 07/05/17.
 */
public class ProfitLossReportAPI extends AbstractXeroApi {

   public static Report getProfitLoss(String fromDate, String toDate) throws IOException {

      return client.getReportProfitLoss( null,null,fromDate,toDate,null,null,null,null, false,false);
   }

}
