/**
 * Copyright 2017 TerraMeta Software, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples.quickstart;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.plasma.query.Expression;
import org.plasma.runtime.*;
import org.plasma.sdo.*;
import org.plasma.sdo.access.client.*;
import org.plasma.sdo.helper.*;

import commonj.sdo.*;
import examples.quickstart.model.Card;
import examples.quickstart.model.Issuer;
import examples.quickstart.model.Transaction;
import examples.quickstart.model.query.QCard;
import examples.quickstart.model.query.QTransaction;

public class ExampleRunner {
  private static final Log log = LogFactory.getLog(ExampleRunner.class);
  public static final int MIN_DOLLARS = 500;
  public static final int MAX_DOLLARS = 600;
  static Random random = new Random();

  public static DataGraph[] runExample() throws IOException {
    SDODataAccessClient client = new SDODataAccessClient(new PojoDataAccessClient(
        DataAccessProviderName.HBASE));

    DataGraph dataGraph = PlasmaDataFactory.INSTANCE.createDataGraph();
    dataGraph.getChangeSummary().beginLogging();
    Type rootType = PlasmaTypeHelper.INSTANCE.getType(Card.class);

    Card card = (Card) dataGraph.createRootObject(rootType);
    card.setNumber(randomCard());
    card.setIssuer(Issuer.VISA.getInstanceName());

    for (int i = 0; i < 100; i++) {
      Transaction trans = card.createTransaction();
      trans.setTransactionDate(new Date());
      trans.setDollars(random.nextInt(1000));
      trans.setCents((short) random.nextInt(99));
    }

    client.commit(dataGraph, ExampleRunner.class.getSimpleName());

    QCard query = QCard.newQuery();
    QTransaction transaction = QTransaction.newQuery();
    Expression slice = transaction.dollars().between(MIN_DOLLARS, MAX_DOLLARS);
    query.select(query.wildcard()).select(query.transaction(slice).wildcard());
    query.where(query.issuer().eq(Issuer.VISA.getInstanceName()));

    DataGraph[] results = client.find(query);
    return results;
  }

  private static String randomCard() {
    StringBuilder buf = new StringBuilder();
    buf.append(String.format("%04d", random.nextInt(9999)));
    buf.append(String.format("%04d", random.nextInt(9999)));
    buf.append(String.format("%04d", random.nextInt(9999)));
    buf.append(String.format("%04d", random.nextInt(9999)));
    return buf.toString();
  }

  public static void main(String[] args) {
    try {
      DataGraph[] results = runExample();
      for (DataGraph graph : results)
        log.info(((PlasmaDataGraph) graph).asXml());
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }
}
