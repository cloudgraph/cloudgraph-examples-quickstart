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
package examples.quickstart.easy.widerows;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.plasma.sdo.PlasmaDataGraph;

import commonj.sdo.DataGraph;
import examples.quickstart.model.Card;
import examples.quickstart.model.Transaction;
import examples.quickstart.ExampleRunner;

public class ExampleTest {
  private static final Log log = LogFactory.getLog(ExampleTest.class);

  @Test
  public void testExcmple() throws IOException {
    DataGraph[] results = ExampleRunner.runExample();
    for (DataGraph graph : results) {
      log.info(((PlasmaDataGraph) graph).asXml());
      Card card = (Card) graph.getRootObject();
      for (Transaction trans : card.getTransaction()) {
        assertTrue(trans.getDollars() >= ExampleRunner.MIN_DOLLARS);
        assertTrue(trans.getDollars() <= ExampleRunner.MAX_DOLLARS);
      }
    }
  }
}
